package entities;

public class User {
	
	private static int cmp;
	private int id;
	private String lastname;
	private String firstname;
	private String password;
	private String email;
	private String role;
	

	public User(int id, String laslname, String firstname, String password, String email, String role) {
		super();
		this.id = id;
		this.lastname = laslname;
		this.firstname = firstname;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	
	public User(String laslname, String firstname, String password, String email, String role) {
		this.id = ++cmp;
		this.lastname = laslname;
		this.firstname = firstname;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLaslname(String laslname) {
		this.lastname = laslname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", laslname=" + lastname + ", firstname=" + firstname + ", password=" + password
				+ ", email=" + email + ", role=" + role + "]";
	}

	public boolean isBlocked() {
		// TODO Auto-generated method stub
		return false;
	}
	   
	
}
