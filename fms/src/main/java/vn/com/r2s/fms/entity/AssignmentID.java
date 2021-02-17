package vn.com.r2s.fms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AssignmentID implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Column(name = "ClassID")
	private Integer classID;
	
	@Column(name = "ModuleID")
	private Integer moduleID;
	
	@Column(name = "TrainerID")
	private String trainerID;
	



	

	public AssignmentID(){
		super();
	}


	public Integer getClassID() {
		return classID;
	}


	public void setClassID(Integer classID) {
		this.classID = classID;
	}


	public Integer getModuleID() {
		return moduleID;
	}


	public void setModuleID(Integer moduleID) {
		this.moduleID = moduleID;
	}


	public String getTrainerID() {
		return trainerID;
	}


	public void setTrainerID(String trainerID) {
		this.trainerID = trainerID;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classID == null) ? 0 : classID.hashCode());
		result = prime * result + ((moduleID == null) ? 0 : moduleID.hashCode());
		result = prime * result + ((trainerID == null) ? 0 : trainerID.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssignmentID other = (AssignmentID) obj;
		if (classID == null) {
			if (other.classID != null)
				return false;
		} else if (!classID.equals(other.classID))
			return false;
		if (moduleID == null) {
			if (other.moduleID != null)
				return false;
		} else if (!moduleID.equals(other.moduleID))
			return false;
		if (trainerID == null) {
			if (other.trainerID != null)
				return false;
		} else if (!trainerID.equals(other.trainerID))
			return false;
		return true;
	}




	
}
