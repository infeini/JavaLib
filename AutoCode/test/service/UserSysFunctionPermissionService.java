package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.UserSysFunctionPermission;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserSysFunctionPermissionService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(UserSysFunctionPermission userSysFunctionPermission) throws Exception
    {
        userSysFunctionPermissionMapper.insert(userSysFunctionPermission);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(UserSysFunctionPermission userSysFunctionPermission) throws Exception
    {
        if (userSysFunctionPermission.getId() == null)
            throw new Exception("不能为空");
        userSysFunctionPermissionMapper.update(userSysFunctionPermission);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(UserSysFunctionPermission userSysFunctionPermission) throws Exception
    {
        return search(userSysFunctionPermission, userSysFunctionPermissionMapper);
    }
}
