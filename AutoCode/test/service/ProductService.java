package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.Product;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(Product product) throws Exception
    {
        productMapper.insert(product);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(Product product) throws Exception
    {
        if (product.getId() == null)
            throw new Exception("产品id不能为空");
        productMapper.update(product);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(Product product) throws Exception
    {
        return search(product, productMapper);
    }
}
