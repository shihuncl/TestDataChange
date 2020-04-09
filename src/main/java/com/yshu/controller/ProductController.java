package com.yshu.controller;

import com.alibaba.fastjson.JSON;
import com.yshu.entity.Product;
import com.yshu.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    /**
     * Get product by id
     *
     * @param productId
     * @return
     * @throws
     */
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long productId)  {
        return productService.select(productId);
    }

    @GetMapping("/a/{id}")
    public Product getProducts(@PathVariable("id") Long productId)  {
        return productService.select1(productId);
    }








    /**
     * Save product
     *
     * @param newProduct
     * @return
     * @throws
     */
    @PostMapping
    public boolean addProduct(@RequestBody Product newProduct)   {
        return productService.add(newProduct);
    }

    @PostMapping("/a")
    public boolean addProduct1(@RequestBody Product newProduct)   {
        return productService.add1(newProduct);
    }

    public static void main(String[] args) {
        Product product=new Product();
        product.setName("ceshi");
        product.setPrice(88888);
        System.out.println(JSON.toJSONString(product));
    }

}
