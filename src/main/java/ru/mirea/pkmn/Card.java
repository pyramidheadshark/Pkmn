package ru.mirea.pkmn;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class Card implements Serializable {
    public static final long serialVersionUID = 1L;

    private PokemonStage pokemonStage;
    private String name;
    private int hp;
    private EnergyType pokemonType;
    private Card evolvesFrom;
    private List<AttackSkill> skills;
    private EnergyType weaknessType;
    private EnergyType resistanceType;
    private String retreatCost;
    private String gameSet;
    private char regulationMark;
    private Student pokemonOwner;

    public Card() {}

    public Card(String name) {
        this.name = name;
    }

    public Card(PokemonStage pokemonStage, String name, int hp, EnergyType pokemonType,
                Card evolvesFrom, List<AttackSkill> skills, EnergyType weaknessType,
                EnergyType resistanceType, String retreatCost, String gameSet,
                char regulationMark, Student pokemonOwner) {
        this.pokemonStage = pokemonStage;
        this.name = name;
        this.hp = hp;
        this.pokemonType = pokemonType;
        this.evolvesFrom = evolvesFrom;
        this.skills = skills;
        this.weaknessType = weaknessType;
        this.resistanceType = resistanceType;
        this.retreatCost = retreatCost;
        this.gameSet = gameSet;
        this.regulationMark = regulationMark;
        this.pokemonOwner = pokemonOwner;
    }

    public PokemonStage getPokemonStage() {
        return pokemonStage;
    }
    public String getName() {
        return name;
    }
    public int getHp() {
        return hp;
    }
    public EnergyType getPokemonType() {
        return pokemonType;
    }
    public Card getEvolvesFrom() {
        return evolvesFrom;
    }
    public List<AttackSkill> getSkills() {
        return skills;
    }
    public EnergyType getWeaknessType() {
        return weaknessType;
    }
    public EnergyType getResistanceType() {
        return resistanceType;
    }
    public String getRetreatCost() {
        return retreatCost;
    }
    public String getGameSet() {
        return gameSet;
    }
    public char getRegulationMark() {
        return regulationMark;
    }
    public Student getPokemonOwner() {
        return pokemonOwner;
    }

    public void setPokemonStage(PokemonStage pokemonStage) {
        this.pokemonStage = pokemonStage;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setPokemonType(EnergyType pokemonType) {
        this.pokemonType = pokemonType;
    }
    public void setEvolvesFrom(Card evolvesFrom) {
        this.evolvesFrom = evolvesFrom;
    }
    public void setSkills(List<AttackSkill> skills) {
        this.skills = skills;
    }
    public void setWeaknessType(EnergyType weaknessType) {
        this.weaknessType = weaknessType;
    }
    public void setResistanceType(EnergyType resistanceType) {
        this.resistanceType = resistanceType;
    }
    public void setRetreatCost(String retreatCost) {
        this.retreatCost = retreatCost;
    }
    public void setGameSet(String gameSet) {
        this.gameSet = gameSet;
    }
    public void setRegulationMark(char regulationMark) {
        this.regulationMark = regulationMark;
    }
    public void setPokemonOwner(Student pokemonOwner) {
        this.pokemonOwner = pokemonOwner;
    }

    @Override
    public String toString() {
        String evolvesFromStr = evolvesFrom != null ? evolvesFrom.toString() : "";
        String weaknessTypeStr = weaknessType != null ? weaknessType.toString() : "нету";
        String resistanceTypeStr = resistanceType != null ? resistanceType.toString() : "нету";
        String skillsStr = skills.stream()
                .map(skill -> String.format("%s; %s; %d", skill.getName(), skill.getCost(), skill.getDamage()))
                .collect(Collectors.joining("; "));

        return String.format("""
    [%s]
    Стадия: %s
    ХП: %d
    Тип: %s
    %s
    Атаки: %s
    Тип слабости: %s
    Тип сопротивления: %s
    Цена побега: %s
    Название сета: %s
    Отметка: %s
    Владелец: %s
    """,
                name,
                pokemonStage,
                hp,
                pokemonType,
                evolvesFromStr.isEmpty() ? "" : "Из кого эволюционирует: " + evolvesFromStr,
                skillsStr,
                weaknessTypeStr,
                resistanceTypeStr,
                retreatCost,
                gameSet,
                regulationMark,
                pokemonOwner);
    }
}
