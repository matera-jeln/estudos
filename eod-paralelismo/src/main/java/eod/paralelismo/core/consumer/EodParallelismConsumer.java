package eod.paralelismo.core.consumer;

import eod.paralelismo.core.dto.ProcessCommandRecord;
import eod.paralelismo.core.service.EodParallelismService;
import eod.paralelismo.core.util.JsonUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EodParallelismConsumer {

    private final EodParallelismService parallelismService;
    private final JsonUtil jsonUtil;

    public EodParallelismConsumer(EodParallelismService parallelismService, JsonUtil jsonUtil) {
        this.parallelismService = parallelismService;
        this.jsonUtil = jsonUtil;
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.execution-process-topic}"
    )
    public void consumerExecutionProcessTopic(String payload) {
        var command = jsonUtil.toObject(payload, ProcessCommandRecord.class);
        try {
            parallelismService.sendEntityExecutionCommand(command);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
