/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.dziekanat.application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import pl.edu.agh.dziekanat.model.Lesson;
import pl.edu.agh.dziekanat.model.LessonFrequency;
import pl.edu.agh.dziekanat.persistance.BusinessSessionFactory;
import pl.edu.agh.dziekanat.person.Logged;
import pl.edu.agh.dziekanat.person.Person;
import pl.edu.agh.dziekanat.person.Student;
import jfx.messagebox.MessageBox;

public class LessonFrequencesController implements Initializable {

    private List<Lesson> lessons = new ArrayList<>();
    private List<LessonFrequency> lessonFrequences = new ArrayList<>();

    private Person prowadzacy;

    @FXML
    private Button saveBtn;
    @FXML
    private ComboBox selectLessonCb;
    @FXML
    private ListView absentLv;
    @FXML
    private ListView presentLv;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!Logged.getInstance().isValid()) {
            System.out.println(Logged.getInstance().getLogged());
            throw new EmptyStackException();
        }
        prowadzacy = Logged.getInstance().getLogged();
        this.getMyLessons();
        this.selectLessonCb.getItems().addAll(this.lessons);
    }

    private void getMyLessons() {
        BusinessSessionFactory bsf = BusinessSessionFactory.getInstance();
        Session session = bsf.getSession().openSession();
        String hql = "FROM pl.edu.agh.dziekanat.model.Lesson where prowadzacy =:prowadzacy";
        Query query = session.createQuery(hql);
        query.setString("prowadzacy", String.valueOf(this.prowadzacy.getPersonId()));
        this.setLessons(query.list());
        session.close();
        bsf.close();

//        for (Lesson lesson : lessons) {
//            System.out.println(lesson.getClassroom());
//        }
    }

    private Lesson getLessonById(int id) {
        for (Lesson lesson : this.getLessons()) {
            if (id == lesson.getLessonId()) {
                return lesson;
            }
        }
        return null;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Person getProwadzacy() {
        return prowadzacy;
    }

    public void setProwadzacy(Person prowadzacy) {
        this.prowadzacy = prowadzacy;
    }

    @FXML
    public void presentLvClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            this.absentLv.getItems().add(this.presentLv.getSelectionModel().getSelectedItem());
            this.presentLv.getItems().remove(this.presentLv.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    public void absentLvClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            ObservableList<Object> toMove;

            this.presentLv.getItems().add(this.absentLv.getSelectionModel().getSelectedItem());
            this.absentLv.getItems().remove(this.absentLv.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    public void setPresentAction() {
        if (!absentLv.getItems().isEmpty()) {
            this.presentLv.getItems().addAll(this.absentLv.getItems());
            this.absentLv.getItems().clear();

            this.printMyLv();
        } else {
            System.out.println("absentLv jest pusty");
        }
    }

    @FXML
    public void setAbsentAction() {
        // jeśli nie jest pusty
        if (!presentLv.getItems().isEmpty()) {
            this.absentLv.getItems().addAll(this.presentLv.getItems());
            this.presentLv.getItems().clear();

            this.printMyLv();
        } else {
            System.out.println("presentLv jest pusty");
        }
    }

    private void printMyLv() {
        System.out.println("Wydruk nieobecnych");
        this.printLvItems(absentLv);
        System.out.println("Wydruk obecnych");
        this.printLvItems(presentLv);

    }

    private void printLvItems(ListView lv) {
        List<Object> l = lv.getItems();
        for (Object o : l) {
            System.out.println(o);
        }
    }

    @FXML
    public void saveAction() {
        if (lessonFrequences.isEmpty()) {
            System.out.println("Pusta lista frekwencji");
            return;
        }
        // OTWIERANIE POLACZENIA
        BusinessSessionFactory bsf = BusinessSessionFactory.getInstance();
        Session session = bsf.getSession().openSession();
        // POŁĄCZENIE OTWARTE

        for (LessonFrequency lf : this.lessonFrequences) {
            lf.setPresent(false);
            System.out.println(lf);

        }
        session.beginTransaction();
        for (Object o : this.presentLv.getItems()) {
            Student s = (Student) o;
            for (LessonFrequency lf : this.lessonFrequences) {
                System.out.println(lf);
                if (lf.getPersonId() == s.getPersonId()) {
                    lf.setPresent(true);
                }
                session.update(lf);
            }
        }
        session.getTransaction().commit();
        // POŁĄCZENIE ZAMKNIĘTE
        session.close();
        bsf.close();
        JOptionPane.showMessageDialog(null, "Zaktualizowano listę obecności.", "Zapisano", JOptionPane.INFORMATION_MESSAGE);
       
    }

    @FXML
    public void selectLessonAction() {

        Object selected = this.selectLessonCb.getSelectionModel().getSelectedItem();
        Lesson lesson = (Lesson) selected;

        BusinessSessionFactory bsf = BusinessSessionFactory.getInstance();
        Session session = bsf.getSession().openSession();

        String hql1 = "FROM pl.edu.agh.dziekanat.person.Student where groupStudent =:groupStudent";
        Query query1 = session.createQuery(hql1);
        query1.setString("groupStudent", String.valueOf(lesson.getGroupStudent()));
        List<Student> students = query1.list();

        if (!students.isEmpty()) {
            // continue;
            String hql2 = "FROM pl.edu.agh.dziekanat.model.LessonFrequency where lessonId =:lesson";
            Query query2 = session.createQuery(hql2);
            query2.setString("lesson", String.valueOf(lesson.getLessonId()));
            this.lessonFrequences.clear();
            this.lessonFrequences.addAll(query2.list());

            if (this.lessonFrequences.isEmpty()) {
                // ustaw każdemu studentowi nieobecność
                session.beginTransaction();

                for (Student student : students) {
                    LessonFrequency lessonFrequency = new LessonFrequency();
                    lessonFrequency.setLessonId(lesson.getLessonId());
                    lessonFrequency.setPersonId(student.getPersonId());
                    lessonFrequency.setPresent(false);

                    session.save(lessonFrequency);
                    this.lessonFrequences.add(lessonFrequency);
                }
                session.getTransaction().commit();
            }
            // poustawiaj studentów w tabelach
            this.presentLv.getItems().clear();
            this.absentLv.getItems().clear();

            for (LessonFrequency lf : this.lessonFrequences) {
                int index = this.getStudentIndex(students, lf.getPersonId());
                Student student = students.get(index);
                if (lf.getPresent()) {
                    this.presentLv.getItems().add(student);
                    System.out.println("Dodałem do presentLv " + (Student) student);
                } else {
                    System.out.println("Dodałem do absentLv " + (Student) student);
                    this.absentLv.getItems().add(student);
                }

            }
        }

        session.close();
        bsf.close();

    }

    private int getStudentIndex(List<Student> students, int id) {
        int index = 0;
        for (Student student : students) {
            if (id == student.getPersonId()) {
                return index;
            }
            index++;
        }
        return 0;
    }

}
