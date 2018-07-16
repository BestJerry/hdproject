package serviceImpl;

import dao.NewsDAO;
import service.NewsService;

public class NewsServiceImpl implements NewsService {
	private NewsDAO newsDAO;

	public NewsDAO getNewsDAO() {
		return newsDAO;
	}

	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}
}
