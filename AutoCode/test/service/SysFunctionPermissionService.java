package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.SysFunctionPermission;

import java.util.HashMap;
import java.util.Map;

@Service
public class SysFunctionPermissionService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(SysFunctionPermission sysFunctionPermission) throws Exception
    {
        sysFunctionPermissionMapper.insert(sysFunctionPermission);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(SysFunctionPermission sysFunctionPermission) throws Exception
    {
        if (sysFunctionPermission.getId() == null)
            throw new Exception("功能id不能为空");
        sysFunctionPermissionMapper.update(sysFunctionPermission);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(SysFunctionPermission sysFunctionPermission) throws Exception
    {
        return search(sysFunctionPermission, sysFunctionPermissionMapper);
    }
}
