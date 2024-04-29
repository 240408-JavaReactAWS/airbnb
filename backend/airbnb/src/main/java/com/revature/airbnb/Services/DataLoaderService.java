package com.revature.airbnb.Services;

import com.revature.airbnb.Services.OwnerService;
import com.revature.airbnb.Services.ListingService;
import java.io.InputStream;
import java.util.Optional;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        JsonNode jsonNode;
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/seed-data.json")) {
            jsonNode = om.readValue(inputStream, JsonNode.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load seed data", e);
        }
        // JsonNode listings = getListings(jsonNode);
    }
    // private JsonNode getListings(JsonNode jsonNode) {
    //     return jsonNode.get("listings");
    // }
}