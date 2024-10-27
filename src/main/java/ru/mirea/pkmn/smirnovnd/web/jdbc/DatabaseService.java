package ru.mirea.pkmn.smirnovnd.web.jdbc;

import ru.mirea.pkmn.*;

public interface DatabaseService {

    Card getCardFromDatabase(String cardName);

    Student getStudentFromDatabase(String studentName);

    void saveCardToDatabase(Card card);

    void createPokemonOwner(String owner);
}