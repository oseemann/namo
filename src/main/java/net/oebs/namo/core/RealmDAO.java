package net.oebs.namo.core;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class RealmDAO extends AbstractDAO<Realm> {

    public RealmDAO(SessionFactory factory) {
        super(factory);
    }

    public Realm create() {
        Realm r = (Realm) namedQuery("X1").uniqueResult();
        return r;
    }

    public Boolean authenticate(String id, String secret) {
        Query q = namedQuery("X2").setString("id", id).setString("secret", secret);
        Realm r = (Realm) q.uniqueResult();
        return r.getValidAuth();
    }
}
