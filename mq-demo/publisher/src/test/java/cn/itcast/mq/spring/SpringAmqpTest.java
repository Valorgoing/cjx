package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/5/8 16:59
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testSendMessage2SimpleQueue(){
        String queueName="simple.queue";
        String message="hello, spring amqp";
        rabbitTemplate.convertAndSend(queueName,message);
    }

    @Test
    public void testSendMessage2WorkQueue() throws InterruptedException{
        String queueName="simple.queue";
        String message="hello, message__";
        for (int i = 1; i <=50 ; i++) {
            rabbitTemplate.convertAndSend(queueName,message+i);
            Thread.sleep(20);
        }
    }

    @Test
    public void tendSendFanoutExchange(){
        //交换机名称
        String exchangeName="itcast.fanout";
        //消息
        String message="hello,every one!";
        //发送消息
        rabbitTemplate.convertAndSend(exchangeName,"",message);
    }

    @Test
    public void tendSendDirectExchange(){
        //交换机名称
        String exchangeName="itcast.direct";
        //消息
        String message="hello,yellow!";
        //发送消息
        rabbitTemplate.convertAndSend(exchangeName,"yellow",message);
    }

    @Test
    public void tendSendTopicExchange(){
        //交换机名称
        String exchangeName="itcast.topic";
        //消息
        String message="加油加油冲冲冲!";
        //发送消息
        rabbitTemplate.convertAndSend(exchangeName,"china.news",message);
    }


    @Test
    public void tendSendObjectQueue(){
        Map<String,Object> msg=new HashMap<>();
        msg.put("name","kk");
        msg.put("age",21);
        rabbitTemplate.convertAndSend("object.queue",msg);
    }
}
