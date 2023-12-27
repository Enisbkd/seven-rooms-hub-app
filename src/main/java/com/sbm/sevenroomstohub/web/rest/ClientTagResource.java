package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.domain.ClientTag;
import com.sbm.sevenroomstohub.repository.ClientTagRepository;
import com.sbm.sevenroomstohub.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.ClientTag}.
 */
@RestController
@RequestMapping("/api/client-tags")
@Transactional
public class ClientTagResource {

    private final Logger log = LoggerFactory.getLogger(ClientTagResource.class);

    private static final String ENTITY_NAME = "clientTag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientTagRepository clientTagRepository;

    public ClientTagResource(ClientTagRepository clientTagRepository) {
        this.clientTagRepository = clientTagRepository;
    }

    /**
     * {@code POST  /client-tags} : Create a new clientTag.
     *
     * @param clientTag the clientTag to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clientTag, or with status {@code 400 (Bad Request)} if the clientTag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ClientTag> createClientTag(@RequestBody ClientTag clientTag) throws URISyntaxException {
        log.debug("REST request to save ClientTag : {}", clientTag);
        if (clientTag.getId() != null) {
            throw new BadRequestAlertException("A new clientTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClientTag result = clientTagRepository.save(clientTag);
        return ResponseEntity
            .created(new URI("/api/client-tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /client-tags/:id} : Updates an existing clientTag.
     *
     * @param id the id of the clientTag to save.
     * @param clientTag the clientTag to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientTag,
     * or with status {@code 400 (Bad Request)} if the clientTag is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clientTag couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientTag> updateClientTag(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClientTag clientTag
    ) throws URISyntaxException {
        log.debug("REST request to update ClientTag : {}, {}", id, clientTag);
        if (clientTag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientTag.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClientTag result = clientTagRepository.save(clientTag);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientTag.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /client-tags/:id} : Partial updates given fields of an existing clientTag, field will ignore if it is null
     *
     * @param id the id of the clientTag to save.
     * @param clientTag the clientTag to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientTag,
     * or with status {@code 400 (Bad Request)} if the clientTag is not valid,
     * or with status {@code 404 (Not Found)} if the clientTag is not found,
     * or with status {@code 500 (Internal Server Error)} if the clientTag couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ClientTag> partialUpdateClientTag(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClientTag clientTag
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClientTag partially : {}, {}", id, clientTag);
        if (clientTag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientTag.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClientTag> result = clientTagRepository
            .findById(clientTag.getId())
            .map(existingClientTag -> {
                if (clientTag.getTag() != null) {
                    existingClientTag.setTag(clientTag.getTag());
                }
                if (clientTag.getTagDisplay() != null) {
                    existingClientTag.setTagDisplay(clientTag.getTagDisplay());
                }
                if (clientTag.getGroup() != null) {
                    existingClientTag.setGroup(clientTag.getGroup());
                }
                if (clientTag.getGroupDisplay() != null) {
                    existingClientTag.setGroupDisplay(clientTag.getGroupDisplay());
                }
                if (clientTag.getColor() != null) {
                    existingClientTag.setColor(clientTag.getColor());
                }
                if (clientTag.getTechLineage() != null) {
                    existingClientTag.setTechLineage(clientTag.getTechLineage());
                }
                if (clientTag.getTechCreatedDate() != null) {
                    existingClientTag.setTechCreatedDate(clientTag.getTechCreatedDate());
                }
                if (clientTag.getTechUpdatedDate() != null) {
                    existingClientTag.setTechUpdatedDate(clientTag.getTechUpdatedDate());
                }
                if (clientTag.getTechMapping() != null) {
                    existingClientTag.setTechMapping(clientTag.getTechMapping());
                }
                if (clientTag.getTechComment() != null) {
                    existingClientTag.setTechComment(clientTag.getTechComment());
                }

                return existingClientTag;
            })
            .map(clientTagRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientTag.getId().toString())
        );
    }

    /**
     * {@code GET  /client-tags} : get all the clientTags.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientTags in body.
     */
    @GetMapping("")
    public List<ClientTag> getAllClientTags() {
        log.debug("REST request to get all ClientTags");
        return clientTagRepository.findAll();
    }

    /**
     * {@code GET  /client-tags/:id} : get the "id" clientTag.
     *
     * @param id the id of the clientTag to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientTag, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientTag> getClientTag(@PathVariable("id") Long id) {
        log.debug("REST request to get ClientTag : {}", id);
        Optional<ClientTag> clientTag = clientTagRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(clientTag);
    }

    /**
     * {@code DELETE  /client-tags/:id} : delete the "id" clientTag.
     *
     * @param id the id of the clientTag to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientTag(@PathVariable("id") Long id) {
        log.debug("REST request to delete ClientTag : {}", id);
        clientTagRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
