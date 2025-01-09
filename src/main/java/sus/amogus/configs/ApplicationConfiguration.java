package sus.amogus.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class ApplicationConfiguration {

    /**
     *  Конфигурирует и создает бин UserDetailsService, который использует JDBC для управления пользователями.
     *  @param dataSource Источник данных для подключения к базе данных.
     *  @return JdbcUserDetailsManager, который реализует UserDetailsService.
     */
    @Bean
    public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    /**
     *  Создает бин PasswordEncoder, использующий BCrypt для хеширования паролей.
     *  @return BCryptPasswordEncoder - кодировщик паролей.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *  Создает бин RestTemplate для выполнения HTTP-запросов.
     *  @return RestTemplate - клиент для работы с REST API.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}