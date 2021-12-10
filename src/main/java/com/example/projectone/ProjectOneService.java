package com.example.projectone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProjectOneService {
    @Autowired
    private ProjectOneRepository projectOneRepository;


    public int createTour(Tour tour) {
        return projectOneRepository.createTour(tour);
    }


    public void addPhoto(TourPhotos tourPhotos) {
        projectOneRepository.addPhoto(tourPhotos);
    }

    public TourDto getTour(Integer id) {
        return projectOneRepository.getTour(id);
    }

    public void addCity(TourCity tourCity) {
        projectOneRepository.addCity(tourCity);
    }

    public void addGuide(TourGuide tourGuide) {
        projectOneRepository.addGuide(tourGuide);
    }

    public void addDriver(TourDriver tourDriver) {
        projectOneRepository.addDriver(tourDriver);
    }

    public List<TourDto> getTourList() {
        return projectOneRepository.getTourList();
    }

    public void deleteTour(Integer id) {
        projectOneRepository.deletePhoto(id);
        projectOneRepository.deleteTour(id);
    }

    public void editTour(Tour tour) {
        projectOneRepository.editTour(tour);
    }

    public void deletePhoto(Integer id) {
        projectOneRepository.deletePhoto(id);

    }

    public TourPhotosDto getPhotos(Integer id){
        return projectOneRepository.getPhotos(id);
    }

    public List<TourWithPhotos> tourWithPhotos() {
        return projectOneRepository.tourWithPhotos();
    }
}

