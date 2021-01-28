package com.cos.project.web;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.project.domain.CommonRespDto;
import com.cos.project.domain.book.Book;
import com.cos.project.domain.book.dto.SaveReqDto;
import com.cos.project.domain.book.dto.SearchReqDto;
import com.cos.project.domain.book.dto.SearchRespDto;
import com.cos.project.service.BookService;
import com.cos.project.util.AirVO;
import com.cos.project.util.ApiExplorer;
import com.cos.project.util.MyUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


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
		BookService bookService = new BookService();
		if (cmd.equals("bookForm")) {
			Map<String, String> result = MyUtils.getAirportId();
			String depAirportId = result.get(request.getParameter("depAirportNm"));
			String arrAirportId = result.get(request.getParameter("arrAirportNm"));
			String depPlandTime = request.getParameter("depPlandTime");
			String arrPlandTime = request.getParameter("arrPlandTime");
			
			int personnel = Integer.parseInt(request.getParameter("personnel"));
			String depAirportNm = request.getParameter("depAirportNm");
			String arrAirportNm = request.getParameter("arrAirportNm");
			
			SearchReqDto dto = new SearchReqDto();
			dto.setDepAirportId(depAirportId);
			dto.setArrAirportId(arrAirportId);
			dto.setDepPlandTime(depPlandTime);
			dto.setArrPlandTime(arrPlandTime);

			System.out.println(dto);
			
			
			List<AirVO> go = ApiExplorer.getAirportJson(dto,"go");
			List<AirVO> back = ApiExplorer.getAirportJson(dto,"back");
			
			SearchRespDto searchRespDto = new SearchRespDto();
			searchRespDto.setPersonnel(personnel);
			searchRespDto.setDepAirportNm(depAirportNm);
			searchRespDto.setArrAirportNm(arrAirportNm);
			
			System.out.println(searchRespDto);
			
			request.setAttribute("flightSearch", searchRespDto);
			request.setAttribute("go", go);
			request.setAttribute("back", back);

			RequestDispatcher dis = request.getRequestDispatcher("book/bookForm.jsp");
			dis.forward(request, response);

		} else if(cmd.equals("book")) {
			BufferedReader br = request.getReader();
			String reqData = br.readLine();
			System.out.println("예약 요청 데이터 : "+reqData);
			
			Gson gson = new Gson();
			JsonParser jsonParser = new JsonParser();
			JsonArray jsonArray = (JsonArray) jsonParser.parse(reqData);
//			System.out.println(jsonArray);
			
			JsonObject go = (JsonObject) jsonArray.get(0);
			JsonObject back = (JsonObject) jsonArray.get(1); 
//			System.out.println("go : "+go);
//			System.out.println("back : "+back);
			
			SaveReqDto goReqDto = gson.fromJson(go, SaveReqDto.class);
			SaveReqDto backReqDto = gson.fromJson(back, SaveReqDto.class);
			
			System.out.println(goReqDto);
			System.out.println(backReqDto);
			
			int goResult = bookService.예약하기(goReqDto);
			int backResult = bookService.예약하기(backReqDto);
			
			CommonRespDto<Object> commonRespDto = new CommonRespDto<>();
			if(goResult == 1 && backResult == 1) {
				commonRespDto.setStatusCode(1);
			} else {
				commonRespDto.setStatusCode(-1);
			}
			
			String responseData = gson.toJson(commonRespDto);
			System.out.println("responseData : " +responseData);
			
		}
	}

}
