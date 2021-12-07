package com.example.projectone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProjectOneService {
@Autowired
private ProjectOneRepository projectOneRepository;
    @PostMapping("")
    public void createTour(@RequestBody Tour tour) {
       projectOneRepository.createTour(tour);
    }
}
