package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import bean.News;
import bean.Users;
import service.NewsService;
import service.UsersService;

public class NewsAction extends ActionSupport {

	private SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
	private String dateTString = ft.format(new Date());
	private static final long serialVersionUID = 1L;
	private News news;
	private NewsService newsService;
	private List<News> newsList = new ArrayList<News>();
	private Integer index = 1;
	private String ind = "6";

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public String getInd() {
		return ind;
	}

	public void setInd(String ind) {
		this.ind = ind;
	}

	public News getNews() {
		return news;
	}

	public String getDateTString() {
		return dateTString;
	}

	public void setDateTString(String dateTString) {
		this.dateTString = dateTString;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public String add() {
		news.setNDate(new Date());
		news.setNPassflag(false);
		Users users = new Users();
		
		users.setUId("999");
		news.setUsers(users);
		newsService.add(news);
		return SUCCESS;

	}

	public String findByTitle() {
		newsList = newsService.findByTitle(news.getTitle());
		if (newsList.size() > 0) {
			news = newsList.get(0);
			return SUCCESS;
		} else {
			return ERROR;
		}

	}

	public String findAll() {
		newsList = newsService.findByAll();
		if (newsList.size() > 0) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String findById() {
		news = newsService.findById(news.getNId());
		if (news != null) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String delete() {
		newsService.delete(news);
		return SUCCESS;
	}

	public String findByPassflag() {
		newsList = newsService.Passflag(news.getNPassflag());
		if (newsList.size() > 0) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
}
