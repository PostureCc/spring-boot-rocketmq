package com.xiaour.spring.boot.rocketmq.controller;

import com.xiaour.spring.boot.rocketmq.producer.Producer;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Xiaour
 * @Description: 这里是为了测试
 * @Date: 2018/8/9 15:16
 */
@Log4j2
@RestController
public class TestController {
    @Autowired
    private Producer producer;

    @RequestMapping("/push")
    public String pushMsg(String msg) {
        try {
//            List<String> lists = new ArrayList<>();
            long start = System.currentTimeMillis();
            for (int i = 0; i < 20000; i++) {
                producer.send("PushTopic", "push", UUID.randomUUID().toString());
            }
            //2019-08-21 11:43:05.662
            //2019-08-21 11:43:06.737
            log.info("send time:{}", System.currentTimeMillis() - start);
            return "success";
//            return lists.toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
}
