package ljs.weixin;

import ljs.lib.Md5Util;
import ljs.lib.StringUtils;

import java.util.*;

public class WeiXinUtils {
    /**
     * 获取微信请求参数签名
     */
    public static String getParamSign(Map<String, String> paramMap, String apiKey) {
        StringBuilder stringBuilder = getParamUrl(paramMap, apiKey);
        return stringBuilder == null ? "" : Md5Util.getMd5(stringBuilder.toString()).toUpperCase();
    }

    /**
     * 获取参数+apiKey排序GET url拼接
     */
    public static StringBuilder getParamUrl(Map<String, String> paramMap, String apiKey) {
        StringBuilder stringBuilder = null;
        if (paramMap != null) {
            List<Map.Entry<String, String>> paramList = new ArrayList<>(paramMap.entrySet());
            Collections.sort(paramList, Comparator.comparing(o -> (o.getKey())));
            stringBuilder = new StringBuilder();
            for (Map.Entry<String, String> item : paramList) {
                String key = item.getKey();
                if (!StringUtils.isEmpty(key)) {
                    String value = item.getValue();
                    stringBuilder.append(key).append("=").append(value).append("&");
                }
            }
            stringBuilder.append("key=").append(apiKey);

        }
        return stringBuilder;
    }
}
