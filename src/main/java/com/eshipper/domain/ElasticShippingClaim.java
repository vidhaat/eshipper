package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ElasticShippingClaim.
 */
@Entity
@Table(name = "elastic_shipping_claim")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ElasticShippingClaim implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private ShippingClaim shippingClaim;

    @ManyToOne
    @JsonIgnoreProperties("elasticShippingClaims")
    private ElasticsearchStatus elasticStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShippingClaim getShippingClaim() {
        return shippingClaim;
    }

    public ElasticShippingClaim shippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaim = shippingClaim;
        return this;
    }

    public void setShippingClaim(ShippingClaim shippingClaim) {
        this.shippingClaim = shippingClaim;
    }

    public ElasticsearchStatus getElasticStatus() {
        return elasticStatus;
    }

    public ElasticShippingClaim elasticStatus(ElasticsearchStatus elasticsearchStatus) {
        this.elasticStatus = elasticsearchStatus;
        return this;
    }

    public void setElasticStatus(ElasticsearchStatus elasticsearchStatus) {
        this.elasticStatus = elasticsearchStatus;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElasticShippingClaim)) {
            return false;
        }
        return id != null && id.equals(((ElasticShippingClaim) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ElasticShippingClaim{" +
            "id=" + getId() +
            "}";
    }
}
