package com.example.springboot_redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Products")
public class Products implements Serializable {
    @Id
    private long id;

    private String name;

    private int quanty;

    private double price;
}
