package com.example.projectone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProjectOneController {
    @Autowired
    private ProjectOneService projectOneService;

    @PostMapping("api/tour")
    public int createTour(@RequestBody TourDto tourDto) {
        return projectOneService.createTour(tourDto);
    }

    @PostMapping("api/addphoto")
    public void addPhoto(@RequestBody TourPhotos tourPhotos) {
        projectOneService.addPhoto(tourPhotos);
    }

    @GetMapping("api/public/tour/{id}")
    public TourDto getTour(@PathVariable("id") java.lang.Integer id) {
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
    public void addDriver(@RequestBody TourDriver tourDriver) {
        projectOneService.addDriver(tourDriver);
    }

    @GetMapping("api/public/tourlist")
    public List<TourDto> getTourList() {
        return projectOneService.getTourList();
    }

    @DeleteMapping("api/deletetour/{id}")
    public void deleteTour(@PathVariable("id") java.lang.Integer id) {
        projectOneService.deleteTour(id);
    }

    @PutMapping("api/edittour")
    public void editTour(@RequestBody TourDto tourDto) {
        projectOneService.editTour(tourDto);
    }

    @DeleteMapping("api/deletephoto/{id}")
    public void deletePhoto(@PathVariable("id") java.lang.Integer id) {
        projectOneService.deletePhoto(id);
    }

    @GetMapping("api/public/photo/{id}")
    public TourPhotosDto getPhotos(@PathVariable("id") java.lang.Integer id) {
        return projectOneService.getPhotos(id);
    }

    @GetMapping("api/public/tourwithphotos")
    public List<TourWithPhotos> tourWithPhotos() {
        return projectOneService.tourWithPhotos();
    }

    @PostMapping("api/public/registeruser")
    public void createUser(@RequestBody User user) {
        projectOneService.createUser(user);
    }

    @PostMapping("/api/public/login")
    public String login(@RequestBody Login login) {
        return projectOneService.login(login.getUserName(), login.getPassword());
    }

    @GetMapping("api/public/gallery/{id}")
    public List<PhotoGalleryDto> getGallery(@PathVariable("id") java.lang.Integer id) {
        return projectOneService.getGallery(id);
    }

    @GetMapping("api/public/gallery")
    public List<PhotoGalleryDto> getFullGallery() {
        return projectOneService.getFullGallery();
    }
    @PutMapping("api/public/editGallery")
    public void editGallery(PhotoGalleryDto photoGalleryDto){
       projectOneService.editGallery(photoGalleryDto);
    }

    @PostMapping("api/addgallery")
    public void addGallery(@RequestBody PhotoGalleryDto photoGalleryDto) {
        projectOneService.addGallery(photoGalleryDto);
    }
    @DeleteMapping("api/public/deletegallery/{id}")
    public void deleteGallery(@PathVariable ("id") Integer id){
        projectOneService.deleteGallery(id);
    }

}