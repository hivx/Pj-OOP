package game;

public class Bot extends Actor{
    public Bot(int indexActor, String listBaiDuocChia, int x_cardau,
               int y_carddau, int xclock, int yclock, int xtime, int ytime, int x_nhan_ktheo,
               int y_nhan_ktheo, int xcardhide, int ycardhide, int xbaira, int ybaira,
               int xnhan_sl_baiconlai, int ynhan_sl_baiconlai, int x_avatar, int y_avatar,
               String tenImageAvatar, String chedochoi, int solabaiconlai,
               int toadoX3bich, int toadoY3bich) {

        this.toadoXnhanthongbaoditruoc=toadoX3bich;
        this.toadoYnhanthongbaoditruoc=toadoY3bich;
        this.chedochoi=chedochoi;
        this.solabaiconlai=solabaiconlai;
        this.indexActor=indexActor;
        this.listBaiDuocChia=listBaiDuocChia;
        this.toadoXnhanclock=xclock;//800
        this.toadoYnhanclock=yclock;//205
        this.toadoXbodemthoigian=xtime;//815
        this.toadoYbodemthoigian=ytime;//225
        this.toadoXtrangthaikhongtheo=x_nhan_ktheo;//950
        this.toadoYtrangthaikhongtheo=y_nhan_ktheo;//220
        this.toadoXcardhide=xcardhide;//900
        this.toadoYcardhide=ycardhide;//310
        this.toadoXdautiencualabaitrongdanhsachbaivuara=xbaira;//800
        this.toadoYdautiencualabaitrongdanhsachbaivuara=ybaira;//250
        this.toadoXnhansolabaiconlai=xnhan_sl_baiconlai;
        this.toadoYnhansolabaiconlai=ynhan_sl_baiconlai;
        this.toadoXnutcarddautien=x_cardau; /*đánh dấu nao bỏ*/
        this.toadoYnutcarddautien=y_carddau;
        this.tenImageAvatar=tenImageAvatar;
        this.toadoXavatar=x_avatar;
        this.toadoYavatar=y_avatar;

        System.out.println("Bài của actor "+indexActor+" = "+(char)34+listBaiDuocChia+(char)34+";");
        setUpCacOptionBot();
    }
    public void setUpCacOptionBot() {
        taoNhanThoiGian();
        nhanthoigian.setVisible(false);
        taoNhanDongHo();
        nhanclock.setVisible(false);
        taoNhanTrangThaiKhongTheo();
        nhankhongtheo.setVisible(false);
        taoCardHide();
        taoNhanSoLuongLaConLai();
        taoAvatar();
        taoNhanWinner();
        taoNhanThongBao3BichDiTruoc();
        taoNhanThongBaoBaiNhoDiTruoc();

        //nhanclock.setVisible(false);
        SapXepDuLieuCacLaBaiTheoSoHieuTangDanKhiDuocChiaBai();
        /*đánh dấu nao bỏ*/
        setUpHinhAnhLaBaiChuaRa();
        /*đánh dấu nao bỏ*/
        taoToaDoXYChoCacLaBaiChuaRa();
    }
    public void batOptionBot() {
        for (Card_Player cardPlayer : baivuachon) {
            int j = cardPlayer.vitri;
            nhanbaira[j].setVisible(false);
        }
        bodem=10;
        nhanthoigian.setText(""+10);
        
        baivuachon.clear();
        nhanclock.setVisible(true);
        nhanthoigian.setVisible(true);
    }
    public void tatOptionBot() {
        nhanbaiconlai.setText(""+solabaiconlai);
        nhanclock.setVisible(false);
        nhanthoigian.setVisible(false);
    }
    public void xuLyRaBai(String dapan) {
        String[] c=dapan.split("\\$");
        for(int j=2;j<Integer.parseInt(c[0])+2;j++) {
            for (Card_Player cardPlayer : mycard) {
                if (Integer.parseInt(c[j]) == cardPlayer.sohieu) {
                    Card_Player tempbaivuachon = new Card_Player(cardPlayer.vitri, Integer.parseInt(c[j]));
                    baivuachon.add(tempbaivuachon);
                }
            }
        }
        setUpHinhAnhRaBai();
        for(int y=0;y<mycard.size();y++) {
            for (Card_Player cardPlayer : baivuachon) {
                int v = cardPlayer.vitri;
                nhanmycard[v].setVisible(false);
                if (mycard.get(y).vitri == cardPlayer.vitri) {
                    mycard.remove(y);
                }
            }
        }
        taoToaDoXYChoCacLaBaiChuaRa();
        solabaiconlai-=baivuachon.size();
    }
}
