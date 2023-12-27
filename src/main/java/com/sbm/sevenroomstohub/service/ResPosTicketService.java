package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.service.dto.ResPosTicketDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.ResPosTicket}.
 */
public interface ResPosTicketService {
    /**
     * Save a resPosTicket.
     *
     * @param resPosTicketDTO the entity to save.
     * @return the persisted entity.
     */
    ResPosTicketDTO save(ResPosTicketDTO resPosTicketDTO);

    /**
     * Updates a resPosTicket.
     *
     * @param resPosTicketDTO the entity to update.
     * @return the persisted entity.
     */
    ResPosTicketDTO update(ResPosTicketDTO resPosTicketDTO);

    /**
     * Partially updates a resPosTicket.
     *
     * @param resPosTicketDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ResPosTicketDTO> partialUpdate(ResPosTicketDTO resPosTicketDTO);

    /**
     * Get all the resPosTickets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ResPosTicketDTO> findAll(Pageable pageable);

    /**
     * Get the "id" resPosTicket.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResPosTicketDTO> findOne(Long id);

    /**
     * Delete the "id" resPosTicket.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
