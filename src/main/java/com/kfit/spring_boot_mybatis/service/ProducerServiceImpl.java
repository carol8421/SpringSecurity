package com.kfit.spring_boot_mybatis.service;

import com.kfit.spring_boot_mybatis.service.ProducerService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by Administrator on 2017/5/3.
 */
@Service
public class ProducerServiceImpl implements ProducerService {

    @Resource(name="jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(Destination destination, final String msg) {
        System.out.println(Thread.currentThread().getName()+" 向队列"+destination.toString()+"发送消息--------->"+msg);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    @Override
    public void sendMessage(final String msg) {
        String destination = jmsTemplate.getDefaultDestinationName();
        System.out.println(Thread.currentThread().getName()+" 向队列"+destination+"发送消息-------->"+msg);
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
}

