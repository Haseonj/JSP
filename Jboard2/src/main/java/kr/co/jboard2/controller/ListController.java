package kr.co.jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.vo.ArticleVO;

@WebServlet("/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pg = req.getParameter("pg");
		
		int limitStart = 0;
		int currentPage = 1;
		int total = 0;
		int lastPageNum = 0;
		int pageGroupCurrent = 1;
		int pageGroupStart = 1;
		int pageGroupEnd = 0;
		int pageStartNum = 0;
		
		total = service.selectCountTotal();
		
		if(total % 10 == 0) {
			lastPageNum = (total / 10);
		}else {
			lastPageNum = (total / 10) + 1;
		}
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		
		limitStart = (currentPage - 1) * 10;
		
		pageGroupCurrent = (int)Math.ceil(currentPage / 10.0);
		pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
		pageGroupEnd = pageGroupCurrent * 10;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;
		}
		
		pageStartNum = total - limitStart;
		

		List<ArticleVO> articles = service.selectArticles(limitStart);
		
		req.setAttribute("articles", articles);
				
		RequestDispatcher dispatcher = req.getRequestDispatcher("/list.jsp");
		dispatcher.forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}

}
