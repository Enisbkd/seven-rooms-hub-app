package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.service.dto.CustomFieldDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.CustomField}.
 */
public interface CustomFieldService {
    /**
     * Save a customField.
     *
     * @param customFieldDTO the entity to save.
     * @return the persisted entity.
     */
    CustomFieldDTO save(CustomFieldDTO customFieldDTO);

    /**
     * Updates a customField.
     *
     * @param customFieldDTO the entity to update.
     * @return the persisted entity.
     */
    CustomFieldDTO update(CustomFieldDTO customFieldDTO);

    /**
     * Partially updates a customField.
     *
     * @param customFieldDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CustomFieldDTO> partialUpdate(CustomFieldDTO customFieldDTO);

    /**
     * Get all the customFields.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CustomFieldDTO> findAll(Pageable pageable);

    /**
     * Get the "id" customField.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomFieldDTO> findOne(Long id);

    /**
     * Delete the "id" customField.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
