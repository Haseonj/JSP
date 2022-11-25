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
	
	public void insertArticle(ArticleVO vo) {
		dao.insertArticle(vo);
	}

	public int selectCountTotal () {
		return dao.selectCountTotal();
	}
	
	public ArticleVO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	
	public List<ArticleVO> selectArticles(int limitStart) {
		return dao.selectArticles(limitStart);
	}
	
	public void updateArticleHit(String no) {
		dao.updateArticleHit(no);
	}
	
}
