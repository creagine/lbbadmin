package com.creagine.lbbadmin.Model;

public class GantiJadwal {

    private String siswa;
    private String tutor;
    private String idSiswa;
    private String idTutor;
    private String jadwal;
    private String tanggal;
    private String jam;
    private String hari;
    private String ruang;
    private String alasan;
    private String status;

    public GantiJadwal() {
    }

    public GantiJadwal(String siswa, String tutor, String idSiswa, String idTutor, String jadwal,
                       String tanggal, String jam, String hari, String ruang, String alasan,
                       String status) {
        this.siswa = siswa;
        this.tutor = tutor;
        this.idSiswa = idSiswa;
        this.idTutor = idTutor;
        this.jadwal = jadwal;
        this.tanggal = tanggal;
        this.jam = jam;
        this.hari = hari;
        this.ruang = ruang;
        this.alasan = alasan;
        this.status = status;
    }

    public String getSiswa() {
        return siswa;
    }

    public void setSiswa(String siswa) {
        this.siswa = siswa;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(String idTutor) {
        this.idTutor = idTutor;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdSiswa() {
        return idSiswa;
    }

    public void setIdSiswa(String idSiswa) {
        this.idSiswa = idSiswa;
    }
}
