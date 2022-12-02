package kr.co.farmstory2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.UserService;
import kr.co.farmstory2.vo.UserVO;

@WebServlet("/user/checkPw.do")
public class CheckPwController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService service = UserService.INSTANCE;
	
	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String success = req.getParameter("success");
		
		req.setAttribute("success", success);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/checkPw.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		String success = req.getParameter("success");
		
		
		UserVO vo = service.selectUser(uid, pass);
		
		if(vo != null) {
			// 회원정보가 일치 할 때
			resp.sendRedirect("/Farmstory2/user/modify.do");
		}else {
			req.setAttribute("success", success);
			resp.sendRedirect("/Farmstory2/user/checkPw.do?success=100");
		}
		
	}

}
