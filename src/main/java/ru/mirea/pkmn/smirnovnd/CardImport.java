package ru.mirea.pkmn.smirnovnd;

import ru.mirea.pkmn.*;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class CardImport
{
    Card card = new Card();
    public Card readFromFile(String filename)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            card.setPokemonStage(PokemonStage.valueOf(reader.readLine().trim()));
            card.setName(reader.readLine().trim());
            card.setHp(Integer.parseInt(reader.readLine().trim()));
            card.setPokemonType(EnergyType.valueOf(reader.readLine().trim()));

            String lineEvolvesFrom = reader.readLine().trim();
            if (!lineEvolvesFrom.equals("-"))
            {
                CardImport cartImport2 = new CardImport();
                Card card2 = cartImport2.readFromFile("src/main/resources/" + lineEvolvesFrom + ".txt");
                card.setEvolvesFrom(card2);
            }
            else
                card.setEvolvesFrom(null);

            String[] skills = reader.readLine().trim().split(", ");
            List<AttackSkill> skillList = new ArrayList<>();
            for (String i : skills)
            {
                String[] meaningattack = i.split("/");
                AttackSkill attack = new AttackSkill (meaningattack[1], "",
                        meaningattack[0], Integer.parseInt(meaningattack[2]));
                skillList.add(attack);
            }
            card.setSkills(skillList);

            String weaknessTypeString = reader.readLine().trim();
            if (!weaknessTypeString.equals("-")) {
                card.setWeaknessType(EnergyType.valueOf(weaknessTypeString));
            }

            String resistanceTypeString = reader.readLine().trim();
            if (!resistanceTypeString.equals("-")) {
                card.setResistanceType(EnergyType.valueOf(resistanceTypeString));
            }

            card.setRetreatCost(reader.readLine().trim());
            card.setGameSet(reader.readLine().trim());
            card.setRegulationMark(reader.readLine().trim().charAt(0));

            String ownerLine = reader.readLine().trim();
            if (!ownerLine.equals("-")) {
                String[] ownerParts = ownerLine.split("/");
                Student studentOwner = new Student(ownerParts[0], ownerParts[1], ownerParts[2], ownerParts[3]);
                card.setPokemonOwner(studentOwner);
            } else {
                card.setPokemonOwner(null);
            }

            String number = reader.readLine().trim();
            card.setNumber(number);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return card;
    }

    public Card importFromFile(String fileName)
    {
        try
        {
            FileInputStream fileInput = new FileInputStream(fileName);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            card = (Card) objectInput.readObject();
            System.out.println("\u001b[38;5;201mД\u001b[38;5;208mе\u001b[38;5;212mс\u001b[38;5;215mе\u001b[38;5;218mр\u001b[38;5;221mи\u001b[38;5;224mз\u001b[38;5;227mа\u001b[38;5;230mц\u001b[38;5;201mи\u001b[38;5;208mя\u001b[38;5;212m \u001b[38;5;215mв\u001b[38;5;218mы\u001b[38;5;221mп\u001b[38;5;224mо\u001b[38;5;227mл\u001b[38;5;230mн\u001b[38;5;201mе\u001b[38;5;208mн\u001b[38;5;212mа");        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return card;
    }
}