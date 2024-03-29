package ljs.spring;

import ljs.exception.KnowException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassUtils {
    /**
     * 获取包下所有类
     *
     * @param packageName <p>包路径,支持正则
     *                    eg:ljs.** 递归扫描包ljs下的类
     *                    ljs 仅扫描ljs下的类,不递归扫描
     */
    public static List<Class> getAllClass(String packageName) throws KnowException {
        String path = packageName.replaceAll("\\.", "/");
        List<Class> classes = new ArrayList<>();
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(patternResolver);
        Resource[] resources;
        try {
            resources = patternResolver.getResources(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + path + "/*.class");
        } catch (IOException e) {
            throw new KnowException("读取类路径失败:" + e.getMessage());
        }
        for (Resource resource : resources) {
            if (resource.isReadable()) {
                MetadataReader metadataReader;
                try {
                    metadataReader = metadataReaderFactory.getMetadataReader(resource);
                    classes.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                } catch (Exception e) {
                    throw new KnowException("读取类文件信息失败:" + e.getMessage());
                }
            }
        }
        return classes;
    }
}
