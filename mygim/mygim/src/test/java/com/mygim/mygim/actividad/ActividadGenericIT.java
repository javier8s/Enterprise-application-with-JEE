/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.actividad;

import com.mygim.mygim.entities.Actividad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import org.mockito.stubbing.Answer;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author JavierSanchezGozalo
 */
public class ActividadGenericIT {
    
     @Mock
    private Actividad fooMock;
     
     

    ActividadGeneric test;
    
    public ActividadGenericIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        test  = spy(new ActividadBookBean());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of init method, of class ActividadGeneric.
     */
    @Test
    public void testInit() {
    }

    /**
     * Test of findByIdActividad method, of class ActividadGeneric.
     */
    @Test
    public void testFindByIdActividad() {
        // EntityManager underTest = mock(EntityManager.class);
        
        //doReturn(true).when(test).getFromDataActividades();
        //when(underTest.createNamedQuery("").setParameter("idactividad", "").getSingleResult()).thenAnswer((Answer<?>) new Actividad());
        
        //assertEquals((Actividad) anyObject(),test.getFromDataActividades());
        
    }

   

   

    
}
