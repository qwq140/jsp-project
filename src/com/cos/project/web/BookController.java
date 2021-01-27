package com.cos.project.web;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.project.domain.book.dto.SearchReqDto;
import com.cos.project.util.AirVO;
import com.cos.project.util.ApiExplorer;
import com.cos.project.util.MyUtils;


@WebServlet("/book")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cmd = request.getParameter("cmd");

		if (cmd.equals("bookForm")) {
			Map<String, String> result = MyUtils.getAirportId();
			String depAirportId = result.get(request.getParameter("depAirportNm"));
			String arrAirportId = result.get(request.getParameter("arrAirportNm"));
			String depPlandTime = request.getParameter("depPlandTime");
			String arrPlandTime = request.getParameter("arrPlandTime");
			int personnel = Integer.parseInt(request.getParameter("personnel"));
			
			SearchReqDto dto = new SearchReqDto();
			dto.setDepAirportId(depAirportId);
			dto.setArrAirportId(arrAirportId);
			dto.setDepPlandTime(depPlandTime);
			dto.setArrPlandTime(arrPlandTime);
			dto.setPersonnel(personnel);

			System.out.println(dto);

			List<AirVO> go = ApiExplorer.getAirportJson(dto,"go");
			List<AirVO> back = ApiExplorer.getAirportJson(dto,"back");
			
			request.setAttribute("flightSearch", dto);
			request.setAttribute("go", go);
			request.setAttribute("back", back);

			RequestDispatcher dis = request.getRequestDispatcher("book/bookForm.jsp");
			dis.forward(request, response);

		}
	}

}
