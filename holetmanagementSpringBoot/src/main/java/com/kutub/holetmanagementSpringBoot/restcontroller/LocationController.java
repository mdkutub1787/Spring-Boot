package com.kutub.holetmanagementSpringBoot.restcontroller;

import com.kutub.holetmanagementSpringBoot.Service.LocationService;
import com.kutub.holetmanagementSpringBoot.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    // Get all locations
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    // Get location by ID
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable int id) {
        Location location = locationService.findById(id);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    // Create a new location
    @PostMapping("/save")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        locationService.saveLocation(location);
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    // Update an existing location
    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable int id, @RequestBody Location updatedLocation) {
        Location existingLocation = locationService.findById(id);
        if (existingLocation != null) {
            updatedLocation.setId(id);
            locationService.saveLocation(updatedLocation);
            return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a location
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
