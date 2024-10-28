package ru.mirea.pkmn.smirnovnd.web.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import retrofit2.*;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class PkmnHttpClient {

    Retrofit client;
    PokemonTcgAPI tcgAPI;
    public PkmnHttpClient() {
        client = new Retrofit.Builder()
                .baseUrl("https://api.pokemontcg.io")
                .addConverterFactory(JacksonConverterFactory.create(new JsonMapper()))
                .build();

        tcgAPI = client.create(PokemonTcgAPI.class);
    }

    public JsonNode getPokemonCard(String name, String id) throws IOException {
        String requestQuery = "name:\"" + name + "\"" + " number:" + id;

        Response<JsonNode> response = tcgAPI.getPokemon(requestQuery).execute();

        if (response.isSuccessful()) { //Проверка успешного выполнения запроса
            return response.body();
        } else {
            //Обработка ошибок
            System.err.println("Ошибка при запросе к API: " + response.code() + " " + response.message());
            return null; //Или выбросить исключение
        }
    }
}
