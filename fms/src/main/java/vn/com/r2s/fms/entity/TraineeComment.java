package vn.com.r2s.fms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "trainee_comment")
public class TraineeComment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TraineeCommentID id;
	
	@JoinColumn(name = "ClassID")
	@MapsId(value = "ClassID")
	@ManyToOne(fetch = FetchType.EAGER)
    private Class classID;
	
	@JoinColumn(name = "ModuleID")
	@MapsId(value = "ModuleID")
	@ManyToOne(fetch = FetchType.EAGER)
	private Module moduleID;
	
	@JoinColumn(name = "TraineeID")
	@MapsId(value = "TraineeID")
	@ManyToOne(fetch = FetchType.EAGER)
	private Trainee trainee;
	
	@Column(name = "Comment")
	private String comment;

	public TraineeCommentID getId() {
		return id;
	}

	public void setId(TraineeCommentID id) {
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

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}	
}