package kr.co.farmstory2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.farmstory2.service.UserService;
import kr.co.farmstory2.vo.UserVO;

@WebServlet("/user/modify.do")
public class ModifyController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService service = UserService.INSTANCE;

	@Override
	public void init() throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pass = req.getParameter("pass");
		
		req.setAttribute("pass", pass);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/modify.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String uid 		= req.getParameter("uid");
		String pass 	= req.getParameter("pass1");
		String name 	= req.getParameter("name");
		String nick 	= req.getParameter("nick");
		String email	= req.getParameter("email");
		String hp	 	= req.getParameter("hp");
		String zip	 	= req.getParameter("zip");
		String addr1 	= req.getParameter("addr1");
		String addr2 	= req.getParameter("addr2");
		
		UserVO vo = new UserVO();
		vo.setUid(uid);
		vo.setPass(pass);
		vo.setName(name);
		vo.setNick(nick);
		vo.setEmail(email);
		vo.setHp(hp);
		vo.setZip(zip);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		
		service.updateUser(vo);
		
		HttpSession sess = req.getSession();
		UserVO sessUser = (UserVO) sess.getAttribute("sessUser");
		uid = sessUser.getUid();
		// 세션 헤제
		sess.removeAttribute("sessUser");
		sess.invalidate();
		
		// 쿠키 삭제
		Cookie cookie = new Cookie("SESSID", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		
		// 데이터 베이스 사용자 sessId update
		service.updateUserForSessionOut(uid);
		
		resp.sendRedirect("/Farmstory2/user/login.do?success=201");
		
	}
}
