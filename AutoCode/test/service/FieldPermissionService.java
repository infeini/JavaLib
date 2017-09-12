package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.FieldPermission;

import java.util.HashMap;
import java.util.Map;

@Service
public class FieldPermissionService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(FieldPermission fieldPermission) throws Exception
    {
        fieldPermissionMapper.insert(fieldPermission);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(FieldPermission fieldPermission) throws Exception
    {
        if (fieldPermission.getId() == null)
            throw new Exception("字段权限id不能为空");
        fieldPermissionMapper.update(fieldPermission);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(FieldPermission fieldPermission) throws Exception
    {
        return search(fieldPermission, fieldPermissionMapper);
    }
}
