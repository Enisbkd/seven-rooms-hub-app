package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.service.dto.TableNumberDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.TableNumber}.
 */
public interface TableNumberService {
    /**
     * Save a tableNumber.
     *
     * @param tableNumberDTO the entity to save.
     * @return the persisted entity.
     */
    TableNumberDTO save(TableNumberDTO tableNumberDTO);

    /**
     * Updates a tableNumber.
     *
     * @param tableNumberDTO the entity to update.
     * @return the persisted entity.
     */
    TableNumberDTO update(TableNumberDTO tableNumberDTO);

    /**
     * Partially updates a tableNumber.
     *
     * @param tableNumberDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TableNumberDTO> partialUpdate(TableNumberDTO tableNumberDTO);

    /**
     * Get all the tableNumbers.
     *
     * @return the list of entities.
     */
    List<TableNumberDTO> findAll();

    /**
     * Get the "id" tableNumber.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TableNumberDTO> findOne(Long id);

    /**
     * Delete the "id" tableNumber.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
