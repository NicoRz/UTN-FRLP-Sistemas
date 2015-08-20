package com.utnfrlp.nicorz.utn_frlp_sistemas.acciones;

import android.database.Cursor;
import java.util.ArrayList;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Entidades.Materia;
import com.utnfrlp.nicorz.utn_frlp_sistemas.Logging.L;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.AprobadaParaCursarDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.AprobadaParaRendirDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.CursadaParaCursarDB;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.DataBase;
import com.utnfrlp.nicorz.utn_frlp_sistemas.db.MateriasDB;

public class ActionMateriasDB {
    public static int ALGEBRA = 1;
    public static int ALGORITMOS = 2;
    public static int AM_1 = 3;
    public static int ARQUITECTURA = 4;
    public static int FISICA_1 = 5;
    public static int INGLES_1 = 6;
    public static int DISCRETA = 7;
    public static int SISTEMAS_Y_ORGANIZACIONES = 8;
    //2 AÑO
    public static int ANALISIS_SISTEMAS = 9;
    public static int AM_2 = 10;
    public static int FISICA_2 = 11;
    public static int QUIMICA = 12;
    public static int SISTEMAS_REPRESENTACION = 13;
    public static int SO = 14;
    public static int SINTAXIS = 15;
    public static int PARADIGMAS = 16;
    //3 AÑO
    public static int DISEÑO_SISTEMAS = 17;
    public static int EMPRENDEDORISMO = 18;
    public static int INGLES_2 = 19;
    public static int PROBABILIDADES = 20;
    public static int COMUNICACION = 21;
    public static int MATEMATICA_SUPERIOR = 22;
    public static int TECNOLOGIA_WEB = 23;
    public static int COMUNICACION_PROFESIONAL = 24;
    public static int ECONOMIA = 25;
    public static int GESTION_DATOS = 26;
    public static int ING_SOCIEDAD = 27;
    public static int SEGURIDAD_SISTEMAS = 28;
    public static int SISTEMAS_TRANSMISION = 29;
    //4 AÑO
    public static int ADMINISTRACION_RECURSOS = 30;
    public static int ING_SOFTWARE = 31;
    public static int AUDITORIA_SISTEMAS = 32;
    public static int INVESTIAGACION_OPERATIVA = 33;
    public static int SIMULACION = 34;
    public static int TEC_EXPLOTACION = 35;
    public static int ACTUALIZACION_TECNOLOGICA = 36;
    public static int BIOINGENIERIA = 37;
    public static int LEGISLACION = 38;
    public static int METODOLOGIA = 39;
    public static int REDES_INFO = 40;
    public static int TEORIA_CONTROL = 41;
    //5 AÑO
    public static int APP_REAL = 42;
    public static int ING_CALIDAD = 43;
    public static int INTERNETWORKING = 44;
    public static int PRACTICA_SUPERVISADA = 45;
    public static int PROYECTO = 46;
    public static int SISTEMA_GESTION = 47;
    public static int TECNOLOGIA_INFO = 48;
    public static int SISTEMA_AVANZADOS_DB = 49;
    public static int SO_AVANZADOS = 50;
    public static int ADMIN_GERENCIAL = 51;
    public static int IA = 52;
    public static int IA_AVANZADA = 53;
    public static int INTERACCION_HS = 54;
    public static int MODELOS_CALIDAD = 55;
    public static int REINGENIERIA = 56;




