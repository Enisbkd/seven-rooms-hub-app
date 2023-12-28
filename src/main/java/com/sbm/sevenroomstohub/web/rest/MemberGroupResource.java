package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.repository.MemberGroupRepository;
import com.sbm.sevenroomstohub.service.MemberGroupService;
import com.sbm.sevenroomstohub.service.dto.MemberGroupDTO;
import com.sbm.sevenroomstohub.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.MemberGroup}.
 */
@RestController
@RequestMapping("/api/member-groups")
public class MemberGroupResource {

    private final Logger log = LoggerFactory.getLogger(MemberGroupResource.class);

    private static final String ENTITY_NAME = "memberGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MemberGroupService memberGroupService;

    private final MemberGroupRepository memberGroupRepository;

    public MemberGroupResource(MemberGroupService memberGroupService, MemberGroupRepository memberGroupRepository) {
        this.memberGroupService = memberGroupService;
        this.memberGroupRepository = memberGroupRepository;
    }

    /**
     * {@code POST  /member-groups} : Create a new memberGroup.
     *
     * @param memberGroupDTO the memberGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new memberGroupDTO, or with status {@code 400 (Bad Request)} if the memberGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MemberGroupDTO> createMemberGroup(@RequestBody MemberGroupDTO memberGroupDTO) throws URISyntaxException {
        log.debug("REST request to save MemberGroup : {}", memberGroupDTO);
        if (memberGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new memberGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MemberGroupDTO result = memberGroupService.save(memberGroupDTO);
        return ResponseEntity
            .created(new URI("/api/member-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /member-groups/:id} : Updates an existing memberGroup.
     *
     * @param id the id of the memberGroupDTO to save.
     * @param memberGroupDTO the memberGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberGroupDTO,
     * or with status {@code 400 (Bad Request)} if the memberGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the memberGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MemberGroupDTO> updateMemberGroup(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MemberGroupDTO memberGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MemberGroup : {}, {}", id, memberGroupDTO);
        if (memberGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, memberGroupDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!memberGroupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MemberGroupDTO result = memberGroupService.update(memberGroupDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, memberGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /member-groups/:id} : Partial updates given fields of an existing memberGroup, field will ignore if it is null
     *
     * @param id the id of the memberGroupDTO to save.
     * @param memberGroupDTO the memberGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberGroupDTO,
     * or with status {@code 400 (Bad Request)} if the memberGroupDTO is not valid,
     * or with status {@code 404 (Not Found)} if the memberGroupDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the memberGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MemberGroupDTO> partialUpdateMemberGroup(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MemberGroupDTO memberGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update MemberGroup partially : {}, {}", id, memberGroupDTO);
        if (memberGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, memberGroupDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!memberGroupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MemberGroupDTO> result = memberGroupService.partialUpdate(memberGroupDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, memberGroupDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /member-groups} : get all the memberGroups.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of memberGroups in body.
     */
    @GetMapping("")
    public ResponseEntity<List<MemberGroupDTO>> getAllMemberGroups(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of MemberGroups");
        Page<MemberGroupDTO> page = memberGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /member-groups/:id} : get the "id" memberGroup.
     *
     * @param id the id of the memberGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the memberGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberGroupDTO> getMemberGroup(@PathVariable("id") Long id) {
        log.debug("REST request to get MemberGroup : {}", id);
        Optional<MemberGroupDTO> memberGroupDTO = memberGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(memberGroupDTO);
    }

    /**
     * {@code DELETE  /member-groups/:id} : delete the "id" memberGroup.
     *
     * @param id the id of the memberGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberGroup(@PathVariable("id") Long id) {
        log.debug("REST request to delete MemberGroup : {}", id);
        memberGroupService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
