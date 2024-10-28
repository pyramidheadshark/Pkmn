package ru.mirea.pkmn.smirnovnd.web.jdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.mirea.pkmn.*;

import java.util.UUID;

public interface DatabaseService {

    Card getCardFromDatabase(String cardName);

    Student getStudentFromDatabase(String studentName);

    void saveCardToDatabase(Card card, UUID student_id) throws JsonProcessingException;

    void createPokemonOwner(String firstName, String surName, String familyName, String group, UUID id);
}