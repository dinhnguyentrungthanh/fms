package vn.com.r2s.fms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FeedbackQuestionID implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "FeedbackID")
	private Integer feedbackID;
	
	@Column(name = "QuestionID")
	private Integer questionID;

	public Integer getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(Integer feedbackID) {
		this.feedbackID = feedbackID;
	}

	public Integer getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Integer questionID) {
		this.questionID = questionID;
	}
}
