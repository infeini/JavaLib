package auto.freemarker;

public class Config
{
    public String pojoPackage;
    public String pojoName;

    public String mapperPackage;
    public String mapperName;

    public String servicePackage;
    public String serviceName;

    public String controllerPackage;
    public String controllerName;

    public Config(String pojoPackage, String pojoName, String mapperPackage, String mapperName, String servicePackage, String serviceName, String controllerPackage, String controllerName)
    {
        this.pojoPackage = pojoPackage;
        this.pojoName = pojoName;
        this.mapperPackage = mapperPackage;
        this.mapperName = mapperName;
        this.servicePackage = servicePackage;
        this.serviceName = serviceName;
        this.controllerPackage = controllerPackage;
        this.controllerName = controllerName;
    }

    public String getPojoPackage()
    {
        return pojoPackage;
    }

    public void setPojoPackage(String pojoPackage)
    {
        this.pojoPackage = pojoPackage;
    }

    public String getPojoName()
    {
        return pojoName;
    }

    public void setPojoName(String pojoName)
    {
        this.pojoName = pojoName;
    }

    public String getMapperPackage()
    {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage)
    {
        this.mapperPackage = mapperPackage;
    }

    public String getMapperName()
    {
        return mapperName;
    }

    public void setMapperName(String mapperName)
    {
        this.mapperName = mapperName;
    }

    public String getServicePackage()
    {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage)
    {
        this.servicePackage = servicePackage;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getControllerPackage()
    {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage)
    {
        this.controllerPackage = controllerPackage;
    }

    public String getControllerName()
    {
        return controllerName;
    }

    public void setControllerName(String controllerName)
    {
        this.controllerName = controllerName;
    }
}