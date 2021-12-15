package com.example.projectone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectOneRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int createTour(Tour tour) {
        String sql = "INSERT INTO tour_main(title, desc_short, desc_long, duration, city) VALUES (:title, :desc_short, :desc_long, :duration, :city)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("title", tour.getTitle());
        paramMap.put("desc_short", tour.getDesc_short());
        paramMap.put("desc_long", tour.getDesc_long());
        paramMap.put("duration", tour.getDuration());
        paramMap.put("city", tour.getCity());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return (Integer) keyHolder.getKeys().get("id");

    }

    public void addPhoto(TourPhotos tourPhotos) {
        String sql = "INSERT INTO tour_photos(photo_title, photo_url, tour_id) VALUES (:photo_title, :photo_url, :tour_id)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("photo_title", tourPhotos.getPhoto_title());
        paramMap.put("photo_url", tourPhotos.getPhoto_url());
        paramMap.put("tour_id", tourPhotos.getTour_id());
        jdbcTemplate.update(sql, paramMap);
    }

    public TourDto getTour(Integer id) {
        String sql = "SELECT * FROM tour_main WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return jdbcTemplate.queryForObject(sql, paramMap, new TourDtoRowMapper());
    }

    private class TourDtoRowMapper implements RowMapper<TourDto> {
        @Override
        public TourDto mapRow(ResultSet resultSet, int i) throws SQLException {
            TourDto result = new TourDto();
            result.setId(resultSet.getInt("id"));
            result.setTitle(resultSet.getString("title"));
            result.setDesc_short(resultSet.getString("desc_short"));
            result.setDesc_long(resultSet.getString("desc_long"));
            result.setDuration(resultSet.getString("duration"));
            result.setCity(resultSet.getInt("city"));
            return result;
        }
    }

    public void addCity(TourCity tourCity) {
        String sql = "INSERT INTO tour_city(city) VALUES (:city)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("city", tourCity.getCity());
        jdbcTemplate.update(sql, paramMap);
    }

    public void addGuide(TourGuide tourGuide) {
        String sql = "INSERT INTO tour_guide (name, phone, email, city_id, hour_rate) VALUES (:name, :phone, :email, :city, :hour_rate)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", tourGuide.getName());
        paramMap.put("phone", tourGuide.getPhone());
        paramMap.put("email", tourGuide.getEmail());
        paramMap.put("city", tourGuide.getCity());
        paramMap.put("hour_rate", tourGuide.getHour_rate());
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap));
    }

    public void addDriver(TourDriver tourDriver) {
        String sql = "INSERT INTO tour_driver (first_name, last_name, phone, email, city, car_size) VALUES" +
                "(:first_name, :last_name, :phone, :email, :city, :car_size)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", tourDriver.getFirst_name());
        paramMap.put("last_name", tourDriver.getLast_name());
        paramMap.put("phone", tourDriver.getPhone());
        paramMap.put("email", tourDriver.getEmail());
        paramMap.put("city", tourDriver.getCity());
        paramMap.put("car_size", tourDriver.getCar_size());
        jdbcTemplate.update(sql, paramMap);
    }

    public List<TourDto> getTourList() {
        String sql = "SELECT * FROM tour_main";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.query(sql, paramMap, new TourDtoRowMapper());
    }

    public void deleteTour(Integer id) {
        String sql = "DELETE FROM tour_main WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        jdbcTemplate.update(sql, paramMap);
    }

    public void editTour(Tour tour) {
        String sql = "UPDATE tour_main SET title = :newTitle, desc_short = :newDesc_short, desc_long = :newDesc_long, duration = :newDuration, city = :newCity WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", tour.getId());
        paramMap.put("newTitle", tour.getTitle());
        paramMap.put("newDesc_short", tour.getDesc_short());
        paramMap.put("newDesc_long", tour.getDesc_long());
        paramMap.put("newDuration", tour.getDuration());
        paramMap.put("newCity", tour.getCity());
        jdbcTemplate.update(sql, paramMap);


    }

    public void deletePhoto(Integer id) {
        String sql = "DELETE FROM tour_photos WHERE tour_id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        jdbcTemplate.update(sql, paramMap);


    }

    public TourPhotosDto getPhotos(Integer id) {
        String sql = "SELECT * FROM tour_photos WHERE tour_id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return jdbcTemplate.queryForObject(sql, paramMap, new TourPhotosDtoRowMapper());
    }

    private class TourPhotosDtoRowMapper implements RowMapper<TourPhotosDto> {
        @Override
        public TourPhotosDto mapRow(ResultSet resultSet, int i) throws SQLException {
            TourPhotosDto result = new TourPhotosDto();
            result.setId(resultSet.getInt("id"));
            result.setPhoto_title(resultSet.getString("photo_title"));
            result.setPhoto_url(resultSet.getString("photo_url"));
            result.setTour_id(resultSet.getInt("tour_id"));
            return result;
        }
    }

    public List<TourWithPhotos> tourWithPhotos() {
        String sql = "SELECT * FROM tour_main b JOIN tour_photos c ON b.id = c.tour_id";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.query(sql, paramMap, new TourWithPhotosRowMapper());
    }

    private class TourWithPhotosRowMapper implements RowMapper<TourWithPhotos> {
        @Override
        public TourWithPhotos mapRow(ResultSet resultSet, int i) throws SQLException {
            TourWithPhotos result = new TourWithPhotos();
            result.setId(resultSet.getInt("id"));
            result.setDesc_short(resultSet.getString("desc_short"));
            result.setDesc_long(resultSet.getString("desc_long"));
            result.setDuration(resultSet.getString("duration"));
            result.setCity(resultSet.getInt("city"));
            result.setTitle(resultSet.getString("title"));
            result.setPhoto_title(resultSet.getString("photo_title"));
            result.setPhoto_url(resultSet.getString("photo_url"));

            return result;
        }
    }

    public void createUser(String userName, String password) {

        String sql="INSERT INTO user_db(user_name, password) VALUES (:user_name, :password)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_name", userName);
        paramMap.put("password", password);
        jdbcTemplate.update(sql, paramMap);
    }

    public String getPassword(String userName){
        String sql="SELECT password FROM user_db WHERE user_name = :user_name";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_name", userName);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);

    }
    public List<PhotoGalleryDto> getGallery(Integer id) {
        String sql = "SELECT * FROM tour_gallery WHERE tour_id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return jdbcTemplate.query(sql, paramMap, new PhotoGalleryDtoRowMapper());
    }
    private class PhotoGalleryDtoRowMapper implements RowMapper<PhotoGalleryDto> {
        @Override
        public PhotoGalleryDto mapRow(ResultSet resultSet, int i) throws SQLException {
            PhotoGalleryDto result = new PhotoGalleryDto();
            result.setId(resultSet.getInt("id"));
            result.setPhoto_url(resultSet.getString("photo_url"));
            result.setTour_id(resultSet.getInt("tour_id"));
            return result;
        }
    }
    public void addGallery(PhotoGalleryDto photoGalleryDto) {
        String sql = "INSERT INTO tour_gallery(photo_url, tour_id) VALUES (:photo_url, :tour_id)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("photo_url", photoGalleryDto.getPhoto_url());
        paramMap.put("tour_id", photoGalleryDto.getTour_id());
        jdbcTemplate.update(sql, paramMap);
    }
}
