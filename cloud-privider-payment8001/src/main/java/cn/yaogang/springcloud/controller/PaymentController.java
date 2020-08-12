package cn.yaogang.springcloud.controller;

import cn.yaogang.springcloud.entities.CommonResult;
import cn.yaogang.springcloud.entities.Payment;
import cn.yaogang.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("result: {}", result);

        if (result > 0) {
            return new CommonResult(200, "success", result);
        } else {
            return new CommonResult<>(444, "failed");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("result: {}", result);

        if (result != null) {
            return new CommonResult(200, "success", result);
        } else {
            return new CommonResult<>(444, "No result, query id: " + id);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        discoveryClient.getServices().forEach(e ->log.info(e));
        discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE").forEach(e ->
                log.info(e.getServiceId() + "\t" + e.getHost() + "\t" + e.getPort() + "\t" + e.getUri()));
        return this.discoveryClient;
    }
}
