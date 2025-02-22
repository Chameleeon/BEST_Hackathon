package org.unibl.etf.activity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {
//    Activity findByTime(String name);
}