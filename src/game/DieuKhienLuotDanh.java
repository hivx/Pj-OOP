package game;

import java.util.ArrayList;

public class DieuKhienLuotDanh {
    int SONGUOICHOI;
    int nguoiDanhOViTriHienTai;
    boolean[] khoaLuotPlayer;
    ArrayList<String> dayluottiep=new ArrayList<>();
    String chieucualuot;
    public DieuKhienLuotDanh(int soNguoiChoi,
                             int nguoiDanhOViTriHienTai, boolean []khoaLuotPlayer, String chieucualuot) {
        this.chieucualuot=chieucualuot;
        this.khoaLuotPlayer=khoaLuotPlayer;
        this.SONGUOICHOI=soNguoiChoi;
        this.nguoiDanhOViTriHienTai=nguoiDanhOViTriHienTai;
        if(chieucualuot.equals("cungchieu")) {
            for(int i=nguoiDanhOViTriHienTai+1;i<=nguoiDanhOViTriHienTai+soNguoiChoi;i++) {
                tinhDayLuot(i);
            }
        } else {
            for(int i=nguoiDanhOViTriHienTai+soNguoiChoi-1;i>=nguoiDanhOViTriHienTai+1;i--) {
                tinhDayLuot(i);
            }
        }
    }
    public void tinhDayLuot(int i) {
        if(i%SONGUOICHOI==0) {
            dayluottiep.add(String.valueOf(SONGUOICHOI));
        } else if(i>SONGUOICHOI) {
            dayluottiep.add(String.valueOf(i%SONGUOICHOI));
        } else {
            dayluottiep.add(String.valueOf(i));
        }
    }
    public int nguoiDanhTiepTheoOViTri() {
        int i=nguoiDanhOViTriHienTai;
        for (String s : dayluottiep) {
            if (!khoaLuotPlayer[Integer.parseInt(s)]) {
                return Integer.parseInt(s);
            }
        }
        return i;
    }
}
