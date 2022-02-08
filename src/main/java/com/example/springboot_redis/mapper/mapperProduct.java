package com.example.springboot_redis.mapper;

import com.example.springboot_redis.entity.Products;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface mapperProduct {
    @Select("SELECT * FROM products")
    List<Products> getAllProduct();
    @Select("SELECT * FROM products WHERE id = #{id}")
    Products getProductById(int id);
    @Insert("INSERT INTO products(id,name,quanty,price) VALUES(#{id},#{name},#{quanty},#{price})")
    Products saveProduct(Products a);
    @Delete("DELETE FROM products WHERE id = #{id}")
    void deleteProduct(long id);
}
