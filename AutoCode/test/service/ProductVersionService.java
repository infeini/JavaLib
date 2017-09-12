package service;

import base.BaseService;
import ljs.lib.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.ProductVersion;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductVersionService extends BaseService
{
    //添加
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> add(ProductVersion productVersion) throws Exception
    {
        productVersionMapper.insert(productVersion);
        return success(ADD_OK);
    }

    //修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(ProductVersion productVersion) throws Exception
    {
        if (productVersion.getId() == null)
            throw new Exception("产品版本id不能为空");
        productVersionMapper.update(productVersion);
    }

    //查询
    @Transactional(readOnly = true)
    public HashMap<String, Object> search(ProductVersion productVersion) throws Exception
    {
        return search(productVersion, productVersionMapper);
    }
}
