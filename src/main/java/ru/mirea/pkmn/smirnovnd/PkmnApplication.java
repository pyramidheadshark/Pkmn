package ru.mirea.pkmn.smirnovnd;

import com.fasterxml.jackson.databind.JsonNode;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.smirnovnd.web.http.PkmnHttpClient;

import java.io.IOException;
import java.util.stream.Collectors;

public class PkmnApplication
{
    public static void main(String[] args) throws IOException {
        /*
        CardImport cardImport = new CardImport();
        Card myCard = cardImport.readFromFile("src/main/resources/Blipbug.txt");
        System.out.printf("\u001b[38;5;186m\nНачало задачи:\u001b[38;5;0m\n");
        //System.out.printf(myCard.toString());

        CardExport.ExportToFile(myCard);
        myCard = cardImport.importFromFile("Dottler.crd");
        System.out.printf(myCard.toString());
        */

        PkmnHttpClient pkmnHttpClient = new PkmnHttpClient();

        JsonNode card = pkmnHttpClient.getPokemonCard("Pikachu V", 86);
        System.out.println(card.toPrettyString());

        System.out.println(card.findValues("attacks")
                .stream()
                .map(JsonNode::toPrettyString)
                .collect(Collectors.toSet()));
    }
}