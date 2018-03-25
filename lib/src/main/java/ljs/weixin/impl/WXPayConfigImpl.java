package ljs.weixin.impl;

import ljs.exception.KnowException;
import ljs.io.IOUtil;
import ljs.lib.StringUtils;
import ljs.weixin.IWXPayDomain;
import ljs.weixin.SandBoxKeyProvide;
import ljs.weixin.WXPayConfig;

import java.io.*;

public class WXPayConfigImpl extends WXPayConfig {

    private byte[] certData;
    private String appId;
    private String mchId;
    private String key;
    private boolean sandBox;

    /**
     * @param appId    微信支付App Id
     * @param mchId    商户id
     * @param key      微信Api Key
     * @param certPath 微信https证书文件路径
     */
    public WXPayConfigImpl(String appId, String mchId, String key, String certPath, boolean sandBox) throws KnowException {
        if (StringUtils.isEmpty(appId))
            throw new KnowException("appid不能为空!");
        if (StringUtils.isEmpty(mchId))
            throw new KnowException("商户id不能为空!");
        this.appId = appId;
        this.mchId = mchId;
        this.key = key;
        this.sandBox = sandBox;
        //读取微信https证书数据
        if (!StringUtils.isEmpty(certPath)) {
            File certFile = new File(certPath);
            InputStream certStream = null;
            try {
                certStream = new FileInputStream(certFile);
                this.certData = new byte[(int) certFile.length()];
                certStream.read(this.certData);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new KnowException("微信证书文件不存在:" + certPath);
            } catch (IOException e) {
                e.printStackTrace();
                throw new KnowException("读取微信证书文件失败:" + certPath);
            } finally {
                IOUtil.close(certStream);
            }
        }
    }

    public String getAppID() {
        return this.appId;
    }

    public String getMchID() {
        return this.mchId;
    }

    public String getKey() {
        if (sandBox)
            return new SandBoxKeyProvide().getSandBoxKey(mchId, key);
        return this.key;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    public IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

    @Override
    public int getReportWorkerNum() {
        return 1;
    }

    @Override
    public int getReportBatchSize() {
        return 2;
    }
}
