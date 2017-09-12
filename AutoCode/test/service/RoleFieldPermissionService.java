package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.RoleFieldPermission;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoleFieldPermissionService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(RoleFieldPermission roleFieldPermission) throws Exception
    {
        roleFieldPermissionMapper.insert(roleFieldPermission);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(RoleFieldPermission roleFieldPermission) throws Exception
    {
        if (roleFieldPermission.getId() == null)
            throw new Exception("角色字段权限关系id不能为空");
        roleFieldPermissionMapper.update(roleFieldPermission);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(RoleFieldPermission roleFieldPermission) throws Exception
    {
        return search(roleFieldPermission, roleFieldPermissionMapper);
    }
}
