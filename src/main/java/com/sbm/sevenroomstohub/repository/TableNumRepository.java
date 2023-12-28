package com.sbm.sevenroomstohub.repository;

import com.sbm.sevenroomstohub.domain.TableNum;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TableNum entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TableNumRepository extends JpaRepository<TableNum, Long> {}
