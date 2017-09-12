package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.UserTablePermission;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserTablePermissionService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(UserTablePermission userTablePermission) throws Exception
    {
        userTablePermissionMapper.insert(userTablePermission);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(UserTablePermission userTablePermission) throws Exception
    {
        if (userTablePermission.getId() == null)
            throw new Exception("用户与数据表权限管理不能为空");
        userTablePermissionMapper.update(userTablePermission);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(UserTablePermission userTablePermission) throws Exception
    {
        return search(userTablePermission, userTablePermissionMapper);
    }
}
