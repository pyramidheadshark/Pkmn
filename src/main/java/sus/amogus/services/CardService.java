package sus.amogus.services;

import sus.amogus.models.Card;
import sus.amogus.models.Student;

import java.util.List;
import java.util.UUID;

public interface CardService {
    /**
     *  Возвращает список всех карт.
     *  @return List<Card> список всех карт.
     */
    List<Card> getAllCards();
    /**
     *  Возвращает карту по имени.
     *  @param name имя карты.
     *  @return Card найденная карта.
     */
    Card getCardByName(String name);
    /**
     *  Возвращает карту по владельцу покемона.
     *  @param student владелец покемона.
     *  @return Card найденная карта.
     */
    Card getCardByPokemonOwner(Student student);
    /**
     *  Возвращает карту по ID.
     *  @param id ID карты.
     *  @return Card найденная карта.
     */
    Card getCardById(UUID id);
    /**
     *  Сохраняет карту.
     *  @param card карта для сохранения.
     *  @return Card сохраненная карта.
     */
    Card saveCard(Card card);
    /**
     *  Возвращает URL изображения покемона.
     *  @param name имя покемона.
     *  @param number номер покемона.
     *  @return String URL изображения покемона.
     */
    String getPokemonImage(String name, int number);
}