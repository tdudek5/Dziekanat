package pl.edu.agh.dziekanat.person;

import java.util.Arrays;
import java.util.List;

public enum PersonType {

	ADMINISTRATION("Administracja"), TEACHER("Prowadzący"), STUDENT("Student");

	private String description;

	private PersonType(String description) {
		this.description = description;
	}

	public String toString() {
		return name();
	}

	public String toMnemonic() {
		return name();
	}

	public String getDescription() {
		return description;
	}
	
	public static List<PersonType> toList() {
		return Arrays.asList(PersonType.values());
	}
	
	
    public static class Values {
        public static final String ADMINISTRATION = "ADMINISTRATION";
        public static final String TEACHER = "TEACHER";
        public static final String STUDENT = "STUDENT";
    } 
}
