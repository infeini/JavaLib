package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.Needs;

import java.util.HashMap;
import java.util.Map;

@Service
public class NeedsService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(Needs needs) throws Exception
    {
        needsMapper.insert(needs);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Needs needs) throws Exception
    {
        if (needs.getId() == null)
            throw new Exception("需求id不能为空");
        needsMapper.update(needs);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(Needs needs) throws Exception
    {
        return search(needs, needsMapper);
    }
}
