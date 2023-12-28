package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ClientVenueStats.
 */
@Entity
@Table(name = "client_venue_stats")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientVenueStats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "venue_id")
    private String venueId;

    @Column(name = "avg_rating")
    private Integer avgRating;

    @Column(name = "booked_by_names")
    private String bookedByNames;

    @Column(name = "last_visit_date")
    private String lastVisitDate;

    @Column(name = "num_ratings")
    private Integer numRatings;

    @Column(name = "total_cancellations")
    private Integer totalCancellations;

    @Column(name = "total_covers")
    private Integer totalCovers;

    @Column(name = "total_no_shows")
    private Integer totalNoShows;

    @Column(name = "total_spend")
    private Double totalSpend;

    @Column(name = "total_spend_local")
    private Double totalSpendLocal;

    @Column(name = "total_spend_localper_cover")
    private Double totalSpendLocalperCover;

    @Column(name = "total_spend_local_per_visit")
    private Double totalSpendLocalPerVisit;

    @Column(name = "total_spendper_cover")
    private Double totalSpendperCover;

    @Column(name = "total_spend_per_visit")
    private Double totalSpendPerVisit;

    @Column(name = "total_visit")
    private Integer totalVisit;

    @Column(name = "venue_marketing_optin")
    private Boolean venueMarketingOptin;

    @Column(name = "venue_marketing_optints")
    private String venueMarketingOptints;

    @Column(name = "tech_lineage")
    private String techLineage;

    @Column(name = "tech_created_date")
    private ZonedDateTime techCreatedDate;

    @Column(name = "tech_updated_date")
    private ZonedDateTime techUpdatedDate;

    @Column(name = "tech_mapping")
    private String techMapping;

    @Column(name = "tech_comment")
    private String techComment;

    @JsonIgnoreProperties(
        value = { "clientPhoto", "clientVenueStats", "customFields", "clientTags", "reservations", "memberGroups" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "clientVenueStats")
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ClientVenueStats id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public ClientVenueStats venueId(String venueId) {
        this.setVenueId(venueId);
        return this;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public Integer getAvgRating() {
        return this.avgRating;
    }

    public ClientVenueStats avgRating(Integer avgRating) {
        this.setAvgRating(avgRating);
        return this;
    }

    public void setAvgRating(Integer avgRating) {
        this.avgRating = avgRating;
    }

    public String getBookedByNames() {
        return this.bookedByNames;
    }

    public ClientVenueStats bookedByNames(String bookedByNames) {
        this.setBookedByNames(bookedByNames);
        return this;
    }

    public void setBookedByNames(String bookedByNames) {
        this.bookedByNames = bookedByNames;
    }

    public String getLastVisitDate() {
        return this.lastVisitDate;
    }

    public ClientVenueStats lastVisitDate(String lastVisitDate) {
        this.setLastVisitDate(lastVisitDate);
        return this;
    }

    public void setLastVisitDate(String lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public Integer getNumRatings() {
        return this.numRatings;
    }

    public ClientVenueStats numRatings(Integer numRatings) {
        this.setNumRatings(numRatings);
        return this;
    }

    public void setNumRatings(Integer numRatings) {
        this.numRatings = numRatings;
    }

    public Integer getTotalCancellations() {
        return this.totalCancellations;
    }

    public ClientVenueStats totalCancellations(Integer totalCancellations) {
        this.setTotalCancellations(totalCancellations);
        return this;
    }

    public void setTotalCancellations(Integer totalCancellations) {
        this.totalCancellations = totalCancellations;
    }

    public Integer getTotalCovers() {
        return this.totalCovers;
    }

    public ClientVenueStats totalCovers(Integer totalCovers) {
        this.setTotalCovers(totalCovers);
        return this;
    }

    public void setTotalCovers(Integer totalCovers) {
        this.totalCovers = totalCovers;
    }

    public Integer getTotalNoShows() {
        return this.totalNoShows;
    }

    public ClientVenueStats totalNoShows(Integer totalNoShows) {
        this.setTotalNoShows(totalNoShows);
        return this;
    }

    public void setTotalNoShows(Integer totalNoShows) {
        this.totalNoShows = totalNoShows;
    }

    public Double getTotalSpend() {
        return this.totalSpend;
    }

    public ClientVenueStats totalSpend(Double totalSpend) {
        this.setTotalSpend(totalSpend);
        return this;
    }

    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public Double getTotalSpendLocal() {
        return this.totalSpendLocal;
    }

    public ClientVenueStats totalSpendLocal(Double totalSpendLocal) {
        this.setTotalSpendLocal(totalSpendLocal);
        return this;
    }

    public void setTotalSpendLocal(Double totalSpendLocal) {
        this.totalSpendLocal = totalSpendLocal;
    }

    public Double getTotalSpendLocalperCover() {
        return this.totalSpendLocalperCover;
    }

    public ClientVenueStats totalSpendLocalperCover(Double totalSpendLocalperCover) {
        this.setTotalSpendLocalperCover(totalSpendLocalperCover);
        return this;
    }

    public void setTotalSpendLocalperCover(Double totalSpendLocalperCover) {
        this.totalSpendLocalperCover = totalSpendLocalperCover;
    }

    public Double getTotalSpendLocalPerVisit() {
        return this.totalSpendLocalPerVisit;
    }

    public ClientVenueStats totalSpendLocalPerVisit(Double totalSpendLocalPerVisit) {
        this.setTotalSpendLocalPerVisit(totalSpendLocalPerVisit);
        return this;
    }

    public void setTotalSpendLocalPerVisit(Double totalSpendLocalPerVisit) {
        this.totalSpendLocalPerVisit = totalSpendLocalPerVisit;
    }

    public Double getTotalSpendperCover() {
        return this.totalSpendperCover;
    }

    public ClientVenueStats totalSpendperCover(Double totalSpendperCover) {
        this.setTotalSpendperCover(totalSpendperCover);
        return this;
    }

    public void setTotalSpendperCover(Double totalSpendperCover) {
        this.totalSpendperCover = totalSpendperCover;
    }

    public Double getTotalSpendPerVisit() {
        return this.totalSpendPerVisit;
    }

    public ClientVenueStats totalSpendPerVisit(Double totalSpendPerVisit) {
        this.setTotalSpendPerVisit(totalSpendPerVisit);
        return this;
    }

    public void setTotalSpendPerVisit(Double totalSpendPerVisit) {
        this.totalSpendPerVisit = totalSpendPerVisit;
    }

    public Integer getTotalVisit() {
        return this.totalVisit;
    }

    public ClientVenueStats totalVisit(Integer totalVisit) {
        this.setTotalVisit(totalVisit);
        return this;
    }

    public void setTotalVisit(Integer totalVisit) {
        this.totalVisit = totalVisit;
    }

    public Boolean getVenueMarketingOptin() {
        return this.venueMarketingOptin;
    }

    public ClientVenueStats venueMarketingOptin(Boolean venueMarketingOptin) {
        this.setVenueMarketingOptin(venueMarketingOptin);
        return this;
    }

    public void setVenueMarketingOptin(Boolean venueMarketingOptin) {
        this.venueMarketingOptin = venueMarketingOptin;
    }

    public String getVenueMarketingOptints() {
        return this.venueMarketingOptints;
    }

    public ClientVenueStats venueMarketingOptints(String venueMarketingOptints) {
        this.setVenueMarketingOptints(venueMarketingOptints);
        return this;
    }

    public void setVenueMarketingOptints(String venueMarketingOptints) {
        this.venueMarketingOptints = venueMarketingOptints;
    }

    public String getTechLineage() {
        return this.techLineage;
    }

    public ClientVenueStats techLineage(String techLineage) {
        this.setTechLineage(techLineage);
        return this;
    }

    public void setTechLineage(String techLineage) {
        this.techLineage = techLineage;
    }

    public ZonedDateTime getTechCreatedDate() {
        return this.techCreatedDate;
    }

    public ClientVenueStats techCreatedDate(ZonedDateTime techCreatedDate) {
        this.setTechCreatedDate(techCreatedDate);
        return this;
    }

    public void setTechCreatedDate(ZonedDateTime techCreatedDate) {
        this.techCreatedDate = techCreatedDate;
    }

    public ZonedDateTime getTechUpdatedDate() {
        return this.techUpdatedDate;
    }

    public ClientVenueStats techUpdatedDate(ZonedDateTime techUpdatedDate) {
        this.setTechUpdatedDate(techUpdatedDate);
        return this;
    }

    public void setTechUpdatedDate(ZonedDateTime techUpdatedDate) {
        this.techUpdatedDate = techUpdatedDate;
    }

    public String getTechMapping() {
        return this.techMapping;
    }

    public ClientVenueStats techMapping(String techMapping) {
        this.setTechMapping(techMapping);
        return this;
    }

    public void setTechMapping(String techMapping) {
        this.techMapping = techMapping;
    }

    public String getTechComment() {
        return this.techComment;
    }

    public ClientVenueStats techComment(String techComment) {
        this.setTechComment(techComment);
        return this;
    }

    public void setTechComment(String techComment) {
        this.techComment = techComment;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        if (this.client != null) {
            this.client.setClientVenueStats(null);
        }
        if (client != null) {
            client.setClientVenueStats(this);
        }
        this.client = client;
    }

    public ClientVenueStats client(Client client) {
        this.setClient(client);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientVenueStats)) {
            return false;
        }
        return getId() != null && getId().equals(((ClientVenueStats) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientVenueStats{" +
            "id=" + getId() +
            ", venueId='" + getVenueId() + "'" +
            ", avgRating=" + getAvgRating() +
            ", bookedByNames='" + getBookedByNames() + "'" +
            ", lastVisitDate='" + getLastVisitDate() + "'" +
            ", numRatings=" + getNumRatings() +
            ", totalCancellations=" + getTotalCancellations() +
            ", totalCovers=" + getTotalCovers() +
            ", totalNoShows=" + getTotalNoShows() +
            ", totalSpend=" + getTotalSpend() +
            ", totalSpendLocal=" + getTotalSpendLocal() +
            ", totalSpendLocalperCover=" + getTotalSpendLocalperCover() +
            ", totalSpendLocalPerVisit=" + getTotalSpendLocalPerVisit() +
            ", totalSpendperCover=" + getTotalSpendperCover() +
            ", totalSpendPerVisit=" + getTotalSpendPerVisit() +
            ", totalVisit=" + getTotalVisit() +
            ", venueMarketingOptin='" + getVenueMarketingOptin() + "'" +
            ", venueMarketingOptints='" + getVenueMarketingOptints() + "'" +
            ", techLineage='" + getTechLineage() + "'" +
            ", techCreatedDate='" + getTechCreatedDate() + "'" +
            ", techUpdatedDate='" + getTechUpdatedDate() + "'" +
            ", techMapping='" + getTechMapping() + "'" +
            ", techComment='" + getTechComment() + "'" +
            "}";
    }
}
