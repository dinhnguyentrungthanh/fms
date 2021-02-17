package vn.com.r2s.fms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnrollmentID implements Serializable{

private static final long serialVersionUID = 1L;

@Column(name = "ClassID")
private Integer classID;

@Column(name = "TraineeID")
private String traineeID;

public EnrollmentID() {
	super();
}

public Integer getClassID() {
	return classID;
}

public void setClassID(Integer classID) {
	this.classID = classID;
}

public String getTraineeID() {
	return traineeID;
}

public void setTraineeID(String traineeID) {
	this.traineeID = traineeID;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classID == null) ? 0 : classID.hashCode());
		result = prime * result + ((traineeID == null) ? 0 : traineeID.hashCode());
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
		EnrollmentID other = (EnrollmentID) obj;
		if (classID == null) {
			if (other.classID != null)
				return false;
		} else if (!classID.equals(other.classID))
			return false;
		if (traineeID == null) {
			if (other.traineeID != null)
				return false;
		} else if (!traineeID.equals(other.traineeID))
			return false;
		return true;
	}

}
