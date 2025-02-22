package org.unibl.etf.challenge;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, Long> {
    Challenge findByName(String name);
    Iterable<Challenge> findByPillarId(int pillarId);

}