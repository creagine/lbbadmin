package com.creagine.lbbadmin.Model;

public class Fee {

    String no, idTutor, namaTutor, namaSiswa, bulan, tahun, jurusan, grade, tarif, fee, presensi,
            tglSpp;

    public Fee() {
    }

    public Fee(String no, String idTutor, String namaTutor, String namaSiswa, String bulan,
               String tahun, String jurusan, String grade, String tarif, String fee,
               String presensi, String tglSpp) {
        this.no = no;
        this.idTutor = idTutor;
        this.namaTutor = namaTutor;
        this.namaSiswa = namaSiswa;
        this.bulan = bulan;
        this.tahun = tahun;
        this.jurusan = jurusan;
        this.grade = grade;
        this.tarif = tarif;
        this.fee = fee;
        this.presensi = presensi;
        this.tglSpp = tglSpp;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNamaTutor() {
        return namaTutor;
    }

    public void setNamaTutor(String namaTutor) {
        this.namaTutor = namaTutor;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
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

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getPresensi() {
        return presensi;
    }

    public void setPresensi(String presensi) {
        this.presensi = presensi;
    }

    public String getTglSpp() {
        return tglSpp;
    }

    public void setTglSpp(String tglSpp) {
        this.tglSpp = tglSpp;
    }

    public String getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(String idTutor) {
        this.idTutor = idTutor;
    }
}
