package entity.process.core.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EntityProcessProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public EntityProcessProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String topic, String key, String payload) {
        try {
            kafkaTemplate.send(topic,key, payload);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sendEvent(String topic, int partitionPosition, String key, String payload) {
        try {
            kafkaTemplate.send(topic, partitionPosition, key, payload);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}
