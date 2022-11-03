package kr.co.itwill.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MediaCont {
	
	@Autowired
	private MediaDAO dao;
	
	public MediaCont() {
		System.out.println("-----MediaCont() 객체 생성됨");
	}//end
	
	//결과확인 http://localhost:9095/mediagroup/create.do
	
	@RequestMapping("/media/list.do")
	public ModelAndView	list(int mediagroupno) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("media/list");
		mav.addObject("list", dao.list(mediagroupno));
		mav.addObject("mediagroupno", mediagroupno); //부모글번호
		return mav;
	}//list() end
	
	@RequestMapping(value = "media/create.do", method = RequestMethod.GET)
	public ModelAndView createForm(int mediagroupno) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("media/createForm");
		mav.addObject("mediagroupno", mediagroupno); //부모글번호
		return mav;
	}//createForm() end
	
}//class end
