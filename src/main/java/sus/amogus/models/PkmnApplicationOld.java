package sus.amogus.models;

import java.io.FileWriter;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import sus.amogus.models.web.processes.CardImport;
import sus.amogus.models.web.http.PkmnHttpClient;
import sus.amogus.models.web.jdbc.DatabaseServiceImpl;

import java.sql.SQLException;

public class PkmnApplicationOld
{
    static PkmnHttpClient pkmnHttpClient = new PkmnHttpClient();
    public static void main(String[] args) throws IOException, SQLException {
        System.out.print("\u001b[38;5;40m\nCard Import Prikol in process...\u001b[38;5;15m\n\n");

        CardImport cardImport = new CardImport();
        Card myCard = cardImport.readFromFile("src/main/resources/my_card.txt");

        myCard = CardImport.setDescriptionsFromAPI(myCard, pkmnHttpClient);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        JsonNode card = pkmnHttpClient.getPokemonCard(myCard.getName(), myCard.getNumber());
        JsonElement jsonElement = gson.toJsonTree(card);
        String prettyJson = gson.toJson(jsonElement);
        System.out.println(prettyJson);

        try (FileWriter writer = new FileWriter("src/main/resources/latest.json")) {
            writer.write(gson.toJson(card));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n");

        System.out.print("\u001b[38;5;40m\nDB Prikol in process...\u001b[38;5;15m\n");


        DatabaseServiceImpl db = new DatabaseServiceImpl();
        db.saveCardToDatabase(myCard);
    }
}