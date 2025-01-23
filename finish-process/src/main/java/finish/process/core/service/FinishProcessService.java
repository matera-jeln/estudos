package finish.process.core.service;

import finish.process.core.dto.PactDTO;
import finish.process.core.enums.StatusProccess;
import finish.process.core.producer.FinishProcessProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FinishProcessService {

    @Value("${spring.kafka.topic.execution-process-topic}")
    private String executionProcessTopic;

    private final FinishProcessProducer finishProcessProducer;
    private int counter = 0;

    public FinishProcessService(FinishProcessProducer finishProcessProducer) {
        this.finishProcessProducer = finishProcessProducer;
    }

    public void notify(PactDTO command) {
        if(StatusProccess.SUCCESS.equals(command.getStatus())){
            counter++;
        }
        if (counter == command.getQuantityToBeProcessed()) {
            finishProcessProducer.sendEvent(executionProcessTopic, "Execução finalizada com sucesso! Quantidade de comandos executados: " + counter + " de " + command.getQuantityToBeProcessed());
            counter = 0;
        }
    }
}
