package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A ClaimComment.
 */
@Entity
@Table(name = "claim_comment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClaimComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "header")
    private String header;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "comment_by")
    private String commentBy;

    @ManyToOne
    @JsonIgnoreProperties("claimComments")
    private ShippingClaim shippingClaim;

    @ManyToOne
    @JsonIgnoreProperties("claimComments")
    private User1 user1;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public ClaimComment header(String header) {
        this.header = header;
        return this;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public ClaimComment description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public ClaimComment date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getCommentBy() {
        return commentBy;
    }

    public ClaimComment commentBy(String commentBy) {
        this.commentBy = commentBy;
        return this;
    }

    public void setCommentBy(String commentBy) {
        this.commentBy = commentBy;
    }

    public ShippingClaim getShippingClaim() {
        return shippingClaim;
    }

    public ClaimComment shippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaim = shippingClaim;
        return this;
    }

    public void setShippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaim = shippingClaim;
    }

    public User1 getUser1() {
        return user1;
    }

    public ClaimComment user1(User1 user1) {
        this.user1 = user1;
        return this;
    }

    public void setUser1(User1 user1) {
        this.user1 = user1;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimComment)) {
            return false;
        }
        return id != null && id.equals(((ClaimComment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClaimComment{" +
            "id=" + getId() +
            ", header='" + getHeader() + "'" +
            ", description='" + getDescription() + "'" +
            ", date='" + getDate() + "'" +
            ", commentBy='" + getCommentBy() + "'" +
            "}";
    }
}
