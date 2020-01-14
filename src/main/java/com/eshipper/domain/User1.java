package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A User1.
 */
@Entity
@Table(name = "user_1")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user1")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ClaimComment> claimComments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ClaimComment> getClaimComments() {
        return claimComments;
    }

    public User1 claimComments(Set<ClaimComment> claimComments) {
        this.claimComments = claimComments;
        return this;
    }

    public User1 addClaimComment(ClaimComment claimComment) {
        this.claimComments.add(claimComment);
        claimComment.setUser1(this);
        return this;
    }

    public User1 removeClaimComment(ClaimComment claimComment) {
        this.claimComments.remove(claimComment);
        claimComment.setUser1(null);
        return this;
    }

    public void setClaimComments(Set<ClaimComment> claimComments) {
        this.claimComments = claimComments;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User1)) {
            return false;
        }
        return id != null && id.equals(((User1) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "User1{" +
            "id=" + getId() +
            "}";
    }
}
