/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygim.mygim.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.batch.api.chunk.AbstractItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author JavierSanchezGozalo
 */
@Named
@Dependent
public class SalesReader extends AbstractItemReader {

    private BufferedReader reader;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        reader = new BufferedReader(
                new InputStreamReader(Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream("META-INF/sales.csv")));
    }

    @Override
    public Object readItem() throws Exception {
        String string = null;
        try {
            string = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return string;
    }
}
