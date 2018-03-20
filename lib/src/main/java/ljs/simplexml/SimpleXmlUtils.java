package ljs.simplexml;

import ljs.lib.StringUtils;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeBuilder;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static ljs.SingletonHolder.SimpleXml.INSTANCE;

public class SimpleXmlUtils {
    public static Map<String, String> beanToMap(Object bean) {
        Map<String, String> map = new HashMap<>();
        StringWriter stringWriter = new StringWriter();
        try {
            INSTANCE.write(bean, stringWriter);
            InputNode inputNode = NodeBuilder.read(new StringReader(stringWriter.toString()));
            InputNode nextNode;
            while ((nextNode = inputNode.getNext()) != null) {
                String key = nextNode.getName();
                String value = nextNode.getValue();
                if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value))
                    map.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
