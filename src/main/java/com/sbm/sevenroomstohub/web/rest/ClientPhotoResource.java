package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.domain.ClientPhoto;
import com.sbm.sevenroomstohub.repository.ClientPhotoRepository;
import com.sbm.sevenroomstohub.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.ClientPhoto}.
 */
@RestController
@RequestMapping("/api/client-photos")
@Transactional
public class ClientPhotoResource {

    private final Logger log = LoggerFactory.getLogger(ClientPhotoResource.class);

    private static final String ENTITY_NAME = "clientPhoto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientPhotoRepository clientPhotoRepository;

    public ClientPhotoResource(ClientPhotoRepository clientPhotoRepository) {
        this.clientPhotoRepository = clientPhotoRepository;
    }

    /**
     * {@code POST  /client-photos} : Create a new clientPhoto.
     *
     * @param clientPhoto the clientPhoto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clientPhoto, or with status {@code 400 (Bad Request)} if the clientPhoto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ClientPhoto> createClientPhoto(@RequestBody ClientPhoto clientPhoto) throws URISyntaxException {
        log.debug("REST request to save ClientPhoto : {}", clientPhoto);
        if (clientPhoto.getId() != null) {
            throw new BadRequestAlertException("A new clientPhoto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClientPhoto result = clientPhotoRepository.save(clientPhoto);
        return ResponseEntity
            .created(new URI("/api/client-photos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /client-photos/:id} : Updates an existing clientPhoto.
     *
     * @param id the id of the clientPhoto to save.
     * @param clientPhoto the clientPhoto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientPhoto,
     * or with status {@code 400 (Bad Request)} if the clientPhoto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clientPhoto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientPhoto> updateClientPhoto(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClientPhoto clientPhoto
    ) throws URISyntaxException {
        log.debug("REST request to update ClientPhoto : {}, {}", id, clientPhoto);
        if (clientPhoto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientPhoto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientPhotoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClientPhoto result = clientPhotoRepository.save(clientPhoto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientPhoto.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /client-photos/:id} : Partial updates given fields of an existing clientPhoto, field will ignore if it is null
     *
     * @param id the id of the clientPhoto to save.
     * @param clientPhoto the clientPhoto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientPhoto,
     * or with status {@code 400 (Bad Request)} if the clientPhoto is not valid,
     * or with status {@code 404 (Not Found)} if the clientPhoto is not found,
     * or with status {@code 500 (Internal Server Error)} if the clientPhoto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ClientPhoto> partialUpdateClientPhoto(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClientPhoto clientPhoto
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClientPhoto partially : {}, {}", id, clientPhoto);
        if (clientPhoto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientPhoto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientPhotoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClientPhoto> result = clientPhotoRepository
            .findById(clientPhoto.getId())
            .map(existingClientPhoto -> {
                if (clientPhoto.getClientId() != null) {
                    existingClientPhoto.setClientId(clientPhoto.getClientId());
                }
                if (clientPhoto.getLarge() != null) {
                    existingClientPhoto.setLarge(clientPhoto.getLarge());
                }
                if (clientPhoto.getLargeHeight() != null) {
                    existingClientPhoto.setLargeHeight(clientPhoto.getLargeHeight());
                }
                if (clientPhoto.getLargeWidth() != null) {
                    existingClientPhoto.setLargeWidth(clientPhoto.getLargeWidth());
                }
                if (clientPhoto.getMedium() != null) {
                    existingClientPhoto.setMedium(clientPhoto.getMedium());
                }
                if (clientPhoto.getMediumHeight() != null) {
                    existingClientPhoto.setMediumHeight(clientPhoto.getMediumHeight());
                }
                if (clientPhoto.getMediumWidth() != null) {
                    existingClientPhoto.setMediumWidth(clientPhoto.getMediumWidth());
                }
                if (clientPhoto.getSmall() != null) {
                    existingClientPhoto.setSmall(clientPhoto.getSmall());
                }
                if (clientPhoto.getSmallHeight() != null) {
                    existingClientPhoto.setSmallHeight(clientPhoto.getSmallHeight());
                }
                if (clientPhoto.getSmallWidth() != null) {
                    existingClientPhoto.setSmallWidth(clientPhoto.getSmallWidth());
                }
                if (clientPhoto.getRaw() != null) {
                    existingClientPhoto.setRaw(clientPhoto.getRaw());
                }
                if (clientPhoto.getCropx() != null) {
                    existingClientPhoto.setCropx(clientPhoto.getCropx());
                }
                if (clientPhoto.getCropy() != null) {
                    existingClientPhoto.setCropy(clientPhoto.getCropy());
                }
                if (clientPhoto.getCropHeight() != null) {
                    existingClientPhoto.setCropHeight(clientPhoto.getCropHeight());
                }
                if (clientPhoto.getCropWidth() != null) {
                    existingClientPhoto.setCropWidth(clientPhoto.getCropWidth());
                }
                if (clientPhoto.getTechLineage() != null) {
                    existingClientPhoto.setTechLineage(clientPhoto.getTechLineage());
                }
                if (clientPhoto.getTechCreatedDate() != null) {
                    existingClientPhoto.setTechCreatedDate(clientPhoto.getTechCreatedDate());
                }
                if (clientPhoto.getTechUpdatedDate() != null) {
                    existingClientPhoto.setTechUpdatedDate(clientPhoto.getTechUpdatedDate());
                }
                if (clientPhoto.getTechMapping() != null) {
                    existingClientPhoto.setTechMapping(clientPhoto.getTechMapping());
                }
                if (clientPhoto.getTechComment() != null) {
                    existingClientPhoto.setTechComment(clientPhoto.getTechComment());
                }

                return existingClientPhoto;
            })
            .map(clientPhotoRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientPhoto.getId().toString())
        );
    }

    /**
     * {@code GET  /client-photos} : get all the clientPhotos.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientPhotos in body.
     */
    @GetMapping("")
    public List<ClientPhoto> getAllClientPhotos(@RequestParam(name = "filter", required = false) String filter) {
        if ("client-is-null".equals(filter)) {
            log.debug("REST request to get all ClientPhotos where client is null");
            return StreamSupport
                .stream(clientPhotoRepository.findAll().spliterator(), false)
                .filter(clientPhoto -> clientPhoto.getClient() == null)
                .toList();
        }
        log.debug("REST request to get all ClientPhotos");
        return clientPhotoRepository.findAll();
    }

    /**
     * {@code GET  /client-photos/:id} : get the "id" clientPhoto.
     *
     * @param id the id of the clientPhoto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientPhoto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientPhoto> getClientPhoto(@PathVariable("id") Long id) {
        log.debug("REST request to get ClientPhoto : {}", id);
        Optional<ClientPhoto> clientPhoto = clientPhotoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(clientPhoto);
    }

    /**
     * {@code DELETE  /client-photos/:id} : delete the "id" clientPhoto.
     *
     * @param id the id of the clientPhoto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientPhoto(@PathVariable("id") Long id) {
        log.debug("REST request to delete ClientPhoto : {}", id);
        clientPhotoRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
