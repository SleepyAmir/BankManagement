package com.sleepy.bankmanagement.entity;

import javax.persistence.Column;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class Base {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime lastAccess;

    @Column(name="deleted")
    private boolean deleted;

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