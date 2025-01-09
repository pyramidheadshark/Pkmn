package sus.amogus.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import sus.amogus.models.AttackSkill;

import java.io.IOException;
import java.util.Optional;

public class SkillDeserializer extends JsonDeserializer<AttackSkill> {
    /**
     *  Десериализует JSON в объект AttackSkill.
     *  @param p JsonParser, используемый для чтения JSON.
     *  @param ctxt DeserializationContext, предоставляющий контекст для десериализации.
     *  @return AttackSkill объект, созданный из JSON.
     *  @throws IOException если возникает ошибка ввода/вывода.
     *  @throws JacksonException если возникает ошибка при парсинге JSON.
     */
    @Override
    public AttackSkill deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {

        JsonNode skillNode = ctxt.readTree(p);

        AttackSkill skill = new AttackSkill();
        skill.setName(skillNode.get("name").asText(""));

        Optional.ofNullable(skillNode.get("description"))
                .ifPresent(
                        jsonNode -> skill.setDescription(jsonNode.asText("")));

        skill.setCost(skillNode.get("cost").asText(""));
        skill.setDamage(skillNode.get("damage").asInt(0));

        return skill;
    }
}