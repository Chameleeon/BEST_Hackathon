package org.unibl.etf.userPillar;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPillarRepository extends CrudRepository<UserPillar, Long> {
//    Activity findByTime(String name);
}