package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.domain.MemberGroup;
import com.sbm.sevenroomstohub.repository.MemberGroupRepository;
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
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.MemberGroup}.
 */
@RestController
@RequestMapping("/api/member-groups")
@Transactional
public class MemberGroupResource {

    private final Logger log = LoggerFactory.getLogger(MemberGroupResource.class);

    private static final String ENTITY_NAME = "memberGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MemberGroupRepository memberGroupRepository;

    public MemberGroupResource(MemberGroupRepository memberGroupRepository) {
        this.memberGroupRepository = memberGroupRepository;
    }

    /**
     * {@code POST  /member-groups} : Create a new memberGroup.
     *
     * @param memberGroup the memberGroup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new memberGroup, or with status {@code 400 (Bad Request)} if the memberGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MemberGroup> createMemberGroup(@RequestBody MemberGroup memberGroup) throws URISyntaxException {
        log.debug("REST request to save MemberGroup : {}", memberGroup);
        if (memberGroup.getId() != null) {
            throw new BadRequestAlertException("A new memberGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MemberGroup result = memberGroupRepository.save(memberGroup);
        return ResponseEntity
            .created(new URI("/api/member-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /member-groups/:id} : Updates an existing memberGroup.
     *
     * @param id the id of the memberGroup to save.
     * @param memberGroup the memberGroup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberGroup,
     * or with status {@code 400 (Bad Request)} if the memberGroup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the memberGroup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MemberGroup> updateMemberGroup(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MemberGroup memberGroup
    ) throws URISyntaxException {
        log.debug("REST request to update MemberGroup : {}, {}", id, memberGroup);
        if (memberGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, memberGroup.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!memberGroupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MemberGroup result = memberGroupRepository.save(memberGroup);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, memberGroup.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /member-groups/:id} : Partial updates given fields of an existing memberGroup, field will ignore if it is null
     *
     * @param id the id of the memberGroup to save.
     * @param memberGroup the memberGroup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberGroup,
     * or with status {@code 400 (Bad Request)} if the memberGroup is not valid,
     * or with status {@code 404 (Not Found)} if the memberGroup is not found,
     * or with status {@code 500 (Internal Server Error)} if the memberGroup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MemberGroup> partialUpdateMemberGroup(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MemberGroup memberGroup
    ) throws URISyntaxException {
        log.debug("REST request to partial update MemberGroup partially : {}, {}", id, memberGroup);
        if (memberGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, memberGroup.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!memberGroupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MemberGroup> result = memberGroupRepository.findById(memberGroup.getId()).map(memberGroupRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, memberGroup.getId().toString())
        );
    }

    /**
     * {@code GET  /member-groups} : get all the memberGroups.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of memberGroups in body.
     */
    @GetMapping("")
    public List<MemberGroup> getAllMemberGroups() {
        log.debug("REST request to get all MemberGroups");
        return memberGroupRepository.findAll();
    }

    /**
     * {@code GET  /member-groups/:id} : get the "id" memberGroup.
     *
     * @param id the id of the memberGroup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the memberGroup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberGroup> getMemberGroup(@PathVariable("id") Long id) {
        log.debug("REST request to get MemberGroup : {}", id);
        Optional<MemberGroup> memberGroup = memberGroupRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(memberGroup);
    }

    /**
     * {@code DELETE  /member-groups/:id} : delete the "id" memberGroup.
     *
     * @param id the id of the memberGroup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberGroup(@PathVariable("id") Long id) {
        log.debug("REST request to delete MemberGroup : {}", id);
        memberGroupRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
