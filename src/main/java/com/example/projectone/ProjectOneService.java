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

    @PostMapping("api/tour")
    public int createTour(@RequestBody Tour tour) {
        return projectOneRepository.createTour(tour);
    }

    @PostMapping("api/addphoto")
    public void addPhoto(@RequestBody TourPhotos tourPhotos) {
         projectOneRepository.addPhoto(tourPhotos);
    }
    @GetMapping("api/tour/{id}")
    public TourDto getTour(@PathVariable("id") Integer id) {
        return projectOneRepository.getTour(id);
    }
}
