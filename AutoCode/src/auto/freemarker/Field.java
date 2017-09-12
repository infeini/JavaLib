package auto.freemarker;

public class Field
{
    public MyString name;
    public MyType type;
    public Boolean key;
    public String info;

    public MyString getName()
    {
        return name;
    }

    public void setName(MyString name)
    {
        this.name = name;
    }

    public MyType getType()
    {
        return type;
    }

    public void setType(MyType type)
    {
        this.type = type;
    }

    public Boolean getKey()
    {
        return key;
    }

    public void setKey(Boolean key)
    {
        this.key = key;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public Field(MyString name, MyType type, Boolean key, String info)
    {
        this.name = name;
        this.type = type;
        this.key = key;
        this.info = info;
    }
}