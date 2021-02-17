package vn.com.r2s.fms.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "class")
public class Class implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ClassID")
	private Integer classID;

	@NotNull
	@Size(min =1, max = 255)
	@Column(name = "ClassName")
	private String className;
	
	@NotNull 
	@Min(1)
	@Column(name = "Capacity")
	private Integer capacity;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "StartTime")
	private LocalDate startTime;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "EndTime")
	private LocalDate endTime;
	
	@Column(name = "IsDeleted",nullable = false)
	private boolean isDeleted;

	@JsonIgnore
	@OneToMany(mappedBy = "classID")
	Set<Enrollment> enrollments;
	
    @JsonIgnore
	@OneToMany(mappedBy = "ClassID")
	Set<Assignment> assignments;
	
	@JsonIgnore
	@OneToMany(mappedBy = "classID")
	Set<TraineeComment> traineeComment;
	
	/////////////////////////////////////////////////	
	@OneToMany(mappedBy = "classID") 
	Set<Answer> answers;

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
////////////////////////////////////////////////////

	public Set<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public LocalDate getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}

	public LocalDate getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	
	public Set<TraineeComment> getTraineeComment() {
		return traineeComment;
	}

	public void setTraineeComment(Set<TraineeComment> traineeComment) {
		this.traineeComment = traineeComment;
	}

}