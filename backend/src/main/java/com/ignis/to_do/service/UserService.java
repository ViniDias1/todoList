package com.ignis.to_do.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.dto.UserDTO;
import com.ignis.to_do.model.User;
import com.ignis.to_do.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    // VERIFICAR SE PRECISER private final
    @Autowired
    private UserRepository userRepository;


    public UserDTO createUser(UserDTO userDTO){
        User user = new User(userDTO.getName(), userDTO.getEmail());
        user = userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    public UserDTO getUserDTO(long id){
        User user = userRepository.findById(id).get();
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    public Iterable<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(user -> new UserDTO(user.getId(),
            user.getName(), user.getEmail())).toList();
    }

    
    @Transactional
    public UserDTO updateUser(long id, UserDTO userDTO) {
        userRepository.updateUser(id, userDTO.getName(), userDTO.getEmail());
        return new UserDTO(id, userDTO.getName(), userDTO.getEmail());
    }


    public void deleteUser(Long id){ 
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    public boolean validateUser(String name, String email){
        return userRepository.existsByNameAndEmail(name, email);
    }  

    public User getUser(Long id){
        return userRepository.findById(id).get();
    }
}
