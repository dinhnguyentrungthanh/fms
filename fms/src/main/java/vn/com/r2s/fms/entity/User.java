package vn.com.r2s.fms.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message ="Chưa nhập tên!")
	@Column(name = "name")
	private String name;

	@NotBlank(message ="Chưa nhập email!")
	@Email(message = "Sai định dạng Email!")
	@Column(name = "email")
	private String email;

	@NotBlank(message ="Chưa nhập số điện thoại!")
	@Pattern(regexp = "0+[0-9]{9}|0+[0-9]{10}",message = "Số điện thoại băt đầu số 0, phải 10 hoặc 11 số!!")
	
	@Column(name = "phone")
	private String phone;

	public User() {
	}

	public User(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
