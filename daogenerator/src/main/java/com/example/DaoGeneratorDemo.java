package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGeneratorDemo {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.example.saulovera.proximatedemo.dao");
        addProfile(schema);
        addSection(schema);
        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, "app/src/main/java");
    }


    private static void addProfile(Schema schema) {

        Entity customer = schema.addEntity("ProfileEntity");
        customer.addIdProperty();
        customer.addStringProperty("id_server").notNull();
        customer.addStringProperty("name").notNull();
        customer.addStringProperty("apellidos");
        customer.addStringProperty("correo");
        customer.addStringProperty("numero_documento");
        customer.addStringProperty("ultima_sesion");
        customer.addStringProperty("eliminado");
        customer.addStringProperty("documentos_id");
        customer.addStringProperty("documentos_label");
        customer.addStringProperty("token");
        customer.addStringProperty("url_photo");
        customer.addStringProperty("activo");
        customer.addDoubleProperty("latPoint");
        customer.addDoubleProperty("longPoint");

    }

    private static void addSection(Schema schema) {

        Entity customer = schema.addEntity("SectionEntity");
        customer.addIdProperty();
        customer.addStringProperty("id_server").notNull();
        customer.addStringProperty("seccion").notNull();
        customer.addStringProperty("abrev");



    }
}
