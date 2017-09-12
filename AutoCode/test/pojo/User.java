package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class User extends BasePojo
{
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户密码hash
     */
    private String passWord;
    /**
     * 用户组织id
     */
    private Integer orgId;
    /**
     * 部门id
     */
    private Integer departmentId;
    /**
     * 岗位id
     */
    private Integer jobId;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 用户姓名
     */
    private String userFullName;
    /**
     * 
     */
    private String email;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 用户专业
     */
    private String profession;
    /**
     * 用户学历
     */
    private String education;
    /**
     * 用户政治面貌
     */
    private String politicalStatus;
    /**
     * 用户照片文件hash字符串
     */
    private String photoHash;
    /**
     * 用户手机号码
     */
    private String phone;
    /**
     * 用户家庭地址
     */
    private String address;
    /**
     * 用户座机号码
     */
    private String telephone;
    /**
     * 用户联系人
     */
    private String contact;
    /**
     * 用户联系人电话号码
     */
    private String contactPhone;
    /**
     * 用户办公电话
     */
    private String officePhone;
    /**
     * 用户办公地址
     */
    private String officeAddress;
    /**
     * 用户履历
     */
    private String resume;
    /**
     * 用户备注
     */
    private String remark;
    /**
     * 用户是否启用
     */
    private Boolean enabled;

    /**
     * 用户id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 用户id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 用户名称 Getter
     */
    public String getUserName()
    {
        return this.userName;
    }

    /**
     * 用户名称 Getter
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * 用户密码hash Getter
     */
    public String getPassWord()
    {
        return this.passWord;
    }

    /**
     * 用户密码hash Getter
     */
    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }

    /**
     * 用户组织id Getter
     */
    public Integer getOrgId()
    {
        return this.orgId;
    }

    /**
     * 用户组织id Getter
     */
    public void setOrgId(Integer orgId)
    {
        this.orgId = orgId;
    }

    /**
     * 部门id Getter
     */
    public Integer getDepartmentId()
    {
        return this.departmentId;
    }

    /**
     * 部门id Getter
     */
    public void setDepartmentId(Integer departmentId)
    {
        this.departmentId = departmentId;
    }

    /**
     * 岗位id Getter
     */
    public Integer getJobId()
    {
        return this.jobId;
    }

    /**
     * 岗位id Getter
     */
    public void setJobId(Integer jobId)
    {
        this.jobId = jobId;
    }

    /**
     * 角色id Getter
     */
    public Integer getRoleId()
    {
        return this.roleId;
    }

    /**
     * 角色id Getter
     */
    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }

    /**
     * 用户姓名 Getter
     */
    public String getUserFullName()
    {
        return this.userFullName;
    }

    /**
     * 用户姓名 Getter
     */
    public void setUserFullName(String userFullName)
    {
        this.userFullName = userFullName;
    }

    /**
     *  Getter
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     *  Getter
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * 用户性别 Getter
     */
    public String getSex()
    {
        return this.sex;
    }

    /**
     * 用户性别 Getter
     */
    public void setSex(String sex)
    {
        this.sex = sex;
    }

    /**
     * 用户专业 Getter
     */
    public String getProfession()
    {
        return this.profession;
    }

    /**
     * 用户专业 Getter
     */
    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    /**
     * 用户学历 Getter
     */
    public String getEducation()
    {
        return this.education;
    }

    /**
     * 用户学历 Getter
     */
    public void setEducation(String education)
    {
        this.education = education;
    }

    /**
     * 用户政治面貌 Getter
     */
    public String getPoliticalStatus()
    {
        return this.politicalStatus;
    }

    /**
     * 用户政治面貌 Getter
     */
    public void setPoliticalStatus(String politicalStatus)
    {
        this.politicalStatus = politicalStatus;
    }

    /**
     * 用户照片文件hash字符串 Getter
     */
    public String getPhotoHash()
    {
        return this.photoHash;
    }

    /**
     * 用户照片文件hash字符串 Getter
     */
    public void setPhotoHash(String photoHash)
    {
        this.photoHash = photoHash;
    }

    /**
     * 用户手机号码 Getter
     */
    public String getPhone()
    {
        return this.phone;
    }

    /**
     * 用户手机号码 Getter
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     * 用户家庭地址 Getter
     */
    public String getAddress()
    {
        return this.address;
    }

    /**
     * 用户家庭地址 Getter
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * 用户座机号码 Getter
     */
    public String getTelephone()
    {
        return this.telephone;
    }

    /**
     * 用户座机号码 Getter
     */
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    /**
     * 用户联系人 Getter
     */
    public String getContact()
    {
        return this.contact;
    }

    /**
     * 用户联系人 Getter
     */
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    /**
     * 用户联系人电话号码 Getter
     */
    public String getContactPhone()
    {
        return this.contactPhone;
    }

    /**
     * 用户联系人电话号码 Getter
     */
    public void setContactPhone(String contactPhone)
    {
        this.contactPhone = contactPhone;
    }

    /**
     * 用户办公电话 Getter
     */
    public String getOfficePhone()
    {
        return this.officePhone;
    }

    /**
     * 用户办公电话 Getter
     */
    public void setOfficePhone(String officePhone)
    {
        this.officePhone = officePhone;
    }

    /**
     * 用户办公地址 Getter
     */
    public String getOfficeAddress()
    {
        return this.officeAddress;
    }

    /**
     * 用户办公地址 Getter
     */
    public void setOfficeAddress(String officeAddress)
    {
        this.officeAddress = officeAddress;
    }

    /**
     * 用户履历 Getter
     */
    public String getResume()
    {
        return this.resume;
    }

    /**
     * 用户履历 Getter
     */
    public void setResume(String resume)
    {
        this.resume = resume;
    }

    /**
     * 用户备注 Getter
     */
    public String getRemark()
    {
        return this.remark;
    }

    /**
     * 用户备注 Getter
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    /**
     * 用户是否启用 Getter
     */
    public Boolean getEnabled()
    {
        return this.enabled;
    }

    /**
     * 用户是否启用 Getter
     */
    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }
}