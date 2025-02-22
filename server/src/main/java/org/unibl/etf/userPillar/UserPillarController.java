package org.unibl.etf.userPillar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.challenge.Challenge;
import org.unibl.etf.pillars.PillarType;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class UserPillarController {

    @Autowired
    private UserPillarService userPillarService;

    @Autowired
    private org.unibl.etf.users.UserService userService;

    @PostMapping("/userPillar")
    public ResponseEntity<String> saveUserPillar(@RequestBody UserPillar userPillar)  {
        try {
            userPillarService.saveUserPillar(userPillar);
            return new ResponseEntity<>("UserPillar saved successfully", HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occured during saving. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/userPillar/{username}") // vraca korisnicke stubove korisnika sa odgovarajucim username-om
    public ResponseEntity<?> getUserPillarsByUsername(@PathVariable("username") String username) {
        try {
            Iterable<UserPillar> userPillars = userPillarService.getByUserId(userService.findByUsername(username).getId());
            if (userPillars != null) {
                return new ResponseEntity<>(userPillars, HttpStatus.OK); // Vraća UserPillars ako je pronađen
            } else {
                return new ResponseEntity<>("User pillars associated with user '" + username + "' not found.", HttpStatus.NOT_FOUND); // Vraća 404 ako nije pronađen
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while fetching the UserPillars. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR); // Vraća 500 ako dođe do greške
        }
    }

//    @GetMapping("/challenge/{name}")
//    public ResponseEntity<?> getPillarByTypeName(@PathVariable("name") String name) {
//        try {
//            Activity challenge = activityService.findByName(name);
//            if (challenge != null) {
//                return new ResponseEntity<>(challenge, HttpStatus.OK); // Vraća Challenge ako je pronađen
//            } else {
//                return new ResponseEntity<>("Challenge with name '" + name + "' not found.", HttpStatus.NOT_FOUND); // Vraća 404 ako nije pronađen
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("An error occurred while fetching the PillarType. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR); // Vraća 500 ako dođe do greške
//        }
//    }
}