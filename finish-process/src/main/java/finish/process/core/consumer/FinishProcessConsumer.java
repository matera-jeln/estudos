package finish.process.core.consumer;

import finish.process.core.dto.PactDTO;
import finish.process.core.service.FinishProcessService;
import finish.process.core.util.JsonUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class FinishProcessConsumer {
    private final FinishProcessService finishProcessService;
    private final JsonUtil jsonUtil;

    public FinishProcessConsumer(FinishProcessService finishProcessService, JsonUtil jsonUtil) {
        this.finishProcessService = finishProcessService;
        this.jsonUtil = jsonUtil;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.execution-entity-topic}"
    )
    public void consumerExecutionEntityTopic(String payload) throws InterruptedException {
        var command = jsonUtil.toObject(payload, PactDTO.class);
        finishProcessService.notify(command);
    }
}
