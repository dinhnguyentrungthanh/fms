package vn.com.r2s.fms.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "module")
public class Module implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ModuleID")
	private Integer moduleID;

	@ManyToOne
	@JoinColumn(name = "AdminID")
	private Admin admins;

	@Size(min = 1, max = 255, message = "Please enter module name and less than 255")
	@Column(name = "ModuleName")
	private String moduleName;

	@NotNull(message = "Please choose start date of fill full mm/dd/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "StartTime")
	private LocalDate startTime;

	@NotNull(message = "Please choose end date of fill full mm/dd/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "EndTime")
	private LocalDate endTime;

	@Column(name = "IsDeleted")
	private boolean isDeleted;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "FeedbackStartTime")
	private LocalDateTime feedbackStartTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "FeedbackEndTime")
	private LocalDateTime feedbackEndTime;

	@ManyToOne
	@JoinColumn(name = "FeedbackID")
	private Feedback feedback;
	
	@JsonIgnore
	@OneToMany(mappedBy = "ModuleID")
	Set<Assignment> assignments;
	
	@JsonIgnore
	@OneToMany(mappedBy = "moduleID")
	Set<TraineeComment> traineeComment;
	
	/////////////////////////////////////////////////////////
	@JsonIgnore
	@OneToMany(mappedBy = "moduleID") 
	Set<Answer> answers;

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
////////////////////////////////////////////////////////////

	public Integer getModuleID() {
		return moduleID;
	}

	public Admin getAdmin() {
		return admins;
	}

	public void setAdmin(Admin admin) {
		this.admins = admin;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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

	public LocalDateTime getFeedbackStartTime() {
		return feedbackStartTime;
	}

	public void setFeedbackStartTime(LocalDateTime feedbackStartTime) {
		this.feedbackStartTime = feedbackStartTime;
	}

	public LocalDateTime getFeedbackEndTime() {
		return feedbackEndTime;
	}

	public void setFeedbackEndTime(LocalDateTime feedbackEndTime) {
		this.feedbackEndTime = feedbackEndTime;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public void setModuleID(Integer moduleID) {
		this.moduleID = moduleID;
	}

	public Set<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}
	
	public Set<TraineeComment> getTraineeComment() {
		return traineeComment;
	}

	public void setTraineeComment(Set<TraineeComment> traineeComment) {
		this.traineeComment = traineeComment;
	}

	public Module(Integer moduleID, Admin admins,
			@Size(min = 1, max = 255, message = "Please enter module name and less than 255") String moduleName,
			@NotNull(message = "Please choose start date of fill full mm/dd/yyyy") LocalDate startTime,
			@NotNull(message = "Please choose end date of fill full mm/dd/yyyy") LocalDate endTime, boolean isDeleted,
			LocalDateTime feedbackStartTime, LocalDateTime feedbackEndTime, Feedback feedback,
			Set<TraineeComment> traineeComment) {
		super();
		this.moduleID = moduleID;
		this.admins = admins;
		this.moduleName = moduleName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isDeleted = isDeleted;
		this.feedbackStartTime = feedbackStartTime;
		this.feedbackEndTime = feedbackEndTime;
		this.feedback = feedback;
		this.traineeComment = traineeComment;
	}

	public Module() {
		super();
	}

	

}
