package com.example.projectone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController

public class ProjectOneController {
    @Autowired
    private ProjectOneService projectOneService;

    @PostMapping("api/tour")
    public int createTour( @RequestBody Tour tour){
        return projectOneService.createTour(tour);
    }

    @PostMapping("api/addphoto")
    public void addPhoto(@RequestBody TourPhotos tourPhotos){
        projectOneService.addPhoto(tourPhotos);
    }
    @GetMapping("api/tour/{id}")
    public TourDto getTour(@PathVariable("id") Integer id) {
        return projectOneService.getTour(id);

    }
    @PostMapping("api/city")
    public void addCity(@RequestBody TourCity tourCity) {
        projectOneService.addCity(tourCity);
    }

    @PostMapping("api/guide")
    public void addGuide(@RequestBody TourGuide tourGuide) {
        projectOneService.addGuide(tourGuide);
    }
}
