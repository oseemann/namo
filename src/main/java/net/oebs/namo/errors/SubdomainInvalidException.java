package net.oebs.namo.errors;

import javax.ws.rs.core.Response.Status;

public class SubdomainInvalidException extends NamoException {

    private static final Status status = Status.BAD_REQUEST;
    private static final String error = "SubdomainInvalid";
    private static final String details = "The given subdomain value cannot be "
            + "parsed into a subdomain, domain and tld.";

    public SubdomainInvalidException(String value) {
        super(status, error, value, details);
    }
}
