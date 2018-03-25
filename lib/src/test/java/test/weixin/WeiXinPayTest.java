package test.weixin;

import ljs.weixin.WXPay;
import ljs.weixin.WXPayConfig;
import ljs.weixin.WXPayUtil;
import ljs.weixin.impl.WXPayConfigImpl;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class WeiXinPayTest {
    //appId
    String appId = "wxdff25f635c091808";
    //商户id
    String mchId = "1499745652";
    //api key
    String key = "693ac274c7654a60c422b313530385f6";

    //https证书文件
    String certPath = "D:/CERT/common/apiclient_cert.p12";

    /**
     * 创建预支付订单测试
     */
    @Test
    public void unifiedOrder() throws Exception {
        WXPayConfig wxPayConfig = new WXPayConfigImpl(appId, mchId, key, certPath, false);
        WXPay wxPay = new WXPay(wxPayConfig, false, false);
        Map<String, String> data = new HashMap<>();
        data.put("appid", wxPayConfig.getAppID());
        data.put("mch_id", wxPayConfig.getMchID());
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        data.put("body", "test商品");
        data.put("out_trade_no", "sdasdasdasdas");
        data.put("total_fee", "101");
        data.put("spbill_create_ip", "192.168.1.1");
        data.put("notify_url", "http://www.baidu.com");
        data.put("trade_type", "APP");
        String sign = WXPayUtil.generateSignature(data, wxPayConfig.getKey());
        data.put("sign", sign);
        Map<String, String> responseMap = wxPay.unifiedOrder(data);
        String responseXml = WXPayUtil.mapToXml(responseMap);
        System.out.println(responseXml);
        System.out.println("签名校验通过:" + WXPayUtil.isSignatureValid(responseMap, wxPayConfig.getKey()));
    }

    @Test
    public void getSandBoxSign() throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("mch_id", mchId);
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        data.put("sign", WXPayUtil.generateSignature(data, key));
        System.out.println(WXPayUtil.mapToXml(data));
    }
}
