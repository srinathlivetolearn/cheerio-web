package net.srinathr.cheerio.models;

public class UserModel {
	private String email;
	
	private String password;
	
	private String phone;
	
	private String firstname;
	
	private String lastname;
	
	private String role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserModel [email=" + email + ", password=" + password + ", phone=" + phone + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", role=" + role + "]";
	}

}
