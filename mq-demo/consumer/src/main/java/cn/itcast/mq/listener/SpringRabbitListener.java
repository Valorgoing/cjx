package cn.itcast.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/5/8 17:11
 */
@Component
public class SpringRabbitListener {
//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueue(String msg){
//        System.out.println("消费者接收到simple.queue的消息：【"+msg+"】");
//    }
    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1接收到消息：【"+msg+"】"+ LocalTime.now());
        Thread.sleep(20);
    }
    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2......接收到消息：【"+msg+"】"+ LocalTime.now());
        Thread.sleep(200);
    }
}













