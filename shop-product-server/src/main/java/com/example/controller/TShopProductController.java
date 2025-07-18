package com.example.controller;


import com.example.entity.TShopProduct;
import com.example.service.ITSHOPPRODUCTervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author y
 * @since 2025-07-16
 */
@RestController
@RequestMapping("/product")
public class TShopProductController {
    @Autowired
    private ITSHOPPRODUCTervice itshopproducTervice;
    @Value("${server.port}")
    private String port;

    @GetMapping("/{id}")
    public TShopProduct getById(@PathVariable("id") Long id) {
        TShopProduct product = itshopproducTervice.getById(id);
        product.setPname(product.getPname()+port);
        System.out.println(product);
        return product;

    }

}

