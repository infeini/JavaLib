package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.ProductLine;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductLineService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(ProductLine productLine) throws Exception
    {
        productLineMapper.insert(productLine);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(ProductLine productLine) throws Exception
    {
        if (productLine.getId() == null)
            throw new Exception("产品线id不能为空");
        productLineMapper.update(productLine);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(ProductLine productLine) throws Exception
    {
        return search(productLine, productLineMapper);
    }
}
