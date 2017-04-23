package online.themixhub.demo.sql.impl;


import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Steve on 4/13/2017.
 */
public class InvoiceSQL {

    private static InvoiceSQL thisSQL;
    private DataSource ds;
    private JdbcTemplate sql;

    private InvoiceSQL() { }

    public static InvoiceSQL newInstance(DataSource ds) {
        if(thisSQL == null) {
            thisSQL = new InvoiceSQL();
            thisSQL.ds = ds;
            thisSQL.sql = new JdbcTemplate(ds);
        }
        return thisSQL;
    }

	public synchronized List<Invoice> queryAllInvoicesFromOwnerID(int ownerID) {
		String query = "SELECT * FROM invoices WHERE owner_id = ?";
		List<Invoice> invoices = sql.query(query,
				new Object[]{ownerID},
				new InvoiceMapper()
		);

		if(!invoices.isEmpty()) {
			return invoices;
		}

		return null;
	}

	public synchronized List<Invoice> queryAllInvoicesFromOwnerID(int ownerID, int limit) {
		String query = "SELECT * FROM invoices WHERE owner_id = ? LIMIT ?";
		List<Invoice> invoices = sql.query(query,
				new Object[]{ownerID, limit},
				new InvoiceMapper()
		);

		if(!invoices.isEmpty()) {
			return invoices;
		}

		return null;
	}

	public synchronized List<Invoice> queryAllPaidButNoJobAssignedInvoicesFromOwnerID(int ownerID) {
		String query = "SELECT * FROM invoices WHERE owner_id = ? AND stage = ?";
		List<Invoice> invoices = sql.query(query,
				new Object[]{ownerID, 1},
				new InvoiceMapper()
		);

		if(!invoices.isEmpty()) {
			return invoices;
		}

		return null;
	}

	public synchronized Invoice queryInvoiceFromID(int invoiceID) {
		String query = "SELECT * FROM invoices WHERE id = ?";
		List<Invoice> invoices = sql.query(query,
				new Object[]{invoiceID},
				new InvoiceMapper()
		);

		if(!invoices.isEmpty()) {
			return invoices.get(0);
		}

		return null;
	}

	public synchronized List<Invoice> queryAllInvoicesFromID(int invoiceID) {
		String query = "SELECT * FROM invoices WHERE id = ?";
		List<Invoice> invoices = sql.query(query,
				new Object[]{invoiceID},
				new InvoiceMapper()
		);

		if(!invoices.isEmpty()) {
			return invoices;
		}

		return null;
	}

	/**
	 * Returns a simple count of all the invoices, there has to be a way to do this via SQL without mapping all the objects out.
	 */
	public synchronized int queryTotalCount() {
		String query = "SELECT * FROM invoices";
		List<Invoice> invoices = sql.query(query,
				new Object[]{},
				new InvoiceMapper()
		);

		return invoices.size();
	}

    public synchronized int insert(Invoice invoice) {
		int nextID = (queryTotalCount() + 1);
        long now = System.currentTimeMillis();

		System.out.println(invoice.getAmount().toString());
		System.out.println(invoice.getAmount_tax().toString());
		System.out.println(invoice.getAmount_total().toString());

        String query = "INSERT INTO invoices (" +
				"id, " +
				"owner_id, " +
				"owner_ip, " +
                "product_id, " +
                "date, " +
				"amount, " +
				"amount_tax, " +
				"amount_total, " +
                "stage " +
                ")" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        sql.update(query,
                new Object[]{
						nextID,
						invoice.getOwner_id(),
						invoice.getOwner_ip(),
                        invoice.getProduct_id(),
                        now,
						invoice.getAmount().toString(),
						invoice.getAmount_tax().toString(),
						invoice.getAmount_total().toString(),
                        invoice.getStage()
                }
        );

        return nextID;
    }

	public synchronized void setInvoiceStage(int invoice_id, int stage) {
		String query = "UPDATE invoices SET stage = ? WHERE id = ?";
		sql.update(query,
				new Object[]{stage, invoice_id}
		);
	}

	public synchronized void setInvoiceStageAndJobID(int invoice_id, int stage, int job_id) {
		String query = "UPDATE invoices SET stage = ?, job_id = ? WHERE id = ?";
		sql.update(query,
				new Object[]{stage, job_id, invoice_id}
		);
	}

}
