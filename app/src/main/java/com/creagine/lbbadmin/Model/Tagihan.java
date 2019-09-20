package com.creagine.lbbadmin.Model;

public class Tagihan {

    String noTagihan, deskripsiTagihan, nominalTagihan, tanggalJatuhTempoTagihan, namaSiswaTagihan, statusTagihan;


    public Tagihan() {
    }


    public Tagihan(String noTagihan, String deskripsiTagihan, String nominalTagihan, String tanggalJatuhTempoTagihan, String namaSiswaTagihan, String statusTagihan) {
        this.noTagihan = noTagihan;
        this.deskripsiTagihan = deskripsiTagihan;
        this.nominalTagihan = nominalTagihan;
        this.tanggalJatuhTempoTagihan = tanggalJatuhTempoTagihan;
        this.namaSiswaTagihan = namaSiswaTagihan;
        this.statusTagihan = statusTagihan;
    }

    public String getNoTagihan() {
        return noTagihan;
    }

    public void setNoTagihan(String noTagihan) {
        this.noTagihan = noTagihan;
    }

    public String getDeskripsiTagihan() {
        return deskripsiTagihan;
    }

    public void setDeskripsiTagihan(String deskripsiTagihan) {
        this.deskripsiTagihan = deskripsiTagihan;
    }

    public String getNominalTagihan() {
        return nominalTagihan;
    }

    public void setNominalTagihan(String nominalTagihan) {
        this.nominalTagihan = nominalTagihan;
    }

    public String getTanggalJatuhTempoTagihan() {
        return tanggalJatuhTempoTagihan;
    }

    public void setTanggalJatuhTempoTagihan(String tanggalJatuhTempoTagihan) {
        this.tanggalJatuhTempoTagihan = tanggalJatuhTempoTagihan;
    }

    public String getNamaSiswaTagihan() {
        return namaSiswaTagihan;
    }

    public void setNamaSiswaTagihan(String namaSiswaTagihan) {
        this.namaSiswaTagihan = namaSiswaTagihan;
    }

    public String getStatusTagihan() {
        return statusTagihan;
    }

    public void setStatusTagihan(String statusTagihan) {
        this.statusTagihan = statusTagihan;
    }
}
