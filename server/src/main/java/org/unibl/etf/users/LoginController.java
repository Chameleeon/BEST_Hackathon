package org.unibl.etf.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private org.unibl.etf.users.UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request)  {
       String username = request.getUsername();
       String password = request.getPassword();

       User user = userService.findByUsername(username);

       if(user == null) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
       }
       if(bCryptPasswordEncoder.matches(password, user.getPassword())){
           return ResponseEntity.ok("Login successful");
       }
       else{
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");       }
    }
}
