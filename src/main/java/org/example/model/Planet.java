package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.regex.Pattern;

@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @Column(length = 10)
    private String id;

    @Column(nullable = false, length = 500)
    private String name;

    private static final Pattern ID_PATTERN = Pattern.compile("^[A-Z0]+$");

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (!ID_PATTERN.matcher(id).matches()) {
            throw new IllegalArgumentException("Planet ID must consist of uppercase letters only.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}