package com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades;

public class Materia {
    private Integer id;
    private String nombre;
    private Integer anio;
    private Boolean electiva;
    private Boolean isAnual;
    private Integer enQueCuatrimestre;
    private Integer anioCursada;

    public Materia(Integer id, String nombre, Integer anio, Boolean electiva, Boolean isAnual, Integer enQueCuatrimestre) {
        this.id = id;
        this.nombre = nombre;
        this.anio = anio;
        this.electiva = electiva;
        this.isAnual = isAnual;
        this.enQueCuatrimestre = enQueCuatrimestre;
    }

    public Materia(String nombre) {
        this.nombre = nombre;
    }

    public Materia() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Boolean getElectiva() {
        return electiva;
    }

    public void setElectiva(Boolean electiva) {
        this.electiva = electiva;
    }

    public Boolean getIsAnual() {
        return isAnual;
    }

    public void setIsAnual(Boolean isAnual) {
        this.isAnual = isAnual;
    }

    public Integer getEnQueCuatrimestre() {
        return enQueCuatrimestre;
    }

    public void setEnQueCuatrimestre(Integer enQueCuatrimestre) {
        this.enQueCuatrimestre = enQueCuatrimestre;
    }

    public Integer getAnioCursada() {
        return anioCursada;
    }

    public void setAnioCursada(Integer anioCursada) {
        this.anioCursada = anioCursada;
    }
}
