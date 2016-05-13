package pl.edu.agh.dziekanat.application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import pl.edu.agh.dziekanat.email.EmailSender;
import pl.edu.agh.dziekanat.model.GroupStudent;
import pl.edu.agh.dziekanat.persistance.BusinessSessionFactory;
import pl.edu.agh.dziekanat.person.Person;
import pl.edu.agh.dziekanat.person.PersonType;
import pl.edu.agh.dziekanat.person.Student;

public class SendEmailController implements Initializable {

    @FXML
    private Button removeRecipientBtn;

    @FXML
    private Button addRecipientBtn;

    @FXML
    private Button sendEmailBtn;

    @FXML
    private ComboBox groupsCmb;

    @FXML
    private ListView availablePersonsLv;

    @FXML
    private ListView recipientsLv;

    @FXML
    private TextArea emailBodyTa;

    @FXML
    private TextField emailSubjectTf;

    private List<Person> wszyscyOdbiorcy;
    private List<Student> wszyscyStudenci;
    private List<GroupStudent> grupyStudenckie;

    private static String all = "Wszyscy";

    /**
     * Wyslij wiadomość email
     */
    @FXML
    private void sendEmailAction() {
        if (!this.isValid()) {
            JOptionPane.showMessageDialog(null, "Uzupełnij pola.", "Błąd", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<String> listaOdbiorcow = new ArrayList<>();

        // uzupełnić listę odbiorców
        for (Object o : this.recipientsLv.getItems()) {
            Person p = (Person) o;
            listaOdbiorcow.add(p.getEmail());
        }

        if (listaOdbiorcow.size() < 1) {
            JOptionPane.showMessageDialog(null, "Coś poszło nie tak ... Spytaj googla.", "Błąd", JOptionPane.WARNING_MESSAGE);
            return;
        }
        EmailSender.gmail(listaOdbiorcow, this.emailSubjectTf.getText(), this.emailBodyTa.getText());
        JOptionPane.showMessageDialog(null, "Wiadomość została wysłana.", "Sukces", JOptionPane.NO_OPTION);
    }

    private boolean isValid() {
        if (this.recipientsLv.getItems().size() < 1) {
            return false;
        }
        if (this.emailSubjectTf.getText().equals("")) {
            return false;
        }
        if (this.emailBodyTa.getText().equals("")) {
            return false;
        }
        return true;
    }

    /**
     * Filotrowanie grup
     */
    @FXML
    private void groupsAction() {

        String selected = this.groupsCmb.getSelectionModel().getSelectedItem().toString();
        System.out.println(selected);
        this.availablePersonsLv.getItems().clear();
        List<Person> przefiltrowane = new ArrayList<>();

        if (selected.equals(SendEmailController.all)) {
            this.availablePersonsLv.getItems().addAll(this.wszyscyOdbiorcy);
            return;
        }

        for (Person p : wszyscyOdbiorcy) {
            // jeśli grupa to enum
            if (p.getPersonTypeDesc().equals(selected)) {
                przefiltrowane.add(p);
                continue;
            }
            System.out.println(p.getPersonType());
            // jeśli grupa nie enum
            // zrobić;
        }

        for (Student s : this.wszyscyStudenci) {
            GroupStudent gs = s.getGroupStudent();
            String grupa = gs.getName() + " (" + gs.getGroupStudentType().getDescription() + ")";

            if (grupa.equals(selected)) {
                przefiltrowane.add(this.getPersonById(s.getPersonId()));
            }
        }
        this.availablePersonsLv.getItems().addAll(przefiltrowane);
    }

    private void filtrByGroupsLv(Object wzorzec) {

    }

    /**
     * Dodaj wszystkich odbiorców do listy odbiorców
     */
    @FXML
    private void addRecipientAction() {

        this.recipientsLv.getItems().addAll(this.availablePersonsLv.getItems());
    }

    /**
     * Usuń odbiorcę z listy odbiorców
     */
    @FXML
    private void removeRecipientAction() {
        this.recipientsLv.getItems().clear();
    }

    /**
     * dodanie pojedyńczego odbiorcy
     *
     * @param mouseEvent
     */
    @FXML
    private void availablePersonsLvClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            this.recipientsLv.getItems().add(this.availablePersonsLv.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * usunięcie pojedyńczego odbiorcy
     *
     * @param mouseEvent
     */
    @FXML
    private void recipientsLvClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            this.recipientsLv.getItems().remove(this.availablePersonsLv.getSelectionModel().getSelectedItem());
        }
    }

    private void setGroups() {
        this.groupsCmb.getItems().clear();
        for (PersonType pt : PersonType.values()) {
            this.groupsCmb.getItems().add(pt.getDescription());
        }
        this.groupsCmb.getItems().add(SendEmailController.all);
        for (GroupStudent gs : grupyStudenckie) {

            String grupa = gs.getName() + " (" + gs.getGroupStudentType().getDescription() + ")";
            if (this.groupsCmb.getItems().equals(grupa)) {
                continue;
            }
            this.groupsCmb.getItems().add(grupa);
        }
        this.groupsCmb.setValue(SendEmailController.all);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.availablePersonsLv.getItems().clear();
        // pobierz i ustaw wszystkich odbiorców
        BusinessSessionFactory bsf = BusinessSessionFactory.getInstance();
        Session session = bsf.getSession().openSession();

        this.wszyscyOdbiorcy = session.createCriteria(Person.class).list();
        this.grupyStudenckie = session.createCriteria(GroupStudent.class).list();
        this.wszyscyStudenci = session.createCriteria(Student.class).list();
        List<GroupStudent> gList = session.createCriteria(GroupStudent.class).list();
        session.close();
        bsf.close();

        this.availablePersonsLv.getItems().addAll(wszyscyOdbiorcy);

        this.setGroups();
    }

    private Person getPersonById(int id) {
        for (Person p : this.wszyscyOdbiorcy) {
            if (p.getPersonId() == id) {
                return p;
            }
        }
        return null;
    }
}
