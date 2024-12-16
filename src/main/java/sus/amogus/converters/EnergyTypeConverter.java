package sus.amogus.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import sus.amogus.models.EnergyType;

@Converter // Аннотация, указывающая, что этот класс является конвертером для JPA
public class EnergyTypeConverter implements AttributeConverter<EnergyType, String> {

    @Override
    public String convertToDatabaseColumn(EnergyType attribute) { // Конвертирует EnergyType в строку для хранения в базе данных
        return attribute == null ? null : attribute.name();
    }

    @Override
    public EnergyType convertToEntityAttribute(String dbData) { // Конвертирует строку из базы данных в EnergyType
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return EnergyType.valueOf(dbData.toUpperCase()); // Преобразование строки в enum с учетом регистра
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}