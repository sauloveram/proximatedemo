package com.example.saulovera.proximatedemo.vo;

/**
 * Created by saulovera on 24/8/2017.
 */

public class LoginRequest {

    private String correo = "";//: "prueba@proximateapps.com",
    private String contrasenia= "";// "12digo16digo18#$"

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
