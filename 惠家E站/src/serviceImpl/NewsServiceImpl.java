package serviceImpl;

import java.util.List;

import bean.News;
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

	@Override
	public void add(News news) {
		newsDAO.save(news);

	}

	@Override
	public List findByTitle(String title) {
		return newsDAO.findByTitle(title);

	}

	@Override
	public List<News> findByAll() {
		return newsDAO.findAll();
	}

	@Override
	public News findById(Integer id) {
		return newsDAO.findById(id);
	}

	@Override
	public void delete(News news) {
		newsDAO.delete(news);

	}

	@Override
	public List<News> Passflag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<News> Passflag(Boolean flag) {
		return newsDAO.findByNPassflag(flag);
	}



}
