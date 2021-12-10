package com.example.projectone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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

    @PostMapping("api/driver")
    public void addDriver(@RequestBody TourDriver tourDriver){
        projectOneService.addDriver(tourDriver);
    }

    @GetMapping("api/tourlist")
    public List<TourDto> getTourList(){
        return projectOneService.getTourList();
    }

    @DeleteMapping("api/deletetour/{id}")
    public void deleteTour(@PathVariable("id") Integer id){
        projectOneService.deleteTour(id);
    }

    @PutMapping("api/edittour")
    public void editTour(@RequestBody Tour tour){
        projectOneService.editTour(tour);
    }

    @DeleteMapping("api/deletephoto/{id}")
    public void deletePhoto(@PathVariable("id") Integer id){
        projectOneService.deletePhoto(id);
    }


}
