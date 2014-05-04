package net.oebs.namo.resources;

import net.oebs.namo.core.Realm;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import com.jolbox.bonecp.BoneCP;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/realm")
@Produces(MediaType.APPLICATION_JSON)
public class RealmResource {

    private BoneCP connectionPooler;

    public RealmResource(BoneCP connectionPooler) {
        this.connectionPooler = connectionPooler;
    }

    @GET
    @Timed
    public Realm getRealm(@QueryParam("realm_id") Optional<String> realmId) {
        return new Realm("foobar97");
    }

    @POST
    @Timed
    public Realm createRealm() {
        Connection conn;
        String realmId = null;
        final String insert
                = "INSERT INTO realm VALUES (default) RETURNING id";

        try {
            conn = this.connectionPooler.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(insert);
            rs.next();
            realmId = rs.getString("id");
            conn.close();
        } catch (SQLException ex) {
        }

        return new Realm(realmId);
    }
}
