package com.kh.myspringb.board.controller;

import java.io.File;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.myspringb.board.model.domain.Board;
import com.kh.myspringb.board.model.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService bService;   
	
	// 게시글 작성 페이지
	@RequestMapping(value="/writerForm.do", method = RequestMethod.GET)
	public String boardInsertForm(ModelAndView mv) {
		return "board/writeForm";	  // View페이지에서 작성 후 form action = "bInsert.do" 로 들어오도록 함.
	}
	

	// 작성된 글을 insert
	@RequestMapping(value="/bInsert.do", method =  RequestMethod.POST)
	public ModelAndView boardInsert(Board b, @RequestParam(name="upfile") MultipartFile report, HttpServletRequest request, ModelAndView mv) {
		
		// 첨부파일 저장
		if(report!=null && !report.equals("")) {
			saveFile(report, request);
		}		
		b.setBoard_file(report.getOriginalFilename()); 		// 저장된 파일명을 vo에 set		
		
		bService.insertBoard(b);
		mv.setViewName("redirect:bList.do");   // insertBoard에 성공했다면   !!! View페이지로 이동하는 것이 아니라 컨트롤러 url 중 "게시글 리스트 select로 이동" 하는 "/bList.do"
		return mv;
		// 실패했다면
//		mv.setViewName("errorPage");   // errorPage 페이지로 이동
	}
	
	
	// 게시글 리스트 select
	@RequestMapping(value="/bList.do")
	public ModelAndView boardListService(ModelAndView mv) {
		mv.addObject("list", bService.selectList());
		mv.setViewName("board/blist");    // board/blist View페이지가 보여짐
		return mv;
	}
	
	org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'sqlSessionFactory' defined in ServletContext resource [/WEB-INF/spring/appServlet/servlet-context.xml]: Invocation of init method failed; nested exception is java.io.FileNotFoundException: class path resource [mybatis-config.xml] cannot be opened because it does not exist
	org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1578)
	org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:545)
	org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:482)
	org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:306)
	org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230)
	org.springframework.bean


	
	
	private void saveFile(MultipartFile report, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\uploadFiles";
		File folder = new File(savePath);
		if (!folder.exists()) {
			folder.mkdir(); // 폴더가 없다면 생성한다.
		}
		String filePath = null;
		try {
			// 파일 저장
			System.out.println(report.getOriginalFilename() + "을 저장합니다.");
			System.out.println("저장 경로 : " + savePath);

			filePath = folder + "\\" + report.getOriginalFilename();
			report.transferTo(new File(filePath)); // 파일을 저장한다
			System.out.println("파일 명 : " + report.getOriginalFilename());
			System.out.println("파일 경로 : " + filePath);
			System.out.println("파일 전송이 완료되었습니다.");
		} catch (Exception e) {
			System.out.println("파일 전송 에러 : " + e.getMessage());
		}
	}
	
}




















