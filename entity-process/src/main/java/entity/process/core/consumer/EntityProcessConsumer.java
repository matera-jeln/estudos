package entity.process.core.consumer;

import entity.process.core.dto.PactDTO;

import entity.process.core.service.EntityProcessService;
import entity.process.core.util.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EntityProcessConsumer {

    private final JsonUtil jsonUtil;
    private final EntityProcessService entityProcessService;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.execution-entity-topic}"
    )
    public void consumerExecutionEntityTopic(String payload) throws InterruptedException {
        var command = jsonUtil.toObject(payload, PactDTO.class);
        entityProcessService.process(command);
    }
}