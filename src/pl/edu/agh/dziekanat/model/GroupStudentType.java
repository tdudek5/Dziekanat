package pl.edu.agh.dziekanat.model;

import java.util.Arrays;
import java.util.List;

public enum GroupStudentType {

    LAB("Labolatoryjna"), DEAN("Dziekańska");

    private String description;

    private GroupStudentType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name();
    }

    public String toMnemonic() {
        return name();
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionEx() {
        return description + " " + name();
    }

    public static List<GroupStudentType> toList() {
        return Arrays.asList(GroupStudentType.values());
    }

}
