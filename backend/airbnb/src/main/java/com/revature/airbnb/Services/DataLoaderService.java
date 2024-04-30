package com.revature.airbnb.Services;

import com.revature.airbnb.Models.Listing;
import com.revature.airbnb.Models.Owner;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderService implements CommandLineRunner {
    private final OwnerService os;
    private final ListingService ls;

    DataLoaderService(OwnerService os, ListingService ls) {
        this.os = os;
        this.ls = ls;
    }

    @Override
    public void run(String... args) throws Exception {
        JSONArray jsonArray;
        try {
            String content = new String(Files.readAllBytes(Paths.get(getClass().getResource("/data/seed-Data.json").toURI())));
            jsonArray = new JSONArray(content);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Failed to load seed data", e);
        }

        // traverse array of objects
        // each object represents a Listing
        // each Listing has a primaryHost key points to object; represents an Owner
        for (int i = 0; i < 30; i++) {
            // get listing
            JSONObject lObj = jsonArray.getJSONObject(i);

            // get owner
            JSONObject ownerObj = lObj.getJSONObject("primaryHost"); 

            // create new owner
            Owner newOwner = os.registerOwner(
                // make name unique
                ownerObj.getString("firstName").concat(Integer.toString(i)),
                "password",
                "example@email.com"
            );

            // listing's description
            JSONObject descObj = lObj.getJSONObject("sectionedDescription");

            // check if summary exists
            String desc;
            if (descObj.has("summary")) {
                desc = descObj.getString("summary");
            } else if (descObj.has("description")) {
                desc = descObj.getString("description");
            } else {
                desc = "No description available.";
            }

            // truncate description if too long
            if (desc.length() > 255) {
                desc = desc.substring(0, 255);
            }

            // listing's photos
            JSONArray jsonArrayPhotos = lObj.getJSONArray("photos");
            List<String> photos = new ArrayList<>();

            // collect photos
            for (int j = 0; j < jsonArrayPhotos.length(); j++) {
                photos.add(jsonArrayPhotos.getJSONObject(j).getString("pictureUrl"));
            }

            // create new listing
            Listing newListing = new Listing(
                lObj.getString("address"),
                lObj.getString("city"),
                lObj.getString("state"),
                desc,
                photos,
                lObj.getString("name"),
                newOwner.getUserId()
            );

            // persist to DB
            ls.createListing(newListing);
        }
    }
}