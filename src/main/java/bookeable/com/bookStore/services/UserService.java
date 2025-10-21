package bookeable.com.bookStore.services;

import bookeable.com.bookStore.dtos.UpdateUserNameDTO;
import bookeable.com.bookStore.models.User;
import bookeable.com.bookStore.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void updateUserName(UpdateUserNameDTO userName, long id){

           Optional<User> userOptional = userRepository.findById(id);


           if(userOptional.isPresent()){

               User user = userOptional.get();
               String name = user.getName();
               System.out.println("Name: "+ name);
               user.setName(userName.getName());
               userRepository.save(user);

           }else{
               throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
           }

         // throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
    }

    public void deleteUser(Long id){

        userRepository.deleteById(id);

    }
}
