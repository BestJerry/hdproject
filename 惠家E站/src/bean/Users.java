package bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */

public class Users implements java.io.Serializable {

	// Fields

	private String UId;
	private Userscheck userscheck;
	private String UName;
	private String psd;
	private String number;
	private String UAbouts;
	private String phone;
	private Set newses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String UId, String UName, String psd, String phone) {
		this.UId = UId;
		this.UName = UName;
		this.psd = psd;
		this.phone = phone;
	}

	/** full constructor */
	public Users(String UId, Userscheck userscheck, String UName, String psd, String number, String UAbouts,
			String phone, Set newses) {
		this.UId = UId;
		this.userscheck = userscheck;
		this.UName = UName;
		this.psd = psd;
		this.number = number;
		this.UAbouts = UAbouts;
		this.phone = phone;
		this.newses = newses;
	}

	// Property accessors

	public String getUId() {
		return this.UId;
	}

	public void setUId(String UId) {
		this.UId = UId;
	}

	public Userscheck getUserscheck() {
		return this.userscheck;
	}

	public void setUserscheck(Userscheck userscheck) {
		this.userscheck = userscheck;
	}

	public String getUName() {
		return this.UName;
	}

	public void setUName(String UName) {
		this.UName = UName;
	}

	public String getPsd() {
		return this.psd;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUAbouts() {
		return this.UAbouts;
	}

	public void setUAbouts(String UAbouts) {
		this.UAbouts = UAbouts;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set getNewses() {
		return this.newses;
	}

	public void setNewses(Set newses) {
		this.newses = newses;
	}

}