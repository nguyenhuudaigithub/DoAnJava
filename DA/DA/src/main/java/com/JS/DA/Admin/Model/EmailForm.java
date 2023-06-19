package com.JS.DA.Admin.Model;

import jakarta.persistence.Column;

public class EmailForm {
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeTai() {
        return deTai;
    }

    public void setDeTai(String deTai) {
        this.deTai = deTai;
    }

    private String hoTen;
    private String dienThoai;
    private String email;
    @Column(name = "deTai", columnDefinition = "longblob")
    private String deTai;

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDungGopY() {
        return noiDungGopY;
    }

    public void setNoiDungGopY(String noiDungGopY) {
        this.noiDungGopY = noiDungGopY;
    }

    private String tieuDe;
    private String noiDungGopY;

    public String getDeTaiName() {
        String deTaiName = "";
        switch (deTai) {
            case "HoTro":
                deTaiName = "Hỗ trợ sự cố xảy ra";
                break;
            case "BaoCao":
                deTaiName = "Báo cáo dịch vụ";
                break;
            case "LoiWeb":
                deTaiName = "Thống báo lỗi về website";
                break;
            case "Khac":
                deTaiName = "Khác";
                break;
        }
        return deTaiName;
    }

}
