package auto.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import ljs.io.IOUtil;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Auto
{
    @Test
    public void autoPojo() throws Exception
    {
        DataBase dataBase = new DataBase("plm", "localhost", 3306, "root", "123456");
        for (Table table : dataBase.tables)
        {
            File saveDir = new File("./test");
            HashMap<Object, Object> model = new HashMap<>();
            Config config = new Config("pojo", table.name.type(), "mapper", table.name.type() + "Mapper", "service", table.name.type() + "Service", "controller", table.name.type() + "Controller");
            model.put("config", config);
            model.put("table", table);
            //生成pojo
            write(model, new File("./src/auto/freemarker/pojo.txt"), new File(new File(saveDir, "pojo"), config.pojoName + ".java"));
            //生成mybatis mapper接口
            write(model, new File("./src/auto/freemarker/mybatis_java.txt"), new File(new File(saveDir, "mapper"), config.mapperName + ".java"));
            //生成mybatis xml配置文件
            write(model, new File("./src/auto/freemarker/mybatis_xml.txt"), new File(new File(saveDir, "mapper"), config.mapperName + ".xml"));
            //生成service java文件
            write(model, new File("./src/auto/freemarker/service.txt"), new File(new File(saveDir, "service"), config.serviceName + ".java"));
            //生成controller java文件
            write(model, new File("./src/auto/freemarker/controller.txt"), new File(new File(saveDir, "controller"), config.controllerName + ".java"));
        }
    }

    public void write(Object model, File view, File out) throws Exception
    {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setDirectoryForTemplateLoading(view.getParentFile());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        Template temp = cfg.getTemplate(view.getName());
        if (!out.getParentFile().exists())
            out.getParentFile().mkdirs();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out, false)));
        try
        {
            temp.process(model, writer);
        } finally
        {
            IOUtil.close(writer);
        }
    }
}
