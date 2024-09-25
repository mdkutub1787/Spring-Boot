package com.kutub.holetmanagementSpringBoot.restcontroller;



import com.kutub.holetmanagementSpringBoot.Service.LocationService;
import com.kutub.holetmanagementSpringBoot.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
@CrossOrigin("*")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping("/save")
    public Location saveLocation(@RequestBody Location l){
        return locationService.saveLocation(l);

    }
}
