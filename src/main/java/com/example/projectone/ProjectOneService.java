package com.example.projectone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
}
