package com.kutub.holetmanagementSpringBoot.Service;

import com.kutub.holetmanagementSpringBoot.entity.Location;
import com.kutub.holetmanagementSpringBoot.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAllLocations(){

        return locationRepository.findAll();

    }

    public Location saveLocation(Location l){
      return   locationRepository.save(l);

    }

    public  void deleteLocation(int id){
        locationRepository.deleteById(id);
    }

    public  Location findById(int id){
        return  locationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Location Not Foound by this Id"));
    }

}
