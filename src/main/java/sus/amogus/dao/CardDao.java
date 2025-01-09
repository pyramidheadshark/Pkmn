package sus.amogus.dao;

import sus.amogus.entities.CardEntity;
import sus.amogus.entities.StudentEntity;
import sus.amogus.models.Card;
import sus.amogus.models.Student;
import sus.amogus.repos.CardEntityRepository;
import sus.amogus.repos.StudentEntityRepository;
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
    @Autowired
    private final CardEntityRepository cardentityrepository;

    @Autowired
    private final StudentEntityRepository studentEntityRepository;

    @SneakyThrows
    public List<CardEntity> getAll() {
        return cardentityrepository.findAll();
    }

    @SneakyThrows
    public CardEntity getByName(String name) {
        return cardentityrepository.findByName(name).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));
    }

    @SneakyThrows
    public CardEntity getByPokemonOwnerId(Student student) {
        StudentEntity studentEntity = studentEntityRepository.findBySurNameAndFirstNameAndFamilyNameAndGroup(
                student.getSurName(), student.getFirstName(), student.getFamilyName(), student.getGroup()).orElseThrow(
                        () -> new IllegalArgumentException("Not Found"));

        UUID id = studentEntity.getId();
        return cardentityrepository.findByPokemonOwnerId(id).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));
    }

    @SneakyThrows
    public CardEntity getById(UUID id) {
        return cardentityrepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not Found"));
    }

    @SneakyThrows
    public CardEntity saveCard(CardEntity cardEntity) {
        return cardentityrepository.save(cardEntity);
    }

    public boolean cardExists(Card card) {
        return cardentityrepository.findByName(card.getName()).isPresent();
    }
}
