package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.service.dto.ResPosticketsItemDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.ResPosticketsItem}.
 */
public interface ResPosticketsItemService {
    /**
     * Save a resPosticketsItem.
     *
     * @param resPosticketsItemDTO the entity to save.
     * @return the persisted entity.
     */
    ResPosticketsItemDTO save(ResPosticketsItemDTO resPosticketsItemDTO);

    /**
     * Updates a resPosticketsItem.
     *
     * @param resPosticketsItemDTO the entity to update.
     * @return the persisted entity.
     */
    ResPosticketsItemDTO update(ResPosticketsItemDTO resPosticketsItemDTO);

    /**
     * Partially updates a resPosticketsItem.
     *
     * @param resPosticketsItemDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ResPosticketsItemDTO> partialUpdate(ResPosticketsItemDTO resPosticketsItemDTO);

    /**
     * Get all the resPosticketsItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ResPosticketsItemDTO> findAll(Pageable pageable);

    /**
     * Get the "id" resPosticketsItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResPosticketsItemDTO> findOne(Long id);

    /**
     * Delete the "id" resPosticketsItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
