package com.sbm.sevenroomstohub.repository;

import com.sbm.sevenroomstohub.domain.Table;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Table entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TableRepository extends JpaRepository<Table, Long> {}
