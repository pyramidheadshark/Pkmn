package sus.amogus.configs;

import sus.amogus.security.filters.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService jdbcUserDetailsManager;
    private final JwtFilter jwtFilter;

    /**
     *  Конфигурирует цепочку фильтров безопасности для обработки HTTP-запросов.
     *  @param httpSecurity объект HttpSecurity для настройки безопасности.
     *  @return SecurityFilterChain настроенная цепочка фильтров безопасности.
     *  @throws Exception в случае ошибок при настройке безопасности.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                customizer ->
                        customizer
                                .requestMatchers("/api/v1/cards/all").permitAll()
                                .requestMatchers("/api/v1/cards/owner").permitAll()
                                .requestMatchers("/api/v1/cards/name/{name}").permitAll()
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/api/v1/cards").hasRole("ADMIN")
                                .requestMatchers("/api/v1/students").hasRole("ADMIN")
                                .requestMatchers("/error**").permitAll()
                                .anyRequest().authenticated()
        );

        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.userDetailsService(jdbcUserDetailsManager);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}