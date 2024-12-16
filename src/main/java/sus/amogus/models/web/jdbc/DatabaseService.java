package sus.amogus.models.web.jdbc;

import sus.amogus.models.Card;
import sus.amogus.models.Student;

public interface DatabaseService {
    Card getCardFromDatabase(String cardName);

    Student getStudentFromDatabase(String studentFullName);

    void saveCardToDatabase(Card card);

    void createPokemonOwner(Student owner);
}