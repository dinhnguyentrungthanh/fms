package vn.com.r2s.fms.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "question")
public class Question implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "QuestionID")
	private Integer questionID;
	
	@NotEmpty
	@Column(name = "QuestionContent")
	private String questionContent;
	
	@Column(name = "IsDeleted")
	private Boolean isDeleted;
	
	@OneToMany(mappedBy = "questionID") 
	Set<FeedbackQuestion> questionFeedback;
	 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "TopicID")
	private Topic topic;
	
	/////////////////////////////////////////////////////////////
	@JsonIgnore
	@OneToMany(mappedBy = "questionID") 
	Set<Answer> answers;
	
	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
////////////////////////////////////////////////

	public Integer getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Integer questionID) {
		this.questionID = questionID;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<FeedbackQuestion> getQuestionFeedback() {
		return questionFeedback;
	}

	public void setQuestionFeedback(Set<FeedbackQuestion> questionFeedback) {
		this.questionFeedback = questionFeedback;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
