package vn.com.r2s.fms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Trainer")
public class Trainer {

	@Id
	@Column(name = "Username")
	private String username;

	@Column(name = "Name")
	private String name;

	@Column(name = "Email")
	private String email;

	@Column(name = "Phone")
	private String phone;

	@Column(name = "Address")
	private String address;

	@Column(name = "IsActive")
	private boolean isActive;

	@Column(name = "Password")
	private String password;

	@Column(name = "IdSkill")
	private int idSkill;

	@Column(name = "ActivationCode")
	private String activationCode;

	@Column(name = "ResetPasswordCode")
	private String resetPasswordCode;

	@Column(name = "IsReceiveNotification")
	private boolean isReceiveNotification;

	
	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
    @JsonIgnore
	@OneToMany(mappedBy="TrainerID") 
	private List<Assignment> assignments;

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

	public int getIdSkill() {
		return idSkill;
	}

	public void setIdSkill(int idSkill) {
		this.idSkill = idSkill;
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

	public boolean isReceiveNotification() {
		return isReceiveNotification;
	}

	public void setReceiveNotification(boolean isReceiveNotification) {
		this.isReceiveNotification = isReceiveNotification;
	}


}
