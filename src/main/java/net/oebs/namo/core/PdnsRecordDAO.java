package net.oebs.namo.core;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdnsRecordDAO extends AbstractDAO<PdnsRecord> {

    static final Logger log = LoggerFactory.getLogger(PdnsRecordDAO.class);

    public PdnsRecordDAO(SessionFactory factory) {
        super(factory);
    }

    public void create(Subdomain sub) {
        Session session = this.currentSession();
        Transaction trx = session.beginTransaction();
        PdnsRecord record = new PdnsRecord(sub.getName(), "A", "1.2.3.4", 1);
        record = persist(record);
        trx.commit();
        log.info("new PdnsRecord: {}", record.getId());
    }
}
