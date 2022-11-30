package kr.co.farmstory2.controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.vo.ArticleVO;

@WebServlet("/board/commentWrite.do")
public class CommentWriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ArticleService service = ArticleService.INSTANCE;

	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String no = req.getParameter("no");
		String uid = req.getParameter("uid");
		String content = req.getParameter("content");
		String regip =req.getRemoteAddr();
		
		ArticleVO vo = new ArticleVO();
		vo.setParent(no);
		vo.setUid(uid);
		vo.setContent(content);
		vo.setRegip(regip);
		
		ArticleVO article = service.insertComment(vo);
		
		JsonObject json = new JsonObject();
		json.addProperty("result", 1);
		json.addProperty("no", article.getNo());
		json.addProperty("parent", article.getParent());
		json.addProperty("nick", article.getNick());
		json.addProperty("rdate", article.getRdate());
		json.addProperty("content", article.getContent());
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
	
}
