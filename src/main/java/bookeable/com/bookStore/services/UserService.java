package bookeable.com.bookStore.services;

import bookeable.com.bookStore.dtos.UpdatePasswordDTO;
import bookeable.com.bookStore.dtos.UpdateUserNameDTO;
import bookeable.com.bookStore.models.User;
import bookeable.com.bookStore.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
               user.setName(userName.getName());
               userRepository.save(user);

           }else{
               throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
           }

         // throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
    }

    public void updatePassword(UpdatePasswordDTO dto, Long id){

        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User user = optionalUser.get();
        user.setPassword(encryptedPassword);
        userRepository.save(user);

    }

    public void deleteUser(Long id){

        userRepository.deleteById(id);

    }
}
