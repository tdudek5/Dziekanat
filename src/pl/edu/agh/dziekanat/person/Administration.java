package pl.edu.agh.dziekanat.person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
//import javax.persistence.Table;

@Entity
//@Table(name="ADMINISTRATION")
@DiscriminatorValue(value=PersonType.Values.ADMINISTRATION)
public class Administration extends Person{
	
	

}
