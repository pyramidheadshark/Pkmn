package sus.amogus.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * Клиент для взаимодействия с API Pokemon TCG.
 */
@Component
@RequiredArgsConstructor
public class RestClient {
    private final RestTemplate restTemplate;

    /**
     * Получает данные о карте Pokemon по ее имени.
     * @param cardName имя карты
     * @return ответ от API, содержащий данные о карте, или null, если карта не найдена
     */
    public PokemonCardResponse getCardByName(String cardName) {
        String url = "https://api.pokemontcg.io/v2/cards?q=name:" + cardName;
        return restTemplate.getForObject(url, PokemonCardResponse.class);
    }
}