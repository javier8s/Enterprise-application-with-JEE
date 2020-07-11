/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author JavierSanchezGozalo
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.mygim.mygim.json.ActividadWriter.class);
        resources.add(com.mygim.mygim.json.ApuntarWriter.class);
        resources.add(com.mygim.mygim.json.SalaWriter.class);
        resources.add(com.mygim.mygim.json.VentasWriter.class);
        resources.add(com.mygim.mygim.rest.ActividadFacadeREST.class);
        resources.add(com.mygim.mygim.rest.ApuntarFacadeREST.class);
        resources.add(com.mygim.mygim.rest.GrupousuarioFacadeREST.class);
        resources.add(com.mygim.mygim.rest.SalaFacadeREST.class);
        resources.add(com.mygim.mygim.rest.UsuarioFacadeREST.class);
        resources.add(com.mygim.mygim.rest.VentasFacadeREST.class);
    }
    
}
