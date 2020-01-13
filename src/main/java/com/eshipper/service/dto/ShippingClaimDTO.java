package com.eshipper.service.dto;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.ShippingClaim} entity.
 */
public class ShippingClaimDTO implements Serializable {

    private Long id;

    private ZonedDateTime receivedDate;

    private ZonedDateTime mailedDate;

    private ZonedDateTime createdDate;

    private ZonedDateTime updatedDate;

    private String trackingNumber;

    private String subject;

    private String description;

    private Boolean notifyCustomer;

    private Boolean missingDocuments;


    private Long claimCarrierRefundId;

    private Long claimEshipperRefundId;

    private Long shippingOrderId;

    private Long ticketReasonId;

    private Long claimStatusId;

    private Long claimSolutionId;

    private Long claimAssigneeId;

    private Long claimCommentId;

    private Long contactPreferenceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(ZonedDateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    public ZonedDateTime getMailedDate() {
        return mailedDate;
    }

    public void setMailedDate(ZonedDateTime mailedDate) {
        this.mailedDate = mailedDate;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isNotifyCustomer() {
        return notifyCustomer;
    }

    public void setNotifyCustomer(Boolean notifyCustomer) {
        this.notifyCustomer = notifyCustomer;
    }

    public Boolean isMissingDocuments() {
        return missingDocuments;
    }

    public void setMissingDocuments(Boolean missingDocuments) {
        this.missingDocuments = missingDocuments;
    }

    public Long getClaimCarrierRefundId() {
        return claimCarrierRefundId;
    }

    public void setClaimCarrierRefundId(Long claimCarrierRefundId) {
        this.claimCarrierRefundId = claimCarrierRefundId;
    }

    public Long getClaimEshipperRefundId() {
        return claimEshipperRefundId;
    }

    public void setClaimEshipperRefundId(Long claimEshipperRefundId) {
        this.claimEshipperRefundId = claimEshipperRefundId;
    }

    public Long getShippingOrderId() {
        return shippingOrderId;
    }

    public void setShippingOrderId(Long shippingOrderId) {
        this.shippingOrderId = shippingOrderId;
    }

    public Long getTicketReasonId() {
        return ticketReasonId;
    }

    public void setTicketReasonId(Long ticketReasonId) {
        this.ticketReasonId = ticketReasonId;
    }

    public Long getClaimStatusId() {
        return claimStatusId;
    }

    public void setClaimStatusId(Long claimStatusId) {
        this.claimStatusId = claimStatusId;
    }

    public Long getClaimSolutionId() {
        return claimSolutionId;
    }

    public void setClaimSolutionId(Long claimSolutionId) {
        this.claimSolutionId = claimSolutionId;
    }

    public Long getClaimAssigneeId() {
        return claimAssigneeId;
    }

    public void setClaimAssigneeId(Long claimAssigneeId) {
        this.claimAssigneeId = claimAssigneeId;
    }

    public Long getClaimCommentId() {
        return claimCommentId;
    }

    public void setClaimCommentId(Long claimCommentId) {
        this.claimCommentId = claimCommentId;
    }

    public Long getContactPreferenceId() {
        return contactPreferenceId;
    }

    public void setContactPreferenceId(Long contactPreferenceId) {
        this.contactPreferenceId = contactPreferenceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShippingClaimDTO shippingClaimDTO = (ShippingClaimDTO) o;
        if (shippingClaimDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shippingClaimDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShippingClaimDTO{" +
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
            ", claimCarrierRefundId=" + getClaimCarrierRefundId() +
            ", claimEshipperRefundId=" + getClaimEshipperRefundId() +
            ", shippingOrderId=" + getShippingOrderId() +
            ", ticketReasonId=" + getTicketReasonId() +
            ", claimStatusId=" + getClaimStatusId() +
            ", claimSolutionId=" + getClaimSolutionId() +
            ", claimAssigneeId=" + getClaimAssigneeId() +
            ", claimCommentId=" + getClaimCommentId() +
            ", contactPreferenceId=" + getContactPreferenceId() +
            "}";
    }
}
