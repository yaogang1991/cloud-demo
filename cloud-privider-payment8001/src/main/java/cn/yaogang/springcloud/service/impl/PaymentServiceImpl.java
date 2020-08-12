package cn.yaogang.springcloud.service.impl;

import cn.yaogang.springcloud.dao.PaymentDao;
import cn.yaogang.springcloud.entities.Payment;
import cn.yaogang.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
