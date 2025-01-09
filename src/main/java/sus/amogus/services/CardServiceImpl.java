package sus.amogus.services;

import com.fasterxml.jackson.databind.JsonNode;
import sus.amogus.dao.CardDao;
import sus.amogus.entities.CardEntity;
import sus.amogus.models.Card;
import sus.amogus.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardDao cardDao;
    private final RestTemplate restTemplate;

    /**
     *  Возвращает список всех карт.
     *  @return List<Card> список всех карт.
     */
    @Override
    public List<Card> getAllCards() {
        return cardDao.getAll().stream().map(Card::fromEntity).toList();
    }

    /**
     *  Возвращает карту по её имени.
     *  @param name имя карты.
     *  @return Card найденная карта.
     */
    @Override
    public Card getCardByName(String name) {
        return Card.fromEntity(cardDao.getByName(name));
    }

    /**
     *  Возвращает карту по владельцу покемона.
     *  @param student владелец покемона.
     *  @return Card найденная карта.
     */
    @Override
    public Card getCardByPokemonOwner(Student student) {
        return Card.fromEntity(cardDao.getByPokemonOwnerId(student));
    }

    /**
     *  Возвращает карту по её ID.
     *  @param id ID карты.
     *  @return Card найденная карта.
     */
    @Override
    public Card getCardById(UUID id) {
        return Card.fromEntity(cardDao.getById(id));
    }

    /**
     *  Сохраняет новую карту.
     *  @param card данные карты для сохранения.
     *  @return Card сохраненная карта.
     *  @throws IllegalArgumentException если карта с таким именем уже существует, или у карты нет владельца.
     */
    @Override
    public Card saveCard(Card card) {
        if (cardDao.cardExists(card)) {
            throw new IllegalArgumentException("Карта уже есть в базе данных");
        }
        if (card.getPokemonOwner() == null) {
            throw new IllegalArgumentException("У карты нет владельца");
        }
        return Card.fromEntity(cardDao.saveCard(CardEntity.toEntity(card)));
    }

    /**
     *  Получает URL изображения покемона с внешнего API.
     *  @param name имя покемона.
     *  @param number номер покемона.
     *  @return String URL изображения покемона или "Pokemon not found", если покемон не найден.
     */
    @Override
    public String getPokemonImage(String name, int number) {
        String url = "https://api.pokemontcg.io/v2/cards?q=name:\"" + name + "\" number:" + number;

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

        if (response.getBody() != null && response.getBody().has("data")) {
            JsonNode data = response.getBody().get("data").get(0);
            return data.path("images").path("large").asText();
        }
        return "Pokemon not found";
    }
}