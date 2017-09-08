package test.mybatisCodeGenerate;

import ljs.code.mybatis.annotation.FixName;
import ljs.code.mybatis.annotation.PrimaryKey;

@FixName(name = "_User")
public class User
{
    @PrimaryKey
    private Integer id;
    private String name;
}