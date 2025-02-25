package org.unibl.etf.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.userPillar.UserPillar;

@RestController
@CrossOrigin
public class RegistrationController {
    @Autowired
    private org.unibl.etf.users.UserService userService;

    @Autowired
    private org.unibl.etf.userPillar.UserPillarService userPillarService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user)  {
        try{
            if(userService.findByUsername(user.getUsername())!=null){
                return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
            }
            userService.saveUser(user);
            createFourPillarsForNewUser(user);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occured during registration. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private void createFourPillarsForNewUser(User user) {
        int userId = user.getId();
        userPillarService.saveUserPillar(new UserPillar(1, userId));
        userPillarService.saveUserPillar(new UserPillar(2, userId));
        userPillarService.saveUserPillar(new UserPillar(3, userId));
        userPillarService.saveUserPillar(new UserPillar(4, userId));

    }
}