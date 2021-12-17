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

    public int createTour(TourDto tourDto) {
        String sql = "INSERT INTO tour_main(title, desc_short, desc_long, duration, city) VALUES (:title, :desc_short, :desc_long, :duration, :city)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("title", tourDto.getTitle());
        paramMap.put("desc_short", tourDto.getDesc_short());
        paramMap.put("desc_long", tourDto.getDesc_long());
        paramMap.put("duration", tourDto.getDuration());
        paramMap.put("city", tourDto.getCity());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return (Integer) keyHolder.getKeys().get("id");
// test
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

    private class GuideDtoRowMapper implements RowMapper<GuideDto> {
        @Override
        public GuideDto mapRow(ResultSet resultSet, int i) throws SQLException {
            GuideDto result = new GuideDto();
            result.setId(resultSet.getInt("id"));
            result.setName(resultSet.getString("name"));
            result.setPhone(resultSet.getInt("phone"));
            result.setEmail(resultSet.getString("email"));
            result.setHourRate(resultSet.getInt("hour_rate"));
            result.setCity_id(resultSet.getInt("city_id"));
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
        String sql = "INSERT INTO tour_driver (first_name, last_name, phone, email, city_id, car_size) VALUES" +
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

    public List<GuideDto> getGuideList() {
        String sql = "SELECT * FROM tour_guide";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.query(sql, paramMap, new GuideDtoRowMapper());
    }

    public void deleteTour(Integer id) {
        String sql = "DELETE FROM tour_main WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        jdbcTemplate.update(sql, paramMap);
    }

    public void deleteGuide(Integer id) {
        String sql = "DELETE FROM tour_guide WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        jdbcTemplate.update(sql, paramMap);
    }

    public void editTour(TourDto tourDto) {
        String sql = "UPDATE tour_main SET title = :newTitle, desc_short = :newDesc_short, desc_long = :newDesc_long, duration = :newDuration, city = :newCity WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", tourDto.getId());
        paramMap.put("newTitle", tourDto.getTitle());
        paramMap.put("newDesc_short", tourDto.getDesc_short());
        paramMap.put("newDesc_long", tourDto.getDesc_long());
        paramMap.put("newDuration", tourDto.getDuration());
        paramMap.put("newCity", tourDto.getCity());
        jdbcTemplate.update(sql, paramMap);


    }

    public void editGuide(GuideDto guideDto) {
        String sql = "UPDATE tour_guide SET name = :newName, phone = :newPhone, email = :newEmail, hour_rate= :newHourRate, city_id = :newCity WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", guideDto.getId());
        paramMap.put("newName", guideDto.getName());
        paramMap.put("newPhone", guideDto.getPhone());
        paramMap.put("newEmail", guideDto.getEmail());
        paramMap.put("newHourRate", guideDto.getHourRate());
        paramMap.put("newCity", guideDto.getCity_id());
        jdbcTemplate.update(sql, paramMap);


    }

    public void deletePhoto(Integer id) {
        String sql = "DELETE FROM tour_photos WHERE tour_id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        jdbcTemplate.update(sql, paramMap);


    }

    private class TourGuideRowMapper implements RowMapper<TourGuide> {
        @Override
        public TourGuide mapRow(ResultSet resultSet, int i) throws SQLException {
            TourGuide result = new TourGuide();
            result.setId(resultSet.getInt("id"));
            result.setCity(resultSet.getInt("city_id"));
            result.setName(resultSet.getString("name"));
            result.setPhone(resultSet.getInt("phone"));
            result.setHour_rate(resultSet.getInt("hour_rate"));
            result.setEmail(resultSet.getString("email"));
            return result;
        }


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

        String sql = "INSERT INTO user_db(user_name, password) VALUES (:user_name, :password)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_name", userName);
        paramMap.put("password", password);
        jdbcTemplate.update(sql, paramMap);
    }

    public String getPassword(String userName) {
        String sql = "SELECT password FROM user_db WHERE user_name = :user_name";
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

    public List<PhotoGalleryDto> getFullGallery() {
        String sql = "SELECT * FROM tour_gallery";
        Map<String, Object> paramMap = new HashMap<>();
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

    public void editGallery(PhotoGalleryDto photoGalleryDto) {
        String sql = "UPDATE tour_gallery SET photo_url =:photo_url, tour_id = :tour_id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("photo_url", photoGalleryDto.getPhoto_url());
        paramMap.put("tour_id", photoGalleryDto.getTour_id());
        jdbcTemplate.update(sql, paramMap);

    }

    public void deleteGallery(Integer id) {
        String sql = "DELETE FROM tour_gallery WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        jdbcTemplate.update(sql, paramMap);
    }

    public void addAvbTemplate(AvbTemplate avbTemplate) {
        String sql = "INSERT INTO tour_avb_template (tour_id, start_date, end_date, day_of_week, time, max_group, " +
                "regular_price, reduced_price) VALUES (:tourId, :startDate, :endDate, :dayOfWeek, :time, :maxGroup, " +
                ":regularPrice, :reducedPrice)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tourId", avbTemplate.getTourId());
        paramMap.put("startDate", avbTemplate.getStartDate());
        paramMap.put("endDate", avbTemplate.getEndDate());
        paramMap.put("dayOfWeek", avbTemplate.getDayOfWeek());
        paramMap.put("time", avbTemplate.getTime());
        paramMap.put("maxGroup", avbTemplate.getMaxGroup());
        paramMap.put("regularPrice", avbTemplate.getRegularPrice());
        paramMap.put("reducedPrice", avbTemplate.getReducedPrice());
        jdbcTemplate.update(sql, paramMap);
    }

    public AvbTemplate getAvbTemplate(Integer id) {
        String sql = "SELECT * FROM tour_avb_template WHERE id= :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return jdbcTemplate.queryForObject(sql, paramMap, new AvbTemplateRowMapper());
    }

    public List <AvbTemplate> getTourAvbTemplates(Integer id) {
        String sql = "SELECT * FROM tour_avb_template WHERE tour_id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return jdbcTemplate.query(sql,paramMap, new AvbTemplateRowMapper());
    }

    private class AvbTemplateRowMapper implements RowMapper<AvbTemplate> {
        @Override
        public AvbTemplate mapRow(ResultSet resultSet, int i) throws SQLException {
            AvbTemplate result = new AvbTemplate();
            result.setId(resultSet.getInt("id"));
            result.setTourId(resultSet.getInt("tour_id"));
            result.setStartDate(resultSet.getDate("start_date"));
            result.setEndDate(resultSet.getDate("end_date"));
            result.setDayOfWeek(resultSet.getInt("day_of_week"));
            result.setTime(resultSet.getTime("time"));
            result.setMaxGroup(resultSet.getInt("max_group"));
            result.setRegularPrice(resultSet.getInt("regular_price"));
            result.setReducedPrice(resultSet.getInt("reduced_price"));
            return result;
        }
    }
    public GuideDto getGuide(Integer id) {
        String sql = "SELECT * FROM tour_guide WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return jdbcTemplate.queryForObject(sql, paramMap, new GuideDtoRowMapper());
    }
    public DriverDto getDriver(Integer id) {
        String sql = "SELECT * FROM tour_driver WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return jdbcTemplate.queryForObject(sql, paramMap, new DriverDtoRowMapper());
    }

    public List<DriverDto> getDriverList(){
        String sql = "SELECT * FROM tour_driver";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.query(sql, paramMap, new DriverDtoRowMapper());
    }
    private class DriverDtoRowMapper implements RowMapper<DriverDto> {
        @Override
        public DriverDto mapRow(ResultSet resultSet, int i) throws SQLException {
            DriverDto result = new DriverDto();
            result.setId(resultSet.getInt("id"));
            result.setFirstName(resultSet.getString("first_name"));
            result.setLastName(resultSet.getString("last_name"));
            result.setPhone(resultSet.getString("phone"));
            result.setEmail(resultSet.getString("email"));
            result.setCity_id(resultSet.getInt("city_id"));
            result.setCar_size(resultSet.getInt("car_size"));
            return result;
        }
    }
    public void editDriver(DriverDto driverDto) {
        String sql = "UPDATE tour_driver SET first_name = :newName, last_name = :newLastName, phone = :newPhone, email = :newEmail, car_size= :newCarSize, city_id = :newCity WHERE id = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", driverDto.getId());
        paramMap.put("newName", driverDto.getFirstName());
        paramMap.put("newLastName", driverDto.getLastName());
        paramMap.put("newPhone", driverDto.getPhone());
        paramMap.put("newEmail", driverDto.getEmail());
        paramMap.put("newCarSize", driverDto.getCar_size());
        paramMap.put("newCity", driverDto.getCity_id());
        jdbcTemplate.update(sql, paramMap);


    }
}
