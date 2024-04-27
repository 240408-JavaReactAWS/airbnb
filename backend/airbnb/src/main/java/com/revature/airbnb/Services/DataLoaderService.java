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

    private final ObjectMapper objectMapper;

    private final OwnerService ownerService;

    private final ListingService listingService;

    public DataLoaderService(ObjectMapper objectMapper, OwnerService ownerService, ListingService listingService) {
        this.ownerService = ownerService;
        this.listingService = listingService;
        this.objectMapper = objectMapper;
    }

    /* following tutorial at time: https://youtu.be/EumLbf8WjnY?t=823 */
    @Override
    public void run(String... args) throws Exception {
        JsonNode jsonNode;
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/seed-data.json")) {
            jsonNode = objectMapper.readValue(inputStream, JsonNode.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load seed data", e);
        }
        // JsonNode listings = getListings(jsonNode);
    }
    // private JsonNode getListings(JsonNode jsonNode) {
    //     return jsonNode.get("listings");
    // }
}
