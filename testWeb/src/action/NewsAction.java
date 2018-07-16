package action;

import com.opensymphony.xwork2.ActionSupport;

import bean.News;
import bean.Users;
import service.NewsService;
import service.UsersService;

public class NewsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private News news;
	private NewsService newsService;

	public News getNews() {
		return news;
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

}
