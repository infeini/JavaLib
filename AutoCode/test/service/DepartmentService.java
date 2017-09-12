package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.Department;

import java.util.HashMap;
import java.util.Map;

@Service
public class DepartmentService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(Department department) throws Exception
    {
        departmentMapper.insert(department);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Department department) throws Exception
    {
        if (department.getId() == null)
            throw new Exception("部门id不能为空");
        departmentMapper.update(department);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(Department department) throws Exception
    {
        return search(department, departmentMapper);
    }
}
