package pl.edu.agh.dziekanat.core;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sequence {
	
	@Id
	private int moduleID;
	private int lastID;
	
	public int getModuleID() {
		return moduleID;
	}
	public void setModuleID(int moduleID) {
		this.moduleID = moduleID;
	}
	public int getLastID() {
		return lastID;
	}
	public void setLastID(int lastID) {
		this.lastID = lastID;
	}

}
