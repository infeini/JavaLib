package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.Role;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoleService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(Role role) throws Exception
    {
        roleMapper.insert(role);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Role role) throws Exception
    {
        if (role.getId() == null)
            throw new Exception("角色id不能为空");
        roleMapper.update(role);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(Role role) throws Exception
    {
        return search(role, roleMapper);
    }
}
