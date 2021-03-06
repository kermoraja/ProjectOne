package com.example.projectone;

import com.example.projectone.excpetion.ApplicationException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import liquibase.pro.packaged.A;
import liquibase.pro.packaged.J;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProjectOneService {
    @Autowired
    private ProjectOneRepository projectOneRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;




    public int createTour(TourDto tourDto) {
        return projectOneRepository.createTour(tourDto);
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

    public DriverDto getDriver(Integer id){
        return projectOneRepository.getDriver(id);
    }

    public List<TourDto> getTourList() {
        return projectOneRepository.getTourList();
    }

    public List<GuideDto> getGuideList() {
        return projectOneRepository.getGuideList();
    }

    public void deleteTour(Integer id) {
        projectOneRepository.deletePhoto(id);
        projectOneRepository.deleteTour(id);
    }
    public void deleteGuide(Integer id) {
        projectOneRepository.deleteGuide(id);
    }

    public void editTour(TourDto tourDto) {
        projectOneRepository.editTour(tourDto);
    }
    public void editGuide(GuideDto guideDto) {
        projectOneRepository.editGuide(guideDto);
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

            JwtBuilder builder = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, "secret")
                    .claim("username", userName);
                    return builder.compact();
        }else{
            throw new ApplicationException("Wrong password");
        }

    }

    public List<PhotoGalleryDto> getFullGallery() {
        return projectOneRepository.getFullGallery();
    }

    public List<PhotoGalleryDto> getGallery(Integer id){
        return projectOneRepository.getGallery(id);
    }
    public void addGallery(PhotoGalleryDto photoGalleryDto) {
        projectOneRepository.addGallery(photoGalleryDto);
    }
    public void editGallery(PhotoGalleryDto photoGalleryDto){
        projectOneRepository.editGallery(photoGalleryDto);
    }
    public void deleteGallery(Integer id) {
        projectOneRepository.deleteGallery(id);
    }

    public void addAvbTemplate(AvbTemplate avbTemplate) {
        projectOneRepository.addAvbTemplate(avbTemplate);
    }
    public AvbTemplate getAvbTemplate(Integer id){
        return projectOneRepository.getAvbTemplate(id);
    }
    public List <AvbTemplate> getTourAvbTemplates(Integer id){return projectOneRepository.getTourAvbTemplates(id);}
    public List <AvbTemplate> getAllTourAvbTemplates(){return projectOneRepository.getAllTourAvbTemplates();}

    public void genAvailabilities(){
        List <AvbTemplate> avbTemplates = projectOneRepository.getAllTourAvbTemplates();


    }

    public GuideDto getGuide(@PathVariable("id") java.lang.Integer id) {
        return projectOneRepository.getGuide(id);

    }

    public List<DriverDto> getDriverList(){
        return projectOneRepository.getDriverList();
    }

    public void editDriver(DriverDto driverDto) {
        projectOneRepository.editDriver(driverDto);
    }

}

