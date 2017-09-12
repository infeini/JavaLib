package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.UserFieldPermission;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserFieldPermissionService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(UserFieldPermission userFieldPermission) throws Exception
    {
        userFieldPermissionMapper.insert(userFieldPermission);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(UserFieldPermission userFieldPermission) throws Exception
    {
        if (userFieldPermission.getId() == null)
            throw new Exception("用户与字段权限关系表不能为空");
        userFieldPermissionMapper.update(userFieldPermission);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(UserFieldPermission userFieldPermission) throws Exception
    {
        return search(userFieldPermission, userFieldPermissionMapper);
    }
}
