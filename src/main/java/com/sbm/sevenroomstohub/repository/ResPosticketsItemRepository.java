package com.sbm.sevenroomstohub.repository;

import com.sbm.sevenroomstohub.domain.ResPosticketsItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ResPosticketsItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResPosticketsItemRepository extends JpaRepository<ResPosticketsItem, Long> {}
