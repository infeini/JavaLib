package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.User;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(User user) throws Exception
    {
        userMapper.insert(user);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(User user) throws Exception
    {
        if (user.getId() == null)
            throw new Exception("用户id不能为空");
        userMapper.update(user);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(User user) throws Exception
    {
        return search(user, userMapper);
    }
}
