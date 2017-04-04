package online.themixhub.demo.sql.impl;



import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AccountMapper implements RowMapper<Account> {

    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setPermission(rs.getInt("permission"));
        account.setDate(rs.getLong("date"));
        account.setEmail(rs.getString("email"));
        account.setUsername(rs.getString("username"));
        account.setPassword(rs.getString("password"));
        account.setFirstname(rs.getString("firstname"));
        account.setLastname(rs.getString("lastname"));
        account.setAddress_1(rs.getString("address_1"));
        account.setAddress_2(rs.getString("address_2"));
        account.setCity(rs.getString("city"));
        account.setState(rs.getString("state"));
        account.setCountry(rs.getString("country"));
        account.setZip(rs.getString("zip"));
        account.setPhone(rs.getString("phone"));
        return account;
    }
}