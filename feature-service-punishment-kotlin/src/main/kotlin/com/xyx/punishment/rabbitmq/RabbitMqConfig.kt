package com.xyx.punishment.rabbitmq

import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfig {
    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        //        rabbitTemplate.setChannelTransacted(true);//开启事务
        //消息是否成功发送到Exchange
        rabbitTemplate.setConfirmCallback { _, ack, cause ->
            if (ack) {
                //消息发送成功
                println("rabbitmq发送成功")
            } else {
                //消息发送失败
                println("rabbitmq发送失败，原因为：$cause")
            }
        }
        return rabbitTemplate
    }
}