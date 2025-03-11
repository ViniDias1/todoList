package com.ignis.to_do.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignis.to_do.dto.UserDTO;
import com.ignis.to_do.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
// @AllArgsConstructor Analisar a necesseidade para evitar o @Autowired
// @NoArgsConstructor
@Tag(name = "User Controller", description = "Gerenciamento de Usuários")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {

        return userService.createUser(userDTO);
    }

    @GetMapping("/{id}")
    public UserDTO getUserDTO(@PathVariable long id) {

        return userService.getUserDTO(id);
    }

    @GetMapping("/all")
    public Iterable<UserDTO> getAllUsers() {

        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(
        @PathVariable long id,
        @RequestBody UserDTO userDTO) {

        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        
        userService.deleteUser(id);
    }

    //TESTANDO LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        
        boolean isValid = userService.validateUser(
            userDTO.getName(), userDTO.getEmail());
        
        if (isValid) {
            return ResponseEntity.ok("Logado com sucesso");
        } 
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            "Usuário não encontrado");
    }
}
