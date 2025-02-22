package org.unibl.etf.pillars;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PillarTypeRepository extends CrudRepository<PillarType, Long> {
    PillarType findByTypeName(String typeName);
}