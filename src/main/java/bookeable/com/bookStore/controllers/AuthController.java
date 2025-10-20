package bookeable.com.bookStore.controllers;

import bookeable.com.bookStore.dtos.AuthenticationDTO;
import bookeable.com.bookStore.dtos.LoginResponseDTO;
import bookeable.com.bookStore.dtos.RegisterDTO;
import bookeable.com.bookStore.models.User;
import bookeable.com.bookStore.repositories.UserRepository;
import bookeable.com.bookStore.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService){
        this.authenticationManager =  authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO auth){

        var usernamePassword = new UsernamePasswordAuthenticationToken(auth.email(), auth.password());
        var auth2 = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth2.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.userRepository.findByEmail(data.email()) != null){
           return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), encryptedPassword, data.email(),  data.role());

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();



    }

}
