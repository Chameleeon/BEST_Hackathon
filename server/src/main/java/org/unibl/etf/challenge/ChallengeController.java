package org.unibl.etf.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.pillars.PillarType;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @PostMapping("/challenge")
    public ResponseEntity<String> saveChallenge(@RequestBody Challenge challenge)  {
        try {
            challengeService.saveChallenge(challenge);
            return new ResponseEntity<>("Challenge saved successfully", HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occured during saving. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/challenge/{name}")
    public ResponseEntity<?> getPillarByTypeName(@PathVariable("name") String name) {
        try {
            Challenge challenge = challengeService.findByName(name);
            if (challenge != null) {
                return new ResponseEntity<>(challenge, HttpStatus.OK); // Vraća Challenge ako je pronađen
            } else {
                return new ResponseEntity<>("Challenge with name '" + name + "' not found.", HttpStatus.NOT_FOUND); // Vraća 404 ako nije pronađen
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while fetching the PillarType. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR); // Vraća 500 ako dođe do greške
        }
    }
}