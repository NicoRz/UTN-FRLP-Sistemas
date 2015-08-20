package com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades;

/**
 * Created by Nico Rz on 21/12/2014.
 */
public class Cursada {
    private Integer idCursada;
    private Integer idMateria;
    private String nombreMateria;
    private Integer anioCursada;
    private Integer anioMateria;

    public Cursada(Integer idCursada) {
        this.idCursada = idCursada;
    }

    public Integer getIdCursada() {
        return idCursada;
    }

    public void setIdCursada(Integer idCursada) {
        this.idCursada = idCursada;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Integer getAnioCursada() {
        return anioCursada;
    }

    public void setAnioCursada(Integer anioCursada) {
        this.anioCursada = anioCursada;
    }

    public Integer getAnioMateria() {
        return anioMateria;
    }

    public void setAnioMateria(Integer anioMateria) {
        this.anioMateria = anioMateria;
    }
}
