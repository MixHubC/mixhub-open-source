package online.themixhub.demo.sql.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Created by Steve on 4/13/2017.
 */
public class InvoiceMapper implements RowMapper<Invoice> {

    public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(rs.getInt("id"));
		invoice.setOwner_id(rs.getInt("owner_id"));
		invoice.setOwner_ip(rs.getString("owner_ip"));
        invoice.setProduct_id(rs.getInt("product_id"));
        invoice.setDate(rs.getLong("date"));
		invoice.setAmount(rs.getBigDecimal("amount"));
		invoice.setAmount_tax(rs.getBigDecimal("amount_tax"));
		invoice.setAmount_total(rs.getBigDecimal("amount_total"));
        invoice.setStage(rs.getInt("stage"));
		invoice.setPayment_date(rs.getLong("payment_date"));
		invoice.setPayment_form(rs.getInt("payment_form"));
		invoice.setJob_id(rs.getInt("job_id"));
        return invoice;
    }

}
