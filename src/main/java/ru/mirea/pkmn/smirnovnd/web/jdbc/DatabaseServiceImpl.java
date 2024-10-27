package ru.mirea.pkmn.smirnovnd.web.jdbc;

import ru.mirea.pkmn.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseServiceImpl implements DatabaseService {

    private final Connection connection;

    private final Properties databaseProperties;

    public DatabaseServiceImpl() throws SQLException, IOException {

        // Загружаем файл database.properties

        databaseProperties = new Properties();
        databaseProperties.load(new FileInputStream("/src/main/resources/database.properties"));

        // Подключаемся к базе данных

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
    public void saveCardToDatabase(Card card) {
        // Реализовать отправку данных карты в БД
    }

    @Override
    public void createPokemonOwner(String owner) {
        // Реализовать добавление студента - владельца карты в БД
    }
}
