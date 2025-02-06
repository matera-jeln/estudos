package eod.paralelismo.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.execution-process-topic}")
    private String executionProcessTopic;

    @Value("${spring.kafka.topic.execution-entity-topic}")
    private String executionEntityTopic;

    private NewTopic buildTopic(String name, Integer partitions, Integer replics) {
        return TopicBuilder.name(name)
                .partitions(partitions)
                .replicas(replics)
                .build();
    }

    @Bean
    public NewTopic startExecutionProcessTopic() {
        return buildTopic(executionProcessTopic, 1, 1);
    }

    @Bean
    public NewTopic startExecutionEntityTopic() {
        return buildTopic(executionEntityTopic, 3, 1);
    }
}
