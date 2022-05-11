package ru.redcollar.home.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.Map;

public class CustomRepositoryImpl implements CustomRepository {

    @Override
    public void doSomethingCool() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/rchome");
        driverManagerDataSource.setUsername("gabone");
        driverManagerDataSource.setPassword("1234");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(driverManagerDataSource);

        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from speaker");
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.toString());
            }
        }
    }
}
