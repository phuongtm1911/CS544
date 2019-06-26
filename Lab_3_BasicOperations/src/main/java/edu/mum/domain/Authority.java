package edu.mum.domain;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority {
	@Id
 	private long id;

	private String username;
 	@Column(nullable = false)
 	private String authority;
 	
 	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
 
 	
}
