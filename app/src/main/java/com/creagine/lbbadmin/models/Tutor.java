package com.creagine.lbbadmin.models;

public class Tutor {
    String namaTutor,jurusanTutor;

    public Tutor() {
    }

    public Tutor(String namaTutor, String jurusanTutor) {
        this.namaTutor = namaTutor;
        this.jurusanTutor = jurusanTutor;
    }

    public String getNamaTutor() {
        return namaTutor;
    }

    public void setNamaTutor(String namaTutor) {
        this.namaTutor = namaTutor;
    }

    public String getJurusanTutor() {
        return jurusanTutor;
    }

    public void setJurusanTutor(String jurusanTutor) {
        this.jurusanTutor = jurusanTutor;
    }
}
