package com.example.projectone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProjectOneRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    public void createTour(Tour tour){
        String sql="INSERT INTO tour_main(title, desc_short, desc_long, duration, city) VALUES (:title, :desc_short, :desc_long, :duration, :city)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("title", tour.getTitle());
        paramMap.put("desc_short", tour.getDesc_short());
        paramMap.put("desc_long", tour.getDesc_long());
        paramMap.put("duration", tour.getDuration());
        paramMap.put("city", tour.getCity());
        jdbcTemplate.update(sql, paramMap);

    }

}
