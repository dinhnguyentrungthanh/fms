package vn.com.r2s.fms.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "feedback")
public class Feedback implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FeedbackID")
	private Integer feedbackID;
	
	@NotEmpty
	@Column(name = "Title")
	private String title;

	@ManyToOne
	@JoinColumn(name = "AdminID")
	private Admin admins;

	@Column(name = "IsDeleted")
	private boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "TypeFeedbackID")
	private TypeFeedback typeFeedback;

	@OneToMany(mappedBy = "feedbackID")
	@JsonIgnore
	Set<FeedbackQuestion> feedback_Question;

	public Feedback() {
		super();
	}

	public Feedback(Integer feedbackID, @NotEmpty String title, Admin admins, boolean isDeleted,
			TypeFeedback typeFeedback, Set<FeedbackQuestion> feedback_Question) {
		super();
		this.feedbackID = feedbackID;
		this.title = title;
		this.admins = admins;
		this.isDeleted = isDeleted;
		this.typeFeedback = typeFeedback;
		this.feedback_Question = feedback_Question;
	}

	public Integer getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(Integer feedbackID) {
		this.feedbackID = feedbackID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Admin getAdmins() {
		return admins;
	}

	public void setAdmins(Admin admins) {
		this.admins = admins;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public TypeFeedback getTypeFeedback() {
		return typeFeedback;
	}

	public void setTypeFeedback(TypeFeedback typeFeedback) {
		this.typeFeedback = typeFeedback;
	}

	public Set<FeedbackQuestion> getFeedback_Question() {
		return feedback_Question;
	}

	public void setFeedback_Question(Set<FeedbackQuestion> feedback_Question) {
		this.feedback_Question = feedback_Question;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}