package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.NeedsVersion;

import java.util.HashMap;
import java.util.Map;

@Service
public class NeedsVersionService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(NeedsVersion needsVersion) throws Exception
    {
        needsVersionMapper.insert(needsVersion);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(NeedsVersion needsVersion) throws Exception
    {
        if (needsVersion.getId() == null)
            throw new Exception("需求版本id不能为空");
        needsVersionMapper.update(needsVersion);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(NeedsVersion needsVersion) throws Exception
    {
        return search(needsVersion, needsVersionMapper);
    }
}
