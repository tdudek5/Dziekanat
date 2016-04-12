package pl.edu.agh.dziekanat.person;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import pl.edu.agh.dziekanat.model.GroupStudent;

@Entity
@DiscriminatorValue(value=PersonType.Values.STUDENT)
public class Student extends Person {
	
	@ManyToOne
    @JoinColumn(name="groupId")
	private GroupStudent groupStudent;
	
	@Column(name="albumNumberValue")
	private String albumNumber;

	public GroupStudent getGroupStudent() {
		return groupStudent;
	}

	public void setGroupStudent(GroupStudent groupStudent) {
		this.groupStudent = groupStudent;
	}

	public String getAlbumNumber() {
		return albumNumber;
	}

	public void setAlbumNumber(String albumNumber) {
		this.albumNumber = albumNumber;
	}

}
