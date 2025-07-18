package com.example.mapper;

import com.example.entity.TShopOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author y
 * @since 2025-07-16
 */
@Mapper
public interface TShopMapper extends BaseMapper<TShopOrder> {
    // 如果有自定义 SQL，可以在这里加方法并配合 @Select、@Insert 等注解
}
