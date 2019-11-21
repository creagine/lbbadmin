package com.creagine.lbbadmin.Model;

public class Presensi {

    String namaSiswa, namaTutor, bulan, pekan, tanggalPresensi, materi, keterangan, status, presensiTutor, presensiSiswa, idJadwal;

    public Presensi() {
    }

    public Presensi(String namaSiswa, String namaTutor, String bulan, String pekan,
                    String tanggalPresensi, String materi, String keterangan, String status,
                    String presensiTutor, String presensiSiswa, String idJadwal) {
        this.namaSiswa = namaSiswa;
        this.namaTutor = namaTutor;
        this.bulan = bulan;
        this.pekan = pekan;
        this.tanggalPresensi = tanggalPresensi;
        this.materi = materi;
        this.keterangan = keterangan;
        this.status = status;
        this.presensiTutor = presensiTutor;
        this.presensiSiswa = presensiSiswa;
        this.idJadwal = idJadwal;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
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

    public String getPekan() {
        return pekan;
    }

    public void setPekan(String pekan) {
        this.pekan = pekan;
    }

    public String getTanggalPresensi() {
        return tanggalPresensi;
    }

    public void setTanggalPresensi(String tanggalPresensi) {
        this.tanggalPresensi = tanggalPresensi;
    }

    public String getMateri() {
        return materi;
    }

    public void setMateri(String materi) {
        this.materi = materi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPresensiTutor() {
        return presensiTutor;
    }

    public void setPresensiTutor(String presensiTutor) {
        this.presensiTutor = presensiTutor;
    }

    public String getPresensiSiswa() {
        return presensiSiswa;
    }

    public void setPresensiSiswa(String presensiSiswa) {
        this.presensiSiswa = presensiSiswa;
    }

    public String getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(String idJadwal) {
        this.idJadwal = idJadwal;
    }
}

