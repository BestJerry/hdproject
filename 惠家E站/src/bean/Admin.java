package bean;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private String AId;
	private String AName;
	private String APsd;
	private String AFlag;

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** full constructor */
	public Admin(String AId, String AName, String APsd, String AFlag) {
		this.AId = AId;
		this.AName = AName;
		this.APsd = APsd;
		this.AFlag = AFlag;
	}

	// Property accessors

	public String getAId() {
		return this.AId;
	}

	public void setAId(String AId) {
		this.AId = AId;
	}

	public String getAName() {
		return this.AName;
	}

	public void setAName(String AName) {
		this.AName = AName;
	}

	public String getAPsd() {
		return this.APsd;
	}

	public void setAPsd(String APsd) {
		this.APsd = APsd;
	}

	public String getAFlag() {
		return this.AFlag;
	}

	public void setAFlag(String AFlag) {
		this.AFlag = AFlag;
	}

}