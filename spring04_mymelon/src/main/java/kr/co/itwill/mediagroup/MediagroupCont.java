package kr.co.itwill.mediagroup;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MediagroupCont {
	
	@Autowired
	private MediagroupDAO dao;
	
	public MediagroupCont() {
		System.out.println("-----MediagroupCont() 객체 생성됨");
	}//end
	
	//미디어그룹 쓰기 페이지 호출
	//결과확인 http://localhost:9095/mediagroup/create.do
	@RequestMapping(value = "mediagroup/create.do", method = RequestMethod.GET)
	public String createForm() {
		return "mediagroup/createForm";
	}//createForm() end
	
	@RequestMapping(value = "mediagroup/create.do", method = RequestMethod.POST)
	public ModelAndView	createProc(@ModelAttribute MediagroupDTO dto) {
		ModelAndView mav=new ModelAndView();
		
		int cnt=dao.create(dto);
		if(cnt==0) {
			mav.setViewName("mediagroup/msgView");
			String msg1="<p>미디어 그룹 등록 실패</p>";
			String img="<img src='../images/kuromi1.png' width='200px'>";
			String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
			mav.addObject("msg1", msg1);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		}else {
			mav.setViewName("redirect:/mediagroup/list.do");
		}//if end
		return mav;		
	}//createProc() end
	
	/* 1) 페이징 없는 목록
	@RequestMapping("mediagroup/list.do")
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mediagroup/list");
		
		int totalRowCount=dao.totalRowCount();
		List<MediagroupDTO> list=dao.list();
		
		mav.addObject("list", list);
		mav.addObject("count", totalRowCount);
		
		return mav;
	}//list() end
	*/
	
	@RequestMapping("mediagroup/list.do")
	public ModelAndView list(HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mediagroup/list");
		
		int totalRowCount=dao.totalRowCount(); //총 글갯수
		
		//페이징
		int numPerPage = 5; //한 페이지당 레코드 갯수
		int pagePerBlock = 10; //페이지 리스트
		
		String pageNum=req.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		
		//현재 페이지가 2페이지라고 하면, 
		int currentPage =Integer.parseInt(pageNum);		// 2
		int startRow    =(currentPage-1)*numPerPage+1;  // 6 = (2-1)*5+1
		int endRow      =currentPage*numPerPage;		// 10 = 2*5
		
		//페이지 수
		double totcnt =(double)totalRowCount/numPerPage; // 7.4=37줄/5개씩
		int totalPage =(int)Math.ceil(totcnt);			 // 8 실제로 8페이지 끝....
		
		//현재 페이지가 2페이지라고 하면, 
		double d_page =(double)currentPage/pagePerBlock; //1.2 = 12/10
		int Pages     =(int)Math.ceil(d_page)-1; // 0 = 1-1
		int startPage =Pages*pagePerBlock;		 // 0 = 0*10
		int endPage   =startPage+pagePerBlock+1; // 11 =  0 + 10 + 1
		
		List list=null;
		if(totalRowCount>0) {
			list=dao.list2(startRow, endRow);
		}else {
			list=Collections.EMPTY_LIST;
		}//if end
		
		mav.addObject("pageNum", currentPage);
		mav.addObject("count", totalRowCount);
		mav.addObject("totalPage", totalPage);
		mav.addObject("startPage", startPage);
		mav.addObject("endPage", endPage);
		mav.addObject("list", list);
		
		return mav;
	}//list() end
	
	@RequestMapping(value = "mediagroup/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteForm(int mediagroupno) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mediagroup/deleteForm");
		mav.addObject("mediagroupno", mediagroupno);
		return mav;
	}//deleteForm() end
	
	@RequestMapping(value = "mediagroup/delete.do", method = RequestMethod.POST)
	public ModelAndView deleteProc(int mediagroupno) {
		ModelAndView mav=new ModelAndView();
		int cnt=dao.delete(mediagroupno);
		if(cnt==0) {
			mav.setViewName("mediagroup/msgView");
			String msg1="<p>미디어 그룹 삭제 실패</p>";
			String img="<img src='../images/kuromi1.png' width='200px'>";
			String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
			mav.addObject("msg1", msg1);
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		}else {
			mav.setViewName("redirect:/mediagroup/list.do");
		}//if end
		
		return mav;
	}//deleteProc() end
	
	@RequestMapping(value = "mediagroup/update.do", method = RequestMethod.GET)
	public ModelAndView updateForm(int mediagroupno) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mediagroup/updateForm");
		//mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dao.read(mediagroupno));
		return mav;
	}//updateForm() end
	
	@RequestMapping(value = "mediagroup/update.do", method = RequestMethod.POST)
	public ModelAndView updateProc(@ModelAttribute MediagroupDTO dto) {
		ModelAndView mav=new ModelAndView();
		
		int cnt=dao.update(dto);
		if(cnt==0) {
			mav.setViewName("mediagroup/msgView");
			String img="<img src='../images/kuromi1.png' width='200px'>";
			String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
			String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
			mav.addObject("msg1", "<p>미디어 그룹 수정 실패</p>");
			mav.addObject("img", img);
			mav.addObject("link1", link1);
			mav.addObject("link2", link2);
		}else {
			mav.setViewName("redirect:/mediagroup/list.do");
		}//if end
		
		return mav;
	}//updateProc() end
	
}//class end
