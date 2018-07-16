package bean;

import java.util.Date;

/**
 * News entity. @author MyEclipse Persistence Tools
 */

public class News implements java.io.Serializable {

	// Fields

	private String NId;
	private Users users;
	private String NFlag;
	private String title;
	private String NBody;
	private String author;
	private Date NDate;
	private String NAbouts;
	private Boolean NPassflag;

	// Constructors

	/** default constructor */
	public News() {
	}

	/** minimal constructor */
	public News(String NId, String NFlag, String title, String NBody, String author, Date NDate, Boolean NPassflag) {
		this.NId = NId;
		this.NFlag = NFlag;
		this.title = title;
		this.NBody = NBody;
		this.author = author;
		this.NDate = NDate;
		this.NPassflag = NPassflag;
	}

	/** full constructor */
	public News(String NId, Users users, String NFlag, String title, String NBody, String author, Date NDate,
			String NAbouts, Boolean NPassflag) {
		this.NId = NId;
		this.users = users;
		this.NFlag = NFlag;
		this.title = title;
		this.NBody = NBody;
		this.author = author;
		this.NDate = NDate;
		this.NAbouts = NAbouts;
		this.NPassflag = NPassflag;
	}

	// Property accessors

	public String getNId() {
		return this.NId;
	}

	public void setNId(String NId) {
		this.NId = NId;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getNFlag() {
		return this.NFlag;
	}

	public void setNFlag(String NFlag) {
		this.NFlag = NFlag;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNBody() {
		return this.NBody;
	}

	public void setNBody(String NBody) {
		this.NBody = NBody;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getNDate() {
		return this.NDate;
	}

	public void setNDate(Date NDate) {
		this.NDate = NDate;
	}

	public String getNAbouts() {
		return this.NAbouts;
	}

	public void setNAbouts(String NAbouts) {
		this.NAbouts = NAbouts;
	}

	public Boolean getNPassflag() {
		return this.NPassflag;
	}

	public void setNPassflag(Boolean NPassflag) {
		this.NPassflag = NPassflag;
	}

}