package prj.trip.FBoard.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import prj.trip.FBoard.controller.Action;
import prj.trip.FBoard.dao.FreeBoardDao;

public class FBoardWrite implements Action{
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//String id = (String) session.getAttribute("LoginId");
		String id = request.getParameter("loginid");
		String title = request.getParameter("writeTitle");
		String cont = request.getParameter("writeContent");
		
		request.setAttribute("id", id);
		request.setAttribute("title", title);
		request.setAttribute("cont", cont);
		
		
		FreeBoardDao dao = new FreeBoardDao();
		int memnum = dao.getMemNum(id);
		String nick = dao.getNick(id);	
		request.setAttribute("nick", nick);
		System.out.println(id +title + cont + memnum+"닉네임 : " + nick );
		int insertcheck = dao.InsertFBboard( title, cont, memnum);
		
		if(insertcheck == 1 ) {
			String path="/view/fboard/FBoardContent.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}else {
			String path="/view/fboard/FBoardWrite.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}
	}

}
