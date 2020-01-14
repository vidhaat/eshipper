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
    private Set<ClaimAttachment> claimAttachments = new HashSet<>();

    @OneToMany(mappedBy = "shippingClaim")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ClaimMissingDocument> claimMissingDocuments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("shippingClaims")
    private ShippingOrder shippingOrder;

    @ManyToOne
    @JsonIgnoreProperties("shippingClaims")
    private TicketReason ticketReason;

    @ManyToOne
    @JsonIgnoreProperties("shippingClaims")
    private ClaimStatus claimStatus;

    @ManyToOne
    @JsonIgnoreProperties("shippingClaims")
    private ClaimSolution claimSolution;

    @ManyToOne
    @JsonIgnoreProperties("shippingClaims")
    private ClaimAssignee claimAssignee;

    @ManyToOne
    @JsonIgnoreProperties("shippingClaims")
    private ClaimComment claimComment;

    @ManyToOne
    @JsonIgnoreProperties("shippingClaims")
    private ContactPreference contactPreference;

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

    public Set<ClaimAttachment> getClaimAttachments() {
        return claimAttachments;
    }

    public ShippingClaim claimAttachments(Set<ClaimAttachment> claimAttachments) {
        this.claimAttachments = claimAttachments;
        return this;
    }

    public ShippingClaim addClaimAttachment(ClaimAttachment claimAttachment) {
        this.claimAttachments.add(claimAttachment);
        claimAttachment.setShippingClaim(this);
        return this;
    }

    public ShippingClaim removeClaimAttachment(ClaimAttachment claimAttachment) {
        this.claimAttachments.remove(claimAttachment);
        claimAttachment.setShippingClaim(null);
        return this;
    }

    public void setClaimAttachments(Set<ClaimAttachment> claimAttachments) {
        this.claimAttachments = claimAttachments;
    }

    public Set<ClaimMissingDocument> getClaimMissingDocuments() {
        return claimMissingDocuments;
    }

    public ShippingClaim claimMissingDocuments(Set<ClaimMissingDocument> claimMissingDocuments) {
        this.claimMissingDocuments = claimMissingDocuments;
        return this;
    }

    public ShippingClaim addClaimMissingDocument(ClaimMissingDocument claimMissingDocument) {
        this.claimMissingDocuments.add(claimMissingDocument);
        claimMissingDocument.setShippingClaim(this);
        return this;
    }

    public ShippingClaim removeClaimMissingDocument(ClaimMissingDocument claimMissingDocument) {
        this.claimMissingDocuments.remove(claimMissingDocument);
        claimMissingDocument.setShippingClaim(null);
        return this;
    }

    public void setClaimMissingDocuments(Set<ClaimMissingDocument> claimMissingDocuments) {
        this.claimMissingDocuments = claimMissingDocuments;
    }

    public ShippingOrder getShippingOrder() {
        return shippingOrder;
    }

    public ShippingClaim shippingOrder(ShippingOrder shippingOrder) {
        this.shippingOrder = shippingOrder;
        return this;
    }

    public void setShippingOrder(ShippingOrder shippingOrder) {
        this.shippingOrder = shippingOrder;
    }

    public TicketReason getTicketReason() {
        return ticketReason;
    }

    public ShippingClaim ticketReason(TicketReason ticketReason) {
        this.ticketReason = ticketReason;
        return this;
    }

    public void setTicketReason(TicketReason ticketReason) {
        this.ticketReason = ticketReason;
    }

    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }

    public ShippingClaim claimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
        return this;
    }

    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }

    public ClaimSolution getClaimSolution() {
        return claimSolution;
    }

    public ShippingClaim claimSolution(ClaimSolution claimSolution) {
        this.claimSolution = claimSolution;
        return this;
    }

    public void setClaimSolution(ClaimSolution claimSolution) {
        this.claimSolution = claimSolution;
    }

    public ClaimAssignee getClaimAssignee() {
        return claimAssignee;
    }

    public ShippingClaim claimAssignee(ClaimAssignee claimAssignee) {
        this.claimAssignee = claimAssignee;
        return this;
    }

    public void setClaimAssignee(ClaimAssignee claimAssignee) {
        this.claimAssignee = claimAssignee;
    }

    public ClaimComment getClaimComment() {
        return claimComment;
    }

    public ShippingClaim claimComment(ClaimComment claimComment) {
        this.claimComment = claimComment;
        return this;
    }

    public void setClaimComment(ClaimComment claimComment) {
        this.claimComment = claimComment;
    }

    public ContactPreference getContactPreference() {
        return contactPreference;
    }

    public ShippingClaim contactPreference(ContactPreference contactPreference) {
        this.contactPreference = contactPreference;
        return this;
    }

    public void setContactPreference(ContactPreference contactPreference) {
        this.contactPreference = contactPreference;
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
