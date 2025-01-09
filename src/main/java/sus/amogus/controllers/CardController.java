package sus.amogus.controllers;

import sus.amogus.models.Card;
import sus.amogus.models.Student;
import sus.amogus.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    /**
     *  Возвращает список всех карт.
     *  @return List<Card> список всех карт.
     */
    @GetMapping("/all")
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    /**
     *  Возвращает карту по её имени.
     *  @param name имя карты.
     *  @return Card найденная карта.
     */
    @GetMapping("/name/{name}")
    public Card getCardByName(@PathVariable String name) {
        return cardService.getCardByName(name);
    }

    /**
     *  Возвращает карту по владельцу покемона.
     *  @param student владелец покемона.
     *  @return Card найденная карта.
     */
    @GetMapping("/owner")
    public Card getCardByPokemonOwner(@RequestBody Student student){
        return cardService.getCardByPokemonOwner(student);
    }

    /**
     *  Возвращает карту по её ID.
     *  @param id ID карты.
     *  @return Card найденная карта.
     */
    @GetMapping("/id/{id}")
    public Card getCardById(@PathVariable UUID id) {
        return cardService.getCardById(id);
    }

    /**
     *  Сохраняет новую карту.
     *  @param card данные карты для сохранения.
     *  @return Card сохраненная карта.
     */
    @PostMapping("")
    public Card saveCard(@RequestBody Card card) {
        return cardService.saveCard(card);
    }

    /**
     *  Возвращает изображение покемона по его имени и номеру.
     *  @param name имя покемона.
     *  @param number номер покемона.
     *  @return String URL изображения покемона.
     */
    @GetMapping("/image")
    public String getPokemonImage(@RequestParam String name, @RequestParam int number) {
        return cardService.getPokemonImage(name, number);
    }
}