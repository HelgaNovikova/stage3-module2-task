package com.mjc.school.repository.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class AuthorModel implements BaseEntity<Long> {
    private Long id;
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;

    public String getName() {
        return name;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public AuthorModel(Long id, String name, LocalDateTime createDate, LocalDateTime lastUpdateDate) {
        this.createDate = createDate;
        this.id = id;
        this.lastUpdateDate = lastUpdateDate;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorModel author = (AuthorModel) o;
        return id == author.id && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
