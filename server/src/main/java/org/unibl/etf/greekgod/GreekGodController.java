package org.unibl.etf.greekgod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class GreekGodController {

    @Autowired
    private GreekGodService greekGodService;

    @PostMapping("/greekGod")
    public ResponseEntity<String> saveChallenge(@RequestBody GreekGod greekGod)  {
        try {
            greekGodService.saveGreekGod(greekGod);
            return new ResponseEntity<>("Greek god saved successfully", HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occured during saving. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/greekGod/{name}")
    public ResponseEntity<?> getPillarByTypeName(@PathVariable("name") String name) {
        try {
            GreekGod challenge = greekGodService.findByName(name);
            if (challenge != null) {
                return new ResponseEntity<>(challenge, HttpStatus.OK); // Vraća Challenge ako je pronađen
            } else {
                return new ResponseEntity<>("Greek god with name '" + name + "' not found.", HttpStatus.NOT_FOUND); // Vraća 404 ako nije pronađen
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while fetching the PillarType. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR); // Vraća 500 ako dođe do greške
        }
    }
}