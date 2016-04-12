package pl.edu.agh.dziekanat.application.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pl.edu.agh.dziekanat.person.Person;

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
    private ListView allPersonsLv;

    @FXML
    private ListView recipientsLv;

    @FXML
    private TextArea emailBodyTa;

    @FXML
    private TextField emailSubjectTf;

//    private final String emailPattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    private final String emailPattern = ".*<(.*?)>";

    /**
     * Wyslij wiadomość email
     */
    @FXML
    private void sendEmailAction() {
        List<String> listaOdbiorcow;
        ObservableList<String> recipientsList;
        recipientsList = this.recipientsLv.getItems();
        listaOdbiorcow = recipientsList;

    }

    /**
     *
     */
    @FXML
    private void groupsAction() {

    }

    /**
     * Dodaj odbiorcę do listy odbiorców
     */
    @FXML
    private void addRecipientAction() {

    }

    /**
     * Usuń odbiorcę z listy odbiorców
     */
    @FXML
    private void removeRecipientAction() {

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

    @FXML
    private void allPersonsLvClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            if (!this.existsLv(this.allPersonsLv.getSelectionModel().getSelectedItem(), this.recipientsLv)) {
                this.addItemToListView(this.allPersonsLv.getSelectionModel().getSelectedItem(), this.recipientsLv);
            }
        }
    }

    private boolean existsLv(Object item, ListView listView) {
        return listView.getItems().indexOf(item) != -1;
    }

    /**
     * example: from konieczny raval <koniecznyraval@gmail.com>
     * return koniecznyraval@gmail.com
     *
     * @param string
     * @return
     */
    private String getEmail(String string) {
        return string.toString().replaceAll(this.emailPattern, "$1");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Person> persons = FXCollections.observableArrayList();

        persons = PersonController.getPersons();
        System.out.println("rozmiar listy: " + persons.size());
        allPersonsLv.setItems(persons);

        for (Object str : allPersonsLv.getItems()) {

        }

    }
}
