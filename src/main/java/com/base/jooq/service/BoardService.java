package com.base.jooq.service;

import com.base.jooq.jooq.bean.tables.pojos.Board;
import com.base.jooq.jooq.bean.tables.records.BoardRecord;
import com.base.jooq.jooq.dao.BoardDao;
import com.base.jooq.jooq.dto.reference.BoardDto;
import com.base.jooq.jooq.dto.request.board.BoardPageReq;
import com.base.jooq.jooq.dto.request.board.BoardSaveReq;
import com.base.jooq.jooq.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService extends BaseService {

    private final BoardDao dao;
    private final ModelMapper mapper;

    @Transactional(readOnly = true)
    public List<BoardResponse> getAllBoard(BoardPageReq req) {
        List<BoardResponse> list = dao.getAllBoard(req);
        list.stream().peek(board -> board.setComment(dao.getCommentByBoardNo(board.getBoardNo())));

        return dao.getAllBoard(req);
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long boardNo) throws Exception{
        return dao.getBoardByNo(boardNo).orElseThrow(() ->
                new Exception("This data could not be found."));
    }

    public boolean save(BoardSaveReq req) throws Exception {
        if (Objects.isNull(req)) throw new Exception("This data is null.");

        BoardRecord record = mapper.map(req, BoardRecord.class);
        return dao.save(record);
    }

    public boolean remove(Long boardNo) throws Exception {
        if (Objects.isNull(boardNo) || boardNo < 1) throw new Exception("This data could not be found.");
        return dao.remove(boardNo);
    }
}
