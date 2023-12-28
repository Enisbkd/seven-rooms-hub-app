package com.sbm.sevenroomstohub.repository;

import com.sbm.sevenroomstohub.domain.MemberGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MemberGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MemberGroupRepository extends JpaRepository<MemberGroup, Long> {}
