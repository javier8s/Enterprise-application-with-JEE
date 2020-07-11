/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.batch;

import com.mygim.mygim.entities.Ventas;
import java.util.StringTokenizer;
import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named
@Dependent
public class SalesProcessor implements ItemProcessor {

    @Override
    public Ventas processItem(Object t) {
        Ventas sales = new Ventas();
        StringTokenizer tokens = new StringTokenizer((String) t, ",");
        sales.setIdcuentas(Integer.parseInt(tokens.nextToken()));
        sales.setNombreactividad(tokens.nextToken());
        sales.setVenta(Integer.parseInt(tokens.nextToken()));
        return sales;
    }
}
