package com.ignis.to_do.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignis.to_do.dto.BoardDTO;
import com.ignis.to_do.dto.TaskListDTO;
import com.ignis.to_do.service.BoardService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/board")
@Tag(name = "Board Controller", description = "Gerenciamento de Boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/createBoard")
    public ResponseEntity<BoardDTO> createBoard(
        @RequestBody BoardDTO boardDTO){

        return ResponseEntity.ok(boardService.createBoard(boardDTO));
    }
    
    @GetMapping("/getBoard/{boardId}")
    public ResponseEntity<BoardDTO> getBoardDTO(
        @PathVariable Long boardId) {

        return ResponseEntity.ok(boardService.getBoardById(boardId));
    }   

    @GetMapping("/allBoards")
    public Iterable<BoardDTO> getAllBoards() {

        return boardService.getAllBoards();
    }

    @GetMapping("myBoards/{boardId}")
    public Iterable<BoardDTO> getMyBoardsByOwnerId(
        @PathVariable Long boardId) {
        
        return boardService.getMyBoardsByOwnerId(boardId);
    }

    @GetMapping("/isFavorite/{boardId}")
    public Boolean isFavorite(
        @PathVariable Long boardId) {  

        return boardService.isFavorite(boardId);
    }

    @GetMapping("myTasksByBoard/{boardId}")
    public Iterable<TaskListDTO> myTasksListsByBoard(
        @PathVariable Long boardId){

        return boardService.myTasksListsByBoardId(boardId);
    }

    @PutMapping("/updateBoardTitle")
    public BoardDTO updateBoardTitle(
        @RequestBody BoardDTO boardDTO) {
            
        return boardService.updateBoardTitle(boardDTO);
    }

    @PutMapping("/toggleFavorite/{boardId}")
    public void toggleFavorite(@PathVariable Long boardId) {
        boardService.toggleFavorite(boardId);
    }
    
    @DeleteMapping("/deleteBoard/{boardId}")
    public void deleteBoardById(@PathVariable Long boardId) {

        boardService.deleteBoardById(boardId);
    }
}
