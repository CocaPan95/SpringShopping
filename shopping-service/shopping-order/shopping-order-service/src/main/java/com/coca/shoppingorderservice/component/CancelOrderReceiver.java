package com.coca.shoppingorderservice.component;

import com.coca.shoppingorderapi.OmsOrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的处理者
 * Created by macro on 2018/9/14.
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {
    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);
    @DubboReference
    private OmsOrderService omsOrderService;

    @RabbitHandler
    public void handle(Long orderId){
        omsOrderService.cancelOrder(orderId);
        LOGGER.info("process orderId:{}",orderId);
    }
}
