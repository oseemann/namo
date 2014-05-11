package net.oebs.namo;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.auth.Authenticator;
import net.oebs.namo.core.Realm;
import net.oebs.namo.core.RealmDAO;

public class NamoAuthenticator implements Authenticator<BasicCredentials, Realm> {

    RealmDAO dao;

    public NamoAuthenticator(RealmDAO dao) {
        this.dao = dao;
    }

    @Override
    public Optional<Realm> authenticate(BasicCredentials credentials) throws AuthenticationException {

        String realmId = credentials.getUsername();
        String realmSecret = credentials.getPassword();

        if (dao.authenticate(realmId, realmSecret)) {
            return Optional.of(new Realm(realmId));
        }
        return Optional.absent();
    }
}
