package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.Organization;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrganizationService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(Organization organization) throws Exception
    {
        organizationMapper.insert(organization);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Organization organization) throws Exception
    {
        if (organization.getId() == null)
            throw new Exception("组织id不能为空");
        organizationMapper.update(organization);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(Organization organization) throws Exception
    {
        return search(organization, organizationMapper);
    }
}
