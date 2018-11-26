package com.tec.salsas.carpoolingtec.model;

import java.util.LinkedList;

public class StudentRun extends Student {

    private String myDriver;

    public StudentRun(){

    }

    public StudentRun(String carne, String name, String email, String nodoResidencia, String pass, String myDriver, LinkedList<Driver> frens, Double rate, String modo) {
        super(carne, name, email, nodoResidencia, pass, frens, rate ,modo);
        this.myDriver = myDriver;
    }

    public StudentRun(Student student, String myDriver){
        super(student.getCarne(), student.getName(), student.getEmail(), student.getNodoResidencia(), student.getPass(), student.getAmigos(), student.getRate(), student.getModo());
        this.myDriver = myDriver;
    }

    public void setStudent(Student student){
        setName(student.getName());
        setEmail(student.getEmail());
        setCarne(student.getCarne());
        setNodoResidencia(student.getNodoResidencia());
        setPass(student.getPass());
        setAmigos(student.getAmigos());
        setRate(student.getRate());
    }

    public String getMyDriver() {
        return myDriver;
    }

    public void setMyDriver(String myDriver) {
        this.myDriver = myDriver;
    }
}
