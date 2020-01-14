package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ClaimDocumentType.
 */
@Entity
@Table(name = "claim_document_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClaimDocumentType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "claimDocumentType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ClaimMissingDocument> claimMissingDocuments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ClaimDocumentType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ClaimMissingDocument> getClaimMissingDocuments() {
        return claimMissingDocuments;
    }

    public ClaimDocumentType claimMissingDocuments(Set<ClaimMissingDocument> claimMissingDocuments) {
        this.claimMissingDocuments = claimMissingDocuments;
        return this;
    }

    public ClaimDocumentType addClaimMissingDocument(ClaimMissingDocument claimMissingDocument) {
        this.claimMissingDocuments.add(claimMissingDocument);
        claimMissingDocument.setClaimDocumentType(this);
        return this;
    }

    public ClaimDocumentType removeClaimMissingDocument(ClaimMissingDocument claimMissingDocument) {
        this.claimMissingDocuments.remove(claimMissingDocument);
        claimMissingDocument.setClaimDocumentType(null);
        return this;
    }

    public void setClaimMissingDocuments(Set<ClaimMissingDocument> claimMissingDocuments) {
        this.claimMissingDocuments = claimMissingDocuments;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimDocumentType)) {
            return false;
        }
        return id != null && id.equals(((ClaimDocumentType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClaimDocumentType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
