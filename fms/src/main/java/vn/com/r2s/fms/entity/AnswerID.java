package vn.com.r2s.fms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AnswerID implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "ClassID")
	private Integer classID;
	
	@Column(name = "ModuleID")
    private Integer moduleID;
	
	@Column(name = "TraineeID")
    private String traineeID;
	
	@Column(name = "QuestionID")
    private Integer questionID;

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

	public String getTraineeID() {
		return traineeID;
	}

	public void setTraineeID(String traineeID) {
		this.traineeID = traineeID;
	}

	public Integer getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Integer questionID) {
		this.questionID = questionID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
