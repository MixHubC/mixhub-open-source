package online.themixhub.demo.sql.impl;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Steve on 4/13/2017.
 */
public class Logging_UserMapper implements RowMapper<Logging_User> {

    public Logging_User mapRow(ResultSet rs, int rowNum) throws SQLException {
		Logging_User logging_user = new Logging_User();
		logging_user.setId(rs.getInt("id"));
		logging_user.setDate(rs.getLong("date"));
		logging_user.setTitle(rs.getString("title"));
		logging_user.setDescription(rs.getString("description"));
		logging_user.setOwner_id(rs.getInt("owner_id"));
		logging_user.setOwner_ip(rs.getString("owner_ip"));
		logging_user.setOwner_useragent(rs.getString("owner_useragent"));
		logging_user.setOwner_referrer(rs.getString("owner_referrer"));
        return logging_user;
    }

}
