/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slangword;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class slang {
    private String key;
    private String mean;

    public slang(String key, String mean) {
        this.key = key;
        this.mean = mean;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String toString(){
        return this.key+ " "+this.mean ;
    }   
}
