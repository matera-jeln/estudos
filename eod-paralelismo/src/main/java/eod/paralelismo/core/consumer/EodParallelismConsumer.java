package eod.paralelismo.core.consumer;

import eod.paralelismo.core.dto.ProcessCommandRecord;
import eod.paralelismo.core.service.EodParallelismService;
import eod.paralelismo.core.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class EodParallelismConsumer {

    private final EodParallelismService parallelismService;
    private final JsonUtil jsonUtil;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.execution-process-topic}"
    )
    public void consumerExecutionProcessTopic(String payload) {
        var command = jsonUtil.toObject(payload, ProcessCommandRecord.class);
        try {
            parallelismService.sendEntityExecutionCommand(command);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

}
