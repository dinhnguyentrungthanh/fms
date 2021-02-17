package vn.com.r2s.fms.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "assignment")
public class Assignment {
	
	@EmbeddedId
	private AssignmentID assignmentID;


	@ManyToOne
	@MapsId(value = "ClassID")
	@JoinColumn(name = "ClassID")
	private Class ClassID;
	

	@ManyToOne
	@MapsId(value = "UserName")
	@JoinColumn(name = "TrainerID")
	private Trainer TrainerID;
	
	

	@ManyToOne
	@MapsId(value = "ModuleID")
	@JoinColumn(name = "ModuleID")
	private Module ModuleID;
	
	

	@Column(name = "RegistrationCode", nullable = true)
	private String RegistrationCode;


	

	public Assignment(AssignmentID assignmentID, Class classID, Trainer trainerID, Module moduleID,
			String registrationCode) {
		super();
		this.assignmentID = assignmentID;
		ClassID = classID;
		TrainerID = trainerID;
		ModuleID = moduleID;
		RegistrationCode = registrationCode;
	}




	public String getRegistrationCode() {
		return RegistrationCode;
	}




	public void setRegistrationCode(String registrationCode) {
		RegistrationCode = registrationCode;
	}




	public Assignment() {
		super();
		// TODO Auto-generated constructor stub
	}

	


	public AssignmentID getAssignmentID() {
		return assignmentID;
	}




	public void setAssignmentID(AssignmentID assignmentID) {
		this.assignmentID = assignmentID;
	}




	public Trainer getTrainerID() {
		return TrainerID;
	}




	public void setTrainerID(Trainer trainerID) {
		TrainerID = trainerID;
	}




	public Module getModuleID() {
		return ModuleID;
	}




	public void setModuleID(Module moduleID) {
		ModuleID = moduleID;
	}




	public Class getClassID() {
		return ClassID;
	}

	public void setClassID(Class classID) {
		ClassID = classID;
	}


	
	
}
	
