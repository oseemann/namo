package net.oebs.namo.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * All API Errors are returned as JSON responses of this type.
 */
public class RequestError {

    private final String error;
    private final String details;
    private final String value;

    public RequestError(String error, String value, String details) {
        this.error = error;
        this.value = value;
        this.details = details;
    }

    @JsonProperty
    public String getError() {
        return error;
    }

    @JsonProperty
    public String getValue() {
        return value;
    }

    @JsonProperty
    public String getDetails() {
        return details;
    }
}
