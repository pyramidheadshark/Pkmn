package ru.mirea.pkmn.smirnovnd;

import com.fasterxml.jackson.databind.JsonNode;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.Student;
import ru.mirea.pkmn.smirnovnd.web.jdbc.*;
import ru.mirea.pkmn.smirnovnd.web.http.PkmnHttpClient;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class PkmnApplication
{
    private static final DatabaseService databaseService;

    static {
        try {
            databaseService = new DatabaseServiceImpl();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        CardImport cardImport = new CardImport();
        File directory = new File("src/main/resources/");
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
        assert files != null;
        for (File file : files) {
            Card myCard = cardImport.readFromFile(file.getAbsolutePath());
            CardExport.ExportToFile(myCard);
        }

        Card blipbug = cardImport.importFromFile("Blipbug.crd");
        Card dottler = cardImport.importFromFile("Dottler.crd");
        Card orbeetle = cardImport.importFromFile("Orbeetle.crd");

        ArrayList<Card> predeterminedCards = new ArrayList<>();
        predeterminedCards.add(blipbug);
        predeterminedCards.add(dottler);
        predeterminedCards.add(orbeetle);

        Student student = predeterminedCards.getFirst().getPokemonOwner();
        UUID student_id = UUID.randomUUID();

        String firstName = student.getFirstName();
        String surName = student.getSurName();
        String familyName = student.getFamilyName();
        String group = student.getGroup();

        databaseService.createPokemonOwner(firstName, surName, familyName, group, student_id);

        PkmnHttpClient pkmnHttpClient = new PkmnHttpClient();
        for (Card card : predeterminedCards) {
            JsonNode jsonNode = pkmnHttpClient.getPokemonCard(card.getName(), card.getNumber());
            Card updated_card = null;
            if (jsonNode != null) {
                JsonPrinter.printCardData(jsonNode);
                JsonCardSkillUpdater.updateSkillDescriptions(card, jsonNode);
                CardExport.ExportToFile(card);
                updated_card = cardImport.importFromFile(card.getName() + ".crd");
                System.out.println(updated_card.getSkills());
            }
            databaseService.saveCardToDatabase(updated_card, student_id);
        }
    }
}