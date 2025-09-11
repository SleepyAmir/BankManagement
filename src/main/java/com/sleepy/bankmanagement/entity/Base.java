package com.sleepy.bankmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class Base {

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "last_access")
    private LocalDateTime lastAccess;

    @Column(name = "deleted")
    private boolean deleted = false;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        modifiedAt = LocalDateTime.now();
    }

    @PostLoad
    public void postLoad() {
        lastAccess = LocalDateTime.now();
    }
}
