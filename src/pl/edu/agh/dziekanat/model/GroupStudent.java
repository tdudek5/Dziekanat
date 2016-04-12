package pl.edu.agh.dziekanat.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import pl.edu.agh.dziekanat.core.Module;
import pl.edu.agh.dziekanat.person.PersonType;

@Entity
@Table(name = "GroupStudent")
public class GroupStudent implements Module {

	@Id
	private int groupId;
	private String name;
	private GroupStudentType groupStudentType;

	public static final int moduleID = 2;

	public int getModuleID() {
		return moduleID;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GroupStudentType getGroupStudentType() {
		return groupStudentType;
	}

	public void setGroupStudentType(GroupStudentType groupStudentType) {
		this.groupStudentType = groupStudentType;
	}

}