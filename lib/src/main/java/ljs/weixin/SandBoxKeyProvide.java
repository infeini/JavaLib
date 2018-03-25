package ljs.weixin;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class SandBoxKeyProvide {
    String url = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
    OkHttpClient okHttpClient = new OkHttpClient();

    public String getSandBoxKey(String mch_id, String key) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put(WxFields.mch_id, mch_id);
        requestMap.put(WxFields.nonce_str, WXPayUtil.generateNonceStr());
        String sandBoxKey = "";
        try {
            requestMap.put(WxFields.sign, WXPayUtil.generateSignature(requestMap, key));
            Request request = new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/xml;charset=UTF-8"), WXPayUtil.mapToXml(requestMap))).build();
            Map<String, String> responseMap = WXPayUtil.xmlToMap(okHttpClient.newCall(request).execute().body().string());
            sandBoxKey = responseMap.get(WxFields.sandbox_signkey);
        } catch (Exception e) {
            System.out.println("获取沙箱key失败:" + e.getMessage());
        }
        return sandBoxKey;
    }
}
