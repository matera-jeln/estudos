package eod.paralelismo.core.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EodParallelismProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public EodParallelismProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String topic, String key, String payload) {
        kafkaTemplate.send(topic,key, payload);
    }

    public void sendEvent(String topic, String payload) {
        try {
            kafkaTemplate.send(topic, payload);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sendEvent(String topic, Integer partitionPosition, String key, String payload) {
        try {
            kafkaTemplate.send(topic, partitionPosition, key, payload);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
