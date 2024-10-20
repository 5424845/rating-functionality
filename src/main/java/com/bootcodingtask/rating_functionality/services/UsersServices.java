package com.bootcodingtask.rating_functionality.services;

import com.bootcodingtask.rating_functionality.models.UsersResponse;
import com.bootcodingtask.rating_functionality.entities.Users;
import com.bootcodingtask.rating_functionality.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsersServices {
  @Autowired
    private UsersRepository usersRepository;


  public Users createUser(Users users){
     return  usersRepository.save(users);
  }

  public Optional<Users> getUsersById(Integer id){
      return usersRepository.findById(id);
  }

  public List<Users> getAllUsers(){
      return usersRepository.findAll();
  }

  public Users updateUser(Integer id , Users updateUser){
      Optional<Users> existingUser = usersRepository.findById(id);
      if(existingUser.isPresent()){
          Users user = existingUser.get();
          user.setUsername(updateUser.getUsername());
          user.setEmail(updateUser.getEmail());
          user.setPasswordHash(updateUser.getPasswordHash());
          return  usersRepository.save(user);
      }
      return null;
  }

  public boolean deleteUserByID(Integer id){
      if(usersRepository.existsById(id)){
          usersRepository.deleteById(id);
          return true;
      }
      return false;
  }

    public UsersResponse mapToUsersResponse(Users user) {
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setUser_id(user.getUserId());
        usersResponse.setUsername(user.getUsername());
        usersResponse.setEmail(user.getEmail());
        usersResponse.setCreatedAt(user.getCreatedAt().toString());
        return usersResponse;
    }

}
