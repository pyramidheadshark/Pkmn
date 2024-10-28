package ru.mirea.pkmn.smirnovnd;

import com.fasterxml.jackson.databind.JsonNode;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.smirnovnd.web.http.PkmnHttpClient;

import java.io.IOException;
import java.util.stream.Collectors;

public class PkmnApplication
{
    public static void main(String[] args) throws IOException {
        CardImport cardImport = new CardImport();

        Card myCard1 = cardImport.readFromFile("src/main/resources/Blipbug.txt");
        CardExport.ExportToFile(myCard1);
        Card myCard2 = cardImport.readFromFile("src/main/resources/Dottler.txt");
        CardExport.ExportToFile(myCard2);
        Card myCard3 = cardImport.readFromFile("src/main/resources/Orbeetle.txt");
        CardExport.ExportToFile(myCard3);

        Card blipbug = cardImport.importFromFile("Blipbug.crd");
        Card dottler = cardImport.importFromFile("Dottler.crd");
        Card orbeetle = cardImport.importFromFile("Orbeetle.crd");

        PkmnHttpClient pkmnHttpClient = new PkmnHttpClient();

        JsonNode jsonNode = pkmnHttpClient.getPokemonCard(dottler.getName(), dottler.getNumber());
        if (jsonNode != null) {
            JsonPrinter.printCardData(jsonNode);
            JsonCardSkillUpdater.updateSkillDescriptions(dottler, jsonNode);
            CardExport.ExportToFile(dottler);
            dottler = cardImport.importFromFile("Dottler.crd");
            System.out.println(dottler.getSkills());
        }
    }
}