package org.unibl.etf.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
    @Autowired
    private org.unibl.etf.users.UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user)  {
        try{
            if(userService.findByUsername(user.getUsername())!=null){
                return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
            }
            userService.saveUser(user);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occured during registration. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}