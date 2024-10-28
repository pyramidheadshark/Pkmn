package ru.mirea.pkmn.smirnovnd;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.stream.Collectors;

public class JsonPrinter {

    public static void printCardData(JsonNode json) {
        if (json != null && json.has("data") && json.get("data").isArray() && json.get("data").size() > 0) {
            JsonNode cardData = json.get("data").get(0);

            System.out.println("Card Data:");
            System.out.println("  Name: " + cardData.path("name").asText());
            System.out.println("  HP: " + cardData.path("hp").asInt());
            System.out.println("  Evolves From: " + cardData.path("evolvesFrom").asText(""));
            System.out.println("  Game Set: " + cardData.path("set").path("name").asText());
            System.out.println("  Card Number: " + cardData.path("number").asText());
            System.out.println("  Stage: " + cardData.path("subtypes").get(0).asText());
            System.out.println("  Retreat Cost: " + cardData.path("retreatCost").toString());

            JsonNode weaknesses = cardData.path("weaknesses");
            if (weaknesses.isArray() && weaknesses.size() > 0) {
                System.out.println("  Weakness Type: " + weaknesses.get(0).path("type").asText());
            }


            JsonNode resistances = cardData.path("resistances");
            if (resistances.isArray() && resistances.size() > 0) {
                System.out.println("  Resistance Type: " + resistances.get(0).path("type").asText());
            }

            JsonNode types = cardData.path("types");
            if (types.isArray() && types.size() > 0) {
                System.out.println("  Pokemon Type: " + types.get(0).asText());
            }

            System.out.println("  Regulation Mark: " + cardData.path("regulationMark").asText());

            // Вывод атак
            JsonNode attacks = cardData.path("attacks");
            if (attacks.isArray()) {
                System.out.println("\nAttacks:");
                for (JsonNode attack : attacks) {
                    System.out.println("  Name: " + attack.path("name").asText());
                    System.out.println("  Cost: " + attack.path("cost").toString());
                    System.out.println("  Damage: " + attack.path("damage").asText());
                    System.out.println("  Description: " + attack.path("text").asText());
                    System.out.println("  ------------------");
                }
            }

        } else {
            System.err.println("JSON data is invalid or empty.");
        }
    }
}
