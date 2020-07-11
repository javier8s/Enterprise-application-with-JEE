/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.batch;

import com.mygim.mygim.entities.Ventas;
import java.util.List;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named
@RequestScoped
public class SalesBean {

    @PersistenceContext
    EntityManager em;

    public List<Ventas> getSalesData() {
        return em.createNamedQuery("Ventas.findAll", Ventas.class)
                .getResultList();
    }

    public void runJob() throws JobSecurityException {
        try {
            JobOperator jo = BatchRuntime.getJobOperator();
            long jobId = jo.start("eod-sales", new Properties());
        } catch (JobStartException ex) {
            ex.printStackTrace();
        }
    }
}
