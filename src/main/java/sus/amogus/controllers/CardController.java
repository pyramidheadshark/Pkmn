package sus.amogus.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sus.amogus.entities.CardEntity;
import sus.amogus.models.Student;
import sus.amogus.services.CardService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cards") // Базовый путь для всех запросов к карточкам
@RequiredArgsConstructor
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("") // Получение всех карточек
    public List<CardEntity> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/id/{id}") // Получение карточки по ID
    public ResponseEntity<CardEntity> getCardById(@PathVariable UUID id) { // @PathVariable извлекает значение из URL
        CardEntity card = cardService.getCardById(id);
        return card != null ? ResponseEntity.ok(card) : ResponseEntity.notFound().build(); // Возвращает 200 OK, если карта найдена, 404 Not Found иначе.
    }

    @PostMapping("") // Создание новой карточки
    public ResponseEntity<CardEntity> createCard(@RequestBody CardEntity card) { // @RequestBody десериализует JSON из тела запроса
        CardEntity savedCard = cardService.saveCard(card);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED); // Возвращает 201 Created с созданной картой
    }

    @PutMapping("/id/{id}") // Обновление карточки по ID
    public CardEntity updateCard(@PathVariable UUID id, @RequestBody CardEntity card) {
        return cardService.updateCard(id, card);
    }

    @DeleteMapping("/id/{id}") // Удаление карточки по ID
    public ResponseEntity<Void> deleteCard(@PathVariable UUID id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build(); // Возвращает 204 No Content
    }

    @GetMapping("/owner") // Получение карточек по владельцу
    public List<CardEntity> getCardsByOwner(@RequestBody Student ownerRequest) {
        return cardService.getCardsByOwner(ownerRequest.getFirstName(), ownerRequest.getSurName(), ownerRequest.getFatherName());
    }

    @GetMapping("/{name}") // Получение карточек по имени
    public List<CardEntity> getCardsByName(@PathVariable String name) {
        return cardService.getCardsByName(name);
    }
}