package com.eshipper.service.dto;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.ClaimComment} entity.
 */
public class ClaimCommentDTO implements Serializable {

    private Long id;

    private String header;

    private String description;

    private ZonedDateTime date;

    private String commentBy;


    private Long user1Id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(String commentBy) {
        this.commentBy = commentBy;
    }

    public Long getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Long user1Id) {
        this.user1Id = user1Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClaimCommentDTO claimCommentDTO = (ClaimCommentDTO) o;
        if (claimCommentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), claimCommentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClaimCommentDTO{" +
            "id=" + getId() +
            ", header='" + getHeader() + "'" +
            ", description='" + getDescription() + "'" +
            ", date='" + getDate() + "'" +
            ", commentBy='" + getCommentBy() + "'" +
            ", user1Id=" + getUser1Id() +
            "}";
    }
}
