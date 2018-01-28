package ljs.fastjson;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import ljs.exception.KnowException;
import ljs.lib.StringUtils;
import ljs.reflect.ClassUtils;

public class MapBeanHttpMessageConverter extends FastJsonHttpMessageConverter {

    public MapBeanHttpMessageConverter(String[] classes) throws KnowException {
        this(classes, null);
    }

    public MapBeanHttpMessageConverter(String beanPackage) throws KnowException {
        this(null, beanPackage);
    }

    public MapBeanHttpMessageConverter(String[] classes, String beanPackage) throws KnowException {
        if (!StringUtils.isEmpty(beanPackage))
            setPackage(beanPackage);
        if (classes != null)
            setClasses(classes);
    }

    private void setPackage(String beanPackage) throws KnowException {
        for (Class clazz : ClassUtils.getAllClass(beanPackage))
            defineMapBeanConver(clazz);
    }

    private void setClasses(String[] classes) {
        for (String strClass : classes) {
            try {
                defineMapBeanConver(Class.forName(strClass));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void defineMapBeanConver(Class type) {
        if (type != null)
            ParserConfig.getGlobalInstance().putDeserializer(type, new JavaBeanDeserializer(ParserConfig.global, type));
    }
}