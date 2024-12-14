package sus.amogus.pkmn.models.web.processes;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonPrinter {

    public static void printCardData(JsonNode json) {
        if (json != null && json.has("data") && json.get("data").isArray() && !json.get("data").isEmpty()) {
            JsonNode cardData = json.get("data").get(0);

            System.out.println("===============================================");
            System.out.println("Карточка покемона:");
            System.out.println("===============================================");
            System.out.println("  Имя: " + cardData.path("name").asText());
            System.out.println("  HP: " + cardData.path("hp").asInt());
            System.out.println("  Эволюционирует из: " + cardData.path("evolvesFrom").asText(""));
            System.out.println("  Игра: " + cardData.path("set").path("name").asText());
            System.out.println("  Номер карты: " + cardData.path("number").asText());
            System.out.println("  Стадия: " + cardData.path("subtypes").get(0).asText());
            System.out.println("  Стоимость отступления: " + cardData.path("retreatCost").toString());

            JsonNode weaknesses = cardData.path("weaknesses");
            if (weaknesses.isArray() && !weaknesses.isEmpty()) {
                System.out.println("  Слабость: " + weaknesses.get(0).path("type").asText());
            }

            JsonNode resistances = cardData.path("resistances");
            if (resistances.isArray() && !resistances.isEmpty()) {
                System.out.println("  Сопротивление: " + resistances.get(0).path("type").asText());
            }

            JsonNode types = cardData.path("types");
            if (types.isArray() && !types.isEmpty()) {
                System.out.println("  Тип покемона: " + types.get(0).asText());
            }

            System.out.println("  Марка регуляции: " + cardData.path("regulationMark").asText());

            // Вывод атак
            JsonNode attacks = cardData.path("attacks");
            if (attacks.isArray()) {
                System.out.println("\nАтаки:");
                for (JsonNode attack : attacks) {
                    System.out.println("  Имя: " + attack.path("name").asText());
                    System.out.println("  Стоимость: " + attack.path("cost").toString());
                    System.out.println("  Урон: " + attack.path("damage").asText());
                    System.out.println("  Описание: " + attack.path("text").asText());
                    System.out.println("  ------------------");
                }
            }

            System.out.println("===============================================");
        } else {
            System.err.println("JSON data is invalid or empty.");
        }
    }
}
