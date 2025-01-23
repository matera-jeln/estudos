package entity.process.core.service;

import entity.process.core.dto.PactDTO;
import entity.process.core.enums.StatusProcess;
import entity.process.core.producer.EntityProcessProducer;
import entity.process.core.util.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EntityProcessService {

    @Value("${spring.kafka.topic.execution-entity-topic}")
    private String executionEntityTopic;

    private final ServletWebServerApplicationContext webServerAppContext;
    private final EntityProcessProducer entityProcessProducer;
    private final JsonUtil jsonUtil;

    public EntityProcessService(ServletWebServerApplicationContext webServerAppContext,
                                EntityProcessProducer entityProcessProducer,
                                JsonUtil jsonUtil) {
        this.webServerAppContext = webServerAppContext;
        this.entityProcessProducer = entityProcessProducer;
        this.jsonUtil = jsonUtil;
    }

    public void process(PactDTO pact) throws InterruptedException {
        if(StatusProcess.PROCESSING.equals(pact.getStatus())) {
            int port = webServerAppContext.getWebServer().getPort();
            String key = pact.getId().toString();
            Thread.sleep(2000);
            System.out.println("Fim do processamento fake do pacto: " + pact.getId() + " rodando na server port: " + port);
            pact.setStatus(StatusProcess.SUCCESS);
            entityProcessProducer.sendEvent(executionEntityTopic, key, jsonUtil.toJson(pact));
        }
    }

}
