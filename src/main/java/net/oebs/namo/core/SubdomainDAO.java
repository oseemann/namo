package net.oebs.namo.core;

import net.oebs.namo.errors.SubdomainInvalidException;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.exception.ConstraintViolationException;
import net.oebs.namo.errors.SubdomainNotAvailableException;
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
            throw new SubdomainInvalidException(subdomain);
        }

        try {
            sub = persist(sub);
        } catch (ConstraintViolationException e) {
            throw new SubdomainNotAvailableException(subdomain);
        }

        return sub.getSubdomainId();
    }
}
