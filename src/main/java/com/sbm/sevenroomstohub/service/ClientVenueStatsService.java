package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.service.dto.ClientVenueStatsDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.ClientVenueStats}.
 */
public interface ClientVenueStatsService {
    /**
     * Save a clientVenueStats.
     *
     * @param clientVenueStatsDTO the entity to save.
     * @return the persisted entity.
     */
    ClientVenueStatsDTO save(ClientVenueStatsDTO clientVenueStatsDTO);

    /**
     * Updates a clientVenueStats.
     *
     * @param clientVenueStatsDTO the entity to update.
     * @return the persisted entity.
     */
    ClientVenueStatsDTO update(ClientVenueStatsDTO clientVenueStatsDTO);

    /**
     * Partially updates a clientVenueStats.
     *
     * @param clientVenueStatsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClientVenueStatsDTO> partialUpdate(ClientVenueStatsDTO clientVenueStatsDTO);

    /**
     * Get all the clientVenueStats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClientVenueStatsDTO> findAll(Pageable pageable);

    /**
     * Get all the ClientVenueStatsDTO where Client is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<ClientVenueStatsDTO> findAllWhereClientIsNull();

    /**
     * Get the "id" clientVenueStats.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClientVenueStatsDTO> findOne(Long id);

    /**
     * Delete the "id" clientVenueStats.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
