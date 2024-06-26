package com.atguigu.rabbitmq.utils;

/**
 * 睡眠工具类
 * @author LiHongFei
 * @since 2024/6/15
 */
public class SleepUtils {
    public static void sleep(int second){
        try {
            Thread.sleep(1000*second);
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }
}