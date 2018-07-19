package service;

import java.util.List;

import bean.News;

public interface NewsService {

	public void add(News news);

	public List findByTitle(String title);

	public News findById(Integer id);

	public List findByAll();

	public void delete(News news);

	public List<News> Passflag();

	public List<News> Passflag(Boolean flag);

}
