package org.unibl.etf.pillars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class PillarTypeController {

    @Autowired
    private org.unibl.etf.pillars.PillarTypeService pillarTypeService;

    @PostMapping("/pillar")
    public ResponseEntity<String> addPillar(@RequestBody PillarType pillar)  {
        try {
            pillarTypeService.savePilar(pillar);
            return new ResponseEntity<>("PillarType added successfully", HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occured during saving. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pillar/{typeName}")
    public ResponseEntity<?> getPillarByTypeName(@PathVariable("typeName") String typeName) {
        try {
            PillarType pillar = pillarTypeService.findByTypeName(typeName);
            if (pillar != null) {
                return new ResponseEntity<>(pillar, HttpStatus.OK); // Vraća PillarType ako je pronađen
            } else {
                return new ResponseEntity<>("PillarType with typeName '" + typeName + "' not found.", HttpStatus.NOT_FOUND); // Vraća 404 ako nije pronađen
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while fetching the PillarType. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR); // Vraća 500 ako dođe do greške
        }
    }

}