package kr.co.itwill.book;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookCont {
	public BookCont() {
		System.out.println("-----BookCont()객체 생성됨");
	}//end
	
	//결과확인 http://localhost:9095/book/book.do
	
	@RequestMapping("book/book.do")
	public String bookTest() {
		return "book/bookTest";
	}//bookTest() end
	
	@RequestMapping("book/booksend.do")
	@ResponseBody
	public String bookSend(HttpServletRequest req) {
		//요청한 정보 가져오기
		int bookIndex=Integer.parseInt(req.getParameter("book"));
		
		String img[]= {"spring.jpg","android.jpg","jquery.jpg","jsmasterbook.jpg"};
		
		return img[bookIndex]; //응답메세지
		
	}//bookSend() end
	
	//////////////////////////////////////////////////////////
	
	//결과확인 http://localhost:9095/book/searchform.do
	
	@RequestMapping("book/searchform.do")
	public String bookSearch() {
		return "book/searchTest";
	}//bookSearch() end
	
	@RequestMapping("book/searchproc.do")
	@ResponseBody
	public String searchProc(HttpServletRequest req) {
		String keyword=req.getParameter("keyword").trim();
		String message=""; //응답메세지
		
		if(keyword.length()>0) { //검색어가 존재하는지?
			//예) 검색어 : 자바
			//   "자바" "자바 프로그래밍" "자바 안드로이드" 반환
			ArrayList<String> list=search(keyword);
			//message=list.toString();
			
			//응답메세지 -> 갯수|찾은 문자열,찾은 문자열,찾은 문자열,찾은 문자열, ~~
			//		  예) 3|"자바" "자바 프로그래밍" "자바 안드로이드"
			int size=list.size(); //3
			if(size>0) {
				message += size + "|";
				for(int i=0; i<size; i++) {
					String title=list.get(i);
					message += title;
					if(i<size-1) { //맨 마지막 책 제목에는 , 를 붙이지 않기 위함
						message += ",";
					}//if end
				}//for end
			}//if end
			
		}//if end
		
		return message;
	}//searchProc() end
	
	public ArrayList<String> search(String keyword){
		//검색하고자 하는 문자열 목록
		//예) where title like '%자바%'
		String[] keywords= {"Ajax", "Ajax 실전 프로그래밍", "자바",
							"웹프로그래밍", "웹마스터", "자바 프로그래밍",
							"자전거", "자라", "JSP 프로그래밍",
							"자바 안드로이드"};
		
		//keyword를 keywords배열에서 첫글자부터 비교해서
		//같으면 ArrayList에 저장해서 리턴한다
		ArrayList<String> list=new ArrayList<>();
		for(String word : keywords) {
			word=word.toUpperCase();
			if(word.startsWith(keyword.toUpperCase())) {
				list.add(word);
			}//if end
		}//for end
		
		return list; //"자바" "자바 프로그래밍" "자바 안드로이드"
	}//search() end

}//class end
