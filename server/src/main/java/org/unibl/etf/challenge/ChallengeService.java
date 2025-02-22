package org.unibl.etf.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    public void saveChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
    }
    public Challenge findByName(String name) {
        return challengeRepository.findByName(name);
    }
    public Iterable<Challenge> findByPillarId(int pillarId) { return challengeRepository.findByPillarId(pillarId); }
    public Iterable<Challenge> findAll() { return challengeRepository.findAll(); }

}