package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.RoleTablePermission;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoleTablePermissionService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(RoleTablePermission roleTablePermission) throws Exception
    {
        roleTablePermissionMapper.insert(roleTablePermission);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(RoleTablePermission roleTablePermission) throws Exception
    {
        if (roleTablePermission.getId() == null)
            throw new Exception("角色数据表权限关系id不能为空");
        roleTablePermissionMapper.update(roleTablePermission);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(RoleTablePermission roleTablePermission) throws Exception
    {
        return search(roleTablePermission, roleTablePermissionMapper);
    }
}
