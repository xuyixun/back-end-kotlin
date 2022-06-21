package com.xyx.punishment.rabbitmq

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class RabbitMqService(private val rabbitTemplate: RabbitTemplate) {
    fun punishmentBillFileWatermark(message: String) = rabbitTemplate.convertAndSend(RABBITMQ_EXCHANGE_PUNISHMENT_BILL_FILE_WATERMARK, RABBITMQ_KEY_COMMON, message)

    companion object {
        const val RABBITMQ_KEY_COMMON = "rabbit_common_key"
        const val RABBITMQ_EXCHANGE_PUNISHMENT_BILL_FILE_WATERMARK = "exchange_punishment_bill_file_watermark"
        const val RABBITMQ_QUEUE_PUNISHMENT_BILL_FILE_WATERMARK = "queue_punishment_bill_file_watermark"
    }
}