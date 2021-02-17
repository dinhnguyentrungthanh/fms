package vn.com.r2s.fms.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "enrollment")
public class Enrollment {

	@EmbeddedId
	private EnrollmentID enrollmentID;

	@ManyToOne
	@MapsId(value = "classID")
	@JoinColumn(name = "ClassID")
	private Class classID;

	@ManyToOne
	@MapsId(value = "username")
	@JoinColumn(name = "TraineeID")
	private Trainee traineeID;

	public Enrollment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnrollmentID getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(EnrollmentID enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public Class getClassID() {
		return classID;
	}

	public void setClassID(Class classID) {
		this.classID = classID;
	}

	public Trainee getTraineeID() {
		return traineeID;
	}

	public void setTraineeID(Trainee traineeID) {
		this.traineeID = traineeID;
	}
}
