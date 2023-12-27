package com.sbm.sevenroomstohub.repository;

import com.sbm.sevenroomstohub.domain.ResCustomField;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ResCustomField entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResCustomFieldRepository extends JpaRepository<ResCustomField, Long> {}
