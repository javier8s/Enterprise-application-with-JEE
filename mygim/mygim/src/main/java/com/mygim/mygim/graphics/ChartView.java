/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.graphics;

/**
 *
 * @author JavierSanchezGozalo
 */
import com.mygim.mygim.entities.Actividad;
import com.mygim.mygim.entities.Apuntar;
import com.mygim.mygim.entities.Sala;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class ChartView implements Serializable {

    @PersistenceContext
    private EntityManager em;

    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public void setAnimatedModel1(LineChartModel animatedModel1) {
        this.animatedModel1 = animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    public void setAnimatedModel2(BarChartModel animatedModel2) {
        this.animatedModel2 = animatedModel2;
    }

    public String getEmail() {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getUserPrincipal().getName();
    }

    public List<Sala> getAllSalas() {
        return em.createNamedQuery("Sala.findAll",
                Sala.class).getResultList();
    }

    public List<Apuntar> getAllApuntados() {
        return em.createNamedQuery("Apuntar.findAll",
                Apuntar.class).getResultList();
    }

    public List<Actividad> getAllActividades() {
        return em.createNamedQuery("Actividad.findAll",
                Actividad.class).getResultList();
    }

    public List<Actividad> getActividadesByEmail() {
        return em.createNamedQuery("Actividad.findByEmail",
                Actividad.class).setParameter("email", getEmail()).getResultList();
    }
    Integer maxBar = 0;
    Integer maxLin = 1;

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries parcial = new ChartSeries();
        parcial.setLabel("Parcial");
        ChartSeries total = new ChartSeries();
        total.setLabel("Máximo");

        List<Sala> salas = getAllSalas();
        for (Actividad a : getActividadesByEmail()) {
            for (Sala s : salas) {
                if (s.getNombresala().equals(a.getNombresala())) {
                    parcial.set(a.getNombre(), (s.getPuestos() - a.getVacantes()) * a.getPrecio());
                    total.set(a.getNombre(), s.getPuestos() * a.getPrecio());
                    
                    if (maxBar < s.getPuestos() * a.getPrecio()) {
                        maxBar = (s.getPuestos() * a.getPrecio()) + 1;
                    }
                    if (maxBar < (s.getPuestos() - a.getVacantes()) * a.getPrecio()) {
                        maxBar = (s.getPuestos() - a.getVacantes()) * a.getPrecio() + 1;
                    }
                }
            }
        }

        model.addSeries(parcial);
        model.addSeries(total);

        return model;
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        Hashtable<String, ArrayList<Integer>> ents = new Hashtable<String, ArrayList<Integer>>();
        for (Actividad a : getAllActividades()) {
            if (!ents.containsKey(a.getEmail())) {
                ents.put(a.getEmail(), new ArrayList<Integer>());
            }
            if (ents.containsKey(a.getEmail())) {
                ents.get(a.getEmail()).add(a.getIdactividad());
            }
        }

        List<Apuntar> aps = getAllApuntados();
        Hashtable<String, ArrayList<Integer>> clients = new Hashtable<String, ArrayList<Integer>>();
        for (Apuntar p : aps) {
            if (!clients.containsKey(p.getEmail())) {
                clients.put(p.getEmail(), new ArrayList<Integer>());
            }
            if (clients.containsKey(p.getEmail())) {
                clients.get(p.getEmail()).add(p.getIdactividad());
            }
        }
        for (String k : ents.keySet()) {
            LineChartSeries series1 = new LineChartSeries();
            if (k.equals(getEmail())) {
                series1.setLabel("Tu");
            } else {
                series1.setLabel("Entrenador X");
            }
            int j = 0;
            for (String e : clients.keySet()) {
                int temp = 0;
                j += 1;
                for (Integer idap : clients.get(e)) {
                    for (Integer idact : ents.get(k)) {
                        if (Objects.equals(idap, idact)) {
                            temp += 1;
                        }

                    }
                }
                if (maxLin < temp) {
                    maxLin = temp + 1;
                }
                series1.set(j, temp);
            }

            model.addSeries(series1);
        }

        return model;
    }

    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Clientes apuntados");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(maxLin);

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Tu rendimiento");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(maxBar);
    }

}
