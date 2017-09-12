package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.Job;

import java.util.HashMap;
import java.util.Map;

@Service
public class JobService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(Job job) throws Exception
    {
        jobMapper.insert(job);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Job job) throws Exception
    {
        if (job.getId() == null)
            throw new Exception("岗位id不能为空");
        jobMapper.update(job);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(Job job) throws Exception
    {
        return search(job, jobMapper);
    }
}
