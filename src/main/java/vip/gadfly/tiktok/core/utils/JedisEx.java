package vip.gadfly.tiktok.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 缓存数据库处理工具
 *
 * @author OF
 */
public class JedisEx {
    private static final Logger log = LoggerFactory.getLogger(JedisEx.class);
    /**
     * lua 脚本
     */
    private static final Map<String, String> luaScript = new HashMap<String, String>();
    // Jedis
    private ShardedJedisPool jedisPool;
    /**
     * lua 脚本路径
     */
    private String scriptPath;

    /**
     * 根据操作类型获取连接池
     *
     * @param oType R:读取缓存数据库 W:操作缓存数据
     * @return
     */
    public ShardedJedis getShardedJedis() {
        return jedisPool.getResource();

    }

    /**
     * 返回关闭数据
     *
     * @param shardedJedis
     */
    @SuppressWarnings("deprecation")
    public void returnBrokenResource(ShardedJedis shardedJedis) {
        try {
            jedisPool.returnBrokenResource(shardedJedis);
        } catch (Exception e) {
            log.error("returnBrokenResource error:{}", e.getMessage());
        }
    }

    /**
     * 返回关闭数据
     *
     * @param shardedJedis
     */
    @SuppressWarnings("deprecation")
    public void returnResource(ShardedJedis shardedJedis) {
        try {
            jedisPool.returnResource(shardedJedis);
        } catch (Exception e) {
            log.error("returnResource error：{}", e.getMessage());
        }
    }

    /**
     * @param script
     * @return SHA 返回空时load失败
     */
    public String scriptload(String script) {
        String sha = null;
        log.debug("SCRIPT LOAD \n{}\n", script);
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            Jedis Jedis = shardedJedis.getShard("");
            sha = Jedis.scriptLoad(script);
        } catch (Exception ex) {
            log.error("scriptload error:{}", ex.getMessage());
            ex.printStackTrace();
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return sha;

    }

    /**
     * @param script
     * @param params
     * @return
     */
    public Object eval(String script, String... params) {
        Object rs = null;
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            Jedis Jedis = shardedJedis.getShard("");
            rs = Jedis.eval(script, params.length, params);
        } catch (Exception ex) {
            log.error("eval error:{}", ex);
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return rs;

    }

    /**
     * @param sha
     * @param params
     * @return
     */
    public Object evalsha(String sha, String... params) {
        Object rs = null;
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {

            Jedis Jedis = shardedJedis.getShard("");
            rs = Jedis.evalsha(sha, params.length, params);
        } catch (Exception ex) {
            log.error("evalsha scriptName error:{} \n sha:[{}] params:[{}]",
                    ex.getMessage(), sha,
                    JsonUtil.objectToJson(params));
            ex.printStackTrace();
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return rs;

    }

    public Object call(String scriptName, String... params) {
        Object rs = null;
        String sha = luaScript.get(scriptName);
        if (StringUtils.isBlank(sha)) {
            log.debug("script未加载:[{}]", scriptName);
            sha = this.loadLuaScript(FileUtil
                    .getFile(scriptName + ".lua", null));
        }
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            Jedis Jedis = shardedJedis.getShard("");
            rs = Jedis.evalsha(sha, params.length, params);
        } catch (Exception ex) {
            log.error("call scriptName error:{} \n [{}] sha:[{}] params:[{}]",
                    ex.getMessage(), scriptName, sha,
                    JsonUtil.objectToJson(params));
            ex.printStackTrace();
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return rs;
    }

    /**
     * 加载脚本文件
     *
     * @param file
     * @return
     */
    private String loadLuaScript(File file) {
        StringBuffer script = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.UTF_8));
            String line = null;
            while ((line = reader.readLine()) != null) {
                script.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("loadFile error:{} [{}]", e.getMessage(), file);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String sha = this.scriptload(script.toString());
        String name = file.getName();
        name = name.substring(0, name.lastIndexOf(".lua"));
        luaScript.put(name, sha);
        log.info("加载luascript文件完成：{}", file.getPath());
        return sha;
    }

