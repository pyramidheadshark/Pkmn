package sus.amogus.pkmn.models.web.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class PkmnHttpClient {
    Retrofit client;
    PokemonTcgAPI tcgAPI;

    private static final String BASE_URL = "https://api.pokemontcg.io";

    public PkmnHttpClient(){
        client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(new JsonMapper()))
                .build();
        tcgAPI = client.create(PokemonTcgAPI.class);
    }
    public JsonNode getPokemonCard(String name, String number) throws IOException {
        String requestQuery = "name:\""+name+"\"" + " " + "number:"+number;
        Response<JsonNode> response = tcgAPI.getPokemon(requestQuery).execute();
        JsonNode responseBody = response.body();
        if (responseBody == null) {
            throw new IOException("Response body is null");
        }
        return responseBody;
    }
}