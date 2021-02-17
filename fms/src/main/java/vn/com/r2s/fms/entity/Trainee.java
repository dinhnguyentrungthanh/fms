package vn.com.r2s.fms.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "trainee")
public class Trainee implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message ="Chưa nhập ID!")
	@Id

	@Column(name = "Username")
	private String username;

	@NotBlank(message = "Chưa nhập tên!")
	@Column(name = "Name")
	private String name;

	@NotBlank(message = "Chưa nhập Email!")
	@Column(name = "Email")
	private String email;

	@NotBlank(message = "Chưa nhập SĐT!")
	@Column(name = "Phone")
	private String phone;

	@NotBlank(message = "Chưa nhập Địa Chỉ!")
	@Column(name = "Address")
	private String address;

	@Column(name = "IsActive")
	private boolean isActive;

	@NotBlank(message = "Chưa nhập Password!")
	@Column(name = "Password")
	private String password;

	@NotBlank(message = "Chưa nhập ActivationCode!")
	@Column(name = "ActivationCode")
	private String activationCode;

	@NotBlank(message = "Chưa nhập ResetPasswordCode!")
	@Column(name = "ResetPasswordCode")
	private String resetPasswordCode;
	
/////////////////////////////////////////////////
	@JsonIgnore
	@OneToMany(mappedBy = "traineeID")
	Set<Enrollment> enrollments;

	@OneToMany(mappedBy = "trainee")
	Set<TraineeComment> traineeComment;
	
	public Trainee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	////////////////////////////////////////////////
	@JsonIgnore
	@OneToMany(mappedBy = "traineeID") 
	Set<Answer> answers;

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	////////////////////////////////////////////



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getResetPasswordCode() {
		return resetPasswordCode;
	}

	public void setResetPasswordCode(String resetPasswordCode) {
		this.resetPasswordCode = resetPasswordCode;
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

	


