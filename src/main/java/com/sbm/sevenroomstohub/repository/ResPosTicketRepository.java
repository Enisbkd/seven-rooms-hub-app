package com.sbm.sevenroomstohub.repository;

import com.sbm.sevenroomstohub.domain.ResPosTicket;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ResPosTicket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResPosTicketRepository extends JpaRepository<ResPosTicket, Long> {}
