package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ContactPreference.
 */
@Entity
@Table(name = "contact_preference")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ContactPreference implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("contactPreferences")
    private ShippingClaim shippingClaim;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public ContactPreference user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShippingClaim getShippingClaim() {
        return shippingClaim;
    }

    public ContactPreference shippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaim = shippingClaim;
        return this;
    }

    public void setShippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaim = shippingClaim;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContactPreference)) {
            return false;
        }
        return id != null && id.equals(((ContactPreference) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ContactPreference{" +
            "id=" + getId() +
            "}";
    }
}
