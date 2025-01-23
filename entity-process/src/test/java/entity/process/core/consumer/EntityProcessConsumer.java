package entity.process.core.consumer;

import entity.process.core.dto.PactDTO;


import entity.process.core.service.EntityProcessService;
import entity.process.core.util.JsonUtil;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EntityProcessConsumer {

    private final JsonUtil jsonUtil;
    private final EntityProcessService entityProcessService;

    public EntityProcessConsumer(JsonUtil jsonUtil, EntityProcessService entityProcessService) {
        this.jsonUtil = jsonUtil;
        this.entityProcessService = entityProcessService;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.execution-entity-topic}"
    )
    public void consumerExecutionEntityTopic(String payload) throws InterruptedException {
        var command = jsonUtil.toObject(payload, PactDTO.class);
        entityProcessService.process(command);
    }
}