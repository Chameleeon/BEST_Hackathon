package org.unibl.etf.users;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

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
