package ru.mirea.pkmn;

import java.io.Serializable;

public class Student implements Serializable {
    public static final long serialVersionUID = 1L;

    private String firstName;
    private String surName;
    private String familyName;
    private String group;

    public Student() {}

    public Student(String firstName, String surName, String familyName, String group) {
        this.firstName = firstName;
        this.surName = surName;
        this.familyName = familyName;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getSurName() {
        return surName;
    }
    public String getFamilyName() {
        return familyName;
    }
    public String getGroup() {
        return group;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    public void setGroup(String group) {
        this.group = group;
    }

    public String getFullName() {
        return firstName + " " + surName + " " + familyName;
    }

    @Override
    public String toString() {
        return firstName + "/" + surName + "/" + familyName + "/" + group;
    }
}