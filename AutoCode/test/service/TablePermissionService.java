package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.TablePermission;

import java.util.HashMap;
import java.util.Map;

@Service
public class TablePermissionService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(TablePermission tablePermission) throws Exception
    {
        tablePermissionMapper.insert(tablePermission);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(TablePermission tablePermission) throws Exception
    {
        if (tablePermission.getId() == null)
            throw new Exception("数据库权限id不能为空");
        tablePermissionMapper.update(tablePermission);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(TablePermission tablePermission) throws Exception
    {
        return search(tablePermission, tablePermissionMapper);
    }
}
