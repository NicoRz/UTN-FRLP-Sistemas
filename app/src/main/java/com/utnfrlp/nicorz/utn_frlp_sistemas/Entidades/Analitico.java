package com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades;

/**
 * Created by Nico Rz on 21/12/2014.
 */
public class Analitico {
    private Integer cantidadFinales;
    private Float promedioConAplazos;
    private Float promedioSinAplazos;

    public Analitico(Integer cantidadFinales, Float promedioConAplazos, Float promedioSinAplazos) {
        this.cantidadFinales = cantidadFinales;
        this.promedioConAplazos = promedioConAplazos;
        this.promedioSinAplazos = promedioSinAplazos;
    }

    public Analitico() {

    }

    public Integer getCantidadFinales() {
        return cantidadFinales;
    }

    public void setCantidadFinales(Integer cantidadFinales) {
        this.cantidadFinales = cantidadFinales;
    }

    public Float getPromedioConAplazos() {
        return promedioConAplazos;
    }

    public void setPromedioConAplazos(Float promedioConAplazos) {
        this.promedioConAplazos = promedioConAplazos;
    }

    public Float getPromedioSinAplazos() {
        return promedioSinAplazos;
    }

    public void setPromedioSinAplazos(Float promedioSinAplazos) {
        this.promedioSinAplazos = promedioSinAplazos;
    }
}
