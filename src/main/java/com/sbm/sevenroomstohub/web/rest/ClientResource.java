package com.sbm.sevenroomstohub.web.rest;

import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.repository.ClientRepository;
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
 * REST controller for managing {@link com.sbm.sevenroomstohub.domain.Client}.
 */
@RestController
@RequestMapping("/api/clients")
@Transactional
public class ClientResource {

    private final Logger log = LoggerFactory.getLogger(ClientResource.class);

    private static final String ENTITY_NAME = "client";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientRepository clientRepository;

    public ClientResource(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * {@code POST  /clients} : Create a new client.
     *
     * @param client the client to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new client, or with status {@code 400 (Bad Request)} if the client has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Client> createClient(@RequestBody Client client) throws URISyntaxException {
        log.debug("REST request to save Client : {}", client);
        if (client.getId() != null) {
            throw new BadRequestAlertException("A new client cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Client result = clientRepository.save(client);
        return ResponseEntity
            .created(new URI("/api/clients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /clients/:id} : Updates an existing client.
     *
     * @param id the id of the client to save.
     * @param client the client to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated client,
     * or with status {@code 400 (Bad Request)} if the client is not valid,
     * or with status {@code 500 (Internal Server Error)} if the client couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id", required = false) final Long id, @RequestBody Client client)
        throws URISyntaxException {
        log.debug("REST request to update Client : {}, {}", id, client);
        if (client.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, client.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Client result = clientRepository.save(client);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, client.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /clients/:id} : Partial updates given fields of an existing client, field will ignore if it is null
     *
     * @param id the id of the client to save.
     * @param client the client to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated client,
     * or with status {@code 400 (Bad Request)} if the client is not valid,
     * or with status {@code 404 (Not Found)} if the client is not found,
     * or with status {@code 500 (Internal Server Error)} if the client couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Client> partialUpdateClient(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Client client
    ) throws URISyntaxException {
        log.debug("REST request to partial update Client partially : {}, {}", id, client);
        if (client.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, client.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Client> result = clientRepository
            .findById(client.getId())
            .map(existingClient -> {
                if (client.getClientId() != null) {
                    existingClient.setClientId(client.getClientId());
                }
                if (client.getCreatedDate() != null) {
                    existingClient.setCreatedDate(client.getCreatedDate());
                }
                if (client.getUpdatedDate() != null) {
                    existingClient.setUpdatedDate(client.getUpdatedDate());
                }
                if (client.getDeletedDate() != null) {
                    existingClient.setDeletedDate(client.getDeletedDate());
                }
                if (client.getLastname() != null) {
                    existingClient.setLastname(client.getLastname());
                }
                if (client.getFirstname() != null) {
                    existingClient.setFirstname(client.getFirstname());
                }
                if (client.getGender() != null) {
                    existingClient.setGender(client.getGender());
                }
                if (client.getSalutation() != null) {
                    existingClient.setSalutation(client.getSalutation());
                }
                if (client.getTitle() != null) {
                    existingClient.setTitle(client.getTitle());
                }
                if (client.getBirthdayDay() != null) {
                    existingClient.setBirthdayDay(client.getBirthdayDay());
                }
                if (client.getBirthdayMonth() != null) {
                    existingClient.setBirthdayMonth(client.getBirthdayMonth());
                }
                if (client.getBirthdayAltMonth() != null) {
                    existingClient.setBirthdayAltMonth(client.getBirthdayAltMonth());
                }
                if (client.getAnniversaryDay() != null) {
                    existingClient.setAnniversaryDay(client.getAnniversaryDay());
                }
                if (client.getAnniversaryMonth() != null) {
                    existingClient.setAnniversaryMonth(client.getAnniversaryMonth());
                }
                if (client.getCompany() != null) {
                    existingClient.setCompany(client.getCompany());
                }
                if (client.getEmail() != null) {
                    existingClient.setEmail(client.getEmail());
                }
                if (client.getEmailAlt() != null) {
                    existingClient.setEmailAlt(client.getEmailAlt());
                }
                if (client.getPhoneNumber() != null) {
                    existingClient.setPhoneNumber(client.getPhoneNumber());
                }
                if (client.getPhoneNumberlocale() != null) {
                    existingClient.setPhoneNumberlocale(client.getPhoneNumberlocale());
                }
                if (client.getPhoneNumberalt() != null) {
                    existingClient.setPhoneNumberalt(client.getPhoneNumberalt());
                }
                if (client.getPhoneNumberaltlocale() != null) {
                    existingClient.setPhoneNumberaltlocale(client.getPhoneNumberaltlocale());
                }
                if (client.getAddress() != null) {
                    existingClient.setAddress(client.getAddress());
                }
                if (client.getAddress2() != null) {
                    existingClient.setAddress2(client.getAddress2());
                }
                if (client.getCity() != null) {
                    existingClient.setCity(client.getCity());
                }
                if (client.getPostalCode() != null) {
                    existingClient.setPostalCode(client.getPostalCode());
                }
                if (client.getState() != null) {
                    existingClient.setState(client.getState());
                }
                if (client.getCountry() != null) {
                    existingClient.setCountry(client.getCountry());
                }
                if (client.getIsContactPrivate() != null) {
                    existingClient.setIsContactPrivate(client.getIsContactPrivate());
                }
                if (client.getIsOnetimeGuest() != null) {
                    existingClient.setIsOnetimeGuest(client.getIsOnetimeGuest());
                }
                if (client.getStatus() != null) {
                    existingClient.setStatus(client.getStatus());
                }
                if (client.getLoyaltyId() != null) {
                    existingClient.setLoyaltyId(client.getLoyaltyId());
                }
                if (client.getLoyaltyRank() != null) {
                    existingClient.setLoyaltyRank(client.getLoyaltyRank());
                }
                if (client.getLoyaltyTier() != null) {
                    existingClient.setLoyaltyTier(client.getLoyaltyTier());
                }
                if (client.getMarketingOptin() != null) {
                    existingClient.setMarketingOptin(client.getMarketingOptin());
                }
                if (client.getMarketingOptints() != null) {
                    existingClient.setMarketingOptints(client.getMarketingOptints());
                }
                if (client.getMarketingOptOutts() != null) {
                    existingClient.setMarketingOptOutts(client.getMarketingOptOutts());
                }
                if (client.getHasBillingProfile() != null) {
                    existingClient.setHasBillingProfile(client.getHasBillingProfile());
                }
                if (client.getNotes() != null) {
                    existingClient.setNotes(client.getNotes());
                }
                if (client.getPrivateNotes() != null) {
                    existingClient.setPrivateNotes(client.getPrivateNotes());
                }
                if (client.getTags() != null) {
                    existingClient.setTags(client.getTags());
                }
                if (client.getTotalVisits() != null) {
                    existingClient.setTotalVisits(client.getTotalVisits());
                }
                if (client.getTotalCovers() != null) {
                    existingClient.setTotalCovers(client.getTotalCovers());
                }
                if (client.getTotalCancellations() != null) {
                    existingClient.setTotalCancellations(client.getTotalCancellations());
                }
                if (client.getTotalNoShows() != null) {
                    existingClient.setTotalNoShows(client.getTotalNoShows());
                }
                if (client.getTotalSpend() != null) {
                    existingClient.setTotalSpend(client.getTotalSpend());
                }
                if (client.getTotalSpendPerCover() != null) {
                    existingClient.setTotalSpendPerCover(client.getTotalSpendPerCover());
                }
                if (client.getTotalspendPerVisit() != null) {
                    existingClient.setTotalspendPerVisit(client.getTotalspendPerVisit());
                }
                if (client.getAvgRating() != null) {
                    existingClient.setAvgRating(client.getAvgRating());
                }
                if (client.getReferenceCode() != null) {
                    existingClient.setReferenceCode(client.getReferenceCode());
                }
                if (client.getExternalUserId() != null) {
                    existingClient.setExternalUserId(client.getExternalUserId());
                }
                if (client.getVenueGroupId() != null) {
                    existingClient.setVenueGroupId(client.getVenueGroupId());
                }
                if (client.getBirthdayAltDay() != null) {
                    existingClient.setBirthdayAltDay(client.getBirthdayAltDay());
                }
                if (client.getUserId() != null) {
                    existingClient.setUserId(client.getUserId());
                }
                if (client.getUserName() != null) {
                    existingClient.setUserName(client.getUserName());
                }
                if (client.getTotalOrderCount() != null) {
                    existingClient.setTotalOrderCount(client.getTotalOrderCount());
                }
                if (client.getPreferredLanguageCode() != null) {
                    existingClient.setPreferredLanguageCode(client.getPreferredLanguageCode());
                }
                if (client.getTechLineage() != null) {
                    existingClient.setTechLineage(client.getTechLineage());
                }
                if (client.getTechCreatedDate() != null) {
                    existingClient.setTechCreatedDate(client.getTechCreatedDate());
                }
                if (client.getTechUpdatedDate() != null) {
                    existingClient.setTechUpdatedDate(client.getTechUpdatedDate());
                }
                if (client.getTechMapping() != null) {
                    existingClient.setTechMapping(client.getTechMapping());
                }
                if (client.getTechComment() != null) {
                    existingClient.setTechComment(client.getTechComment());
                }

                return existingClient;
            })
            .map(clientRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, client.getId().toString())
        );
    }

    /**
     * {@code GET  /clients} : get all the clients.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clients in body.
     */
    @GetMapping("")
    public List<Client> getAllClients() {
        log.debug("REST request to get all Clients");
        return clientRepository.findAll();
    }

    /**
     * {@code GET  /clients/:id} : get the "id" client.
     *
     * @param id the id of the client to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the client, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {
        log.debug("REST request to get Client : {}", id);
        Optional<Client> client = clientRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(client);
    }

    /**
     * {@code DELETE  /clients/:id} : delete the "id" client.
     *
     * @param id the id of the client to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        log.debug("REST request to delete Client : {}", id);
        clientRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
