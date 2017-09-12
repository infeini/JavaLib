package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.RoleSysFunctionPermission;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoleSysFunctionPermissionService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(RoleSysFunctionPermission roleSysFunctionPermission) throws Exception
    {
        roleSysFunctionPermissionMapper.insert(roleSysFunctionPermission);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(RoleSysFunctionPermission roleSysFunctionPermission) throws Exception
    {
        if (roleSysFunctionPermission.getId() == null)
            throw new Exception("角色与系统功能权限关系id不能为空");
        roleSysFunctionPermissionMapper.update(roleSysFunctionPermission);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(RoleSysFunctionPermission roleSysFunctionPermission) throws Exception
    {
        return search(roleSysFunctionPermission, roleSysFunctionPermissionMapper);
    }
}
