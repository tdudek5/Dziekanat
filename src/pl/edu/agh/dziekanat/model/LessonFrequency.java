package pl.edu.agh.dziekanat.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import pl.edu.agh.dziekanat.core.Module;

@Entity
@Table(name = "LessonFrequency")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class LessonFrequency implements Module {

    public static final int moduleID = 5;

    @Id
    private int lessonFrequencyId;
    /**
     * Jakiej lekcjo dotyczy obecność
     */
    private int lessonId;
    /**
     * Kogo dotyczy obecność
     */
    private int personId;
    /**
     * Czy jest obecny na zajęciach
     */
    private boolean present;

    @Override
    public int getModuleID() {
        return moduleID;
    }

    public int getLessonFrequencyId() {
        return lessonFrequencyId;
    }

    public void setLessonFrequencyId(int lessonFrequencyId) {
        this.lessonFrequencyId = lessonFrequencyId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public boolean isPresent() {
        return present;
    }

    public boolean getPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return "LessonFrequency{" + "lessonFrequencyId=" + lessonFrequencyId + ", lessonId=" + lessonId + ", personId=" + personId + ", present=" + present + '}';
    }

}
