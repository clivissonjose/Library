package bookeable.com.bookStore.controllers;

import bookeable.com.bookStore.dtos.UpdatePasswordDTO;
import bookeable.com.bookStore.dtos.UpdateUserNameDTO;
import bookeable.com.bookStore.models.User;
import bookeable.com.bookStore.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

     private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> listAll(){
       List<User> allUsers =  userService.findAll();

       return ResponseEntity.ok(allUsers);
    }

    @PatchMapping("/{id}")
    public ResponseEntity  updateUserName(@RequestBody @Valid UpdateUserNameDTO userName, @PathVariable Long id ){
          userService.updateUserName(userName, id);

          return ResponseEntity.ok("Nome Atualizado!");
    }

    @PatchMapping("/password/{id}")
    public ResponseEntity updatePassword(@RequestBody @Valid  UpdatePasswordDTO dto, @PathVariable Long id){

        userService.updatePassword(dto, id);

        return ResponseEntity.ok("Senha Atualizada!");
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
       
        User user =  userService.findById(id);

         if(user != null){
             return ResponseEntity.ok(user);
         }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
