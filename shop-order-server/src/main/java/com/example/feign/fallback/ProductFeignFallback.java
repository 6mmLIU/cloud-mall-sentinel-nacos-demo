package com.example.feign.fallback;


import com.example.entity.TShopProduct;
import com.example.feign.ProductFeignApi;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignFallback implements ProductFeignApi {
    @Override
    public TShopProduct getById(Long id) {
        TShopProduct product = new TShopProduct();
        product.setPid(-1l);
        product.setPname("错误商品");
        product.setPprice(0.0);
        product.setStock(0);
        return product;
    }
}
