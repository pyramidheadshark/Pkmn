package sus.amogus.pkmn.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestClient {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private final RestTemplate restTemplate;

    public PokemonCardResponse getCardByName(String cardName) {
        String url = "https://api.pokemontcg.io/v2/cards?q=name:" + cardName;
        return restTemplate.getForObject(url, PokemonCardResponse.class);
    }
}
