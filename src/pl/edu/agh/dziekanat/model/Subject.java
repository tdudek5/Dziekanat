package pl.edu.agh.dziekanat.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import pl.edu.agh.dziekanat.core.Module;

@Entity
@Table(name = "subject")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class Subject implements Module {

    @Id
    private int subjectId;
    private String name;
    private String shortcut;
    public static final int moduleID = 3;

    @Override
    public int getModuleID() {
        return moduleID;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

}
