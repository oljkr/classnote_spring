package kr.co.itwill.bbs;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BbsCont {
	public BbsCont() {
		System.out.println("-----BbsCont() 객체 생성됨");
	}//end

	//결과확인 http://localhost:9095/bbs/create.do GET방식으로 호출
	@RequestMapping(value = "/bbs/create.do", method = RequestMethod.GET)
	/* 1)
	public ModelAndView bbsForm() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbs/bbsForm"); // /WEB-INF/views/bbs/bbsForm.jsp
		return mav;
		
	}//bbsForm() end
	*/
	//2) 위의 1)번과 동일하게 페이지 이동 가능하다
	public String bbsForm() {
		return "bbs/bbsForm"; // /WEB-INF/views/bbs/bbsForm.jsp
	}//bbsForm() end
	
	//동일한 요청명령어를 GET | POST 방식으로 구분해서 호출 가능하다
	@RequestMapping(value = "/bbs/create.do", method = RequestMethod.POST)
	/*
	public ModelAndView bbsIns(HttpServletRequest req) {
		//사용자가 입력 요청한 정보 가져오기
		String wname = req.getParameter("wname").trim();
		String subject = req.getParameter("subject").trim();
		String content = req.getParameter("content").trim();
		String passwd = req.getParameter("passwd").trim();
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbs/bbsResult");
		
		mav.addObject("wname", wname);
		mav.addObject("subject", subject);
		mav.addObject("content", content);
		mav.addObject("passwd", passwd);
		
		return mav;
		
	}//bbsIns() end
	*/
	
	//2)매개변수(parameter)가 DTO 객체인 경우
	//->해당클래스(BbsDTO)에 반드시 폼 컨트롤 요소이름으로 되어 있는 멤버변수와 각 getter와 setter가 있어야 함
	//@ModelAttribute 생략가능
	public ModelAndView bbsIns(@ModelAttribute BbsDTO dto) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("bbs/bbsResult2");
		mav.addObject("dto", dto);
		return mav;
	}//bbsIns() end
	
}//class end
