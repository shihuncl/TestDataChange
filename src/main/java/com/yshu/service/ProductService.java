package com.yshu.service;


import com.yshu.annotation.BeforeTransactional;
import com.yshu.configuration.DynamicDataSourceContextHolder;
import com.yshu.dao.master.ProductDao;
import com.yshu.dao.messagebase.ProductDao1;
import com.yshu.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Product service for handler logic of product operation
 *
 * @author HelloWood
 * @date 2017-07-11 11:58
 * @Email hellowoodes@gmail.com
 */

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductDao1 productDao1;

    /**
     * Get product by id
     * If not found product will throw ServiceException
     *
     * @param productId
     * @return
     * @throws
     */
    @BeforeTransactional
    public Product select(long productId)  {
        System.out.println("当前查询data:"+DynamicDataSourceContextHolder.getDataSourceKey());
        Product product = productDao.select(productId);
        System.out.println("当前查询data1:"+DynamicDataSourceContextHolder.getDataSourceKey());

        return product;
    }



    /**
     * Add product to DB
     *
     * @param newProduct
     * @return
8     * @throws
     */
    @BeforeTransactional
    @Transactional(rollbackFor = DataAccessException.class)
    public boolean add(Product newProduct)   {
        System.out.println(DynamicDataSourceContextHolder.getDataSourceKey());
        Integer num = productDao.insert(newProduct);
        System.out.println("当前查询data1:"+DynamicDataSourceContextHolder.getDataSourceKey());

        System.out.println("执行新增"+num);

        return true;
    }
    @BeforeTransactional(value = "messagebase")
    @Transactional(rollbackFor = Exception.class)
    public boolean add1(Product newProduct)   {
        System.out.println(DynamicDataSourceContextHolder.getDataSourceKey());
        Integer num = productDao1.insert(newProduct);
        System.out.println("当前查询data1:"+DynamicDataSourceContextHolder.getDataSourceKey());
        System.out.println("执行新增"+num);
        return true;
    }

    @BeforeTransactional(value = "messagebase")
    public Product select1(Long productId) {

        System.out.println("当前查询data:"+DynamicDataSourceContextHolder.getDataSourceKey());
        Product product = productDao1.select(productId);
        System.out.println("当前查询data1:"+DynamicDataSourceContextHolder.getDataSourceKey());

        return product;
    }
}
