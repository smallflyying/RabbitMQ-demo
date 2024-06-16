package com.atguigu.rabbitmq.eight;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

/**
 *  死信队列 之 生产者代码
 * @author LiHongFei
 * @since 2024/6/16
 */
public class Producer {

    // 普通交换机的名称
    public static final String NORMAL_EXCHANGE = "normal_exchange";
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        // 延迟消息或死信消息 设置TTL时间
        /*AMQP.BasicProperties properties =
                new AMQP.BasicProperties()
                        .builder().expiration("10000").build();*/
        for (int i = 1; i <11; i++) {
            String message = "info" + i;  //info1....info10
            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",null,message.getBytes());
//            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",properties,message.getBytes());
        }
    }
}
