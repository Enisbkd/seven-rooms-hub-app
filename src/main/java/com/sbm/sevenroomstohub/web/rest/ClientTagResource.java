package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.repository.ClientTagRepository;
import com.sbm.sevenroomstohub.service.ClientTagService;
import com.sbm.sevenroomstohub.service.dto.ClientTagDTO;
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
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.ClientTag}.
 */
@RestController
@RequestMapping("/api/client-tags")
public class ClientTagResource {

    private final Logger log = LoggerFactory.getLogger(ClientTagResource.class);

    private static final String ENTITY_NAME = "clientTag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientTagService clientTagService;

    private final ClientTagRepository clientTagRepository;

    public ClientTagResource(ClientTagService clientTagService, ClientTagRepository clientTagRepository) {
        this.clientTagService = clientTagService;
        this.clientTagRepository = clientTagRepository;
    }

    /**
     * {@code POST  /client-tags} : Create a new clientTag.
     *
     * @param clientTagDTO the clientTagDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clientTagDTO, or with status {@code 400 (Bad Request)} if the clientTag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ClientTagDTO> createClientTag(@RequestBody ClientTagDTO clientTagDTO) throws URISyntaxException {
        log.debug("REST request to save ClientTag : {}", clientTagDTO);
        if (clientTagDTO.getId() != null) {
            throw new BadRequestAlertException("A new clientTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClientTagDTO result = clientTagService.save(clientTagDTO);
        return ResponseEntity
            .created(new URI("/api/client-tags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /client-tags/:id} : Updates an existing clientTag.
     *
     * @param id the id of the clientTagDTO to save.
     * @param clientTagDTO the clientTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientTagDTO,
     * or with status {@code 400 (Bad Request)} if the clientTagDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clientTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientTagDTO> updateClientTag(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClientTagDTO clientTagDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClientTag : {}, {}", id, clientTagDTO);
        if (clientTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientTagDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClientTagDTO result = clientTagService.update(clientTagDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientTagDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /client-tags/:id} : Partial updates given fields of an existing clientTag, field will ignore if it is null
     *
     * @param id the id of the clientTagDTO to save.
     * @param clientTagDTO the clientTagDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientTagDTO,
     * or with status {@code 400 (Bad Request)} if the clientTagDTO is not valid,
     * or with status {@code 404 (Not Found)} if the clientTagDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the clientTagDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ClientTagDTO> partialUpdateClientTag(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClientTagDTO clientTagDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClientTag partially : {}, {}", id, clientTagDTO);
        if (clientTagDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientTagDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClientTagDTO> result = clientTagService.partialUpdate(clientTagDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientTagDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /client-tags} : get all the clientTags.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientTags in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ClientTagDTO>> getAllClientTags(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ClientTags");
        Page<ClientTagDTO> page = clientTagService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /client-tags/:id} : get the "id" clientTag.
     *
     * @param id the id of the clientTagDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientTagDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientTagDTO> getClientTag(@PathVariable("id") Long id) {
        log.debug("REST request to get ClientTag : {}", id);
        Optional<ClientTagDTO> clientTagDTO = clientTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clientTagDTO);
    }

    /**
     * {@code DELETE  /client-tags/:id} : delete the "id" clientTag.
     *
     * @param id the id of the clientTagDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientTag(@PathVariable("id") Long id) {
        log.debug("REST request to delete ClientTag : {}", id);
        clientTagService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
