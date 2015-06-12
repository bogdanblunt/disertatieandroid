package com.example.user.master.utils;

/**
 * Created by User on 12.06.2015.
 */
public class TimpiStComuneBean {

    String liniePlecare = "";
    String linieSosire = "";
    String statieComuna = "";
    Integer timp = 1000;

    public TimpiStComuneBean() {
    }

    public TimpiStComuneBean(String liniePlecare, String linieSosire, String statieComuna, Integer timp) {
        this.liniePlecare = liniePlecare;
        this.linieSosire = linieSosire;
        this.statieComuna = statieComuna;
        this.timp = timp;
    }

    public String getLiniePlecare() {
        return liniePlecare;
    }

    public void setLiniePlecare(String liniePlecare) {
        this.liniePlecare = liniePlecare;
    }

    public String getLinieSosire() {
        return linieSosire;
    }

    public void setLinieSosire(String linieSosire) {
        this.linieSosire = linieSosire;
    }

    public Integer getTimp() {
        return timp;
    }

    public void setTimp(Integer timp) {
        this.timp = timp;
    }

    public String getStatieComuna() {
        return statieComuna;
    }

    public void setStatieComuna(String statieComuna) {
        this.statieComuna = statieComuna;
    }
}
