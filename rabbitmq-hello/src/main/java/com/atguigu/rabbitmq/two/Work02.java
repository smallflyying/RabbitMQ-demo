package com.atguigu.rabbitmq.two;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 这是一个工程线程（相当于之前的消费者)
 * @author LiHongFei
 * @since 2024/6/15
 */
public class Work02 {

    // 队列的名称
    public static final String QUEUE_NAME = "hello";

    // 消息的接收
    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();

        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到的消息: " + new String(message.getBody()));
        };

        // 消息接收被取消时 执行下面的内容
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag + "消息者取消消费接口回调逻辑");
        };

        //消息的接收
        /**
         * 消费者消费队列
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答true 代表的自动应答 false 代表手动应答
         * 3.消费者未成功消费的回调
         * 4.消费者取消消费的回调
         */
        System.out.println("C2等待接收消息......");
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
