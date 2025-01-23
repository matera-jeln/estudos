package finish.process.core.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


import java.util.concurrent.ExecutionException;

@Component
public class FinishProcessProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public FinishProcessProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String topic, String payload) {
        try {
            kafkaTemplate.send(topic, payload);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
