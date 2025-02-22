package org.unibl.etf.pillars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PillarTypeService {

    @Autowired
    private org.unibl.etf.pillars.PillarTypeRepository pillarsRepository;

    public void savePilar(PillarType user) {
        pillarsRepository.save(user);
    }
    public PillarType findByTypeName(String typeName) {
        return pillarsRepository.findByTypeName(typeName);
    }
    public PillarType findById(int id) {
        return pillarsRepository.findById(id);
    }

}