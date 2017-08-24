package com.example.saulovera.proximatedemo.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saulovera on 24/8/2017.
 */
public class ProfileData {

    private String id = "";//1,
    private String nombres = "";//"Alejandro",
    private String apellidos = "";//"Oropeza López",
    private String correo = "";//"prueba@proximateapps.com",
    private String numero_documento = "";//"6666666666",
    private String ultima_sesion = "";//"2017-08-24T02:21:20.000Z",
    private String eliminado = "";//0,
    private String documentos_id = "";//1,
    private String documentos_abrev = "";//"CC",
    private String documentos_label = "";//"Cédula de Ciudadania",
    private String estados_usuarios_label = "";//"ACTIVO",
    private List<ProfileSection> secciones = new ArrayList<>();//[


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getUltima_sesion() {
        return ultima_sesion;
    }

    public void setUltima_sesion(String ultima_sesion) {
        this.ultima_sesion = ultima_sesion;
    }

    public String getEliminado() {
        return eliminado;
    }

    public void setEliminado(String eliminado) {
        this.eliminado = eliminado;
    }

    public String getDocumentos_id() {
        return documentos_id;
    }

    public void setDocumentos_id(String documentos_id) {
        this.documentos_id = documentos_id;
    }

    public String getDocumentos_abrev() {
        return documentos_abrev;
    }

    public void setDocumentos_abrev(String documentos_abrev) {
        this.documentos_abrev = documentos_abrev;
    }

    public String getDocumentos_label() {
        return documentos_label;
    }

    public void setDocumentos_label(String documentos_label) {
        this.documentos_label = documentos_label;
    }

    public String getEstados_usuarios_label() {
        return estados_usuarios_label;
    }

    public void setEstados_usuarios_label(String estados_usuarios_label) {
        this.estados_usuarios_label = estados_usuarios_label;
    }

    public List<ProfileSection> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<ProfileSection> secciones) {
        this.secciones = secciones;
    }
}
