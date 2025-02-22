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

    @Autowired
    private org.unibl.etf.pillars.PillarTypeService pillarTypeService;

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
    public ResponseEntity<?> getChallengeByName(@PathVariable("name") String name) {
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

    @PostMapping("/challenge/filter")
    public ResponseEntity<?> getChallengesByPillarTypeName(@RequestBody String pillarTypeName) {
        try {
            System.out.println(pillarTypeName);
            PillarType pillar = pillarTypeService.findByTypeName(pillarTypeName);
            System.out.println(pillar);
            Iterable<Challenge> challenges = challengeService.findByPillarId(pillarTypeService.findByTypeName(pillarTypeName).getId());
            if (challenges != null) {
                return new ResponseEntity<>(challenges, HttpStatus.OK); // Vraća Challenge ako je pronađen
            } else {
                return new ResponseEntity<>("Challenges associated with pillar '" + pillarTypeName + "' not found.", HttpStatus.NOT_FOUND); // Vraća 404 ako nije pronađen
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while fetching the PillarType. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR); // Vraća 500 ako dođe do greške
        }
    }

    @GetMapping("/challenge")
    public ResponseEntity<?> getAllChallenges() {
        try {
            Iterable<Challenge> challenges = challengeService.findAll();
            if (challenges != null) {
                return new ResponseEntity<>(challenges, HttpStatus.OK); // Vraća Challenge ako je pronađen
            } else {
                return new ResponseEntity<>("Challenges not found.", HttpStatus.NOT_FOUND); // Vraća 404 ako nije pronađen
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while fetching the PillarType. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR); // Vraća 500 ako dođe do greške
        }
    }
}