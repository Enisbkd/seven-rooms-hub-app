package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.service.dto.TableNumDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.TableNum}.
 */
public interface TableNumService {
    /**
     * Save a tableNum.
     *
     * @param tableNumDTO the entity to save.
     * @return the persisted entity.
     */
    TableNumDTO save(TableNumDTO tableNumDTO);

    /**
     * Updates a tableNum.
     *
     * @param tableNumDTO the entity to update.
     * @return the persisted entity.
     */
    TableNumDTO update(TableNumDTO tableNumDTO);

    /**
     * Partially updates a tableNum.
     *
     * @param tableNumDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TableNumDTO> partialUpdate(TableNumDTO tableNumDTO);

    /**
     * Get all the tableNums.
     *
     * @return the list of entities.
     */
    List<TableNumDTO> findAll();

    /**
     * Get the "id" tableNum.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TableNumDTO> findOne(Long id);

    /**
     * Delete the "id" tableNum.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
