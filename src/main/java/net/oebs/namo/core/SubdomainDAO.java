package net.oebs.namo.core;

import io.dropwizard.hibernate.AbstractDAO;
import javax.validation.ConstraintViolationException;
import org.hibernate.SessionFactory;

public class SubdomainDAO extends AbstractDAO<Subdomain> {

    public SubdomainDAO(SessionFactory factory) {
        super(factory);
    }

    public Integer claim(Realm realm, String subdomain) {
        Subdomain sub = new Subdomain();
        sub.setRealmId(realm.getRealmId());

        try {
            String[] x = subdomain.split("\\.", 2);
            sub.setDomain(x[1]);
            sub.setName(x[0]);
        } catch (Exception e) {
        }

        try {
            sub = persist(sub);
        } catch (ConstraintViolationException e) {
        }

        return sub.getSubdomainId();
    }
}
