package pl.edu.agh.dziekanat.application.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import pl.edu.agh.dziekanat.persistance.BusinessSessionFactory;
import pl.edu.agh.dziekanat.person.Administration;
import pl.edu.agh.dziekanat.person.Person;
import pl.edu.agh.dziekanat.person.PersonType;
import pl.edu.agh.dziekanat.person.PersonUtil;
import pl.edu.agh.dziekanat.person.Student;
import pl.edu.agh.dziekanat.person.Teacher;

public class PersonController implements Initializable {

	@FXML
	private TableView<Person> tblPerson;
	@FXML
	private TableColumn<Person, String> colImie;
	@FXML
	private TableColumn<Person, String> colNazwisko;
	@FXML
	private TableColumn<Person, String> colEmail;
	@FXML
	private TableColumn<Person, String> colTyp;
	@FXML
	private TableColumn<Person, String> colLogin;

	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtLogin;
	@FXML
	private TextField txtEmail;
	@FXML
	private ComboBox<PersonType> cmbPersonType;
	@FXML
	private Button btSavePerson;
	@FXML
	private Button btClearPerson;

	@FXML
	private Button btSaveStudent;
	@FXML
	private Label lbImie;

	@FXML
	private Pane paneStudent;
	@FXML
	private TextField txtAlbumNumber;

	private Person person;

	private PersonUtil personUtil;

	private ObservableList<Person> personList = FXCollections.observableArrayList();

