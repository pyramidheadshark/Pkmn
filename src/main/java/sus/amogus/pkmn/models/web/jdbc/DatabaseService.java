package sus.amogus.pkmn.models.web.jdbc;

import sus.amogus.pkmn.models.Card;
import sus.amogus.pkmn.models.Student;

public interface DatabaseService {
    Card getCardFromDatabase(String cardName);

    Student getStudentFromDatabase(String studentFullName);

    void saveCardToDatabase(Card card);

    void createPokemonOwner(Student owner);
}