    public static void CargarMaterias(DataBase db) {
        Integer cantidadMaterias=0;
        Cursor cursor = MateriasDB.getCantidadMaterias(db.getConection());
        if (cursor.moveToFirst()) {
            do {
                int columnaCantidadMaterias = cursor.getColumnIndex("cantidadMaterias");
                cantidadMaterias = cursor.getInt(columnaCantidadMaterias);
                LogD("CantidadMaterias=",cantidadMaterias.toString());
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        Integer esElectiva = 1;
        Integer esObligatoria = 0;
        Integer anio1 = 1;
        Integer anio2 = 2;
        Integer anio3 = 3;
        Integer anio4 = 4;
        Integer anio5 = 5;
        Integer esAnual = 0;
        Integer esCuatrimestral = 1;
        Integer sinCuatrimestre = 0;
        Integer primerCuatrimestre = 1;
        Integer segundoCuatrimestre = 2;

        if (cantidadMaterias==0) {
            MateriasDB.Insert(db.getConection(),"Algebra y Geometria Analitica",anio1,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Algoritmos y Estructuras de Datos",anio1,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Analisis Matematico I",anio1,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Arquitectura de Computadoras",anio1,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Fisica I",anio1,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Ingles I",anio1,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Matematica Discreta",anio1,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Sistemas y Organizaciones",anio1,esObligatoria,esAnual,sinCuatrimestre);
            //Segundo Anio
            MateriasDB.Insert(db.getConection(),"Analisis de Sistemas",anio2,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Analisis Matematico II",anio2,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Fisica II",anio2,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Quimica",anio2,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Sistemas de Representacion",anio2,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Sistemas Operativos",anio2,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Sintaxis y Semantica del Lenguaje",anio2,esObligatoria,esCuatrimestral,primerCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Paradigmas de Programacion",anio2,esObligatoria,esCuatrimestral,segundoCuatrimestre);
            //Tercer Anio
            MateriasDB.Insert(db.getConection(),"Diseño de Sistemas",anio3,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Emprendedorismo",anio3,esElectiva,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Ingles II",anio3,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Probabilidades y Estadisticas",anio3,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Comunicaciones",anio3,esObligatoria,esCuatrimestral,primerCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Matematica Superior (S)",anio3,esObligatoria,esCuatrimestral,primerCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Tecnologia y Gestion Web",anio3,esElectiva,esCuatrimestral,primerCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Comunicacion Profesional",anio3,esElectiva,esCuatrimestral,primerCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Economia",anio3,esObligatoria,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Gestion de Datos",anio3,esObligatoria,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Ingenieria y Sociedad",anio3,esObligatoria,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Seguridad en Sistemas de Informacion",anio3,esElectiva,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Sistemas de Transmision y Redes Inalambricas",anio3,esElectiva,esCuatrimestral,segundoCuatrimestre);
            //Cuarto Anio
            MateriasDB.Insert(db.getConection(),"Administracion de Recursos",anio4,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Ingenieria de Software",anio4,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Auditoria de Sistemas de Informacion",anio4,esElectiva,esCuatrimestral,primerCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Investigacion Operativa",anio4,esObligatoria,esCuatrimestral,primerCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Simulacion",anio4,esObligatoria,esCuatrimestral,primerCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Tecnologias para la Explotacion de la Informacion",anio4,esElectiva,esCuatrimestral,primerCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Actualizacion Tecnologica e Innovacion",anio4,esElectiva,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"BioIngenieria",anio4,esElectiva,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Legislacion",anio4,esObligatoria,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Metodologia de la Investigacion",anio4,esElectiva,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Redes de informacion",anio4,esObligatoria,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Teoria de Control",anio4,esObligatoria,esCuatrimestral,segundoCuatrimestre);
            //Quinto Anio
            MateriasDB.Insert(db.getConection(),"Aplicaciones en Tiempo Real ",anio5,esElectiva,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Ingenieria en Calidad",anio5,esElectiva,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"InternetWorking",anio5,esElectiva,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Practica Supervisada (Sistemas)",anio5,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Proyecto",anio5,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Sistemas de Gestion",anio5,esObligatoria,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Tecnologias de Informacion para la Gestion Empresarial",anio5,esElectiva,esAnual,sinCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Sistemas Avanzados de Bases de Datos",anio5,esElectiva,esCuatrimestral,primerCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Sistemas Operativos Avanzados",anio5,esObligatoria,esCuatrimestral,primerCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Administracion Gerencial",anio5,esObligatoria,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Inteligencia Artificial",anio5,esObligatoria,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Inteligencia Artificial Avanzada",anio5,esElectiva,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Interaccion Hombre-Sistema",anio5,esElectiva,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"Modelos de Calidad en la Industria del Software",anio5,esElectiva,esCuatrimestral,segundoCuatrimestre);
            MateriasDB.Insert(db.getConection(),"ReIngenieria",anio5,esElectiva,esCuatrimestral,segundoCuatrimestre);
        }

        Integer cantidadCursadaParaCursar=0;
        Cursor c = CursadaParaCursarDB.getCantidadMaterias(db.getConection());
        if (c.moveToFirst()) {
            do {
                int columnaCantidadMaterias = c.getColumnIndex("cantidadMaterias");
                cantidadCursadaParaCursar = c.getInt(columnaCantidadMaterias);
                LogD("CursadaParaCursar=",cantidadMaterias.toString());
                c.moveToNext();
            } while (!c.isAfterLast());
        }

        if (cantidadCursadaParaCursar==0) {
            CursadaParaCursarDB.Insert(db.getConection(),ALGEBRA,AM_2);
            CursadaParaCursarDB.Insert(db.getConection(),ALGEBRA,PROBABILIDADES);
            CursadaParaCursarDB.Insert(db.getConection(),ALGORITMOS,ANALISIS_SISTEMAS);
            CursadaParaCursarDB.Insert(db.getConection(),ALGORITMOS,SO);
            CursadaParaCursarDB.Insert(db.getConection(),ALGORITMOS,SINTAXIS);
            CursadaParaCursarDB.Insert(db.getConection(),ALGORITMOS,PARADIGMAS);
            CursadaParaCursarDB.Insert(db.getConection(),AM_1,PROBABILIDADES);
            CursadaParaCursarDB.Insert(db.getConection(),AM_1,FISICA_2);
            CursadaParaCursarDB.Insert(db.getConection(),AM_1,AM_2);
            CursadaParaCursarDB.Insert(db.getConection(),ARQUITECTURA,SO);
            CursadaParaCursarDB.Insert(db.getConection(),ARQUITECTURA,COMUNICACION);
            CursadaParaCursarDB.Insert(db.getConection(),FISICA_1,FISICA_2);
            CursadaParaCursarDB.Insert(db.getConection(),DISCRETA,SO);
            CursadaParaCursarDB.Insert(db.getConection(),DISCRETA,SINTAXIS);
            CursadaParaCursarDB.Insert(db.getConection(),DISCRETA,PARADIGMAS);
            CursadaParaCursarDB.Insert(db.getConection(),SISTEMAS_Y_ORGANIZACIONES,ANALISIS_SISTEMAS);
            CursadaParaCursarDB.Insert(db.getConection(),SISTEMAS_Y_ORGANIZACIONES,EMPRENDEDORISMO);
            CursadaParaCursarDB.Insert(db.getConection(),SISTEMAS_Y_ORGANIZACIONES,COMUNICACION_PROFESIONAL);
            //2 Anio
            CursadaParaCursarDB.Insert(db.getConection(),ANALISIS_SISTEMAS,DISEÑO_SISTEMAS);
            CursadaParaCursarDB.Insert(db.getConection(),ANALISIS_SISTEMAS,ECONOMIA);
            CursadaParaCursarDB.Insert(db.getConection(),ANALISIS_SISTEMAS,GESTION_DATOS);
            CursadaParaCursarDB.Insert(db.getConection(),ANALISIS_SISTEMAS,SEGURIDAD_SISTEMAS);
            CursadaParaCursarDB.Insert(db.getConection(),ANALISIS_SISTEMAS,LEGISLACION);
            CursadaParaCursarDB.Insert(db.getConection(),AM_2,COMUNICACION);
            CursadaParaCursarDB.Insert(db.getConection(),AM_2,MATEMATICA_SUPERIOR);
            CursadaParaCursarDB.Insert(db.getConection(),AM_2,SISTEMAS_TRANSMISION);
            CursadaParaCursarDB.Insert(db.getConection(),FISICA_2,COMUNICACION);
            CursadaParaCursarDB.Insert(db.getConection(),FISICA_2,SISTEMAS_TRANSMISION);
            CursadaParaCursarDB.Insert(db.getConection(),QUIMICA,TEORIA_CONTROL);
            CursadaParaCursarDB.Insert(db.getConection(),SO,TECNOLOGIA_WEB);
            CursadaParaCursarDB.Insert(db.getConection(),SO,SEGURIDAD_SISTEMAS);
            CursadaParaCursarDB.Insert(db.getConection(),SO,ADMINISTRACION_RECURSOS);
            CursadaParaCursarDB.Insert(db.getConection(),SO,REDES_INFO);
            CursadaParaCursarDB.Insert(db.getConection(),SO,ACTUALIZACION_TECNOLOGICA);
            CursadaParaCursarDB.Insert(db.getConection(),SINTAXIS,TECNOLOGIA_WEB);
            CursadaParaCursarDB.Insert(db.getConection(),SINTAXIS,GESTION_DATOS);
            CursadaParaCursarDB.Insert(db.getConection(),SINTAXIS,ACTUALIZACION_TECNOLOGICA);
            CursadaParaCursarDB.Insert(db.getConection(),PARADIGMAS,DISEÑO_SISTEMAS);
            CursadaParaCursarDB.Insert(db.getConection(),PARADIGMAS,GESTION_DATOS);
            CursadaParaCursarDB.Insert(db.getConection(),PARADIGMAS,SEGURIDAD_SISTEMAS);
            CursadaParaCursarDB.Insert(db.getConection(),PARADIGMAS,ACTUALIZACION_TECNOLOGICA);
            //3 Anio
            CursadaParaCursarDB.Insert(db.getConection(),DISEÑO_SISTEMAS,ADMINISTRACION_RECURSOS);
            CursadaParaCursarDB.Insert(db.getConection(),DISEÑO_SISTEMAS,ING_SOFTWARE);
            CursadaParaCursarDB.Insert(db.getConection(),DISEÑO_SISTEMAS,TEC_EXPLOTACION);
            CursadaParaCursarDB.Insert(db.getConection(),DISEÑO_SISTEMAS,BIOINGENIERIA);
            CursadaParaCursarDB.Insert(db.getConection(),DISEÑO_SISTEMAS,METODOLOGIA);
            CursadaParaCursarDB.Insert(db.getConection(),PROBABILIDADES,ING_SOFTWARE);
            CursadaParaCursarDB.Insert(db.getConection(),PROBABILIDADES,INVESTIAGACION_OPERATIVA);
            CursadaParaCursarDB.Insert(db.getConection(),PROBABILIDADES,SIMULACION);
            CursadaParaCursarDB.Insert(db.getConection(),COMUNICACION,SISTEMAS_TRANSMISION);
            CursadaParaCursarDB.Insert(db.getConection(),COMUNICACION,REDES_INFO);
            CursadaParaCursarDB.Insert(db.getConection(),COMUNICACION,APP_REAL);
            CursadaParaCursarDB.Insert(db.getConection(),MATEMATICA_SUPERIOR,INVESTIAGACION_OPERATIVA);
            CursadaParaCursarDB.Insert(db.getConection(),MATEMATICA_SUPERIOR,SIMULACION);
            CursadaParaCursarDB.Insert(db.getConection(),MATEMATICA_SUPERIOR,TEORIA_CONTROL);
            CursadaParaCursarDB.Insert(db.getConection(),ECONOMIA,ADMINISTRACION_RECURSOS);
            CursadaParaCursarDB.Insert(db.getConection(),GESTION_DATOS,ING_SOFTWARE);
            CursadaParaCursarDB.Insert(db.getConection(),GESTION_DATOS,SISTEMA_AVANZADOS_DB);
            CursadaParaCursarDB.Insert(db.getConection(),GESTION_DATOS,REINGENIERIA);
            CursadaParaCursarDB.Insert(db.getConection(),ING_SOCIEDAD,LEGISLACION);
            CursadaParaCursarDB.Insert(db.getConection(),SEGURIDAD_SISTEMAS,AUDITORIA_SISTEMAS);
            //4 Anio
            CursadaParaCursarDB.Insert(db.getConection(),ADMINISTRACION_RECURSOS,ING_CALIDAD);
            CursadaParaCursarDB.Insert(db.getConection(),ADMINISTRACION_RECURSOS,INTERNETWORKING);
            CursadaParaCursarDB.Insert(db.getConection(),ADMINISTRACION_RECURSOS,PRACTICA_SUPERVISADA);
            CursadaParaCursarDB.Insert(db.getConection(),ADMINISTRACION_RECURSOS,PROYECTO);
            CursadaParaCursarDB.Insert(db.getConection(),ADMINISTRACION_RECURSOS,SISTEMA_GESTION);
            CursadaParaCursarDB.Insert(db.getConection(),ADMINISTRACION_RECURSOS,ADMIN_GERENCIAL);
            CursadaParaCursarDB.Insert(db.getConection(),ADMINISTRACION_RECURSOS,IA_AVANZADA);
            CursadaParaCursarDB.Insert(db.getConection(),ING_SOFTWARE,PRACTICA_SUPERVISADA);
            CursadaParaCursarDB.Insert(db.getConection(),ING_SOFTWARE,PROYECTO);
            CursadaParaCursarDB.Insert(db.getConection(),ING_SOFTWARE,TECNOLOGIA_INFO);
            CursadaParaCursarDB.Insert(db.getConection(),ING_SOFTWARE,IA_AVANZADA);
            CursadaParaCursarDB.Insert(db.getConection(),ING_SOFTWARE,INTERACCION_HS);
            CursadaParaCursarDB.Insert(db.getConection(),ING_SOFTWARE,MODELOS_CALIDAD);
            CursadaParaCursarDB.Insert(db.getConection(),INVESTIAGACION_OPERATIVA,SISTEMA_GESTION);
            CursadaParaCursarDB.Insert(db.getConection(),INVESTIAGACION_OPERATIVA,ADMIN_GERENCIAL);
            CursadaParaCursarDB.Insert(db.getConection(),INVESTIAGACION_OPERATIVA,IA);
            CursadaParaCursarDB.Insert(db.getConection(),SIMULACION,SISTEMA_GESTION);
            CursadaParaCursarDB.Insert(db.getConection(),SIMULACION,IA);
            CursadaParaCursarDB.Insert(db.getConection(),SIMULACION,INTERACCION_HS);
            CursadaParaCursarDB.Insert(db.getConection(),LEGISLACION,PRACTICA_SUPERVISADA);
            CursadaParaCursarDB.Insert(db.getConection(),LEGISLACION,PROYECTO);
            CursadaParaCursarDB.Insert(db.getConection(),LEGISLACION,REINGENIERIA);
            CursadaParaCursarDB.Insert(db.getConection(),REDES_INFO,APP_REAL);
            CursadaParaCursarDB.Insert(db.getConection(),REDES_INFO,PRACTICA_SUPERVISADA);
            CursadaParaCursarDB.Insert(db.getConection(),REDES_INFO,PROYECTO);
            CursadaParaCursarDB.Insert(db.getConection(),REDES_INFO,SISTEMA_AVANZADOS_DB);
            CursadaParaCursarDB.Insert(db.getConection(),REDES_INFO,SO_AVANZADOS);
            CursadaParaCursarDB.Insert(db.getConection(),TEORIA_CONTROL,APP_REAL);
            //5 Anio
            CursadaParaCursarDB.Insert(db.getConection(),IA,IA_AVANZADA);
        }

        Integer cantidadFinalParaCursar=0;
        Cursor cc = AprobadaParaCursarDB.getCantidadMaterias(db.getConection());
        if (cc.moveToFirst()) {
            do {
                int columnaCantidadMaterias = cc.getColumnIndex("cantidadMaterias");
                cantidadFinalParaCursar = cc.getInt(columnaCantidadMaterias);
                LogD("FinalParaCursar=",cantidadMaterias.toString());
                cc.moveToNext();
            } while (!cc.isAfterLast());
        }

        if (cantidadFinalParaCursar==0) {
            AprobadaParaCursarDB.Insert(db.getConection(),ALGEBRA,COMUNICACION);
            AprobadaParaCursarDB.Insert(db.getConection(),ALGEBRA,MATEMATICA_SUPERIOR);
            AprobadaParaCursarDB.Insert(db.getConection(),ALGORITMOS,DISEÑO_SISTEMAS);
            AprobadaParaCursarDB.Insert(db.getConection(),ALGORITMOS,TECNOLOGIA_WEB);
            AprobadaParaCursarDB.Insert(db.getConection(),ALGORITMOS,ECONOMIA);
            AprobadaParaCursarDB.Insert(db.getConection(),ALGORITMOS,GESTION_DATOS);
            AprobadaParaCursarDB.Insert(db.getConection(),ALGORITMOS,LEGISLACION);
            AprobadaParaCursarDB.Insert(db.getConection(),ALGORITMOS,REDES_INFO);
            AprobadaParaCursarDB.Insert(db.getConection(),AM_1,COMUNICACION);
            AprobadaParaCursarDB.Insert(db.getConection(),AM_1,MATEMATICA_SUPERIOR);
            AprobadaParaCursarDB.Insert(db.getConection(),ARQUITECTURA,ADMINISTRACION_RECURSOS);
            AprobadaParaCursarDB.Insert(db.getConection(),ARQUITECTURA,REDES_INFO);
            AprobadaParaCursarDB.Insert(db.getConection(),FISICA_1,COMUNICACION);
            AprobadaParaCursarDB.Insert(db.getConection(),FISICA_1,SISTEMAS_TRANSMISION);
            AprobadaParaCursarDB.Insert(db.getConection(),INGLES_1,INGLES_2);
            AprobadaParaCursarDB.Insert(db.getConection(),INGLES_1,ADMINISTRACION_RECURSOS);
            AprobadaParaCursarDB.Insert(db.getConection(),DISCRETA,DISEÑO_SISTEMAS);
            AprobadaParaCursarDB.Insert(db.getConection(),DISCRETA,GESTION_DATOS);
            AprobadaParaCursarDB.Insert(db.getConection(),DISCRETA,REDES_INFO);
            AprobadaParaCursarDB.Insert(db.getConection(),SISTEMAS_Y_ORGANIZACIONES,DISEÑO_SISTEMAS);
            AprobadaParaCursarDB.Insert(db.getConection(),SISTEMAS_Y_ORGANIZACIONES,ECONOMIA);
            AprobadaParaCursarDB.Insert(db.getConection(),SISTEMAS_Y_ORGANIZACIONES,GESTION_DATOS);
            AprobadaParaCursarDB.Insert(db.getConection(),SISTEMAS_Y_ORGANIZACIONES,LEGISLACION);
            AprobadaParaCursarDB.Insert(db.getConection(),SISTEMAS_Y_ORGANIZACIONES,MODELOS_CALIDAD);
            //2 Anio
            AprobadaParaCursarDB.Insert(db.getConection(),ANALISIS_SISTEMAS,ADMINISTRACION_RECURSOS);
            AprobadaParaCursarDB.Insert(db.getConection(),ANALISIS_SISTEMAS,ING_SOFTWARE);
            AprobadaParaCursarDB.Insert(db.getConection(),ANALISIS_SISTEMAS,BIOINGENIERIA);
            AprobadaParaCursarDB.Insert(db.getConection(),ANALISIS_SISTEMAS,INTERACCION_HS);
            AprobadaParaCursarDB.Insert(db.getConection(),AM_2,INVESTIAGACION_OPERATIVA);
            AprobadaParaCursarDB.Insert(db.getConection(),AM_2,SIMULACION);
            AprobadaParaCursarDB.Insert(db.getConection(),AM_2,REDES_INFO);
            AprobadaParaCursarDB.Insert(db.getConection(),AM_2,TEORIA_CONTROL);
            AprobadaParaCursarDB.Insert(db.getConection(),FISICA_2,REDES_INFO);
            AprobadaParaCursarDB.Insert(db.getConection(),FISICA_2,TEORIA_CONTROL);
            AprobadaParaCursarDB.Insert(db.getConection(),SISTEMAS_REPRESENTACION,PRACTICA_SUPERVISADA);
            AprobadaParaCursarDB.Insert(db.getConection(),SISTEMAS_REPRESENTACION,PROYECTO);
            AprobadaParaCursarDB.Insert(db.getConection(),SO,PRACTICA_SUPERVISADA);
            AprobadaParaCursarDB.Insert(db.getConection(),SO,PROYECTO);
            AprobadaParaCursarDB.Insert(db.getConection(),SO,SISTEMA_GESTION);
            AprobadaParaCursarDB.Insert(db.getConection(),SO,SISTEMA_AVANZADOS_DB);
            AprobadaParaCursarDB.Insert(db.getConection(),SO,SO_AVANZADOS);
            AprobadaParaCursarDB.Insert(db.getConection(),SO,ADMIN_GERENCIAL);
            AprobadaParaCursarDB.Insert(db.getConection(),SINTAXIS,ING_SOFTWARE);
            AprobadaParaCursarDB.Insert(db.getConection(),PARADIGMAS,ADMINISTRACION_RECURSOS);
            AprobadaParaCursarDB.Insert(db.getConection(),PARADIGMAS,ING_SOFTWARE);
            AprobadaParaCursarDB.Insert(db.getConection(),PARADIGMAS,TEC_EXPLOTACION);
            //3 Anio
            AprobadaParaCursarDB.Insert(db.getConection(),DISEÑO_SISTEMAS,ING_CALIDAD);
            AprobadaParaCursarDB.Insert(db.getConection(),DISEÑO_SISTEMAS,PRACTICA_SUPERVISADA);
            AprobadaParaCursarDB.Insert(db.getConection(),DISEÑO_SISTEMAS,PROYECTO);
            AprobadaParaCursarDB.Insert(db.getConection(),DISEÑO_SISTEMAS,SISTEMA_GESTION);
            AprobadaParaCursarDB.Insert(db.getConection(),DISEÑO_SISTEMAS,TECNOLOGIA_INFO);
            AprobadaParaCursarDB.Insert(db.getConection(),DISEÑO_SISTEMAS,ADMIN_GERENCIAL);
            AprobadaParaCursarDB.Insert(db.getConection(),DISEÑO_SISTEMAS,IA);
            AprobadaParaCursarDB.Insert(db.getConection(),DISEÑO_SISTEMAS,REINGENIERIA);
            AprobadaParaCursarDB.Insert(db.getConection(),INGLES_2,PRACTICA_SUPERVISADA);
            AprobadaParaCursarDB.Insert(db.getConection(),INGLES_2,PROYECTO);
            AprobadaParaCursarDB.Insert(db.getConection(),PROBABILIDADES,PRACTICA_SUPERVISADA);
            AprobadaParaCursarDB.Insert(db.getConection(),PROBABILIDADES,PROYECTO);
            AprobadaParaCursarDB.Insert(db.getConection(),PROBABILIDADES,ADMIN_GERENCIAL);
            AprobadaParaCursarDB.Insert(db.getConection(),PROBABILIDADES,IA);
            AprobadaParaCursarDB.Insert(db.getConection(),COMUNICACION,PRACTICA_SUPERVISADA);
            AprobadaParaCursarDB.Insert(db.getConection(),COMUNICACION,PROYECTO);
            AprobadaParaCursarDB.Insert(db.getConection(),MATEMATICA_SUPERIOR,APP_REAL);
            AprobadaParaCursarDB.Insert(db.getConection(),MATEMATICA_SUPERIOR,SISTEMA_GESTION);
            AprobadaParaCursarDB.Insert(db.getConection(),MATEMATICA_SUPERIOR,ADMIN_GERENCIAL);
            AprobadaParaCursarDB.Insert(db.getConection(),MATEMATICA_SUPERIOR,IA);
            AprobadaParaCursarDB.Insert(db.getConection(),ECONOMIA,PRACTICA_SUPERVISADA);
            AprobadaParaCursarDB.Insert(db.getConection(),ECONOMIA,PROYECTO);
            AprobadaParaCursarDB.Insert(db.getConection(),ECONOMIA,SISTEMA_GESTION);
            AprobadaParaCursarDB.Insert(db.getConection(),ECONOMIA,ADMIN_GERENCIAL);
            AprobadaParaCursarDB.Insert(db.getConection(),ECONOMIA,REINGENIERIA);
            AprobadaParaCursarDB.Insert(db.getConection(),GESTION_DATOS,PRACTICA_SUPERVISADA);
            AprobadaParaCursarDB.Insert(db.getConection(),GESTION_DATOS,PROYECTO);
            AprobadaParaCursarDB.Insert(db.getConection(),ING_SOCIEDAD,PRACTICA_SUPERVISADA);
            AprobadaParaCursarDB.Insert(db.getConection(),ING_SOCIEDAD,PROYECTO);
            AprobadaParaCursarDB.Insert(db.getConection(),INVESTIAGACION_OPERATIVA,IA_AVANZADA);
            AprobadaParaCursarDB.Insert(db.getConection(),SIMULACION,IA_AVANZADA);
        }

        Integer cantidadFinalParaFinal=0;
        Cursor ccc = AprobadaParaRendirDB.getCantidadMaterias(db.getConection());
        if (ccc.moveToFirst()) {
            do {
                int columnaCantidadMaterias = ccc.getColumnIndex("cantidadMaterias");
                cantidadFinalParaFinal = ccc.getInt(columnaCantidadMaterias);
                LogD("cantidadFinalParaFinal=",cantidadMaterias.toString());
                ccc.moveToNext();
            } while (!ccc.isAfterLast());
        }

        if (cantidadFinalParaFinal==0) {
            AprobadaParaRendirDB.Insert(db.getConection(),ALGEBRA,AM_2);
            AprobadaParaRendirDB.Insert(db.getConection(),ALGEBRA,PROBABILIDADES);
            AprobadaParaRendirDB.Insert(db.getConection(),ALGEBRA,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),ALGORITMOS,ANALISIS_SISTEMAS);
            AprobadaParaRendirDB.Insert(db.getConection(),ALGORITMOS,SO);
            AprobadaParaRendirDB.Insert(db.getConection(),ALGORITMOS,SINTAXIS);
            AprobadaParaRendirDB.Insert(db.getConection(),ALGORITMOS,PARADIGMAS);
            AprobadaParaRendirDB.Insert(db.getConection(),ALGORITMOS,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),AM_1,AM_2);
            AprobadaParaRendirDB.Insert(db.getConection(),AM_1,FISICA_2);
            AprobadaParaRendirDB.Insert(db.getConection(),AM_1,PROBABILIDADES);
            AprobadaParaRendirDB.Insert(db.getConection(),AM_1,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),ARQUITECTURA,SO);
            AprobadaParaRendirDB.Insert(db.getConection(),ARQUITECTURA,COMUNICACION);
            AprobadaParaRendirDB.Insert(db.getConection(),ARQUITECTURA,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),FISICA_1,FISICA_2);
            AprobadaParaRendirDB.Insert(db.getConection(),FISICA_1,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),INGLES_1,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),DISCRETA,SO);
            AprobadaParaRendirDB.Insert(db.getConection(),DISCRETA,PARADIGMAS);
            AprobadaParaRendirDB.Insert(db.getConection(),DISCRETA,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),SISTEMAS_Y_ORGANIZACIONES,ANALISIS_SISTEMAS);
            AprobadaParaRendirDB.Insert(db.getConection(),SISTEMAS_Y_ORGANIZACIONES,EMPRENDEDORISMO);
            AprobadaParaRendirDB.Insert(db.getConection(),SISTEMAS_Y_ORGANIZACIONES,COMUNICACION_PROFESIONAL);
            AprobadaParaRendirDB.Insert(db.getConection(),SISTEMAS_Y_ORGANIZACIONES,PROYECTO);
            //2 Anio
            AprobadaParaRendirDB.Insert(db.getConection(),ANALISIS_SISTEMAS,DISEÑO_SISTEMAS);
            AprobadaParaRendirDB.Insert(db.getConection(),ANALISIS_SISTEMAS,ECONOMIA);
            AprobadaParaRendirDB.Insert(db.getConection(),ANALISIS_SISTEMAS,GESTION_DATOS);
            AprobadaParaRendirDB.Insert(db.getConection(),ANALISIS_SISTEMAS,SEGURIDAD_SISTEMAS);
            AprobadaParaRendirDB.Insert(db.getConection(),ANALISIS_SISTEMAS,LEGISLACION);
            AprobadaParaRendirDB.Insert(db.getConection(),ANALISIS_SISTEMAS,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),AM_2,COMUNICACION);
            AprobadaParaRendirDB.Insert(db.getConection(),AM_2,MATEMATICA_SUPERIOR);
            AprobadaParaRendirDB.Insert(db.getConection(),AM_2,SISTEMAS_TRANSMISION);
            AprobadaParaRendirDB.Insert(db.getConection(),AM_2,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),FISICA_2,COMUNICACION);
            AprobadaParaRendirDB.Insert(db.getConection(),FISICA_2,SISTEMAS_TRANSMISION);
            AprobadaParaRendirDB.Insert(db.getConection(),FISICA_2,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),QUIMICA,TEORIA_CONTROL);
            AprobadaParaRendirDB.Insert(db.getConection(),QUIMICA,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),SISTEMAS_REPRESENTACION,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),SO,TECNOLOGIA_WEB);
            AprobadaParaRendirDB.Insert(db.getConection(),SO,SEGURIDAD_SISTEMAS);
            AprobadaParaRendirDB.Insert(db.getConection(),SO,ADMINISTRACION_RECURSOS);
            AprobadaParaRendirDB.Insert(db.getConection(),SO,REDES_INFO);
            AprobadaParaRendirDB.Insert(db.getConection(),SO,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),SO,ACTUALIZACION_TECNOLOGICA);
            AprobadaParaRendirDB.Insert(db.getConection(),SINTAXIS,TECNOLOGIA_WEB);
            AprobadaParaRendirDB.Insert(db.getConection(),SINTAXIS,GESTION_DATOS);
            AprobadaParaRendirDB.Insert(db.getConection(),SINTAXIS,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),SINTAXIS,ACTUALIZACION_TECNOLOGICA);
            AprobadaParaRendirDB.Insert(db.getConection(),PARADIGMAS,DISEÑO_SISTEMAS);
            AprobadaParaRendirDB.Insert(db.getConection(),PARADIGMAS,GESTION_DATOS);
            AprobadaParaRendirDB.Insert(db.getConection(),PARADIGMAS,SEGURIDAD_SISTEMAS);
            AprobadaParaRendirDB.Insert(db.getConection(),PARADIGMAS,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),PARADIGMAS,ACTUALIZACION_TECNOLOGICA);
            //3 Anio
            AprobadaParaRendirDB.Insert(db.getConection(),DISEÑO_SISTEMAS,ADMINISTRACION_RECURSOS);
            AprobadaParaRendirDB.Insert(db.getConection(),DISEÑO_SISTEMAS,ING_SOFTWARE);
            AprobadaParaRendirDB.Insert(db.getConection(),DISEÑO_SISTEMAS,TEC_EXPLOTACION);
            AprobadaParaRendirDB.Insert(db.getConection(),DISEÑO_SISTEMAS,BIOINGENIERIA);
            AprobadaParaRendirDB.Insert(db.getConection(),DISEÑO_SISTEMAS,METODOLOGIA);
            AprobadaParaRendirDB.Insert(db.getConection(),DISEÑO_SISTEMAS,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),EMPRENDEDORISMO,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),INGLES_2,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),PROBABILIDADES,ING_SOFTWARE);
            AprobadaParaRendirDB.Insert(db.getConection(),PROBABILIDADES,INVESTIAGACION_OPERATIVA);
            AprobadaParaRendirDB.Insert(db.getConection(),PROBABILIDADES,SIMULACION);
            AprobadaParaRendirDB.Insert(db.getConection(),PROBABILIDADES,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),COMUNICACION,SISTEMAS_TRANSMISION);
            AprobadaParaRendirDB.Insert(db.getConection(),COMUNICACION,REDES_INFO);
            AprobadaParaRendirDB.Insert(db.getConection(),COMUNICACION,APP_REAL);
            AprobadaParaRendirDB.Insert(db.getConection(),COMUNICACION,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),MATEMATICA_SUPERIOR,INVESTIAGACION_OPERATIVA);
            AprobadaParaRendirDB.Insert(db.getConection(),MATEMATICA_SUPERIOR,SIMULACION);
            AprobadaParaRendirDB.Insert(db.getConection(),MATEMATICA_SUPERIOR,TEORIA_CONTROL);
            AprobadaParaRendirDB.Insert(db.getConection(),MATEMATICA_SUPERIOR,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),TECNOLOGIA_WEB,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),COMUNICACION_PROFESIONAL,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),ECONOMIA,ADMINISTRACION_RECURSOS);
            AprobadaParaRendirDB.Insert(db.getConection(),ECONOMIA,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),GESTION_DATOS,ING_SOFTWARE);
            AprobadaParaRendirDB.Insert(db.getConection(),GESTION_DATOS,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),GESTION_DATOS,SISTEMA_AVANZADOS_DB);
            AprobadaParaRendirDB.Insert(db.getConection(),GESTION_DATOS,REINGENIERIA);
            AprobadaParaRendirDB.Insert(db.getConection(),ING_SOCIEDAD,LEGISLACION);
            AprobadaParaRendirDB.Insert(db.getConection(),ING_SOCIEDAD,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),SEGURIDAD_SISTEMAS,AUDITORIA_SISTEMAS);
            AprobadaParaRendirDB.Insert(db.getConection(),SEGURIDAD_SISTEMAS,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),SISTEMAS_TRANSMISION,PROYECTO);
            //4 Anio
            AprobadaParaRendirDB.Insert(db.getConection(),ADMINISTRACION_RECURSOS,ING_CALIDAD);
            AprobadaParaRendirDB.Insert(db.getConection(),ADMINISTRACION_RECURSOS,INTERNETWORKING);
            AprobadaParaRendirDB.Insert(db.getConection(),ADMINISTRACION_RECURSOS,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),ADMINISTRACION_RECURSOS,SISTEMA_GESTION);
            AprobadaParaRendirDB.Insert(db.getConection(),ADMINISTRACION_RECURSOS,ADMIN_GERENCIAL);
            AprobadaParaRendirDB.Insert(db.getConection(),ADMINISTRACION_RECURSOS,IA_AVANZADA);
            AprobadaParaRendirDB.Insert(db.getConection(),ING_SOFTWARE,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),ING_SOFTWARE,TECNOLOGIA_INFO);
            AprobadaParaRendirDB.Insert(db.getConection(),ING_SOFTWARE,IA_AVANZADA);
            AprobadaParaRendirDB.Insert(db.getConection(),ING_SOFTWARE,INTERACCION_HS);
            AprobadaParaRendirDB.Insert(db.getConection(),ING_SOFTWARE,MODELOS_CALIDAD);
            AprobadaParaRendirDB.Insert(db.getConection(),AUDITORIA_SISTEMAS,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),INVESTIAGACION_OPERATIVA,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),INVESTIAGACION_OPERATIVA,SISTEMA_GESTION);
            AprobadaParaRendirDB.Insert(db.getConection(),INVESTIAGACION_OPERATIVA,ADMIN_GERENCIAL);
            AprobadaParaRendirDB.Insert(db.getConection(),INVESTIAGACION_OPERATIVA,IA);
            AprobadaParaRendirDB.Insert(db.getConection(),SIMULACION,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),SIMULACION,SISTEMA_GESTION);
            AprobadaParaRendirDB.Insert(db.getConection(),SIMULACION,IA);
            AprobadaParaRendirDB.Insert(db.getConection(),SIMULACION,INTERACCION_HS);
            AprobadaParaRendirDB.Insert(db.getConection(),TEC_EXPLOTACION,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),LEGISLACION,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),LEGISLACION,REINGENIERIA);
            AprobadaParaRendirDB.Insert(db.getConection(),METODOLOGIA,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),REDES_INFO,APP_REAL);
            AprobadaParaRendirDB.Insert(db.getConection(),REDES_INFO,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),REDES_INFO,SISTEMA_AVANZADOS_DB);
            AprobadaParaRendirDB.Insert(db.getConection(),REDES_INFO,SO_AVANZADOS);
            AprobadaParaRendirDB.Insert(db.getConection(),TEORIA_CONTROL,APP_REAL);
            AprobadaParaRendirDB.Insert(db.getConection(),TEORIA_CONTROL,PROYECTO);
            //5 Anio
            AprobadaParaRendirDB.Insert(db.getConection(),APP_REAL,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),ING_CALIDAD,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),INTERNETWORKING,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),PRACTICA_SUPERVISADA,PROYECTO);
            AprobadaParaRendirDB.Insert(db.getConection(),IA,IA_AVANZADA);
        }
    }

    public static String[] getNombreMaterias(DataBase db, Integer anio,Integer orden) {
        Cursor cursor = MateriasDB.getMateriasPorAnio(db.getConection(),anio,orden);
        Integer cantidad = cursor.getCount();
        LogD("Cantidad del cursor:",cantidad.toString());
        String[] lista = new String[cursor.getCount()];

        int i=0;
        if (cursor.moveToFirst()) {
            do {
                int columnaNombreMateria = cursor.getColumnIndex("nombreMateria");
                lista[i] = cursor.getString(columnaNombreMateria);
                i++;
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        return lista;
    }

    public static String[] getNombreMateriasAgregarCursada(DataBase db, Integer anio,Integer orden) {
        Cursor cursor = MateriasDB.getMateriasPorAnioAgregarCursada(db.getConection(), anio, orden);
        Integer cantidad = cursor.getCount();
        LogD("Cantidad del cursor:",cantidad.toString());
        String[] lista = new String[cursor.getCount()];

        int i=0;
        if (cursor.moveToFirst()) {
            do {
                int columnaNombreMateria = cursor.getColumnIndex("nombreMateria");
                lista[i] = cursor.getString(columnaNombreMateria);
                i++;
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        return lista;
    }

    public static String[] getNombreMateriasAgregarFinal(DataBase db, Integer anio,Integer orden) {
        Cursor cursor = MateriasDB.getMateriasPorAnioAgregarFinal(db.getConection(), anio, orden);
        Integer cantidad = cursor.getCount();
        LogD("Cantidad del cursor:",cantidad.toString());
        String[] lista = new String[cursor.getCount()];

        int i=0;
        if (cursor.moveToFirst()) {
            do {
                int columnaNombreMateria = cursor.getColumnIndex("nombreMateria");
                lista[i] = cursor.getString(columnaNombreMateria);
                i++;
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        return lista;
    }

    public static ArrayList<Materia> getTodasMaterias(DataBase db,Integer anio) {
        ArrayList<Materia> listaMaterias = new ArrayList<>();

        Cursor cursor = MateriasDB.getTodasMaterias(db.getConection(),anio);
        if (cursor.moveToFirst()) {
            do {
                int columnaNombreMateria = cursor.getColumnIndex("nombreMateria");
                int columnaAnioMateria = cursor.getColumnIndex("anioMateria");
                int columnaIdMateria = cursor.getColumnIndex("idMateria");
                int columnaDuracion = cursor.getColumnIndex("esAnual");
                int columnaCuatrimestre = cursor.getColumnIndex("queCuatrimestre");
                int columnaElectiva = cursor.getColumnIndex("electiva");

                Materia materia = new Materia(cursor.getString(columnaNombreMateria));
                materia.setAnio(cursor.getInt(columnaAnioMateria));
                materia.setId(cursor.getInt(columnaIdMateria));
                if (cursor.getInt(columnaDuracion)==1) {
                    materia.setIsAnual(false);
                } else {
                    materia.setIsAnual(true);
                }
                materia.setEnQueCuatrimestre(cursor.getInt(columnaCuatrimestre));
                if (cursor.getInt(columnaElectiva)==1) {
                    materia.setElectiva(true);
                } else {
                    materia.setElectiva(false);
                }

                listaMaterias.add(materia);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        return listaMaterias;
    }

    public static Materia getMateriaCompleta (DataBase db,Integer idMateria) {
        Materia materia = new Materia();

        Cursor cursor = MateriasDB.getMateriaCompleta(db.getConection(), idMateria);
        if (cursor.moveToFirst()) {
            do {
                int columnaNombreMateria = cursor.getColumnIndex("nombreMateria");
                int columnaAnioMateria = cursor.getColumnIndex("anioMateria");
                int columnaIdMateria = cursor.getColumnIndex("idMateria");
                int columnaDuracion = cursor.getColumnIndex("esAnual");
                int columnaCuatrimestre = cursor.getColumnIndex("queCuatrimestre");
                int columnaElectiva = cursor.getColumnIndex("electiva");

                materia.setNombre(cursor.getString(columnaNombreMateria));
                materia.setAnio(cursor.getInt(columnaAnioMateria));
                materia.setId(cursor.getInt(columnaIdMateria));
                if (cursor.getInt(columnaDuracion)==1) {
                    materia.setIsAnual(false);
                } else {
                    materia.setIsAnual(true);
                }
                materia.setEnQueCuatrimestre(cursor.getInt(columnaCuatrimestre));
                if (cursor.getInt(columnaElectiva)==1) {
                    materia.setElectiva(true);
                } else {
                    materia.setElectiva(false);
                }

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        return materia;
    }

    public static void LogD(String nombreMetodo,String mensaje) {
        L.LogD("ActionMateriasDB", nombreMetodo + " " + mensaje);
    }
}
