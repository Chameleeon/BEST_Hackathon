package org.unibl.etf.greekgod;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreekGodRepository extends CrudRepository<GreekGod, Long> {
    GreekGod findByName(String name);
}