package ru.mirea.pkmn.smirnovnd.web.jdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mirea.pkmn.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

public class DatabaseServiceImpl implements DatabaseService {
    private final Connection connection;
    private final Properties databaseProperties;

    public DatabaseServiceImpl() throws SQLException, IOException {
        databaseProperties = new Properties();
        databaseProperties.load(new FileInputStream("src/main/resources/database.properties"));

        connection = DriverManager.getConnection(
                databaseProperties.getProperty("database.url"),
                databaseProperties.getProperty("database.user"),
                databaseProperties.getProperty("database.password")
        );
        System.out.println("Connection is "+(connection.isValid(0) ? "up" : "down"));
    }

    @Override
    public Card getCardFromDatabase(String cardName) {
        // Реализовать получение данных о карте из БД
        return null;
    }

    @Override
    public Student getStudentFromDatabase(String studentName) {
        // Реализовать получение данных о студенте из БД
        return null;
    }

    @Override
    public void saveCardToDatabase(Card card, UUID student_id) throws JsonProcessingException {
        UUID pokemon_id = UUID.randomUUID();
        UUID evolves_from_id = checkEvolvesFrom(card);

        ObjectMapper mapper = new ObjectMapper();

        String jsonSkills = mapper.writeValueAsString(card.getSkills());

        String query = "INSERT INTO card (" +
                "id, name, hp, evolves_from, " +
                "game_set, card_number, pokemon_owner, stage, " +
                "retreat_cost, weakness_type, resistance_type, " +
                "attack_skills, pokemon_type, regulation_mark" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, pokemon_id);
            statement.setString(2, card.getName());
            statement.setInt(3, card.getHp());
            statement.setObject(4, evolves_from_id);
            statement.setString(5, card.getGameSet());
            //statement.setObject(6, card.getNumber(), Types.SMALLINT);
            statement.setString(6, card.getNumber());
            statement.setObject(7, student_id, Types.OTHER);
            statement.setString(8, card.getPokemonStage().toString());
            statement.setString(9, card.getRetreatCost());
            statement.setString(10, card.getWeaknessType() != null ? card.getWeaknessType().toString() : null);
            statement.setString(11, card.getResistanceType() != null ? card.getResistanceType().toString() : null);
            statement.setObject(12, jsonSkills, Types.OTHER);
            statement.setString(13, card.getPokemonType().toString());
            statement.setObject(14, card.getRegulationMark(), Types.CHAR);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public UUID checkEvolvesFrom(Card card) {
        UUID evolves_from_id = null;
        if (card.getEvolvesFrom() != null) {
            String evolvesFromName = card.getEvolvesFrom().getName();
            String query = "SELECT id FROM card WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, evolvesFromName);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    evolves_from_id = (UUID) resultSet.getObject(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            evolves_from_id = null;
        }
        return evolves_from_id;
    }

    @Override
    public void createPokemonOwner(String firstName, String surName, String familyName, String group, UUID student_id) {
        String query = "INSERT INTO student (" +
                "id, familyName, firstName, patronicName, \"group\"" +
                ") VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, student_id); // Id
            statement.setString(2, familyName);
            statement.setString(3, firstName);
            statement.setString(4, surName);
            statement.setString(5, group);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
