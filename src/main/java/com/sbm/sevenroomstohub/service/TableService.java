package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.service.dto.TableDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.Table}.
 */
public interface TableService {
    /**
     * Save a table.
     *
     * @param tableDTO the entity to save.
     * @return the persisted entity.
     */
    TableDTO save(TableDTO tableDTO);

    /**
     * Updates a table.
     *
     * @param tableDTO the entity to update.
     * @return the persisted entity.
     */
    TableDTO update(TableDTO tableDTO);

    /**
     * Partially updates a table.
     *
     * @param tableDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TableDTO> partialUpdate(TableDTO tableDTO);

    /**
     * Get all the tables.
     *
     * @return the list of entities.
     */
    List<TableDTO> findAll();

    /**
     * Get the "id" table.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TableDTO> findOne(Long id);

    /**
     * Delete the "id" table.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
