package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ElasticsearchStatus.
 */
@Entity
@Table(name = "elasticsearch_status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ElasticsearchStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "elasticStatus")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ElasticShippingClaim> elasticShippingClaims = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ElasticShippingClaim> getElasticShippingClaims() {
        return elasticShippingClaims;
    }

    public ElasticsearchStatus elasticShippingClaims(Set<ElasticShippingClaim> elasticShippingClaims) {
        this.elasticShippingClaims = elasticShippingClaims;
        return this;
    }

    public ElasticsearchStatus addElasticShippingClaim(ElasticShippingClaim elasticShippingClaim) {
        this.elasticShippingClaims.add(elasticShippingClaim);
        elasticShippingClaim.setElasticStatus(this);
        return this;
    }

    public ElasticsearchStatus removeElasticShippingClaim(ElasticShippingClaim elasticShippingClaim) {
        this.elasticShippingClaims.remove(elasticShippingClaim);
        elasticShippingClaim.setElasticStatus(null);
        return this;
    }

    public void setElasticShippingClaims(Set<ElasticShippingClaim> elasticShippingClaims) {
        this.elasticShippingClaims = elasticShippingClaims;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElasticsearchStatus)) {
            return false;
        }
        return id != null && id.equals(((ElasticsearchStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ElasticsearchStatus{" +
            "id=" + getId() +
            "}";
    }
}
