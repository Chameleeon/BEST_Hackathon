package org.unibl.etf.userPillar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPillarService {

    @Autowired
    private UserPillarRepository userPillarRepository;

    public void saveUserPillar(UserPillar pillar) {
        userPillarRepository.save(pillar);
    }

    public Iterable<UserPillar> getByUserId(int userId) { return userPillarRepository.findByUserId(userId); }
//    public Activity findByName(String name) {
//        return activityRepository.findByName(name);
//    }

}