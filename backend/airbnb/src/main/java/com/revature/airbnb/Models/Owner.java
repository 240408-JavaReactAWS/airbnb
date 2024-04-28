package com.revature.airbnb.Models;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "owners")
public class Owner extends User {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ownerId")
    private List<Listing> listings;

    public Owner() {
        super();
    }

    public Owner(String username, String password, String email, List<Listing> listings) {
        super(username, password, email);
        this.listings = listings;
    }
}