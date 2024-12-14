package sus.amogus.pkmn.models.web.processes;

import com.fasterxml.jackson.databind.JsonNode;
import sus.amogus.pkmn.models.Card;
import sus.amogus.pkmn.models.AttackSkill;

import java.util.List;

public class JsonCardSkillUpdater {

    public static void updateSkillDescriptions(Card card, JsonNode json) {
        if (card == null || json == null) {
            return; // Или выбросить исключение, если это не ожидаемое поведение
        }

        if (json.has("data") && json.get("data").isArray() && json.get("data").size() > 0) {
            JsonNode cardData = json.get("data").get(0);
            JsonNode attacks = cardData.path("attacks");

            if (attacks.isArray()) {
                List<AttackSkill> existingSkills = card.getSkills();

                for (int i = 0; i < attacks.size() && i < existingSkills.size(); i++) {
                    JsonNode attack = attacks.get(i);
                    AttackSkill existingSkill = existingSkills.get(i);

                    String description = attack.path("text").asText();
                    existingSkill.setDescription(description);


                }
            }
        }
    }
}
