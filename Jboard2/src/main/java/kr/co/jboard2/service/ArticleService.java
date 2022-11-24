package kr.co.jboard2.service;

import java.util.List;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.vo.ArticleVO;

public enum ArticleService {
	
	INSTANCE;
	private ArticleDAO dao;
	
	private ArticleService() {
		dao = new ArticleDAO();
	}

	public int selectCountTotal () {
		return dao.selectCountTotal();
	}
	
	public void selectArticle() {
		dao.selectArticle();
	}
	
	public List<ArticleVO> selectArticles(int limitStart) {
		return dao.selectArticles(limitStart);
	}
	
	
	
}
