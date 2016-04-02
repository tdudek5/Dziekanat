package pl.edu.agh.dziekanat.person;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value=PersonType.Values.STUDENT)
public class Student extends Person {
	
	@Column(name="grupaValue")
	private String grupa;
	@Column(name="albumNumberValue")
	private String albumNumber;

	public String getGrupa() {
		return grupa;
	}

	public void setGrupa(String grupa) {
		this.grupa = grupa;
	}

	public String getAlbumNumber() {
		return albumNumber;
	}

	public void setAlbumNumber(String albumNumber) {
		this.albumNumber = albumNumber;
	}

}
