package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.service.dto.ClientPhotoDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.sevenroomstohub.domain.ClientPhoto}.
 */
public interface ClientPhotoService {
    /**
     * Save a clientPhoto.
     *
     * @param clientPhotoDTO the entity to save.
     * @return the persisted entity.
     */
    ClientPhotoDTO save(ClientPhotoDTO clientPhotoDTO);

    /**
     * Updates a clientPhoto.
     *
     * @param clientPhotoDTO the entity to update.
     * @return the persisted entity.
     */
    ClientPhotoDTO update(ClientPhotoDTO clientPhotoDTO);

    /**
     * Partially updates a clientPhoto.
     *
     * @param clientPhotoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClientPhotoDTO> partialUpdate(ClientPhotoDTO clientPhotoDTO);

    /**
     * Get all the clientPhotos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClientPhotoDTO> findAll(Pageable pageable);

    /**
     * Get all the ClientPhotoDTO where Client is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<ClientPhotoDTO> findAllWhereClientIsNull();

    /**
     * Get the "id" clientPhoto.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClientPhotoDTO> findOne(Long id);

    /**
     * Delete the "id" clientPhoto.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
