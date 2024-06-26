package com.atguigu.rabbitmq.three;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 消息在手动应答时是不丢失、放回同队列中重新消费
 * @author LiHongFei
 * @since 2024/6/15
 */
public class Task2 {

    // 队列名称
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        // 开启发布确认
        channel.confirmSelect();
        // 声明队列
        // 需要让QUEUE进行持久化
        boolean durable = true;
        channel.queueDeclare(TASK_QUEUE_NAME,durable,false,false,null);
        // 从控制台中输入信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            // 设置生产者发送消息为持久化消息 （要求保存到磁盘上） 保存在内存中
            channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者发出消息： " + message);
        }
    }
}
