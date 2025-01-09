package sus.amogus.dao;

import sus.amogus.entities.CardEntity;
import sus.amogus.entities.StudentEntity;
import sus.amogus.models.Card;
import sus.amogus.models.Student;
import sus.amogus.repositories.CardRepository;
import sus.amogus.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CardDao {

    private final CardRepository cardentityrepository;
    private final StudentRepository studentEntityRepository;

    /**
     *  Возвращает список всех CardEntity.
     *  @return List<CardEntity> список всех CardEntity.
     */
    @SneakyThrows
    public List<CardEntity> getAll() {
        return cardentityrepository.findAll();
    }

    /**
     *  Возвращает CardEntity по имени.
     *  @param name имя карты.
     *  @return CardEntity найденный CardEntity.
     *  @throws IllegalArgumentException если CardEntity не найден.
     */
    @SneakyThrows
    public CardEntity getByName(String name) {
        return cardentityrepository.findByName(name).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));
    }

    /**
     *  Возвращает CardEntity по владельцу покемона.
     *  @param student владелец покемона.
     *  @return CardEntity найденный CardEntity.
     *   @throws IllegalArgumentException если CardEntity или StudentEntity не найден.
     */
    @SneakyThrows
    public CardEntity getByPokemonOwnerId(Student student) {
        StudentEntity studentEntity = studentEntityRepository.findBySurNameAndFirstNameAndFamilyNameAndGroup(
                student.getSurName(), student.getFirstName(), student.getFamilyName(), student.getGroup()).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));

        UUID id = studentEntity.getId();
        return cardentityrepository.findByPokemonOwnerId(id).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));
    }

    /**
     *  Возвращает CardEntity по ID.
     *  @param id ID карты.
     *  @return CardEntity найденный CardEntity.
     *  @throws IllegalArgumentException если CardEntity не найден.
     */
    @SneakyThrows
    public CardEntity getById(UUID id) {
        return cardentityrepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));
    }

    /**
     *  Сохраняет CardEntity.
     *  @param cardEntity CardEntity для сохранения.
     *  @return CardEntity сохраненный CardEntity.
     */
    @SneakyThrows
    public CardEntity saveCard(CardEntity cardEntity) {
        return cardentityrepository.save(cardEntity);
    }

    /**
     *  Проверяет, существует ли карта с указанным именем.
     *  @param card карта для проверки.
     *  @return boolean true, если карта существует, false в противном случае.
     */
    public boolean cardExists(Card card) {
        return cardentityrepository.findByName(card.getName()).isPresent();
    }
}