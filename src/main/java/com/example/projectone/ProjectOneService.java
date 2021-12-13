package com.example.projectone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectOneService {
    @Autowired
    private ProjectOneRepository projectOneRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


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

    public void createUser(User user){
        String encodePassword = passwordEncoder.encode(user.getPassword());
        projectOneRepository.createUser(user.getUser_name(), encodePassword);
    }

    public String login(String userName, String password){
        String encodedPassword = projectOneRepository.getPassword(userName);
        if(passwordEncoder.matches(password, encodedPassword)){
            return "Sisse logitud";
        }else{
            return "Parool vale";
        }

    }

}

