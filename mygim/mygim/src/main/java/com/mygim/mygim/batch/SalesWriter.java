/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.batch;

import com.mygim.mygim.entities.Ventas;
import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named 
@Dependent
public class SalesWriter extends AbstractItemWriter {

    @PersistenceContext
    EntityManager em;

 

    @Override
    @Transactional
    public void writeItems(List list) {
        for (Ventas s : (List<Ventas>) list) {
            em.persist(s);
        }
    }


}
