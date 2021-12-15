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
    public int createTour(@RequestBody Tour tour) {
        return projectOneService.createTour(tour);
    }

    @PostMapping("api/addphoto")
    public void addPhoto(@RequestBody TourPhotos tourPhotos) {
        projectOneService.addPhoto(tourPhotos);
    }

    @GetMapping("api/public/tour/{id}")
    public TourDto getTour(@PathVariable("id") Integer id) {
        return projectOneService.getTour(id);

    }

    @PostMapping("api/city")
    public void addCity(@RequestBody TourCity tourCity) {
        projectOneService.addCity(tourCity);
    }

    @PostMapping("api/public/guide")
    public void addGuide(@RequestBody TourGuide tourGuide) {
        projectOneService.addGuide(tourGuide);
    }

    @PostMapping("api/public/driver")
    public void addDriver(@RequestBody TourDriver tourDriver) {
        projectOneService.addDriver(tourDriver);
    }

    @GetMapping("api/public/tourlist")
    public List<TourDto> getTourList() {
        return projectOneService.getTourList();
    }
    @GetMapping("api/public/guidelist")
    public List<GuideDto> getGuideList() {
        return projectOneService.getGuideList();
    }

    @DeleteMapping("api/public/deletetour/{id}")
    public void deleteTour(@PathVariable("id") Integer id) {
        projectOneService.deleteTour(id);
    }
    @DeleteMapping("api/public/deleteguide/{id}")
    public void deleteGuide(@PathVariable("id") Integer id) {
        projectOneService.deleteGuide(id);
    }

    @PutMapping("api/public/edittour")
    public void editTour(@RequestBody Tour tour) {
        projectOneService.editTour(tour);
    }
    @PutMapping("api/public/editguide")
    public void editGuide(@RequestBody GuideDto guideDto) {
        projectOneService.editGuide(guideDto);
    }

    @DeleteMapping("api/public/deletephoto/{id}")
    public void deletePhoto(@PathVariable("id") Integer id) {
        projectOneService.deletePhoto(id);
    }

    @GetMapping("api/public/photo/{id}")
    public TourPhotosDto getPhotos(@PathVariable("id") Integer id) {
        return projectOneService.getPhotos(id);
    }

    @GetMapping("api/public/tourwithphotos")
    public List<TourWithPhotos> tourWithPhotos() {
        return projectOneService.tourWithPhotos();
    }

    @PostMapping("api/public/registeruser")
    public void createUser(@RequestBody User user){
        projectOneService.createUser(user);
    }

    @PostMapping("/api/public/login")
    public String login(@RequestBody Login login){
        return projectOneService.login(login.getUserName(), login.getPassword());
    }
    @GetMapping("api/public/gallery/{id}")
    public List<PhotoGalleryDto> getGallery(@PathVariable("id") Integer id) {
        return projectOneService.getGallery(id);
    }

    @PostMapping("api/addgallery")
    public void addGallery(@RequestBody PhotoGalleryDto photoGalleryDto) {
        projectOneService.addGallery(photoGalleryDto);
    }

}