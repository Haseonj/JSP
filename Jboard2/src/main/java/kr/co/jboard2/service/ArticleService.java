package kr.co.jboard2.service;

import java.util.List;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.vo.ArticleVO;

public enum ArticleService {
	
	INSTANCE;
	private ArticleDAO dao;
	
	public int selectCountTotal () {
		return dao.selectCountTotal();
	}
	
	public List<ArticleVO> selectArticle(int limitStart) {
		return dao.selectArticle(limitStart);
	}
	
	
	
	
}
