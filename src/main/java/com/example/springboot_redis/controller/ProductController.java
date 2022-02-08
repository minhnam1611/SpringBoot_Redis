package com.example.springboot_redis.controller;

import com.example.springboot_redis.entity.Products;
import com.example.springboot_redis.mapper.mapperProduct;
import com.example.springboot_redis.repository.productDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    mapperProduct mapperProducts;

    @Autowired
    productDAO productDAOs;

    @GetMapping("/all")
    public List<Products> getAllProducts(){
        List<Products> list = productDAOs.findAll();
        List<Products> sqlList = mapperProducts.getAllProduct();
        if(list.size()==0){
            for (Products a : sqlList) {
                productDAOs.save(a);
            }
        }else{
            if(list.size()>sqlList.size()){
                for (Products b : list) {
                    productDAOs.deleteProduct(((int) b.getId()));
                }
                for (Products a : sqlList) {
                    productDAOs.save(a);
                }
            }
            for (Products a : sqlList) {
                if(isExist(a,list)==false){
                    productDAOs.save(a);
                }
            }
        }
        return productDAOs.findAll();
    }
    Boolean isExist (Products a, List<Products> list){
        for (Products b : list){
            if(b.getId() == a.getId()){
                return true;
            }
        }
        return false;
    }
    @GetMapping("/{id}")
    public  Products getProductById(@PathVariable int id){
        Products rs = productDAOs.findProductById(id);
        if(rs ==null){
            System.out.println("Get data from MySQL");
            rs = mapperProducts.getProductById(id);
            productDAOs.save(rs);
        }else{
            System.out.println("Get data from Redis");
        }
        return rs;

    }
    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable long id){
        mapperProducts.deleteProduct(id);
        Products a = productDAOs.findProductById(id);
        List<Products> list = productDAOs.findAll();
        if(isExist(a,list)==true){
            productDAOs.deleteProduct(id);
        }
        return"Done";
    }


}
