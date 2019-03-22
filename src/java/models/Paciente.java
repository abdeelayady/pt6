/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author abde
 */
public class Paciente {
    private int idRegister;
    private int edat;
    private String grupEdat;
    private int pes;
    private int talla;
    private double imc;
    private String classificacio;
    private int menarquia;
    private String menopausia;
    private String tipusMenopausia;

    @Override
    public String toString() {
        return "idRegister=" + idRegister + ", edat=" + edat + ", grupEdat=" + grupEdat + ", pes=" + pes + ", talla=" + talla + ", imc=" + imc + ", classificacio=" + classificacio + ", menarquia=" + menarquia + ", menopausia=" + menopausia + ", tipusMenopausia=" + tipusMenopausia + '}';
    }

    public Paciente() {
    }
    
    public Paciente( int edat, String grupEdat, int pes, int talla, double imc, 
                String classificacio, int menarquia, String menopausia,
                String tipusMenopausia) {
        this.edat = edat;
        this.grupEdat = grupEdat;
        this.pes = pes;
        this.talla = talla;
        this.imc = imc;
        this.classificacio = classificacio;
        this.menarquia = menarquia;
        this.menopausia = menopausia;
        this.tipusMenopausia = tipusMenopausia;
    }

    public int getIdRegister() {
        return idRegister;
    }

    public int getEdat() {
        return edat;
    }

    public String getGrupEdat() {
        return grupEdat;
    }

    public int getPes() {
        return pes;
    }

    public int getTalla() {
        return talla;
    }

    public double getImc() {
        return imc;
    }

    public String getClassificacio() {
        return classificacio;
    }

    public int getMenarquia() {
        return menarquia;
    }

    public String getMenopausia() {
        return menopausia;
    }

    public String getTipusMenopausia() {
        return tipusMenopausia;
    }

    public void setIdRegister(int idRegister) {
        this.idRegister = idRegister;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public void setGrupEdat(String grupEdat) {
        this.grupEdat = grupEdat;
    }

    public void setPes(int pes) {
        this.pes = pes;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public void setClassificacio(String classificacio) {
        this.classificacio = classificacio;
    }

    public void setMenarquia(int menarquia) {
        this.menarquia = menarquia;
    }

    public void setMenopausia(String menopausia) {
        this.menopausia = menopausia;
    }

    public void setTipusMenopausia(String tipusMenopausia) {
        this.tipusMenopausia = tipusMenopausia;
    }

   

   
    
    
  
}
