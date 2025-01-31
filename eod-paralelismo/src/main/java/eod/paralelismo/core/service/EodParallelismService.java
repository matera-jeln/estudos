package eod.paralelismo.core.service;

import eod.paralelismo.core.dto.ProcessCommandRecord;
import eod.paralelismo.core.enums.ProcessCommand;
import eod.paralelismo.core.enums.StatusProccess;
import eod.paralelismo.core.model.Pact;
import eod.paralelismo.core.producer.EodParallelismProducer;
import eod.paralelismo.core.repository.PactRepository;
import eod.paralelismo.core.util.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class EodParallelismService {

    private final PactRepository pactRepository;
    private final EodParallelismProducer producer;
    private final JsonUtil jsonUtil;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.execution-entity-topic}")
    private String executionEntityTopic;

    public EodParallelismService(PactRepository pactRepository,
                                 EodParallelismProducer producer,
                                 JsonUtil jsonUtil,
                                 KafkaTemplate<String, String> kafkaTemplate) {
        this.pactRepository = pactRepository;
        this.producer = producer;
        this.jsonUtil = jsonUtil;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional("transactionManager")
    public void sendEntityExecutionCommand(ProcessCommandRecord command) {
        if (command == null || !ProcessCommand.VALORIZA_PACTO.toString().equals(command.processName())) {
            return;
        }
        var pacts = getPacts();
        int totalPacts = pacts.size();
        pacts.forEach(pact -> {
            String key = pact.getId().toString();
            pact.setProcessCommand(ProcessCommand.VALORIZA_PACTO);
            pact.setStatus(StatusProccess.PROCESSING);
            pact.setQuantityToBeProcessed(totalPacts);
            if(pact.getId() == 10) {
                throw  new RuntimeException("erro ao processar pacto: " + pact.getId());
            }
            producer.sendEvent(executionEntityTopic, key, jsonUtil.toJson(pact));
        });
    }

    public List<Pact> getPacts() {
        return pactRepository.findAll();
    }

}
