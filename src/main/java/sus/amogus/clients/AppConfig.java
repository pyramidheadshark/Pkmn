package sus.amogus.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Конфигурационный класс для создания бина RestTemplate.
 */
@Configuration
public class AppConfig {

    /**
     * Создает и настраивает RestTemplate для выполнения HTTP-запросов.
     * @return настроенный RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
