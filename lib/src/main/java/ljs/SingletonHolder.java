package ljs;

import org.simpleframework.xml.core.Persister;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SingletonHolder {
    public static class Random {
        public static java.util.Random INSTANCE = new java.util.Random();
    }

    public static class MD5 {
        public static MessageDigest INSTAT = getMD5();

        private static MessageDigest getMD5() {
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return messageDigest;
        }
    }

    public static class SimpleXml {
        public final static Persister INSTANCE = new Persister();
    }
}
