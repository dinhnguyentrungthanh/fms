package vn.com.r2s.fms.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "feedback_question")
public class FeedbackQuestion {
	
	@EmbeddedId
	private FeedbackQuestionID id;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@MapsId(value = "FeedbackID")
	@JsonManagedReference
	@JoinColumn(name = "FeedbackID")
	private Feedback feedbackID;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JsonBackReference
	@MapsId(value = "QuestionID")
	@JsonManagedReference
	@JoinColumn(name = "QuestionID")
	private Question questionID;

	public FeedbackQuestionID getId() {
		return id;
	}

	public void setId(FeedbackQuestionID id) {
		this.id = id;
	}

	public Feedback getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(Feedback feedbackID) {
		this.feedbackID = feedbackID;
	}

	public Question getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Question questionID) {
		this.questionID = questionID;
	}

	public FeedbackQuestion(FeedbackQuestionID id, Feedback feedbackID, Question questionID) {
		super();
		this.id = id;
		this.feedbackID = feedbackID;
		this.questionID = questionID;
	}

	public FeedbackQuestion() {
		super();
	}
	
	
}

