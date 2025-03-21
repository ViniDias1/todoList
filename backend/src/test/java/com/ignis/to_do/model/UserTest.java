package com.ignis.to_do.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {

        user = new User("Vinicius Dias", "vinicius@email.com", "123456");
        user.setBoards(new ArrayList<>());
    }

    @Test
    void testCriacaoUsuario() {

        assertNotNull(user);
        assertEquals("Vinicius Dias", user.getName());
        assertEquals("vinicius@email.com", user.getEmail());
        assertEquals("123456", user.getPassword());
        assertTrue(user.getBoards().isEmpty());
    }

    @Test
    void testCreateBoard() {

        Board board = new Board("Projeto Ignis", user);
    
        user.createBoard(board);
    
        assertEquals(1, user.getBoards().size());
        assertEquals("Projeto Ignis", user.getBoards().get(0).getTitle());
        assertEquals(user, user.getBoards().get(0).getOwner());
    }  
}
