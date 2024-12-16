package sus.amogus.models.web.http;

import com.fasterxml.jackson.databind.JsonNode;
import retrofit2.*;
import retrofit2.http.*;

public interface PokemonTcgAPI {
    @GET("/v2/cards")
    Call<JsonNode> getPokemon(@Query(value = "q", encoded = true) String query);
}
