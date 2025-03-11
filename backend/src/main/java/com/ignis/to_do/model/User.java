package com.ignis.to_do.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "users")
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "owner")
    private List<Board> boards;
    @ManyToMany
    @JoinTable(
        name = "user_favorite_board",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "board_id")
    )
    private List<Board> favoriteBoards;

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public Board createBoard(String title){

        Board board = new Board();
        board.setTitle(title);
        board.setOwner(this);
        return board;
    }

    public void deleteBoard(Board board){
        
        this.boards.remove(board);
    }   
}