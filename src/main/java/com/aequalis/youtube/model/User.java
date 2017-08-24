/**
 * 
 */
package com.aequalis.youtube.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author anand
 *
 */
@Entity
@Table(name="users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userid;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "bcaddress")
	private String bcaddress;
	
	@Column(name = "isadminguser")
	private Boolean isadminuser;
	
	@Column(name="lastlogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
	
	@Column(name="currentlogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date currentLogin;
	
	@Transient
	private String balance;

	/**
	 * @return the userid
	 */
	public Long getUserid() {
		return userid;
	}

	/**
	 * @return the balance
	 */
	public String getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(String balance) {
		this.balance = balance;
	}

	/**
	 * @return the isadminuser
	 */
	public Boolean getIsadminuser() {
		return isadminuser;
	}

	/**
	 * @param isadminuser the isadminuser to set
	 */
	public void setIsadminuser(Boolean isadminuser) {
		this.isadminuser = isadminuser;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the bcaddress
	 */
	public String getBcaddress() {
		return bcaddress;
	}

	/**
	 * @param bcaddress the bcaddress to set
	 */
	public void setBcaddress(String bcaddress) {
		this.bcaddress = bcaddress;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return the currentLogin
	 */
	public Date getCurrentLogin() {
		return currentLogin;
	}

	/**
	 * @param currentLogin the currentLogin to set
	 */
	public void setCurrentLogin(Date currentLogin) {
		this.currentLogin = currentLogin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", fullname=" + fullname
				+ ", bcaddress=" + bcaddress + ", isadminuser=" + isadminuser + ", lastLogin=" + lastLogin
				+ ", currentLogin=" + currentLogin + ", balance=" + balance + "]";
	}
}
