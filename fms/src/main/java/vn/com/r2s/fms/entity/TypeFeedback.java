package vn.com.r2s.fms.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "typefeedback")
public class TypeFeedback implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TypeID")
	private Integer typeID;	
	
	@Column(name = "TypeName")	
	private String typeName;
	
	@Column(name = "IsDeleted")
	private boolean isDeleted;
	
	@JsonBackReference
	@OneToMany(mappedBy = "typeFeedback", fetch = FetchType.LAZY) 
	private List<Feedback> feedbacks;

	public TypeFeedback() {
		super();
	}

	public TypeFeedback(Integer typeID, String typeName, boolean isDeleted, List<Feedback> feedbacks) {
		super();
		this.typeID = typeID;
		this.typeName = typeName;
		this.isDeleted = isDeleted;
		this.feedbacks = feedbacks;
	}

	public Integer getTypeID() {
		return typeID;
	}

	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}

