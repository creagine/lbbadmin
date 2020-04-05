package com.creagine.lbbadmin.Model;

public class Tutor {
    String namaTutor, searchnameTutor, emailTutor, passwordTutor, jurusanTutor;

    public Tutor() {
    }

    public Tutor(String namaTutor, String searchnameTutor, String emailTutor, String passwordTutor, String jurusanTutor) {
        this.namaTutor = namaTutor;
        this.searchnameTutor = searchnameTutor;
        this.emailTutor = emailTutor;
        this.passwordTutor = passwordTutor;
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
