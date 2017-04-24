package online.themixhub.demo.sql.impl;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Steve on 4/13/2017.
 */
public class Logging_SystemMapper implements RowMapper<Logging_System> {

    public Logging_System mapRow(ResultSet rs, int rowNum) throws SQLException {
		Logging_System logging_system = new Logging_System();
		logging_system.setId(rs.getInt("id"));
		logging_system.setDate(rs.getLong("date"));
		logging_system.setTitle(rs.getString("title"));
		logging_system.setDescription(rs.getString("description"));
        return logging_system;
    }

}
