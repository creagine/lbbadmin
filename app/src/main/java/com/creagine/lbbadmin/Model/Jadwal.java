package com.creagine.lbbadmin.Model;

public class Jadwal {

    String namaSiswa, jurusan, grade, harga, tutor, hari, jam, tanggal, ruang;

    public Jadwal() {
    }

    public Jadwal(String namaSiswa, String jurusan, String grade, String harga, String tutor, String hari, String jam, String tanggal, String ruang) {
        this.namaSiswa = namaSiswa;
        this.jurusan = jurusan;
        this.grade = grade;
        this.harga = harga;
        this.tutor = tutor;
        this.hari = hari;
        this.jam = jam;
        this.tanggal = tanggal;
        this.ruang = ruang;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }
}