    /*
     * @Override public void reload() { this.init(); }
     */
    public void init() {
        if (scriptPath == null) {
            return;
        }
        File path = FileUtil.getFile(scriptPath, null);
        if (!path.exists()) {
            log.warn("文件路径不存在：{}", path);
            return;
        }
        File[] list = path.listFiles();
        for (int index = 0; index < list.length; index++) {
            File file = list[index];
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    if (f.isFile()) {
                        loadLuaScript(f);
                    }
                }
            } else {
                loadLuaScript(file);
            }
        }

    }

    /*
     * @Override public boolean isInited() { return true; }
     */

    public void setJedisPool(ShardedJedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String getScriptPath() {
        return scriptPath;
    }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    /**
     * 存放字符串数据
     *
     * @param key   存储ID
     * @param value 数据
     * @return
     */
    public String set(String key, String value) throws Exception {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.set(key, value);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return value;
    }

    /**
     * 删除缓存数据
     *
     * @param key 存储ID
     * @return
     */
    public Long del(String key) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.del(key);
        } catch (Exception ex) {
            log.error("del error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    /**
     * decr
     *
     * @param key
     * @return
     */
    public Long incr(String key) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.incr(key);
        } catch (Exception ex) {
            log.error("decr error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    /**
     * 删除缓存数据
     *
     * @param key
     * @return
     */
    public String hdel(String key) {
        return this.hdel(key);
    }

    /**
     * 判断key是否存在
     *
     * @param key 存储ID
     * @return true/false
     */
    public Boolean exists(String key) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.exists(key);
        } catch (Exception ex) {
            log.error("exists error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
            return false;
        } finally {
            returnResource(shardedJedis);
        }

    }

    /**
     * 存放字符串数据
     *
     * @param key   存储ID
     * @param value 数据
     * @return
     */
    public String set(String key, String value, int seconds) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            String result = shardedJedis.set(key, value);
            shardedJedis.expire(key, seconds);
            return result;
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return value;
    }

    /**
     * set Object
     */
    public String setOjbect(Object object, String key) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        String result = null;
        try {
            result = shardedJedis.set(key.getBytes(),
                    SerializeUtil.serialize(object));
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * get Object
     */
    public Object getObject(String key) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        byte[] value = null;
        try {
            value = shardedJedis.get(key.getBytes());
            return SerializeUtil.unserialize(value);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    /**
     * 获取缓存数据
     *
     * @param key
     * @return
     */
    public String get(String key) throws Exception {
        return this.get(key, "");
    }

    /**
     * 获取缓存数据
     *
     * @param key
     * @param defaultValue
     * @return
     * @throws Exception
     */
    public String get(String key, String defaultValue) throws Exception {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            String value = shardedJedis.get(key);
            return value == null ? defaultValue : value;
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return defaultValue;
    }

    public String echo(String string) throws Exception {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.echo(string);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return string;
    }

    /**
     * 存放字符串数据
     *
     * @param key     存储ID
     * @param seconds 时间
     * @return
     * @throws Exception
     */
    public Long expire(String key, int seconds) throws Exception {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.expire(key, seconds);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
            throw ex;
        } finally {
            returnResource(shardedJedis);
        }
    }

    public Long setrange(String key, long offset, String value)
            throws Exception {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.setrange(key, offset, value);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return offset;
    }

    public String getrange(String key, long startOffset, long endOffset) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.getrange(key, startOffset, endOffset);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return key;
    }

    public String getSet(String key, String value) throws Exception {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.getSet(key, value);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return value;
    }

    public Long setnx(String key, String value) throws Exception {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.setnx(key, value);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    /**
     * 存放字符串数据(根据我们设定的有效期)
     *
     * @param key   存储ID
     * @param value 数据
     * @return
     */
    public String setex(String key, int second, String value) throws Exception {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.setex(key, second, value);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return value;
    }

    /**
     * 存放kev-value数据
     *
     * @param key   存储ID
     * @param field 字段
     * @param value 数据
     * @return
     */
    public Long hset(String key, String field, String value) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.hset(key, field, value);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
            return -1L;
        } finally {
            returnResource(shardedJedis);
        }
    }

    /**
     * 存放kev-value数据
     *
     * @param key   存储ID
     * @param field 字段
     * @return
     */
    public String hget(String key, String field) throws Exception {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.hget(key, field);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
            return null;
        } finally {
            returnResource(shardedJedis);
        }
    }

    public String hmset(String key, Map<String, String> hash) throws Exception {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.hmset(key, hash);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return key;
    }

    public Boolean hexists(String key, String field) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.hexists(key, field);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
            return false;
        } finally {
            returnResource(shardedJedis);
        }

    }

    public Long hdel(String key, String fields) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.hdel(key, fields);
        } catch (Exception ex) {
            log.error("hdel error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    public Map<String, String> hmgetAll(String key) throws Exception {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.hgetAll(key);
        } catch (Exception ex) {
            log.error("get map error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } finally {
            returnResource(shardedJedis);
        }
    }

    public List<String> blpop(String arg) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.blpop(arg);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            returnResource(shardedJedis);
        }
        return null;
    }

    @Deprecated
    /**
     * This method is deprecated due to bug (scan cursor should be unsigned
     * long) And will be removed on next major release
     *
     * @see https://github.com/xetorthio/jedis/issues/531
     */
    public ScanResult<Entry<String, String>> hscan(String key, int cursor) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.hscan(key, cursor);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return null;

    }

    @Deprecated
    /**
     * This method is deprecated due to bug (scan cursor should be unsigned
     * long) And will be removed on next major release
     *
     * @see https://github.com/xetorthio/jedis/issues/531
     */
    public ScanResult<String> sscan(String key, int cursor) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.sscan(key, cursor);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return null;

    }

    @Deprecated
    /**
     * This method is deprecated due to bug (scan cursor should be unsigned
     * long) And will be removed on next major release
     *
     * @see https://github.com/xetorthio/jedis/issues/531
     */
    public ScanResult<Tuple> zscan(String key, int cursor) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.zscan(key, cursor);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return null;

    }

    public ScanResult<Entry<String, String>> hscan(String key,
                                                   final String cursor) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.hscan(key, cursor);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return null;

    }

    public ScanResult<String> sscan(String key, final String cursor) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.sscan(key, cursor);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return null;

    }

    public ScanResult<Tuple> zscan(String key, final String cursor) {
        ShardedJedis shardedJedis = this.getShardedJedis();
        try {
            return shardedJedis.zscan(key, cursor);
        } catch (Exception ex) {
            log.error("set error：{}", ex.getMessage());
            returnBrokenResource(shardedJedis);

        } finally {
            returnResource(shardedJedis);
        }
        return null;

    }
}