	public void initialize(URL url, ResourceBundle rb) {

		personList = getPerson();
		colImie.setEditable(true);
		colImie.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));

		colImie.setCellFactory(TextFieldTableCell.forTableColumn());
		colImie.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {

			@Override
			public void handle(CellEditEvent<Person, String> t) {
				Person person = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
				person.setFirstName(t.getNewValue());
				commitPerson(person);
			}
		});

		colNazwisko.setEditable(true);
		colNazwisko.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		colNazwisko.setCellFactory(TextFieldTableCell.forTableColumn());
		colNazwisko.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> t) {
				Person person = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
				person.setLastName(t.getNewValue());
				commitPerson(person);

			}
		});

		colEmail.setEditable(true);
		colEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
		colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		colEmail.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> t) {
				Person person = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
				person.setEmail(t.getNewValue());
				commitPerson(person);

			}
		});

		colLogin.setEditable(true);
		colLogin.setCellValueFactory(new PropertyValueFactory<Person, String>("nickName"));
		colLogin.setCellFactory(TextFieldTableCell.forTableColumn());
		colEmail.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> t) {
				Person person = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
				person.setNickName(t.getNewValue());
				commitPerson(person);
			}
		});
		
		colTyp.setCellValueFactory(new PropertyValueFactory<Person, String>("personTypeDesc"));

		tblPerson.setItems(personList);

		tblPerson.setEditable(true);

		addListeners();
		setupControlls();

	}

	public ObservableList<Person> getPerson() {

		BusinessSessionFactory bsf = BusinessSessionFactory.getInstance();
		Session session = bsf.getSession().openSession();

		List<Person> pList = session.createCriteria(Person.class).list();
		for (Person person : pList) {
			personList.add(person);
		}

		session.close();
		bsf.close();
		return personList;
	}

	@FXML
	private void searchPerson(ActionEvent e) {
		// setupColumns();
	}

	@FXML
	private void savePerson(ActionEvent e) {
		BusinessSessionFactory bsf = BusinessSessionFactory.getInstance();
		Session session = bsf.getSession().openSession();

		if (cmbPersonType.getValue().equals(PersonType.ADMINISTRATION)) {
			// Administration administration =
			// (Administration)session.load(Administration.class,
			// person.getPersonId());
			Administration administration = new Administration();

			System.out.println(administration.getFirstName());
			System.out.println(administration.getLastName());

			administration.setFirstName(txtFirstName.getText());
			administration.setLastName(txtLastName.getText());
			administration.setNickName(txtLogin.getText());
			administration.setEmail(txtEmail.getText());
			session.beginTransaction();
			session.save(administration);
			personList.add(administration);
		}

		if (cmbPersonType.getValue().equals(PersonType.STUDENT)) {

			// System.out.println(person.getPersonId());
			// Student student = (Student) session.load(Student.class,
			// person.getPersonId());
			// Student student = (Student)person;
			Student student = new Student();

			System.out.println(student.getPersonId());
			System.out.println(student.getFirstName());
			System.out.println(student.getLastName());
			

			session.beginTransaction();
			student.setFirstName(txtFirstName.getText());
			student.setLastName(txtLastName.getText());
			student.setNickName(txtLogin.getText());
			student.setEmail(txtEmail.getText());
			student.setAlbumNumber(txtAlbumNumber.getText());

			System.out.println(txtFirstName.getText());
			System.out.println(student.getFirstName());

			session.save(student);
			personList.add(student);
		}

		if (cmbPersonType.getValue().equals(PersonType.TEACHER)) {

			// Teacher teacher = (Teacher)session.load(Teacher.class,
			// person.getPersonId());
			Teacher teacher = new Teacher();
			System.out.println(teacher.getFirstName());
			System.out.println(teacher.getLastName());

			teacher.setFirstName(txtFirstName.getText());
			teacher.setLastName(txtLastName.getText());
			teacher.setNickName(txtLogin.getText());
			teacher.setEmail(txtEmail.getText());
			session.beginTransaction();
			session.save(teacher);
			personList.add(teacher);
		}

		session.getTransaction().commit();

		tblPerson.refresh();
		doClearPersonTextField(null);
		btSavePerson.setVisible(false);
		btSaveStudent.setVisible(true);
		btSaveStudent.setDisable(true);
		btClearPerson.setDisable(true);
		bsf.close();
	}

	private void addListeners() {
		tblPerson.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
				// Check whether item is selected and set value of selected item
				// to Label
				if (tblPerson.getSelectionModel().getSelectedItem() != null) {

					TableViewSelectionModel<Person> selectionModel = tblPerson.getSelectionModel();
					// tableIndex = selectionModel.se
					person = selectionModel.getSelectedItem();
					btSaveStudent.setDisable(true);
					panePassword.setDisable(true);
					cleanPasswordPane();
					cleanStudentPane();

					if (PersonType.valueOf(person.getPersonType()).equals(PersonType.STUDENT)) {
						Student student = (Student) person;
						txtAlbumNumber.setText(student.getAlbumNumber().toString());
						paneStudent.setDisable(false);
						// btSaveStudent.setDisable(true);
					} else {
						paneStudent.setDisable(true);
						// btSaveStudent.setDisable(true);

					}
					btSaveStudent.setDisable(true);
				}
			}
		});
	}

	private void cleanStudentPane() {
		txtAlbumNumber.setText("");
		lbStudentSaveStatus.setText("");
	}

	private void clearPersonPane() {
		txtFirstName.setText("");
		txtLastName.setText("");
		txtEmail.setText("");
		txtLogin.setText("");
		cmbPersonType.setValue(null);
		txtAlbumNumber.setText("");
	}

	private void cleanPasswordPane() {
		pfChangePasswordFirst.setText("");
		pfChangePasswordSecond.setText("");
		lbPasswordStatus.setText("");
	}

	private void setupControlls() {
		List<PersonType> personType = PersonType.toList();

		ObservableList<PersonType> options = FXCollections.observableArrayList(PersonType.values());
		cmbPersonType.getItems().setAll(options);

		cmbPersonType.setConverter(new StringConverter<PersonType>() {
			@Override
			public String toString(PersonType personType) {
				if (personType == null) {
					return null;
				} else {
					return personType.getDescription();
				}
			}

			@Override
			public PersonType fromString(String id) {
				return null;
			}
		});
		
		cmbPersonType.valueProperty().addListener(new ChangeListener<PersonType>() {

			@Override
			public void changed(ObservableValue<? extends PersonType> observable, PersonType oldValue,
					PersonType newValue) {
				if ((newValue != null && newValue.equals(PersonType.STUDENT)) || (observable != null && observable.getValue().equals(PersonType.STUDENT))){
					paneStudent.setDisable(false);
				} else {
					paneStudent.setDisable(true);
				}
			}
		});

		txtFirstName.textProperty().addListener((observable, oldValue, newValue) -> {
			boolean status = oldValue.equals(newValue);
			btSavePerson.setDisable(status);
			btSavePerson.setVisible(!status);
			btClearPerson.setDisable(status);
			btSaveStudent.setVisible(status);
			if (oldValue.isEmpty())
				cleanStudentPane();
				paneStudent.setDisable(true);

		});

		txtLastName.textProperty().addListener((observable, oldValue, newValue) -> {
			boolean status = oldValue.equals(newValue);
			btSavePerson.setDisable(status);
			btSavePerson.setVisible(!status);
			btClearPerson.setDisable(status);
			btSaveStudent.setVisible(status);

		});

		// txtImie.textProperty().addListener((observable, oldValue, newValue)
		// -> {
		// boolean status = oldValue.equals(newValue);
		// btSavePerson.setDisable(status);
		// btSavePerson.setVisible(!status);
		// btSaveStudent.setVisible(status);
		// });
		//
		// txtImie.textProperty().addListener((observable, oldValue, newValue)
		// -> {
		// boolean status = oldValue.equals(newValue);
		// btSavePerson.setDisable(status);
		// btSavePerson.setVisible(!status);
		// btSaveStudent.setVisible(status);
		// });

		txtAlbumNumber.textProperty().addListener((observable, oldValue, newValue) -> {
			if (oldValue.equals(newValue))
				btSaveStudent.setDisable(true);
			else
				btSaveStudent.setDisable(false);
		});
	}

	@FXML
	private void colImieOnEditStart(CellEditEvent<Person, String> e) {
	}

	@FXML
	private void colImieOnEditCommit(CellEditEvent<Person, String> e) {
	}

	@FXML
	private void doSaveStudentAction(ActionEvent e) {
		BusinessSessionFactory bsf = BusinessSessionFactory.getInstance();
		Session session = bsf.getSession().openSession();
		session.beginTransaction();
		Student student = (Student) session.load(Student.class, person.getPersonId());
		((Student) tblPerson.getSelectionModel().getSelectedItem()).setAlbumNumber(txtAlbumNumber.getText());
		student.setAlbumNumber(txtAlbumNumber.getText());
		session.getTransaction().commit();
		lbStudentSaveStatus.setText("Dane zostały zapisane.");
		bsf.close();
	}

	@FXML
	private Pane panePassword;
	@FXML
	private PasswordField pfChangePasswordFirst;
	@FXML
	private PasswordField pfChangePasswordSecond;
	@FXML
	private Label lbPasswordStatus;
	@FXML
	private Label lbStudentSaveStatus;

	@FXML
	private void doMenuChangePassword(ActionEvent e) {
		if (tblPerson.getSelectionModel().getSelectedItem() != null) {
			panePassword.setDisable(false);
			pfChangePasswordFirst.requestFocus();
		}
	}

	@FXML
	private void doClearPersonTextField(ActionEvent e) {
		clearPersonPane();
		cleanStudentPane();
		paneStudent.setDisable(true);
	}

	@FXML
	private void doChangePasswordAction(ActionEvent e) {
		if (pfChangePasswordFirst.getText().equals(pfChangePasswordSecond.getText())) {
			TableViewSelectionModel<Person> selectionModel = tblPerson.getSelectionModel();
			person = selectionModel.getSelectedItem();
			person.setPassword(personUtil.getHashedPassword(pfChangePasswordFirst.getText()));
			changePasswordForPerson(person);
			lbPasswordStatus.setText("Hasło zostało zmienione.");
		} else {
			lbPasswordStatus.setText("Hasła nie pokrywają się!");
		}

	}

	private void changePasswordForPerson(Person person) {
		BusinessSessionFactory bsf = BusinessSessionFactory.getInstance();
		Session session = bsf.getSession().openSession();
		session.beginTransaction();
		Person pers = (Person) session.load(Person.class, person.getPersonId());
		pers.setPassword(person.getPassword());
		session.getTransaction().commit();
		bsf.close();
	}

	private void commitPerson(Person person) {
		BusinessSessionFactory bsf = BusinessSessionFactory.getInstance();
		Session session = bsf.getSession().openSession();
		session.beginTransaction();
		Person pers = (Person) session.load(Person.class, person.getPersonId());
		pers.setFirstName(person.getFirstName());
		pers.setLastName(person.getLastName());
		pers.setEmail(person.getEmail());
		session.getTransaction().commit();
		bsf.close();
	}
}
