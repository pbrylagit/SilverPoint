package pl.silverpoint.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import pl.silverpoint.validator.Email;
import pl.silverpoint.validator.Username;

@XmlRootElement
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int userId;
	@NotBlank(message= "{notBlank.user.username.validation}")
	@Username
	private String username;
	@NotBlank(message= "{notBlank.user.firstName.validation}")
	@Column(name="first_name")
	private String firstName;
	@NotBlank(message= "{notBlank.user.lastName.validation}")
	@Column(name="last_name")
	private String lastName;
	@NotBlank(message= "{notBlank.user.password.validation}")
	@Size(min=8, max=20, message = "{size.user.password.validation}")
	private String password;
	private String permission;
	@Pattern(regexp="[a-z.]+@silverpoint.pl", message="{pattern.user.email.validation}")
	@NotBlank(message= "{notBlank.user.email.validation}")
	@Email
	private String email;

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
}
