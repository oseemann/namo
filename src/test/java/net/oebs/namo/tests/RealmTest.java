package net.oebs.namo.tests;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.junit.Assert.*;
import io.dropwizard.jackson.Jackson;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.oebs.namo.core.Realm;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

public class RealmTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Realm realm = new Realm("FOOBAR1", "SECRET42");

        assertThat(
                MAPPER.writeValueAsString(realm),
                is(equalTo(MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/realm.json"), Realm.class))))
        );

    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Realm realm = new Realm("FOOBAR1", "SECRET42");
        final Realm x = MAPPER.readValue(fixture("fixtures/realm.json"), Realm.class);
        assertThat(x, equalTo(realm));
    }
}
