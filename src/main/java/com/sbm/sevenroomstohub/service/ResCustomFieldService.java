package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.service.dto.ResCustomFieldDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.ResCustomField}.
 */
public interface ResCustomFieldService {
    /**
     * Save a resCustomField.
     *
     * @param resCustomFieldDTO the entity to save.
     * @return the persisted entity.
     */
    ResCustomFieldDTO save(ResCustomFieldDTO resCustomFieldDTO);

    /**
     * Updates a resCustomField.
     *
     * @param resCustomFieldDTO the entity to update.
     * @return the persisted entity.
     */
    ResCustomFieldDTO update(ResCustomFieldDTO resCustomFieldDTO);

    /**
     * Partially updates a resCustomField.
     *
     * @param resCustomFieldDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ResCustomFieldDTO> partialUpdate(ResCustomFieldDTO resCustomFieldDTO);

    /**
     * Get all the resCustomFields.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ResCustomFieldDTO> findAll(Pageable pageable);

    /**
     * Get the "id" resCustomField.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResCustomFieldDTO> findOne(Long id);

    /**
     * Delete the "id" resCustomField.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
