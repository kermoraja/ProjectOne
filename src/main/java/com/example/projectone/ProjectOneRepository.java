package com.example.projectone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProjectOneRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    public int createTour(Tour tour){
        String sql="INSERT INTO tour_main(title, desc_short, desc_long, duration, city) VALUES (:title, :desc_short, :desc_long, :duration, :city)";
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

    public void addPhoto(TourPhotos tourPhotos){
        String sql="INSERT INTO tour_photos(photo_title, photo_url, tour_id) VALUES (:photo_title, :photo_url, :tour_id)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("photo_title", tourPhotos.getPhoto_title());
        paramMap.put("photo_url", tourPhotos.getPhoto_url());
        paramMap.put("tour_id", tourPhotos.getTour_id());
        jdbcTemplate.update(sql, paramMap);
    }

    public TourDto getTour(@PathVariable("id") Integer id) {
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
        String sql="INSERT INTO tour_city(city) VALUES (:city)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("city", tourCity.getCity());
        jdbcTemplate.update(sql, paramMap);
    }

    public void addGuide(TourGuide tourGuide){
        String sql = "INSERT INTO tour_guide (name, phone, email, city, hour_rate) VALUES (:name, :phone, :email, :city, :hour_rate)";
        Map<String,Object>  paramMap = new HashMap<>();
        paramMap.put("name",tourGuide.getName());
        paramMap.put("phone",tourGuide.getPhone());
        paramMap.put("email", tourGuide.getEmail());
        paramMap.put("city",tourGuide.getCity());
        paramMap.put("hour_rate",tourGuide.getHourRate());
        jdbcTemplate.update(sql,new MapSqlParameterSource(paramMap));
    }


}
