package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.domain.ResTag;
import com.sbm.sevenroomstohub.repository.ResTagRepository;
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
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.ResTag}.
 */
@RestController
@RequestMapping("/api/res-tags")
@Transactional
public class ResTagResource {

    private final Logger log = LoggerFactory.getLogger(ResTagResource.class);

    private static final String ENTITY_NAME = "resTag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResTagRepository resTagRepository;

    public ResTagResource(ResTagRepository resTagRepository) {
        this.resTagRepository = resTagRepository;
    }

    /**
     * {@code POST  /res-tags} : Create a new resTag.
     *
     * @param resTag the resTag to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resTag, or with status {@code 400 (Bad Request)} if the resTag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ResTag> createResTag(@RequestBody ResTag resTag) throws URISyntaxException {
        log.debug("REST request to save ResTag : {}", resTag);
        if (resTag.getId() != null) {
            throw new BadRequestAlertException("A new resTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResTag result = resTagRepository.save(resTag);
        return ResponseEntity
            .created(new URI("/api/res-tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /res-tags/:id} : Updates an existing resTag.
     *
     * @param id the id of the resTag to save.
     * @param resTag the resTag to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resTag,
     * or with status {@code 400 (Bad Request)} if the resTag is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resTag couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResTag> updateResTag(@PathVariable(value = "id", required = false) final Long id, @RequestBody ResTag resTag)
        throws URISyntaxException {
        log.debug("REST request to update ResTag : {}, {}", id, resTag);
        if (resTag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resTag.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ResTag result = resTagRepository.save(resTag);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resTag.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /res-tags/:id} : Partial updates given fields of an existing resTag, field will ignore if it is null
     *
     * @param id the id of the resTag to save.
     * @param resTag the resTag to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resTag,
     * or with status {@code 400 (Bad Request)} if the resTag is not valid,
     * or with status {@code 404 (Not Found)} if the resTag is not found,
     * or with status {@code 500 (Internal Server Error)} if the resTag couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResTag> partialUpdateResTag(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ResTag resTag
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResTag partially : {}, {}", id, resTag);
        if (resTag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resTag.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResTag> result = resTagRepository
            .findById(resTag.getId())
            .map(existingResTag -> {
                if (resTag.getTag() != null) {
                    existingResTag.setTag(resTag.getTag());
                }
                if (resTag.getTagDisplay() != null) {
                    existingResTag.setTagDisplay(resTag.getTagDisplay());
                }
                if (resTag.getGroup() != null) {
                    existingResTag.setGroup(resTag.getGroup());
                }
                if (resTag.getGroupDisplay() != null) {
                    existingResTag.setGroupDisplay(resTag.getGroupDisplay());
                }
                if (resTag.getColor() != null) {
                    existingResTag.setColor(resTag.getColor());
                }
                if (resTag.getTechLineage() != null) {
                    existingResTag.setTechLineage(resTag.getTechLineage());
                }
                if (resTag.getTechCreatedDate() != null) {
                    existingResTag.setTechCreatedDate(resTag.getTechCreatedDate());
                }
                if (resTag.getTechUpdatedDate() != null) {
                    existingResTag.setTechUpdatedDate(resTag.getTechUpdatedDate());
                }
                if (resTag.getTechMapping() != null) {
                    existingResTag.setTechMapping(resTag.getTechMapping());
                }
                if (resTag.getTechComment() != null) {
                    existingResTag.setTechComment(resTag.getTechComment());
                }

                return existingResTag;
            })
            .map(resTagRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resTag.getId().toString())
        );
    }

    /**
     * {@code GET  /res-tags} : get all the resTags.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resTags in body.
     */
    @GetMapping("")
    public List<ResTag> getAllResTags() {
        log.debug("REST request to get all ResTags");
        return resTagRepository.findAll();
    }

    /**
     * {@code GET  /res-tags/:id} : get the "id" resTag.
     *
     * @param id the id of the resTag to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resTag, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResTag> getResTag(@PathVariable("id") Long id) {
        log.debug("REST request to get ResTag : {}", id);
        Optional<ResTag> resTag = resTagRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(resTag);
    }

    /**
     * {@code DELETE  /res-tags/:id} : delete the "id" resTag.
     *
     * @param id the id of the resTag to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResTag(@PathVariable("id") Long id) {
        log.debug("REST request to delete ResTag : {}", id);
        resTagRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
