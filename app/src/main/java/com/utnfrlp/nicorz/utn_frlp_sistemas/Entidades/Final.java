package com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Final {
    private Integer idFinal;
    private Integer idMateria;
    private String nombreMateria;
    private Integer anioMateria;
    private Integer nota;
    private String fecha;
    private Date fechaFinal;

    public Final(Integer idFinal) {
        this.idFinal = idFinal;
    }

    public Integer getIdFinal() {
        return idFinal;
    }

    public void setIdFinal(Integer idFinal) {
        this.idFinal = idFinal;
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

    public Integer getAnioMateria() {
        return anioMateria;
    }

    public void setAnioMateria(Integer anioMateria) {
        this.anioMateria = anioMateria;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
        try {
            setFechaFinal((new SimpleDateFormat("dd/MM/yyyy")).parse(fecha));
            return;
        }
        catch (ParseException parseexception) {
            parseexception.printStackTrace();
        }
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
