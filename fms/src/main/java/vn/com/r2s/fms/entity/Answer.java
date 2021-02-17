package vn.com.r2s.fms.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "answer")
public class Answer {
	
	@EmbeddedId
	private AnswerID id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId(value = "ClassID")
	@JsonManagedReference
	@JoinColumn(name = "ClassID")
	private Class classID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId(value = "ModuleID")
	@JsonManagedReference
	@JoinColumn(name = "ModuleID")
    private Module moduleID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@MapsId(value = "Username")
	@JoinColumn(name = "TraineeID")
    private Trainee traineeID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId(value = "QuestionID")
	@JsonManagedReference
	@JoinColumn(name = "QuestionID")
    private Question questionID;
	
	@Column(name = "Value")
	private Integer value;

	public Answer() {
		super();
	}

	public Answer(AnswerID id, Class classID, Module moduleID, Trainee traineeID, Question questionID, Integer value) {
		super();
		this.id = id;
		this.classID = classID;
		this.moduleID = moduleID;
		this.traineeID = traineeID;
		this.questionID = questionID;
		this.value = value;
	}

	public AnswerID getId() {
		return id;
	}

	public void setId(AnswerID id) {
		this.id = id;
	}

	public Class getClassID() {
		return classID;
	}

	public void setClassID(Class classID) {
		this.classID = classID;
	}

	public Module getModuleID() {
		return moduleID;
	}

	public void setModuleID(Module moduleID) {
		this.moduleID = moduleID;
	}

	public Trainee getTraineeID() {
		return traineeID;
	}

	public void setTraineeID(Trainee traineeID) {
		this.traineeID = traineeID;
	}

	public Question getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Question questionID) {
		this.questionID = questionID;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	
}


