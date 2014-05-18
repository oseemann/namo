/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.oebs.namo.errors;

import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import net.oebs.namo.core.RequestError;

/**
 *
 * @author oliver
 */
public class NamoException extends WebApplicationException {

    public NamoException(Status status, String error, String value, String details) {
        super(new ResponseBuilderImpl().status(status)
                .type(MediaType.APPLICATION_JSON)
                .entity(new RequestError(error, value, details))
                .build()
        );
    }
}
