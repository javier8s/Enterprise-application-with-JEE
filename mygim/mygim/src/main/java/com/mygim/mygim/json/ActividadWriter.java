/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.json;

import com.mygim.mygim.entities.Actividad;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author JavierSanchezGozalo
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ActividadWriter implements MessageBodyWriter<Actividad> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Actividad.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Actividad t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Actividad act, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) throws IOException, WebApplicationException {
        JsonGenerator gen = Json.createGenerator(entityStream);
        gen.writeStartObject()
                .write("idactividad", act.getIdactividad())
                .write("nombre", act.getNombre())
                .write("vacantes", act.getVacantes())
                .write("descripcion", act.getDescripcion())
                .write("nombresala", act.getNombresala())
                .write("dia", act.getDia())
                .write("horacomienzo", act.getHoracomienzo())
                .write("horafin", act.getHorafin())
                .write("precio", act.getPrecio())
                .write("email", act.getEmail())
                .writeEnd();

        gen.flush();
    }

}
