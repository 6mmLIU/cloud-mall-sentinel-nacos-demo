package com.example.controller;

import com.example.entity.TShopOrder;
import com.example.entity.TShopProduct;
import com.example.feign.ProductFeignApi;
import com.example.service.ITShopOrderService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * 订单控制器（演示版）
 * <p>
 * 课堂目的：演示“订单服务调用商品服务”的最朴素流程：
 * ① 从注册中心发现所有 product 实例
 * ② 手动随机挑一个（客户端负载均衡雏形）
 * ③ 用 RestTemplate 拼 URL 调用商品接口
 * ④ 组装订单返回
 * <p>
 * 教学故意留坑：真正请求时仍写死 localhost:8091，
 * 让你体会 Connection refused 的错误。
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class TShopOrderController {

    @Autowired
    private ITShopOrderService orderService;      // 订单持久层（本例未用到）
    @Autowired
    private DiscoveryClient discoveryClient;      // ① 服务发现组件
    @Autowired
    private ProductFeignApi productFeignApi;      // 备用：Feign 写法（本例未启用）

    /**
     * 保存订单接口：
     * 访问示例：http://localhost:8091/order/save?uid=1&pid=4&num=2
     */
    @RequestMapping("/save")
    public TShopOrder save(Long uid, Long pid, Integer num) {
        log.info("调用orderController的save方法");
        /* ========== 步骤①：从注册中心拉取 product 实例列表 ========== */
        // 可能返回 0~N 个实例；productList.size() == 0 时会埋下边界问题
        List<ServiceInstance> productList = discoveryClient.getInstances("product");

        /* ========== 步骤②：把实例端口提取出来，准备做随机负载 ========== */
        // ports.length == productList.size()
        String[] ports = new String[productList.size()];
        for (int i = 0; i < productList.size(); i++) {
            ServiceInstance instance = productList.get(i);
            Integer port = instance.getPort();
            ports[i] = port.toString();  // ②-1 把端口塞到数组
        }

        /* ========== 步骤③：随机选一个端口（客户端负载均衡雏形） ========== */
        Random random = new Random();
        int pos = random.nextInt(ports.length);   // ⚠ 若 ports.length==0 会抛 bound must be positive
        String port = ports[pos];

        /* ========== 步骤④：远程调用商品服务 ========== */
        // ⚠ 演示坑点：下面硬编码了 localhost:8091，而不是用上面随机出的 port！
        //    若商品服务并未监听 8091 → Connection refused。
        RestTemplate restTemplate = new RestTemplate();
        TShopProduct product = restTemplate.getForObject(
                "http://localhost:" + port + "/product/" + pid,     // TODO: 正确写法应改成 "http://localhost:" + port + "/product/" + pid
                TShopProduct.class
        );

        /* ========== 步骤⑤：组装订单并返回给前端 ========== */
        TShopOrder order = new TShopOrder();
        order.setUid(uid);                     // 买家
        order.setPid(pid);                     // 商品 ID
        order.setPname(product.getPname());    // 商品名称
        order.setPprice(product.getPprice());  // 商品单价
        order.setNumber(num);                  // 购买数量
        // orderService.save(order);           // 真业务里应持久化，这里仅演示
        return order;
    }
}
