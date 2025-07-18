package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author y
 * @since 2025-07-16
 */
@Data
public class TShopProduct implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "pid", type = IdType.AUTO)
    private Long pid;

    private String pname;

    private Double pprice;

    private Integer stock;


    @Override
    public String toString() {
        return "TShopProduct{" +
        "pid=" + pid +
        ", pname=" + pname +
        ", pprice=" + pprice +
        ", stock=" + stock +
        "}";
    }
}
