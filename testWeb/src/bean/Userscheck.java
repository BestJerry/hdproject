package bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Userscheck entity. @author MyEclipse Persistence Tools
 */

public class Userscheck implements java.io.Serializable {

	// Fields

	private String ucId;
	private String ucNumber;
	private Date ucCommitdate;
	private Boolean ucFlag;
	private Date ucEnddate;
	private Set userses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Userscheck() {
	}

	/** minimal constructor */
	public Userscheck(String ucId, String ucNumber, Date ucCommitdate, Boolean ucFlag) {
		this.ucId = ucId;
		this.ucNumber = ucNumber;
		this.ucCommitdate = ucCommitdate;
		this.ucFlag = ucFlag;
	}

	/** full constructor */
	public Userscheck(String ucId, String ucNumber, Date ucCommitdate, Boolean ucFlag, Date ucEnddate, Set userses) {
		this.ucId = ucId;
		this.ucNumber = ucNumber;
		this.ucCommitdate = ucCommitdate;
		this.ucFlag = ucFlag;
		this.ucEnddate = ucEnddate;
		this.userses = userses;
	}

	// Property accessors

	public String getUcId() {
		return this.ucId;
	}

	public void setUcId(String ucId) {
		this.ucId = ucId;
	}

	public String getUcNumber() {
		return this.ucNumber;
	}

	public void setUcNumber(String ucNumber) {
		this.ucNumber = ucNumber;
	}

	public Date getUcCommitdate() {
		return this.ucCommitdate;
	}

	public void setUcCommitdate(Date ucCommitdate) {
		this.ucCommitdate = ucCommitdate;
	}

	public Boolean getUcFlag() {
		return this.ucFlag;
	}

	public void setUcFlag(Boolean ucFlag) {
		this.ucFlag = ucFlag;
	}

	public Date getUcEnddate() {
		return this.ucEnddate;
	}

	public void setUcEnddate(Date ucEnddate) {
		this.ucEnddate = ucEnddate;
	}

	public Set getUserses() {
		return this.userses;
	}

	public void setUserses(Set userses) {
		this.userses = userses;
	}

}