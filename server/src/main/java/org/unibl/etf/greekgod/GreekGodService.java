package org.unibl.etf.greekgod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreekGodService {

    @Autowired
    private GreekGodRepository greekGodRepository;

    public void saveGreekGod(GreekGod god) {
        greekGodRepository.save(god);
    }
    public GreekGod findByName(String name) {
        return greekGodRepository.findByName(name);
    }

}