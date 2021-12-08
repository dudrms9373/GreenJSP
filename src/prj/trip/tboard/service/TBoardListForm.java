package prj.trip.tboard.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import prj.trip.tboard.dao.TBoardDao;
import prj.trip.tboard.vo.TBoardVo;

public class TBoardListForm implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		String loginId  = (String) session.getAttribute("LoginId"); //세션과의 연결
		int currentPage = 1; // 첫페이지를 보여줌
		int dpp         = 10; // 기본 페이지당 자료수는 10
		
		TBoardDao dao = new TBoardDao();
		int totalData   = dao.getDataCount();
		request.setAttribute("dpp", dpp);
		request.setAttribute("totalData", totalData);
		List<TBoardVo> boardList =  dao.getPagingData(currentPage, dpp-10);
		request.setAttribute("boardList", boardList);
		
		
		String path="/view/tboard/tboardlist.jsp";
		request.getRequestDispatcher(path).forward(request, response);
		

	}

}
