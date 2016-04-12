package pl.edu.agh.dziekanat.person;

import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
import javax.persistence.DiscriminatorValue;


@Entity
//@Table(name="Teacher")
@DiscriminatorValue(value=PersonType.Values.TEACHER)
public class Teacher extends Person {

	

}
