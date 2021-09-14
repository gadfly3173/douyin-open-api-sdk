package vip.gadfly.tiktok.core.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理数据中参数
 *
 * @author OF
 */
public class MatchUtil {
    /**
     * ${paramname}
     */
    protected static final Pattern patternParam = Pattern
            .compile("[$]\\{([\\w]+)\\}");

    /**
     * <b>根据$ 匹配 $+{+参数名称+} ;</b><br>
     * 根据参数名称获取参数 params中的值<br>
     * <i>此功能可用作模板填充中</i>
     *
     * @param s      元数据
     * @param params 参数Map
     * @return StringBuilder
     */
    public static String matchValue(String s, Map<?, ?> params) {
        for (Matcher matcher = patternParam.matcher(s); matcher.find(); ) {
            int sub = matcher.groupCount();
            String n = matcher.group(sub).trim();
            String val = String.valueOf(params.get(matcher.group(sub).trim()));
            s = s.replace("${" + n + "}", val);
        }
        return s;
    }

    /**
     * 获取匹配好的信息
     *
     * @param pattern 正则表达式
     * @param msg     需要进行匹配的字符串
     * @return
     */
    public static String getMatchValue(String pattern, String msg) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(msg);
        if (matcher != null && matcher.find()) {
            return matcher.group(matcher.groupCount()).trim();
        }
        return "";
    }
}
