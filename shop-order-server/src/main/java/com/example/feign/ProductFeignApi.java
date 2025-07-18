package com.example.feign;

import com.example.entity.TShopProduct;
import com.example.feign.fallback.ProductFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="product",fallback = ProductFeignFallback.class)
public interface ProductFeignApi {
    @GetMapping("/product/{id}")
    TShopProduct getById(@PathVariable("id") Long id);

    }

