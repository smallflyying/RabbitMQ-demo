package com.atguigu.rabbitmq.six;

import com.atguigu.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * @author LiHongFei
 * @since 2024/6/16
 */
public class DirectLogs {

    // 交换机的名称
    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            String bindKey = scanner.next();
            channel.basicPublish(EXCHANGE_NAME, bindKey, null, message.getBytes("UTF-8"));
            System.out.println("生产者发出消息: " + message);
        }
    }
}
