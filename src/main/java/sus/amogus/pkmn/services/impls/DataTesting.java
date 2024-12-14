package sus.amogus.pkmn.services.impls;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import sus.amogus.pkmn.controllers.CardController;
import sus.amogus.pkmn.entities.CardEntity;
import sus.amogus.pkmn.services.StudentService;

import java.io.File;

@Component
@RequiredArgsConstructor
public class DataTesting {
    private final ObjectMapper objectMapper;

    private final CardController cardController;

    private final StudentService studentService;

    @PostConstruct
    @SneakyThrows
    public void init() {
        System.out.println("Post construct init");
        File jsonFile = new File("C:\\Users\\123\\Downloads\\final\\pkmn\\src\\main\\resources\\cards.json");
        CardEntity card = objectMapper.readValue(jsonFile, CardEntity.class);

        // Создание карточки на сервере
        // Установите уникальный идентификатор
        CardEntity createdCard = cardController.createCard(card).getBody();
        System.out.println("Created Card: " + createdCard);

        // Получение изображения карты по имени карты
        String cardName = createdCard.getName();
        String imageUrl = studentService.getCardImageByName(cardName);
        if (imageUrl != null) {
            System.out.println("Image URL: " + imageUrl);
        } else {
            System.out.println("Image not found for card: " + cardName);
        }
    }
}