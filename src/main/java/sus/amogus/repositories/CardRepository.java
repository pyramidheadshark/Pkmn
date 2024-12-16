package sus.amogus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sus.amogus.entities.CardEntity;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<CardEntity, UUID> { // Интерфейс репозитория для сущности CardEntity с UUID в качестве первичного ключа

    @Query( // JPQL запрос для поиска карт по владельцу (ФИО)
            """
SELECT card
FROM CardEntity as card
WHERE card.pokemonOwner.firstName = :firstName
AND card.pokemonOwner.surName = :surName
AND card.pokemonOwner.familyName = :familyName
"""
    )
    List<CardEntity> findByPokemonOwner(String firstName, String surName, String familyName); // Находит список карт по ФИО владельца

    List<CardEntity> findByName(String name); // Находит список карт по имени покемона
}