package com.kh.myspringb.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.myspringb.board.model.dao.BoardDao;
import com.kh.myspringb.board.model.domain.Board;

@Service("bService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao bDao;
	
	@Override
	public int listCount() {
		return bDao.listCount();
	}

	@Override
	public int insertBoard(Board b) {
		return bDao.insertBoard(b);
	}

	@Override
	public List<Board> selectList() {
		return bDao.selectList();
	}

}
