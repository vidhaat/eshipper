package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A ShippingClaim.
 */
@Entity
@Table(name = "shipping_claim")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ShippingClaim implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "received_date")
    private ZonedDateTime receivedDate;

    @Column(name = "mailed_date")
    private ZonedDateTime mailedDate;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @Column(name = "updated_date")
    private ZonedDateTime updatedDate;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "subject")
    private String subject;

    @Column(name = "description")
    private String description;

    @Column(name = "notify_customer")
    private Boolean notifyCustomer;

    @Column(name = "missing_documents")
    private Boolean missingDocuments;

    @OneToOne
    @JoinColumn(unique = true)
    private ClaimCarrierRefund claimCarrierRefund;

    @OneToOne
    @JoinColumn(unique = true)
    private ClaimEshipperRefund claimEshipperRefund;

    @OneToMany(mappedBy = "shippingClaim")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ShippingOrder> shippingOrders = new HashSet<>();

    @OneToMany(mappedBy = "shippingClaim")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TicketReason> ticketReasons = new HashSet<>();

    @OneToMany(mappedBy = "shippingClaim")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ClaimStatus> claimStatuses = new HashSet<>();

    @OneToMany(mappedBy = "shippingClaim")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ClaimSolution> claimSolutions = new HashSet<>();

    @OneToMany(mappedBy = "shippingClaim")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ClaimAssignee> claimAssignees = new HashSet<>();

    @OneToMany(mappedBy = "shippingClaim")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ClaimComment> claimComments = new HashSet<>();

    @OneToMany(mappedBy = "shippingClaim")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ContactPreference> contactPreferences = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("shippingClaims")
    private ClaimAttachment claimAttachment;

    @ManyToOne
    @JsonIgnoreProperties("shippingClaims")
    private ClaimMissingDocument claimMissingDocument;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getReceivedDate() {
        return receivedDate;
    }

    public ShippingClaim receivedDate(ZonedDateTime receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    public void setReceivedDate(ZonedDateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    public ZonedDateTime getMailedDate() {
        return mailedDate;
    }

    public ShippingClaim mailedDate(ZonedDateTime mailedDate) {
        this.mailedDate = mailedDate;
        return this;
    }

    public void setMailedDate(ZonedDateTime mailedDate) {
        this.mailedDate = mailedDate;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public ShippingClaim createdDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    public ShippingClaim updatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public ShippingClaim trackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        return this;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getSubject() {
        return subject;
    }

    public ShippingClaim subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public ShippingClaim description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isNotifyCustomer() {
        return notifyCustomer;
    }

    public ShippingClaim notifyCustomer(Boolean notifyCustomer) {
        this.notifyCustomer = notifyCustomer;
        return this;
    }

    public void setNotifyCustomer(Boolean notifyCustomer) {
        this.notifyCustomer = notifyCustomer;
    }

    public Boolean isMissingDocuments() {
        return missingDocuments;
    }

    public ShippingClaim missingDocuments(Boolean missingDocuments) {
        this.missingDocuments = missingDocuments;
        return this;
    }

    public void setMissingDocuments(Boolean missingDocuments) {
        this.missingDocuments = missingDocuments;
    }

    public ClaimCarrierRefund getClaimCarrierRefund() {
        return claimCarrierRefund;
    }

    public ShippingClaim claimCarrierRefund(ClaimCarrierRefund claimCarrierRefund) {
        this.claimCarrierRefund = claimCarrierRefund;
        return this;
    }

    public void setClaimCarrierRefund(ClaimCarrierRefund claimCarrierRefund) {
        this.claimCarrierRefund = claimCarrierRefund;
    }

    public ClaimEshipperRefund getClaimEshipperRefund() {
        return claimEshipperRefund;
    }

    public ShippingClaim claimEshipperRefund(ClaimEshipperRefund claimEshipperRefund) {
        this.claimEshipperRefund = claimEshipperRefund;
        return this;
    }

    public void setClaimEshipperRefund(ClaimEshipperRefund claimEshipperRefund) {
        this.claimEshipperRefund = claimEshipperRefund;
    }

    public Set<ShippingOrder> getShippingOrders() {
        return shippingOrders;
    }

    public ShippingClaim shippingOrders(Set<ShippingOrder> shippingOrders) {
        this.shippingOrders = shippingOrders;
        return this;
    }

    public ShippingClaim addShippingOrder(ShippingOrder shippingOrder) {
        this.shippingOrders.add(shippingOrder);
        shippingOrder.setShippingClaim(this);
        return this;
    }

    public ShippingClaim removeShippingOrder(ShippingOrder shippingOrder) {
        this.shippingOrders.remove(shippingOrder);
        shippingOrder.setShippingClaim(null);
        return this;
    }

    public void setShippingOrders(Set<ShippingOrder> shippingOrders) {
        this.shippingOrders = shippingOrders;
    }

    public Set<TicketReason> getTicketReasons() {
        return ticketReasons;
    }

    public ShippingClaim ticketReasons(Set<TicketReason> ticketReasons) {
        this.ticketReasons = ticketReasons;
        return this;
    }

    public ShippingClaim addTicketReason(TicketReason ticketReason) {
        this.ticketReasons.add(ticketReason);
        ticketReason.setShippingClaim(this);
        return this;
    }

    public ShippingClaim removeTicketReason(TicketReason ticketReason) {
        this.ticketReasons.remove(ticketReason);
        ticketReason.setShippingClaim(null);
        return this;
    }

    public void setTicketReasons(Set<TicketReason> ticketReasons) {
        this.ticketReasons = ticketReasons;
    }

    public Set<ClaimStatus> getClaimStatuses() {
        return claimStatuses;
    }

    public ShippingClaim claimStatuses(Set<ClaimStatus> claimStatuses) {
        this.claimStatuses = claimStatuses;
        return this;
    }

    public ShippingClaim addClaimStatus(ClaimStatus claimStatus) {
        this.claimStatuses.add(claimStatus);
        claimStatus.setShippingClaim(this);
        return this;
    }

    public ShippingClaim removeClaimStatus(ClaimStatus claimStatus) {
        this.claimStatuses.remove(claimStatus);
        claimStatus.setShippingClaim(null);
        return this;
    }

    public void setClaimStatuses(Set<ClaimStatus> claimStatuses) {
        this.claimStatuses = claimStatuses;
    }

    public Set<ClaimSolution> getClaimSolutions() {
        return claimSolutions;
    }

    public ShippingClaim claimSolutions(Set<ClaimSolution> claimSolutions) {
        this.claimSolutions = claimSolutions;
        return this;
    }

    public ShippingClaim addClaimSolution(ClaimSolution claimSolution) {
        this.claimSolutions.add(claimSolution);
        claimSolution.setShippingClaim(this);
        return this;
    }

    public ShippingClaim removeClaimSolution(ClaimSolution claimSolution) {
        this.claimSolutions.remove(claimSolution);
        claimSolution.setShippingClaim(null);
        return this;
    }

    public void setClaimSolutions(Set<ClaimSolution> claimSolutions) {
        this.claimSolutions = claimSolutions;
    }

    public Set<ClaimAssignee> getClaimAssignees() {
        return claimAssignees;
    }

    public ShippingClaim claimAssignees(Set<ClaimAssignee> claimAssignees) {
        this.claimAssignees = claimAssignees;
        return this;
    }

    public ShippingClaim addClaimAssignee(ClaimAssignee claimAssignee) {
        this.claimAssignees.add(claimAssignee);
        claimAssignee.setShippingClaim(this);
        return this;
    }

    public ShippingClaim removeClaimAssignee(ClaimAssignee claimAssignee) {
        this.claimAssignees.remove(claimAssignee);
        claimAssignee.setShippingClaim(null);
        return this;
    }

    public void setClaimAssignees(Set<ClaimAssignee> claimAssignees) {
        this.claimAssignees = claimAssignees;
    }

    public Set<ClaimComment> getClaimComments() {
        return claimComments;
    }

    public ShippingClaim claimComments(Set<ClaimComment> claimComments) {
        this.claimComments = claimComments;
        return this;
    }

    public ShippingClaim addClaimComment(ClaimComment claimComment) {
        this.claimComments.add(claimComment);
        claimComment.setShippingClaim(this);
        return this;
    }

    public ShippingClaim removeClaimComment(ClaimComment claimComment) {
        this.claimComments.remove(claimComment);
        claimComment.setShippingClaim(null);
        return this;
    }

    public void setClaimComments(Set<ClaimComment> claimComments) {
        this.claimComments = claimComments;
    }

    public Set<ContactPreference> getContactPreferences() {
        return contactPreferences;
    }

    public ShippingClaim contactPreferences(Set<ContactPreference> contactPreferences) {
        this.contactPreferences = contactPreferences;
        return this;
    }

    public ShippingClaim addContactPreference(ContactPreference contactPreference) {
        this.contactPreferences.add(contactPreference);
        contactPreference.setShippingClaim(this);
        return this;
    }

    public ShippingClaim removeContactPreference(ContactPreference contactPreference) {
        this.contactPreferences.remove(contactPreference);
        contactPreference.setShippingClaim(null);
        return this;
    }

    public void setContactPreferences(Set<ContactPreference> contactPreferences) {
        this.contactPreferences = contactPreferences;
    }

    public ClaimAttachment getClaimAttachment() {
        return claimAttachment;
    }

    public ShippingClaim claimAttachment(ClaimAttachment claimAttachment) {
        this.claimAttachment = claimAttachment;
        return this;
    }

    public void setClaimAttachment(ClaimAttachment claimAttachment) {
        this.claimAttachment = claimAttachment;
    }

    public ClaimMissingDocument getClaimMissingDocument() {
        return claimMissingDocument;
    }

    public ShippingClaim claimMissingDocument(ClaimMissingDocument claimMissingDocument) {
        this.claimMissingDocument = claimMissingDocument;
        return this;
    }

    public void setClaimMissingDocument(ClaimMissingDocument claimMissingDocument) {
        this.claimMissingDocument = claimMissingDocument;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShippingClaim)) {
            return false;
        }
        return id != null && id.equals(((ShippingClaim) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ShippingClaim{" +
            "id=" + getId() +
            ", receivedDate='" + getReceivedDate() + "'" +
            ", mailedDate='" + getMailedDate() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", trackingNumber='" + getTrackingNumber() + "'" +
            ", subject='" + getSubject() + "'" +
            ", description='" + getDescription() + "'" +
            ", notifyCustomer='" + isNotifyCustomer() + "'" +
            ", missingDocuments='" + isMissingDocuments() + "'" +
            "}";
    }
}
