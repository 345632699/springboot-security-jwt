package com.hotel.minapp.scenes.delayTask;

import com.hotel.minapp.dto.RoomOrderDto;
import com.hotel.minapp.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xu
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {
    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);

    @Autowired
    private OrderService orderService;
 
    @RabbitHandler
    public void handle(Integer orderId){
        LOGGER.info("消息被消费成功");
        LOGGER.info("process orderId:{}",orderId);
        RoomOrderDto infoById = orderService.getInfoById(orderId);
        if (infoById.getPayStatus().equals(0)) {
            orderService.cancelOrder(orderId);
        }
    }
}