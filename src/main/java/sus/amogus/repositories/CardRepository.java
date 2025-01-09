package sus.amogus.repositories;

import org.springframework.stereotype.Repository;
import sus.amogus.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, UUID> {
    /**
     *  Возвращает список всех CardEntity.
     *  @return List<CardEntity> список всех CardEntity.
     */
    List<CardEntity> findAll();
    /**
     *  Находит CardEntity по имени.
     *  @param name имя карты.
     *  @return Optional<CardEntity> обертка над найденным CardEntity.
     */
    Optional<CardEntity> findByName(String name);
    /**
     *   Находит CardEntity по ID владельца покемона.
     *  @param id ID владельца покемона.
     *  @return Optional<CardEntity> обертка над найденным CardEntity.
     */
    Optional<CardEntity> findByPokemonOwnerId(UUID id);
    /**
     *  Находит CardEntity по ID.
     *  @param id ID карты.
     *  @return Optional<CardEntity> обертка над найденным CardEntity.
     */
    Optional<CardEntity> findById(UUID id);
}