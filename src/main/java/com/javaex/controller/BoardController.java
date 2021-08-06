package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller

public class BoardController {

	@Autowired
	private BoardService boardService;

	
	//게시판 리스트 (페이징)
	@RequestMapping(value = "/board/list2", method = { RequestMethod.GET, RequestMethod.POST })
	public String list2(Model model,
			@RequestParam(value="crtPage", required = false, defaultValue = "1")int crtPage,
			@RequestParam(value="keyword", required = false, defaultValue = "")String keyword) {
		System.out.println("BoardController.list2");
		System.out.println(crtPage);
		
		
		
		Map<String,Object> listMap = boardService.getList2(crtPage,keyword);
		
		System.out.println("----------"+listMap);
		
		
		model.addAttribute("listMap",listMap);
		return"board/list2";
	}
	
	
	
	
	
	
	
	@RequestMapping(value = "/board/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(Model model, @RequestParam("no") int no) {

		System.out.println("[boardController.read]");
		System.out.println(no);

		BoardVo boardVo = boardService.getBoard(no);

		System.out.println(boardVo);

		model.addAttribute("boardVo", boardVo);

		return "board/read";
	}

	@RequestMapping(value = "/board/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("boardController.list");

		// 서비스
		List<BoardVo> boardList = boardService.viewsList();

		System.out.println("-------리턴");
		System.out.println(boardList);

		model.addAttribute("boardList", boardList);

		return "board/list";

	}

	@RequestMapping(value = "/board/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute BoardVo boardVo) {

		System.out.println("boardController.delete");
		System.out.println(boardVo);

		boardService.delete(boardVo);

		return "redirect:/board/list";

	}

	// 글쓰기 폼
	@RequestMapping(value = "/board/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {

		return "board/writeForm";

	}

	// 글쓰기
	@RequestMapping(value = "/board/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo boardVo) {

		System.out.println("BoardController.write");
		System.out.println(boardVo);

		boardService.write(boardVo);

		return "redirect:/board/list2";

	}

	// 수정폼
	@RequestMapping(value = "/board/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam("no") int no, Model model, HttpSession session) {

		System.out.println("modifyForm접속");
		System.out.println(no);

		BoardVo boardVo = boardService.listOne(no);
		UserVo authUser = (UserVo)session.getAttribute("authUser");

		int authUserNo = authUser.getNo();
		int userNo = boardVo.getUserNo();

		if(authUser == null) {
			System.out.println("로그인 안한 경우");
			
			return "redirect:/board/list";
		}
		
		if (authUserNo == userNo) {
			System.out.println("사용자의 게시물인 경우");
			model.addAttribute("boardVo", boardVo);

			return "board/modifyForm";
		}else {
			System.out.println("사용자의 게시물이 아닌 경우");
			
			return "redirect:/board/list";
		}

	}

	// 수정
	@RequestMapping(value = "/board/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("modify접속");
		System.out.println(boardVo);

		int count = boardService.modify(boardVo);

		return "redirect:/board/list";

	}

}
