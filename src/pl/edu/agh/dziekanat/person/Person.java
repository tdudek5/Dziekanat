package pl.edu.agh.dziekanat.person;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.DiscriminatorColumn;

import pl.edu.agh.dziekanat.core.Module;
import pl.edu.agh.dziekanat.model.GroupStudent;


@Entity
@Table(name="Person")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(  
		  name="personTypeMnemonic",   
		  discriminatorType=DiscriminatorType.STRING
		  ) 

public class Person implements Module {
	
	@Id
	private int personId;
	private String lastName;
	private String firstName;
	private String nickName;
	private String email;
	private String password;

	public static final int moduleID = 1;
	
	public int getModuleID() {
		return moduleID;
	}

	
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonType() {
		return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}
	
	public String getPersonTypeDesc() {
		//personTypeDesc = PersonType.valueOf(this.getPersonType()).getDescription();
		//return personTypeDesc;
		return PersonType.valueOf(this.getPersonType()).getDescription();
	}
	
}
