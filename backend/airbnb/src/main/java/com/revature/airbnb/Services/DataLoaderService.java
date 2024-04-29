package com.revature.airbnb.Services;

import com.revature.airbnb.Services.OwnerService;
import com.revature.airbnb.Models.Listing;
import com.revature.airbnb.Services.ListingService;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataLoaderService implements CommandLineRunner {

    private final ObjectMapper om;
    private final OwnerService os;
    private final ListingService ls;

    public DataLoaderService(ObjectMapper om, OwnerService os, ListingService ls) {
        this.os = os;
        this.ls = ls;
        this.om = om;
    }

    /* following tutorial at time: https://youtu.be/EumLbf8WjnY?t=823 */
    @Override
    public void run(String... args) throws Exception {
        // List<Listing> listings = new ArrayList<>();
        List<JsonNode> listings = new ArrayList<>();
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/seed-Data.json")) {
            listings = om.readValue(inputStream, om.getTypeFactory().constructCollectionType(List.class, Listing.class));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load seed data", e);
        }

        // for (int i = 0; i < listings.size(); i++) {
        //     System.out.println(listings.get(i));
        // }
        // for (int i = 0; i < 5; i++) {
        //     String l = listings.get(i).asText();
        //     // System.out.println(l);
        //     // System.out.println(listings.get(i).toString());
        // }
    }
}