package com.example.projectone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class ProjectOneController {
    @Autowired
    private ProjectOneService projectOneService;

    @PostMapping("api/tour")
    public void createTour( @RequestBody Tour tour){
        projectOneService.createTour(tour);
    }



}
