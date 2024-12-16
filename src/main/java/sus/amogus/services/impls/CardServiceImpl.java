package sus.amogus.services.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sus.amogus.dao.CardDao;
import sus.amogus.entities.CardEntity;
import sus.amogus.services.CardService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public abstract class CardServiceImpl implements CardService {
    private final CardDao cardDao;

    @Override
    public List<CardEntity> getAllCards() {
        return cardDao.findAll();
    }

    @Override
    public CardEntity getCardById(UUID id) {
        return cardDao.findById(id).orElse(null);
    }

    @Override
    public CardEntity saveCard(CardEntity card) {
        // Валидация уникальности
        if (!Objects.isNull(card.getId()) && cardDao.findById(card.getId()).isPresent()) {
            throw new RuntimeException("Card with this ID already exists.");
        }
        return cardDao.save(card);
    }

    @Override
    public CardEntity updateCard(UUID id, CardEntity card) {
        if (cardDao.findById(id).isEmpty()) {
            throw new RuntimeException("Card not found.");
        }
        card.setId(id);
        return cardDao.save(card);
    }

    @Override
    public void deleteCard(UUID id) {
        cardDao.deleteById(id);
    }

    @Override
    public List<CardEntity> getCardsByOwner(String firstName, String surName, String familyName) {
        return cardDao.findCardsByOwner(firstName, surName, familyName);
    }

    @Override
    public List<CardEntity> getCardsByName(String name) {
        return cardDao.findCardsByName(name);
    }
}
