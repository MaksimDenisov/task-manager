package ru.denisovmaksim.taskmanager.scheduler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.denisovmaksim.taskmanager.scheduler.client.TaskManagerClient;

@Configuration
public class TaskManagerClientConfig {

    @Bean
    TaskManagerClient taskManagerClient(
            @Value("${app.task-manager-url}") String baseUrl,
            @Value("${app.internal-token}") String internalToken
    ) {
        RestClient restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("X-Internal-Token", internalToken)
                .build();

        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory
                        .builderFor(RestClientAdapter.create(restClient))
                        .build();

        return factory.createClient(TaskManagerClient.class);
    }
}
