package online.themixhub.demo.sql.impl;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobMapper implements RowMapper<Job> {

    public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
		Job job = new Job();
        job.setId(rs.getInt("id"));
        job.setOwner_id(rs.getInt("owner_id"));
        job.setEngineer_id(rs.getInt("engineer_id"));
        job.setTitle(rs.getString("title"));
        job.setDate(rs.getLong("date"));
		job.setStage(rs.getInt("stage"));
		job.setAnonymous_engineer(rs.getInt("anonymous_engineer"));
		job.setLast_activity_date(rs.getLong("last_activity_date"));
        return job;
    }
}