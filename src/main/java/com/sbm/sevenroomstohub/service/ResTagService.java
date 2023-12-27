package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.service.dto.ResTagDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.ResTag}.
 */
public interface ResTagService {
    /**
     * Save a resTag.
     *
     * @param resTagDTO the entity to save.
     * @return the persisted entity.
     */
    ResTagDTO save(ResTagDTO resTagDTO);

    /**
     * Updates a resTag.
     *
     * @param resTagDTO the entity to update.
     * @return the persisted entity.
     */
    ResTagDTO update(ResTagDTO resTagDTO);

    /**
     * Partially updates a resTag.
     *
     * @param resTagDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ResTagDTO> partialUpdate(ResTagDTO resTagDTO);

    /**
     * Get all the resTags.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ResTagDTO> findAll(Pageable pageable);

    /**
     * Get the "id" resTag.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResTagDTO> findOne(Long id);

    /**
     * Delete the "id" resTag.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
