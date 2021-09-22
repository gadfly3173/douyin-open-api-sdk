package vip.gadfly.tiktok.core.util;

import org.apache.commons.lang3.StringUtils;
import vip.gadfly.tiktok.core.exception.TikTokException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 *
 * @author OF
 */
public class StringUtil extends StringUtils {

    public static final int LITERAL = 0x10;

    /**
     * 默认编码级别
     */
    public static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

    /**
     * 获取报文头头长度
     *
     * @param msg      报文信息
     * @param len      报文头长度
     * @param encoding 编码集
     * @return
     * @throws TikTokException
     */
    public static String getMsgHead(String msg, int len, String encoding)
            throws TikTokException {
        if (isBlank(encoding)) {
            throw new TikTokException("msg is blank!");
        }
        if (len <= 0) {
            throw new TikTokException("len is must be bigger than 0 !");
        }
        try {
            String head = String.format("%0" + len + "d",
                    msg.getBytes(encoding).length);
            return head + msg;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 字符串中字符替换
     *
     * @param source
     * @param oldStr
     * @param newStr
     * @return
     */
    public static String replace(String source, String oldStr, String newStr) {
        return Pattern.compile(oldStr, Pattern.LITERAL).matcher(source)
                .replaceAll(quoteReplacement(newStr));
    }

    /**
     * 去除字符串前四个字符
     *
     * @param msg
     * @return
     */
    public static String remove4First(String msg) {
        String message = "";
        if (msg == null || msg.equals("")) {
            message = null;
        } else {
            message = msg.substring(4);
        }
        return message;
    }

    /**
     * 判断数组strs中是否有str这个值
     *
     * @param strs
     * @param str
     * @return 有true 没有false
     */
    public static boolean hasInArray(String[] strs, String str) {
        for (String tmp : strs) {
            if (tmp.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 逐个判断字符变量是否为null或""
     *
     * @param names
     * @return 有一个为空true 都不为空 false
     */
    public static boolean isEmpty(String names) {
        return names == null || "".equals(names.trim());
    }

    public static boolean isEmpty(Object name) {
        if (name == null) {
            return true;
        }
        if (name instanceof String) {
            if ("".equals(name.toString().trim())) {
                return true;
            }

        }
        if (name instanceof Double) {
            return (Double) name == 0.0;
        }
        return false;
    }

    /**
     * @param time
     * @return
     */
    public static int getTime(String time) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        String str = sdf.format(date);
        String times = time.substring(0, 2);
        String timef = time.substring(2, 4);
        String nowtimes = str.substring(0, 2);
        String nowtimef = str.substring(2, 4);
        int newtime;
        if (Integer.parseInt(times) > Integer.parseInt(nowtimes)) {
            newtime = (Integer.parseInt(times) - Integer.parseInt(nowtimes)) * 60;
            newtime = newtime + Integer.parseInt(timef)
                              - Integer.parseInt(nowtimef);
        } else if (Integer.parseInt(times) == Integer.parseInt(nowtimes)) {
            if (Integer.parseInt(timef) >= Integer.parseInt(nowtimef)) {
                newtime = Integer.parseInt(timef) - Integer.parseInt(nowtimef);
            } else {
                newtime = 24 * 60 - Integer.parseInt(nowtimef)
                                  + Integer.parseInt(timef);
            }
        } else {
            newtime = (24 - Integer.parseInt(nowtimes) + Integer
                    .parseInt(times)) * 60;
            newtime = newtime + Integer.parseInt(timef)
                              - Integer.parseInt(nowtimef);
        }
        return newtime;
    }

    public static String quoteReplacement(String s) {
        if ((s.indexOf('\\') == -1) && (s.indexOf('$') == -1)) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\\') {
                sb.append('\\');
                sb.append('\\');
            } else if (c == '$') {
                sb.append('\\');
                sb.append('$');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * @param sour
     * @param fillStr
     * @param len
     * @return
     */
    public static String lpad(String sour, String fillStr, int len) {

        StringBuilder blank = new StringBuilder();

        for (int index = sour.length(); index < len; ++index) {
            blank.append(fillStr);
        }
        return blank.append(sour).toString();
    }

    /**
     * 以字节为单位进行截取
     */
    public static String subbyte(String str, int beginIndex, int endIndex,
                                 String encoding) throws UnsupportedEncodingException {

        byte[] bts = str.getBytes(encoding);
        int len = endIndex - beginIndex;

        return new String(bts, beginIndex, len, encoding);
    }

    /**
     * 以字节为单位进行截取
     */
    public static String subbyte(String str, int beginIndex, int endIndex)
            throws UnsupportedEncodingException {

        byte[] bts = str.getBytes(DEFAULT_ENCODING);
        int len = endIndex - beginIndex;

        return new String(bts, beginIndex, len, DEFAULT_ENCODING);
    }

    /**
     * @param str
     * @param beginIndex
     * @param len
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String subbyteByLen(String str, int beginIndex, int len)
            throws UnsupportedEncodingException {

        byte[] bts = str.getBytes(DEFAULT_ENCODING);

        return new String(bts, beginIndex, len, DEFAULT_ENCODING);
    }

    /**
     * HTML字符转义
     *
     * @return String 过滤后的字符串
     * <p>对输入参数中的敏感字符进行过滤替换,防止用户利用JavaScript等方式输入恶意代码</p>
     * <p>{@code String input = <img src='http://t1.baidu.com/it/fm=0&gp=0.jpg'/>}</p>
     * <p>{@code HtmlUtils.htmlEscape(input); //from spring.jar}</p>
     * <p>{@code StringEscapeUtils.escapeHtml(input); //from commons-lang.jar}</p>
     * <p>尽管Spring和Apache都提供了字符转义的方法,但Apache的StringEscapeUtils功能要更强大一些</p>
     * <p>StringEscapeUtils提供了对HTML,Java,JavaScript,SQL,XML等字符的转义和反转义</p>
     * <p>但二者在转义HTML字符时,都不会对单引号和空格进行转义,而本方法则提供了对它们的转义</p>
     */
    public static String htmlEscape(String input) {
        if (isEmpty(input)) {
            return input;
        }
        input = input.replaceAll("&", "&amp;");
        input = input.replaceAll("<", "&lt;");
        input = input.replaceAll(">", "&gt;");
        input = input.replaceAll(" ", "&nbsp;");
        input = input.replaceAll("'", "&#39;"); // IE暂不支持单引号的实体名称,而支持单引号的实体编号,故单引号转义成实体编号,其它字符转义成实体名称
        input = input.replaceAll("\"", "&quot;"); // 双引号也需要转义，所以加一个斜线对其进行转义
        input = input.replaceAll("\n", "<br/>"); // 不能把\n的过滤放在前面，因为还要对<和>过滤，这样就会导致<br/>失效了
        return input;
    }

}
