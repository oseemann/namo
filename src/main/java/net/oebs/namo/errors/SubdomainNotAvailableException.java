package net.oebs.namo.errors;

import javax.ws.rs.core.Response.Status;

public class SubdomainNotAvailableException extends NamoException {

    private static final Status status = Status.BAD_REQUEST;
    private static final String error = "SubdomainNotAvailable";
    private static final String details = "The given subdomain value cannot be "
            + "claimed, most likely because it has already been claimed by "
            + "somebody else.";

    public SubdomainNotAvailableException(String value) {
        super(status, error, value, details);
    }
}
