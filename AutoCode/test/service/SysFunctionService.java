package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.SysFunction;

import java.util.HashMap;
import java.util.Map;

@Service
public class SysFunctionService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(SysFunction sysFunction) throws Exception
    {
        sysFunctionMapper.insert(sysFunction);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(SysFunction sysFunction) throws Exception
    {
        if (sysFunction.getId() == null)
            throw new Exception("功能id不能为空");
        sysFunctionMapper.update(sysFunction);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(SysFunction sysFunction) throws Exception
    {
        return search(sysFunction, sysFunctionMapper);
    }
}
