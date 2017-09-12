package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.Function;

import java.util.HashMap;
import java.util.Map;

@Service
public class FunctionService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(Function function) throws Exception
    {
        functionMapper.insert(function);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Function function) throws Exception
    {
        if (function.getId() == null)
            throw new Exception("产品功能点id不能为空");
        functionMapper.update(function);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(Function function) throws Exception
    {
        return search(function, functionMapper);
    }
}
