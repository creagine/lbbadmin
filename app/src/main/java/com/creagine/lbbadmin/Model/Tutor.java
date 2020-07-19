package com.creagine.lbbadmin.Model;

public class Tutor {
    String idTutor, namaTutor, searchnameTutor, emailTutor, passwordTutor, jurusanTutor;

    public Tutor() {
    }

    public Tutor(String idTutor, String namaTutor, String searchnameTutor, String emailTutor, String passwordTutor, String jurusanTutor) {
        this.idTutor = idTutor;
        this.namaTutor = namaTutor;
        this.searchnameTutor = searchnameTutor;
        this.emailTutor = emailTutor;
        this.passwordTutor = passwordTutor;
        this.jurusanTutor = jurusanTutor;
    }

    public String getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(String idTutor) {
        this.idTutor = idTutor;
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

    public String getSearchnameTutor() {
        return searchnameTutor;
    }

    public void setSearchnameTutor(String searchnameTutor) {
        this.searchnameTutor = searchnameTutor;
    }

    public String getEmailTutor() {
        return emailTutor;
    }

    public void setEmailTutor(String emailTutor) {
        this.emailTutor = emailTutor;
    }

    public String getPasswordTutor() {
        return passwordTutor;
    }

    public void setPasswordTutor(String passwordTutor) {
        this.passwordTutor = passwordTutor;
    }
}
