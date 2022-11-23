package com.blc.customerInterface.lib.dao.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseDomain implements Serializable {

    //private static final long serialVersionUID = 1L;

    private UUID id;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private LocalDateTime deletedDateTime;
    private String description;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Access(AccessType.FIELD)
    @Column(name = "id", nullable = false)
    @Type(type = "uuid-binary")
    public UUID getId() {
        return id;
    }

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @Access(AccessType.FIELD)
    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    @UpdateTimestamp
    @Column(nullable = false)
    @Access(AccessType.FIELD)
    public LocalDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    @Column
    public LocalDateTime getDeletedDateTime() {
        return deletedDateTime;
    }

    public void setDeletedDateTime(LocalDateTime deletedDateTime) {
        this.deletedDateTime = deletedDateTime;
    }

    @Column(length = 2048)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDomain that = (BaseDomain) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
