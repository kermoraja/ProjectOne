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

    @DeleteMapping("api/deletetour/{id}")
    public void deleteTour(@PathVariable("id") java.lang.Integer id) {
        projectOneService.deleteTour(id);
    }
    @DeleteMapping("api/public/deleteguide/{id}")
    public void deleteGuide(@PathVariable("id") Integer id) {
        projectOneService.deleteGuide(id);
    }

    @PutMapping("api/edittour")
    public void editTour(@RequestBody TourDto tourDto) {
        projectOneService.editTour(tourDto);
    }
    @PutMapping("api/public/editguide")
    public void editGuide(@RequestBody GuideDto guideDto) {
        projectOneService.editGuide(guideDto);
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

   @PostMapping("api/avbTemplate")
   public void addAvbTemplate(@RequestBody AvbTemplate avbTemplate){
        projectOneService.addAvbTemplate(avbTemplate);
   }

   @GetMapping("api/avbTemplate/{id}")
    public AvbTemplate getAvbTemplate(@PathVariable("id") Integer id){
        return projectOneService.getAvbTemplate(id);
   }

}