package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGeneratorDemo {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.example.saulovera.proximatedemo.dao");
        addClub(schema);
        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, "app/src/main/java");
    }



    private static void addClub(Schema schema) {

        Entity customer = schema.addEntity("ProfileEntity");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();
        customer.addStringProperty("lastname");
        customer.addStringProperty("secondname");
        customer.addStringProperty("telephone");
        customer.addStringProperty("zipCode");
        customer.addDoubleProperty("latPoint");
        customer.addDoubleProperty("lonPoint");

    }
}
