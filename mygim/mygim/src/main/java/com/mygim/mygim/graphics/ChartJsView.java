/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.graphics;



import com.mygim.mygim.entities.Actividad;
import com.mygim.mygim.entities.Apuntar;
import com.mygim.mygim.entities.Sala;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named
@RequestScoped
public class ChartJsView implements Serializable {

    
    private DonutChartModel donutModel;

    @PostConstruct
    public void init() {
        createDonutModel();
    }

    public String getEmail() {
        FacesContext context = FacesContext.getCurrentInstance();
        return ((HttpServletRequest) context.getExternalContext().getRequest()).getUserPrincipal().getName();
    }

    @PersistenceContext
    private EntityManager em;

    public List<Sala> getAllSalas() {
        return em.createNamedQuery("Sala.findAll",
                Sala.class).getResultList();
    }

    public List<Actividad> getActividadesByEmail() {
        return em.createNamedQuery("Actividad.findByEmail",
                Actividad.class).setParameter("email", getEmail()).getResultList();
    }

    public List<Actividad> getAllActividades() {
        return em.createNamedQuery("Actividad.findAll",
                Actividad.class).getResultList();
    }

    public List<Apuntar> getAllApuntadosByIdactividad(Integer id) {
        return em.createNamedQuery("Apuntar.findByIdactividad",
                Apuntar.class).setParameter("idactividad", id).getResultList();
    }

    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();

        List<String> nombresAct = new ArrayList<>();
        List<Integer> clientesAct = new ArrayList<>();
        for (Actividad a : getAllActividades()) {
            nombresAct.add(a.getNombre());
            clientesAct.add(getAllApuntadosByIdactividad(a.getIdactividad()).size());
        }

        List<String> bgColors = new ArrayList<>();

        int colors[][] = {{0, 0, 0},
         {255, 255, 255},
         {255, 0, 0},
         {0, 255, 0},
         {0, 0, 255},
         {255, 255, 0},
         {255,140,0},
         {255, 0, 255},
         {64,224,208},
         {255,165,0},
         {128, 0, 0},
         {128, 128, 0},
         {0, 128, 0},
         {128, 0, 128},
         {0, 128, 128},
         {0, 0, 128}};
        int k = 2;
        
        try {
        for (int i : clientesAct) {
            values.add(i);

            bgColors.add("rgb(" + Integer.toString(colors[k][0]) + ", " + Integer.toString(colors[k][1] )
                    + "," + Integer.toString(colors[k][2]) + ")");
            k +=1;
            if(k>= colors.length)
                k = 0;
        }

        } catch (Exception e){
            System.out.println( e);}
        dataSet.setData(values);

        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();

        for (String n : nombresAct) {
            labels.add(n);
        }

        data.setLabels(labels);

        donutModel.setData(data);
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }

    public void setDonutModel(DonutChartModel donutModel) {
        this.donutModel = donutModel;
    }

}
