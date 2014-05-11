package net.oebs.namo.core;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

public class DomainDAO extends AbstractDAO<Domain> {

    public DomainDAO(SessionFactory factory) {
        super(factory);
    }

    public List getAll() {
        List result = this.criteria().addOrder(Order.asc("name")).list();
        return result;
    }
}
