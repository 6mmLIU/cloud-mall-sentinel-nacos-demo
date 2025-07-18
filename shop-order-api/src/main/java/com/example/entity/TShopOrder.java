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
public class TShopOrder implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "oid", type = IdType.AUTO)
    private Long oid;

    private Long uid;

    private String username;

    private Long pid;

    private String pname;

    private Double pprice;

    private Integer number;



    @Override
    public String toString() {
        return "TShopOrder{" +
        "oid=" + oid +
        ", uid=" + uid +
        ", username=" + username +
        ", pid=" + pid +
        ", pname=" + pname +
        ", pprice=" + pprice +
        ", number=" + number +
        "}";
    }
}
