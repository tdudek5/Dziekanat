package pl.edu.agh.dziekanat.application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import pl.edu.agh.dziekanat.email.EmailSender;
import pl.edu.agh.dziekanat.person.Person;

public class SendEmailController_1 implements Initializable {

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

    private ObservableList<Person> allAvailableRecipients = FXCollections.observableArrayList();

    private final String emailPattern = ".*<(.*?)>";

    /**
     * Wyslij wiadomość email
     */
    @FXML
    private void sendEmailAction() {
        if (!this.goodEmail()) {
            JOptionPane.showMessageDialog(null, "Uzupełnij wymagane pola.", "Błąd", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<String> listaOdbiorcow = new ArrayList<>();

        for (Object email : this.recipientsLv.getItems()) {
            listaOdbiorcow.add(this.getEmail(email.toString()));
        }
        if (listaOdbiorcow.size() < 1) {
            JOptionPane.showMessageDialog(null, "Coś poszło nie tak ... Spytaj googla.", "Błąd", JOptionPane.WARNING_MESSAGE);
            return;
        }
        EmailSender.gmail(listaOdbiorcow, this.emailSubjectTf.getText(), this.emailBodyTa.getText());
        JOptionPane.showMessageDialog(null, "Wiadomość została wysłana.", "Sukces", JOptionPane.NO_OPTION);
    }

    private boolean goodEmail() {
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
     *
     */
    @FXML
    private void groupsAction() {
        Object selected = this.groupsCmb.getSelectionModel().getSelectedItem();

        switch (selected.toString()) {
            case "Wszyscy":
                this.filtrByGroupsLv(null);
                break;
            case "Administratorzy":
                this.filtrByGroupsLv("ADMINISTRATION");
                break;
            case "Prowadzący":
                this.filtrByGroupsLv("TEACHER");
                break;
            case "Studenci":
                this.filtrByGroupsLv("STUDENT");
                break;
            default:
            // Inna grupa

        }
    }

    private void filtrByGroupsLv(Object wzorzec) {
        if (wzorzec == null) {
            this.setGoupsAllAvailableRecipients();
            return;
        }

        this.availablePersonsLv.setItems(null);
        ObservableList<Person> availablePersons = FXCollections.observableArrayList();
        for (Person osoba : this.allAvailableRecipients) {
            if (osoba.getPersonType().equals(wzorzec.toString())) {
                availablePersons.add(osoba);
            }
        }
        this.availablePersonsLv.setItems(availablePersons);
    }

    /**
     * Dodaj odbiorcę do listy odbiorców
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
        this.recipientsLv.setItems(null);
    }

    /**
     * Dodaj element do listview
     *
     * @param item
     * @param listView
     */
    private void addItemToListView(Object item, ListView listView) {
        listView.getItems().add(listView.getItems().size(), item);
    }

    private void removeItemFromListView(int index, ListView listView) {
        listView.getItems().remove(index);
    }

    @FXML
    private void availablePersonsLvClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            if (!this.existsLv(this.availablePersonsLv.getSelectionModel().getSelectedItem(), this.recipientsLv)) {
                this.addItemToListView(this.availablePersonsLv.getSelectionModel().getSelectedItem(), this.recipientsLv);
            }
        }
    }

    @FXML
    private void recipientsLvClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            this.removeItemFromListView(this.recipientsLv.getSelectionModel().getSelectedIndex(), this.recipientsLv);
        }
    }

    private boolean existsLv(Object item, ListView listView) {
        return listView.getItems().indexOf(item) != -1;
    }

    /**
     * example: <br>from konieczny raval (koniecznyraval@gmail.com)<br>
     * return koniecznyraval@gmail.com
     *
     * @param string
     * @return
     */
    private String getEmail(String string) {
        return string.replaceAll(this.emailPattern, "$1");
    }

    private void setGroups() {
        this.groupsCmb.getItems().add("Wszyscy");
        this.groupsCmb.getItems().add("Studenci");
        this.groupsCmb.getItems().add("Prowadzący");
        this.groupsCmb.getItems().add("Administratorzy");
        this.groupsCmb.setValue("Wszyscy");
    }

    private void setGoupsAllAvailableRecipients() {
        availablePersonsLv.setItems(allAvailableRecipients);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setGroups();
        ObservableList<Person> persons = FXCollections.observableArrayList();
        persons.setAll(PersonController.getPersons());
        allAvailableRecipients = persons;
         
        this.setGoupsAllAvailableRecipients();
//        for (Object str : availablePersonsLv.getItems()) {
//
//        }

    }
}
