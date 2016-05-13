package pl.edu.agh.dziekanat.application.controller;

import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import pl.edu.agh.dziekanat.application.MainApp;
import pl.edu.agh.dziekanat.persistance.BusinessSessionFactory;
import pl.edu.agh.dziekanat.person.Person;
import pl.edu.agh.dziekanat.person.PersonUtil;

import java.io.IOException;
import java.net.URL;

import pl.edu.agh.dziekanat.model.Lesson;
import pl.edu.agh.dziekanat.person.Logged;

public class LoginController implements Initializable {

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Label lbStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        password.setOnKeyPressed((KeyEvent keyEvent) -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {

                loginAction(null);
            }
        });

    }

    @FXML
    private void loginAction(ActionEvent e) {
        // Trzeba wymyślij jak to zrobić w osobnym wątku
        lbStatus.setText("trwa logowanie ...");
        try {
            if (login()) {
                MainApp.showMenu();
                URL paneOneUrl = getClass().getResource("/pl/edu/agh/dziekanat/application/form/StartPane.fxml");
                AnchorPane paneOne = FXMLLoader.load(paneOneUrl);
                BorderPane border = MainApp.getRoot();
                border.setCenter(paneOne);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    private void cancelAction(ActionEvent e) {
        System.exit(0);

    }

    private boolean login() {

        boolean loginStatus = false;
        BusinessSessionFactory bsf = BusinessSessionFactory.getInstance();
        Session session = bsf.getSession().openSession();
        String hql = "FROM pl.edu.agh.dziekanat.person.Person where nickName =:nickName";
        Query query = session.createQuery(hql);
        query.setString("nickName", login.getText());
        List<Person> persons = query.list();

        if (!persons.isEmpty()) {
            Person person = persons.get(0);
            if (person != null && person.getPassword().equals(PersonUtil.getHashedPassword(password.getText()))) {
                loginStatus = true;
                Logged.getInstance().setLogged(person);
                 System.out.println(Logged.getInstance().getLogged());
            } else {
                lbStatus.setText("Niepoprawne hasło!");
            }
        } else {
            lbStatus.setText("Niepoprawny login!");
        }
        session.close();
        bsf.close();
        return loginStatus;

    }

    private void test() {
        BusinessSessionFactory bsf = BusinessSessionFactory.getInstance();
        Session session = bsf.getSession().openSession();
        String hql = "FROM pl.edu.agh.dziekanat.model.Lesson where prowadzacy =:prowadzacy";
        Query query = session.createQuery(hql);
        query.setString("prowadzacy", "12001");
        List<Lesson> lessons = query.list();
        session.close();
        bsf.close();

    }

}
