package com.kh.myspringb.board.model.service;

import java.util.List;

import com.kh.myspringb.board.model.domain.Board;

public interface BoardService {
	public int listCount();
	
	public int insertBoard(Board b);
	
	public List<Board> selectList();
}
