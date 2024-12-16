package sus.amogus.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Класс для представления ответа от API Pokemon TCG, содержащего данные о картах.
 */
@Data
@RequiredArgsConstructor
public class PokemonCardResponse {

    /**
     * Список данных о картах.
     */
    @JsonProperty("data")
    private final List<CardData> data;

    /**
     * Класс для представления данных одной карты.
     */
    @Data
    public static class CardData {

        /**
         * Идентификатор карты.
         */
        private String id;

        /**
         * Название карты.
         */
        private String name;


        /**
         * URL изображения карты (устаревшее поле, использовать images).
         */
        @Deprecated
        private String imageUrl;

        /**
         *  Ссылки на изображения карты в разных размерах.
         */
        @JsonProperty("images")
        private Images images;

        /**
         * Класс для хранения URL изображений карты разных размеров.
         */
        @Data
        public static class Images {
            /**
             * URL маленького изображения.
             */
            private String small;
            /**
             * URL большого изображения.
             */
            private String large;
        }
    }
}