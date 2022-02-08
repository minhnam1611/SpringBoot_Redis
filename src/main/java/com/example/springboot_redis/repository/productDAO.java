package com.example.springboot_redis.repository;

import com.example.springboot_redis.entity.Products;
import com.example.springboot_redis.mapper.mapperProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Repository
public class productDAO {
    public static final String HASH_KEY="Products";
    @Autowired
    private RedisTemplate template;

    public Products save(Products p){
        template.opsForHash().put(HASH_KEY,p.getId(),p);
        return p;
    }
    public List<Products> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }
    public Products findProductById(long id){
        return ((Products) template.opsForHash().get(HASH_KEY, id));
    }
    public void deleteProduct(long id){
        template.opsForHash().delete(HASH_KEY,id);
    }
}
