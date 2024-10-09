package com.vfarma;

import com.vfarma.Modelo.PersonaMoral;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        PersonaMoral personaMoral = new PersonaMoral("VFarma", "General");
        System.out.println("Razón social: " + personaMoral.obtenerRazonSocial());
    }
}