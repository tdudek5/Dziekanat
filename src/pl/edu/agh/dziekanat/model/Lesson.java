package pl.edu.agh.dziekanat.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import pl.edu.agh.dziekanat.core.Module;

@Entity
@Table(name = "lesson")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Lesson implements Module {

    public static final int moduleID = 4;

    /**
     * Id lekcji
     */
    @Id
    private int lessonId;
    /**
     * Przedmiot
     */
    private int subject;
    /**
     * Opis lekcji
     */
    private String description;
    /**
     * Data lekcji
     */
    private String startDate;
    /**
     * Czas trwania
     */
    private String duration;
    /**
     * Sala lekcyjna
     */
    private String classroom;
    /**
     * Grupa której dotyczy
     */
    private int groupStudent;

    /**
     * Prowadzący id
     */
    private int prowadzacy;

    @Override
    public int getModuleID() {
        return moduleID;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getGroupStudent() {
        return groupStudent;
    }

    public void setGroupStudent(int groupStudent) {
        this.groupStudent = groupStudent;
    }

    public int getProwadzacy() {
        return prowadzacy;
    }

    public void setProwadzacy(int prowadzacy) {
        this.prowadzacy = prowadzacy;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.getDescription() + " [dnia " + this.getStartDate() + " (" + this.getDuration() + " min) w sali " + this.getClassroom()+"]";
    }

}
