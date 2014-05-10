package net.oebs.namo.resources;

import com.codahale.metrics.annotation.Timed;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.oebs.namo.core.RealmCreation;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Path("/realm")
@Produces(MediaType.APPLICATION_JSON)
public class RealmResource {

    SessionFactory sessionFactory;

    public RealmResource(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @POST
    @Timed
    public RealmCreation createRealm() {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        /* The create_realm() function is a stored procedure that generates,
         * stores and returns the random values for realmId and realmSecret.
         * RealmSecret is not stored in the db, only a salted digest of it. */
        SQLQuery q = session.createSQLQuery("SELECT * FROM namo.create_realm()");
        q.addEntity(RealmCreation.class);
        List result = q.list();
        RealmCreation r = (RealmCreation) result.get(0);

        tx.commit();
        session.close();
        return r;
    }
}
