package org.unibl.etf.users;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.unibl.etf.pillars.PillarType;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Integer userId) {
        try {
            User user = userService.findById(userId);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK); // Vraća PillarType ako je pronađen
            } else {
                return new ResponseEntity<>("User with ID '" + userId + "' not found.", HttpStatus.NOT_FOUND); // Vraća 404 ako nije pronađen
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while fetching the PillarType. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR); // Vraća 500 ako dođe do greške
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser() {
        try {
            Iterable<User> user = userService.getAllUsers();
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK); // Vraća PillarType ako je pronađen
            } else {
                return new ResponseEntity<>("Users not found.", HttpStatus.NOT_FOUND); // Vraća 404 ako nije pronađen
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while fetching the PillarType. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR); // Vraća 500 ako dođe do greške
        }
    }


//    @Autowired
//    private AdministratorService administratorService;

//    @GetMapping("/checkAdmin")
//    public ResponseEntity<String> isAdmin(@RequestParam("username") String username){
//        Boolean isAdmin = administratorService.checkIsAdministrator(username);
//        if (isAdmin) {
//            return new ResponseEntity<>("User is admin", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("User is unauthorized", HttpStatus.OK);
//        }    }
}
