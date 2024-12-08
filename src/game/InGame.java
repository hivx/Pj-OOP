package game;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import game.menu.BackgroundGame;
import game.menu.Menu_Main;
import javazoom.jl.player.Player;

public class InGame extends JFrame{
    ArrayList<Card_Player_ver_end> theyCard=new ArrayList<>();
    Bot_ver_end bot1,bot2,bot3;
    Player_ver_end player;
    private ChiaBai_Avatar_NgauNhien_ver_end cbnn;
    //private NaiveBayes_Bot_LearnData_ver_end nbbl;
    private GT_TLMN_BOT_TinhToan_va_RaQuyetDinh_ver_end gtbot_ttvrqd;
    private DieuKhienBatDauVaKetThucGame_ver_end dkbatdauketthucgame;
    private DieuKhienLuotDanh_ver_end dkluot;
    //private GT_TLMN_PhanNhomBai_ver_end gtplayer_pnb;
    private  int soPlayerBiKhoa;
    private final int SONGUOICHOI;
    private int isTurn;
    int[] soluonglaconlaicuaActor=new int[30];
    boolean isKETTHUC,isToiTrang;
    private final boolean[] daKhoaLuotActor=new boolean[5];
    private final JLabel khungoption=new JLabel();
    private final String avatarplayer;
    private final String chieucualuot;
    private final String chedochoi;
    private String dapanbotchon;
    private final String nhac;
    private String thoigianhethongbatdaugame;
    int index_actor_truoc=0,index_actor_sau=0;
    private final int BOTRABAI_O_GIAY;
    private final int THOIGIANBATDAUGAME_O_GIAY=0;
    private final SoundPlayer soundPlayer = new SoundPlayer();
    public InGame(String avatar,String chieucualuot,int soluongmay,
                  String chedochoi,String nhac,int thoigiantremayrabai) throws SQLException {
        this.avatarplayer=avatar;
        this.chieucualuot=chieucualuot;
        this.SONGUOICHOI=soluongmay+1;
        this.chedochoi=chedochoi;
        this.nhac=nhac;
        this.BOTRABAI_O_GIAY=10-thoigiantremayrabai;
        run();
    }
    public void run() throws SQLException {
        taoCuaSo();
        chiaBai();
        chiaAvatar();
        taoCacActor();
        setUpOptionPlayerLenBackground(player);
        chonSoBotDeSetupOption();
        isKETTHUC=false;
        isToiTrang=false;
        isTurn=0;
        theyCard.clear();
        tatCaPlayerDuocMoKhoaLuot();
        dkbatdauketthucgame=new DieuKhienBatDauVaKetThucGame_ver_end(SONGUOICHOI,player,bot1,bot2,bot3);
        dkbatdauketthucgame.thongBaoChuanBi();
        dkbatdauketthucgame.timeChuanBi();
        add(dkbatdauketthucgame.thongbaochuanbivaogame);
        add(dkbatdauketthucgame.timechuanbivaogame);
        dkbatdauketthucgame.DieuKhienTrangThaiNhacDauVanGame();
        demNguocChuanBiBatDau();
    }
    public void chiaBai() {
        cbnn=new ChiaBai_Avatar_NgauNhien_ver_end();
        cbnn.getChiaBai();
    }
    public void chiaAvatar() {
        cbnn.getAvatar(9, Integer.parseInt(avatarplayer));
    }
    //Bộ bài đầu vào
    /*cbnn.phanbai1= "2$3$8$10$12$17$18$20$31$33$35$37$40$";
    cbnn.phanbai2= "7$21$22$25$28$38$39$42$43$45$46$49$51$";
    cbnn.phanbai3= "6$9$11$13$19$24$26$30$32$34$36$44$48$";
    cbnn.phanbai4= "1$4$5$14$15$16$23$27$29$41$47$50$52$";*/
    public void taoCacActor() {
        player=new Player_ver_end(1,cbnn.phanbai1,400,440,416,455,
                450,490,550,630,400,450,340,550,450,450,550,450,280,580,avatarplayer,    13    ,150,500);
        bot1=new Bot_ver_end(2,cbnn.phanbai2,70,370,50,205,63,225,100,
                220,120,310,170,250,125,325,20,320,cbnn.avatarnv[2],chedochoi,      13    ,50,240);
        bot2=new Bot_ver_end(3,cbnn.phanbai3,650,100,400,140,416,155,
                500,20,500,90,500,20,505,110,560,120,cbnn.avatarnv[3],chedochoi,    13       ,600,40);
        bot3=new Bot_ver_end(4,cbnn.phanbai4,950,370,800,205,815,225,870,220
                ,880,310,900,250,885,325,950,310,cbnn.avatarnv[4],chedochoi,    13      ,800,240);
        for(int i=1;i<=SONGUOICHOI;i++) {
            soluonglaconlaicuaActor[i]=13;
        }
    }
    public void chonSoBotDeSetupOption() {
        if(SONGUOICHOI==2) {
            setUpOptionBotLenBackground(bot1);
        } else if(SONGUOICHOI==3) {
            setUpOptionBotLenBackground(bot1);
            setUpOptionBotLenBackground(bot2);
        } else if(SONGUOICHOI==4) {
            setUpOptionBotLenBackground(bot1);
            setUpOptionBotLenBackground(bot2);
            setUpOptionBotLenBackground(bot3);
        }
    }
    public void taoKhungOption() {
        URL url = InGame.class.getResource("/image/khungoption.png");
        assert url != null;
        ImageIcon icon = new ImageIcon(url);
        setLayout(null);
        khungoption.setIcon(icon);
        khungoption.setBounds(0,0  ,icon.getIconWidth(), icon.getIconHeight());
    }
    public void setUpOptionBotLenBackground(Bot_ver_end bot) {
        add(bot.winner);
        add(bot.nhanthoigian);
        add(bot.nhanclock);
        add(bot.avatar);
        add(bot.nhankhongtheo);
        for(int i=bot.mycard.size()-1;i>=0;i--) {
            int j=bot.mycard.get(i).vitri;
            add(bot.nhanmycard[j]);
        }
        add(bot.nhanbaiconlai);
        add(bot.cardhide);
        add(bot.nhanthongbao3bichditruoc);
        add(bot.nhanthongbaobainhoditruoc);
    }
    public void setUpOptionPlayerLenBackground(Player_ver_end pl) {
        add(pl.winner);
        add(pl.nhansoundon);
        add(pl.nhansoundoff);
        pl.nhansoundon.addMouseListener(mouseAdapterPlayerChonOptionKhac);
        pl.nhansoundoff.addMouseListener(mouseAdapterPlayerChonOptionKhac);
        add(pl.nhanquestion);
        pl.nhanquestion.addMouseListener(mouseAdapterPlayerChonOptionKhac);
        add(pl.nhanbackhome);
        pl.nhanbackhome.addMouseListener(mouseAdapterPlayerChonOptionKhac);
        add(pl.nhannewgame);
        pl.nhannewgame.addMouseListener(mouseAdapterPlayerChonOptionKhac);
        add(pl.nhanthoigian);
        add(pl.nhanclock);
        add(pl.cardhide);
        add(pl.nhankhongtheo);
        add(pl.nhanthongbao3bichditruoc);
        add(pl.nhanthongbaobainhoditruoc);
        add(pl.nutrabai);
        add(pl.nhanrabaian);
        add(pl.avatar);
        add(khungoption);
        pl.nutrabai.addMouseListener(mouseAdapterPlayerRaQuyetDinh);
        add(pl.nutkhongtheo);
        pl.nutkhongtheo.addMouseListener(mouseAdapterPlayerRaQuyetDinh);
        pl.nutkhongtheo.setVisible(false);
        for(int i=pl.mycard.size()-1;i>=0;i--) {
            int j=pl.mycard.get(i).vitri;
            add(pl.nhanmycard[j]);
            pl.nhanmycard[j].addMouseListener(pl.mouseAdapterPlayerChonBai);
            //pl.nhanmycard[j].setVisible(true);
        }
    }
    public void taoCuaSo(){
        setResizable(false);//không cho phóng to
        setTitle("Game chơi bài 52 lá");
        setBounds(50, 0, 1050, 700);
        setLocation(180, 0);//vị trí mặc định
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL url = InGame.class.getResource("/image/bg_TLMN_2.png");
        assert url != null;
        JPanel panel = new BackgroundGame(new ImageIcon(url).getImage());
        setContentPane(panel);
        taoKhungOption();
        panel.setLayout(null);
    }
    public void demNguocChuanBiBatDau() {
        ActionListener aTime = e -> {
            --dkbatdauketthucgame.bodem;
            dkbatdauketthucgame.timechuanbivaogame.setText(""+dkbatdauketthucgame.bodem);
            if(dkbatdauketthucgame.bodem==THOIGIANBATDAUGAME_O_GIAY) {
                dkbatdauketthucgame.thongbaochuanbivaogame.setVisible(false);
                dkbatdauketthucgame.timechuanbivaogame.setVisible(false);
                Date date = new Date();
                SimpleDateFormat  formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                thoigianhethongbatdaugame  = formatter.format(date);
                int iwin=dkbatdauketthucgame.indexActorKetThucLuonDoToiTrang();
                if(iwin==1) {
                    for(int i=player.baivuachon.size()-1;i>=0;i--) {
                        int j=player.baivuachon.get(i).vitri;
                        add(player.nhanbaira[j]);
                        player.nhanbaira[j].setVisible(true);
                    }
                    dkbatdauketthucgame.iwin=1;
                    dkbatdauketthucgame.isTurn=1;
                    dkbatdauketthucgame.istoitrang=true;
                    isKETTHUC=true;
                    JOptionPane.showMessageDialog(null,"Bài của bạn tới trắng!!! Chúc mừng bạn đã thắng");
                    try {
                        dkbatdauketthucgame.luuLichSuDau(thoigianhethongbatdaugame, SONGUOICHOI,
                        "Yes", cbnn.avatarnv[1],1);
                        dkbatdauketthucgame.xuLyKetThucGame();
                    } catch (SQLException ex) {
                        Logger.getLogger(InGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if((iwin==3&&SONGUOICHOI>=3)||(iwin==4&&SONGUOICHOI>=4)||(iwin==2)&&SONGUOICHOI>=2) {
                    isToiTrang=true;
                    try {
                        dkbatdauketthucgame.iwin=iwin;
                        dapanbotchon=dkbatdauketthucgame.dapantoitrang;
                        if(iwin==2)
                        {raBaiMain(bot1);bot1.tatOptionBot();}
                        if(iwin == 3) {
                            raBaiMain(bot2);bot2.tatOptionBot();
                        }
                        if(iwin == 4) {
                            raBaiMain(bot3);bot3.tatOptionBot();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(InGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        dkbatdauketthucgame.isTurn=iwin;
                        dkbatdauketthucgame.xuLyKetThucGame();
                    } catch (SQLException ex) {
                        Logger.getLogger(InGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    int indexnguoicolabainhonhat=dkbatdauketthucgame.timNguoiCoBaiNhoNhat();
                    chuyenToiLuotActor(indexnguoicolabainhonhat);
                }
                dkbatdauketthucgame.time.stop();
            }
        };
        dkbatdauketthucgame.time= new Timer(1000, aTime);
        dkbatdauketthucgame.time.start();
    }
    public MouseAdapter mouseAdapterPlayerRaQuyetDinh = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            if(e.getSource()==player.nutrabai) {
                player.nhanthongbao3bichditruoc.setVisible(false);
                player.nhanthongbaobainhoditruoc.setVisible(false);
                if(soPlayerBiKhoa==SONGUOICHOI-1) tatCaPlayerDuocMoKhoaLuot();

                player.xuLyRaBai(player);
                theyCard.clear();
                for(int i=0;i<player.baivuachon.size();i++) {
                    Card_Player_ver_end temp=new Card_Player_ver_end(0,player.baivuachon.get(i).sohieu);
                    theyCard.add(temp);
                }
                for(int i=player.baivuachon.size()-1;i>=0;i--) {
                    int j=player.baivuachon.get(i).vitri;
                    add(player.nhanbaira[j]);
                    player.nhanbaira[j].setVisible(true);
                }
                if(player.solabaiconlai==0) {
                    JOptionPane.showMessageDialog(null,"Chúc mừng bạn đã thắng");
                    player.winner.setVisible(true);
                    dkbatdauketthucgame.iwin=1;
                    try {
                        dkbatdauketthucgame.luuLichSuDau(thoigianhethongbatdaugame, SONGUOICHOI,
                            "No", cbnn.avatarnv[1],1);
                        dkbatdauketthucgame.isTurn=isTurn;
                        dkbatdauketthucgame.xuLyKetThucGame();
                    } catch (SQLException ex) {
                        Logger.getLogger(InGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return;
                }
            }
            if(e.getSource()==player.nutkhongtheo) {
                daKhoaLuotActor[1]=true;
                soPlayerBiKhoa++;
                player.tatOptionPlayer();
                for(int i=0;i<player.baivuachon.size();i++) {
                    int j=player.baivuachon.get(i).vitri;
                    player.nhanbaira[j].setVisible(false);
                }
                player.nhankhongtheo.setVisible(true);
            }
            if(!isKETTHUC) playerChuyenLuot();
        }
    };
    public static class SoundPlayer {
        private static final Logger LOGGER = Logger.getLogger(SoundPlayer.class.getName());
        private volatile boolean running = false; // Cờ dừng cho việc phát nhạc
        private SwingWorker<Void, Void> currentWorker; // Để lưu trữ SwingWorker hiện tại

        public void playSound(String nhactest) {
            // Kiểm tra trạng thái trước khi phát nhạc
            if (running) {
                stopSound(); // Dừng nhạc hiện tại nếu có
            }

            running = true; // Bắt đầu chạy nhạc

            // Tạo một SwingWorker để chạy nhạc trong background
            currentWorker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    try (InputStream fis = InGame.class.getResourceAsStream("/sound/" + nhactest + ".mp3")) {
                        if (fis == null) {
                            LOGGER.warning("File not found: /sound/" + nhactest + ".mp3");
                            return null;
                        }
                        Player play = new Player(fis);
                        play.play(); // Phát nhạc một lần
                    } catch (Exception e) {
                        LOGGER.severe("Error while playing sound: " + e.getMessage());
                    } finally {
                        running = false; // Đặt cờ dừng khi hoàn thành
                    }
                    return null;
                }
            };

            currentWorker.execute(); // Khởi chạy SwingWorker
        }

        // Dừng nhạc
        public void stopSound() {
            if (currentWorker != null && !currentWorker.isDone()) {
                running = false; // Đặt cờ dừng nhạc
                currentWorker.cancel(true); // Hủy SwingWorker nếu nó đang chạy
            }
        }
    }
    public MouseAdapter mouseAdapterPlayerChonOptionKhac = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            if(e.getSource()==player.nhansoundon) {
                player.nhansoundon.setVisible(false);player.nhansoundoff.setVisible(true);
                soundPlayer.playSound(nhac);
                try {
                    dkbatdauketthucgame.luuTrangThaiNhac(true);
                } catch (SQLException ignored) {

                }
            } else if(e.getSource()==player.nhansoundoff) {
                soundPlayer.stopSound();
                player.nhansoundoff.setVisible(false);player.nhansoundon.setVisible(true);
                try {
                    dkbatdauketthucgame.luuTrangThaiNhac(false);
                } catch (SQLException ignored) {

                }
            } else if(e.getSource()==player.nhannewgame) {
                dkbatdauketthucgame.isTurn=isTurn;
                try {
                    dkbatdauketthucgame.xuLyKetThucGame();
                } catch (SQLException ex) {
                    Logger.getLogger(InGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    run();
                } catch (SQLException ignored) {

                }
            } else if(e.getSource()==player.nhanbackhome) {
                try {
                    if(dkbatdauketthucgame.nhacDangBat()) soundPlayer.stopSound();
                    dkbatdauketthucgame.luuTrangThaiNhac(false);
                } catch (SQLException ignored) {

                }
                dkbatdauketthucgame.isTurn=isTurn;
                try {
                    dkbatdauketthucgame.xuLyKetThucGame();
                } catch (SQLException ex) {
                    Logger.getLogger(InGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                setVisible(false);
                try {
                    Menu_Main mn = new Menu_Main();
                    mn.setVisible(true);
                } catch (SQLException ignored) {

                }
            } else if(e.getSource()==player.nhanquestion) {
                javax.swing.SwingUtilities.invokeLater(() -> {
                    try {
                        taoGui();
                    } catch (SQLException ex) {
                        Logger.getLogger(LichSuDau.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        }
    };
    private static void taoGui() throws SQLException {
        JFrame frame = new JFrame("Lịch Sử Đấu");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JComponent newContentPane = new LichSuDau();
        
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(300, 200);
    }
    public void tatCaPlayerDuocMoKhoaLuot() {
        soPlayerBiKhoa=0;
        for(int i=1;i<=SONGUOICHOI;i++) {
            daKhoaLuotActor[i]=false;
        }
        player.nhankhongtheo.setVisible(false);
        bot1.nhankhongtheo.setVisible(false);
        bot2.nhankhongtheo.setVisible(false);
        bot3.nhankhongtheo.setVisible(false);
    }
    public void chuyenToiLuotActor(int i) {
        if(i==1) {
            player.isTurnPlayer=true;
            isTurn=1;
            player.nhanthoigian.setText(""+player.bodem);
            player.batOptionPlayer();
            setTimePlayer();
            if(soPlayerBiKhoa==SONGUOICHOI-1) { theyCard.clear();}
            player.theyCard=theyCard;
            player.soPlayerBiKhoa=soPlayerBiKhoa;
            player.SONGUOICHOI=SONGUOICHOI;
            player.bainhonhat=dkbatdauketthucgame.bainhonhat;
            if((theyCard.isEmpty() ||soPlayerBiKhoa==SONGUOICHOI-1)) {
                player.nutkhongtheo.setVisible(false);
                player.nhanrabaian.setVisible(true);
            } else {
                player.nutkhongtheo.setVisible(true);
                GT_TLMN_PhanNhomBai_ver_end getdata=new GT_TLMN_PhanNhomBai_ver_end(player.mycard);
                getdata.xayDungDataCacLoaiBoBaiChoBot();
                GT_TLMN_Player_ver_end gtplayer=new GT_TLMN_Player_ver_end(theyCard,player.baivuachon, getdata.cardbot);
                player.nhanrabaian.setVisible(gtplayer.coDapAn());
            }
        } else  if(i==2) {
            isTurn=2;
            player.isTurnPlayer=false;
            bot1.batOptionBot();
            setTimeBot1();
        } else if(i==3) {
            isTurn=3;
            player.isTurnPlayer=false;
            bot2.batOptionBot();
            setTimeBot2();
        } else if(i==4) {
            isTurn=4;
            player.isTurnPlayer=false;
            bot3.batOptionBot();
            setTimeBot3();
        }
    }
    public final void setTimePlayer() {
        ActionListener aTime = e -> {
            --player.bodem;
            player.nhanthoigian.setText(""+player.bodem);
            if(player.bodem==0) {
                JOptionPane.showMessageDialog(null, "Hết Giờ!!! Bạn Thua");
                try {
                    dkbatdauketthucgame.isTurn=1;
                    player.isKetThuc=true;
                    isKETTHUC=true;
                    dkbatdauketthucgame.xuLyKetThucGame();
                } catch (SQLException ex) {
                    Logger.getLogger(InGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        player.time = new Timer(1000, aTime);
        player.time.start();
    }
    public final void setTimeBot1() {
        ActionListener aTime = e -> {
            --bot1.bodem;
            bot1.nhanthoigian.setText(""+bot1.bodem);
            if(bot1.bodem==BOTRABAI_O_GIAY) {
                try {
                    actionBotMain(bot1);
                } catch (Exception ignored) {

                }
                bot1.time.stop();
            }
        };
        bot1.time = new Timer(1000, aTime);
        bot1.time.start();
    }
    public final void setTimeBot2() {
        ActionListener aTime = e -> {
            --bot2.bodem;
            bot2.nhanthoigian.setText(""+bot2.bodem);
            if(bot2.bodem==BOTRABAI_O_GIAY) {
                try {
                    actionBotMain(bot2);
                } catch (Exception ex) {
                    Logger.getLogger(InGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                bot2.time.stop();
            }
        };
        bot2.time = new Timer(1000, aTime);
        bot2.time.start();
    }
    public final void setTimeBot3() {
        ActionListener aTime = e -> {
            --bot3.bodem;
            bot3.nhanthoigian.setText(""+bot3.bodem);
            if(bot3.bodem==BOTRABAI_O_GIAY) {
                try {
                    actionBotMain(bot3);
                } catch (Exception ex) {
                    Logger.getLogger(InGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                bot3.time.stop();
            }
        };
        bot3.time = new Timer(1000, aTime);
        bot3.time.start();
    }
    public void timIndexActorTruocSau(Bot_ver_end bot) {
        DieuKhienLuotDanh_ver_end timactortruocsau;
        if(chieucualuot.equals("cungchieu")) {
            timactortruocsau=new
                    DieuKhienLuotDanh_ver_end(SONGUOICHOI,bot.indexActor,daKhoaLuotActor,"cungchieu");
            index_actor_sau= timactortruocsau.nguoiDanhTiepTheoOViTri();
            timactortruocsau=new
                    DieuKhienLuotDanh_ver_end(SONGUOICHOI,bot.indexActor,daKhoaLuotActor,"nguocchieu");
        } else {
            timactortruocsau=new
                    DieuKhienLuotDanh_ver_end(SONGUOICHOI,bot.indexActor,daKhoaLuotActor,"nguocchieu");
            index_actor_sau= timactortruocsau.nguoiDanhTiepTheoOViTri();
            timactortruocsau=new
                    DieuKhienLuotDanh_ver_end(SONGUOICHOI,bot.indexActor,daKhoaLuotActor,"cungchieu");
        }
        index_actor_truoc= timactortruocsau.nguoiDanhTiepTheoOViTri();
        // System.out.println("Tôi là "+bot.indexActor+"vị trí SAUU tôi là actor "+": "+index_actor_sau);
    }
    public void actionBotMain(Bot_ver_end bot) throws SQLException, IOException {
        GT_TLMN_PhanNhomBai_ver_end gtbot_pnb = new GT_TLMN_PhanNhomBai_ver_end(bot.mycard);
        gtbot_pnb.xayDungDataCacLoaiBoBaiChoBot();
        if(soPlayerBiKhoa==SONGUOICHOI-1|| theyCard.isEmpty()) {
            soluonglaconlaicuaActor[1]=player.solabaiconlai;
            soluonglaconlaicuaActor[2]=bot1.solabaiconlai;
            soluonglaconlaicuaActor[3]=bot2.solabaiconlai;
            soluonglaconlaicuaActor[4]=bot3.solabaiconlai;
            theyCard.clear();
            tatCaPlayerDuocMoKhoaLuot();
            timIndexActorTruocSau(bot);
            gtbot_ttvrqd=new GT_TLMN_BOT_TinhToan_va_RaQuyetDinh_ver_end(theyCard, gtbot_pnb.cardbot,
                    soluonglaconlaicuaActor,SONGUOICHOI, bot.indexActor,index_actor_truoc,index_actor_sau,
                    Integer.parseInt(dkbatdauketthucgame.bainhonhat));
            botMoComBat(bot);
        } else {
            timIndexActorTruocSau(bot);
            soluonglaconlaicuaActor[1]=player.solabaiconlai;
            soluonglaconlaicuaActor[2]=bot1.solabaiconlai;
            soluonglaconlaicuaActor[3]=bot2.solabaiconlai;
            soluonglaconlaicuaActor[4]=bot3.solabaiconlai;
            gtbot_ttvrqd=new GT_TLMN_BOT_TinhToan_va_RaQuyetDinh_ver_end
                    (theyCard, gtbot_pnb.cardbot,soluonglaconlaicuaActor,SONGUOICHOI,
                            bot.indexActor,index_actor_truoc,index_actor_sau,
                            Integer.parseInt(dkbatdauketthucgame.bainhonhat));
            botDoBai(bot);
        }
        if(!isKETTHUC) botChuyenLuot(bot);
        else {
            dkbatdauketthucgame.xuLyKetThucGame();
        }
    }
    public void botMoComBat(Bot_ver_end bot) throws IOException, SQLException {
        bot.nhanthongbaobainhoditruoc.setVisible(false);
        int uutien=gtbot_ttvrqd.mucDoUuTienRaBai();
        if(uutien==999) {
            bot.nhanthongbao3bichditruoc.setVisible(false);
            dapanbotchon=gtbot_ttvrqd.dapDiTruocLienQuanToiBaiNhoNhat(dkbatdauketthucgame.bainhonhat);
        }
        if(uutien==99) {
            dapanbotchon=gtbot_ttvrqd.dapDiTruocKhongDieuKien();
            System.out.println("Mức ưu tiên 99, Đáp án: "+dapanbotchon);
        } else if(uutien==9||uutien==98||uutien==8) {
            dapanbotchon=gtbot_ttvrqd.dapDiTruocMaxGiuTop();
            System.out.println("Mức ưu tiên 9 98 8. Đáp án: "+dapanbotchon);
        } else if(uutien==0) {
            dapanbotchon=gtbot_ttvrqd.dapAnMinDiTruoc_naivebayes(bot);
            System.out.println("Mức ưu tiên 0, Đáp án: "+dapanbotchon);
        }
        if(dapanbotchon.equals("koco")) {
            dapanbotchon=gtbot_ttvrqd.dapAnBotChonRanDom();
        }
        raBaiMain(bot);
    }
    public void botDoBai(Bot_ver_end bot) throws SQLException {
        int uutien=gtbot_ttvrqd.mucDoUuTienRaBai();
        if(uutien==98||uutien==99) {
            dapanbotchon= gtbot_ttvrqd.dapChanBaiKhongDieuKien();
        } else if(uutien==9||uutien==8) {
            dapanbotchon= gtbot_ttvrqd.dapAnMaxChanBaiGiuBaiTop();
        } else if(uutien==0) {
            dapanbotchon=gtbot_ttvrqd.dapAnMinChanBaiGiuBaiTop();
        }
        if(dapanbotchon.equals("koco")) {
            soPlayerBiKhoa++;
            daKhoaLuotActor[bot.indexActor]=true;
            bot.tatOptionBot();
            bot.nhankhongtheo.setVisible(true);
        } else {
            raBaiMain(bot);
        }
    }
    public void raBaiMain(Bot_ver_end bot) throws SQLException {
        //System.out.println("Đáp án bot "+bot.indexActor+": "+dapanbotchon);
        bot.xuLyRaBai(dapanbotchon);
        theyCard.clear();
        for(int i=0;i<bot.baivuachon.size();i++) {
         Card_Player_ver_end temp=new Card_Player_ver_end(0,bot.baivuachon.get(i).sohieu);
         theyCard.add(temp);
        }
        for(int y=bot.baivuachon.size()-1;y>=0;y--) {
         int t=bot.baivuachon.get(y).vitri;
         add(bot.nhanbaira[t]);
         bot.nhanbaira[t].setVisible(true);
        }
        if(bot.solabaiconlai==0|| isToiTrang) {
         if(isToiTrang) {
             dkbatdauketthucgame.istoitrang=true;
             bot.nhanbaiconlai.setText(String.valueOf(bot.solabaiconlai));
             JOptionPane.showMessageDialog(null,"Tới trắng !!!!. Máy "+bot.indexActor+" thắng");
             dkbatdauketthucgame.luuLichSuDau(thoigianhethongbatdaugame, SONGUOICHOI,
                     "Yes", cbnn.avatarnv[bot.indexActor],bot.indexActor);
         } else {
             bot.nhanbaiconlai.setText("0");
             JOptionPane.showMessageDialog(null,"Trò chơi kết thúc!!! Máy " +bot.indexActor+" thắng");
             dkbatdauketthucgame.luuLichSuDau(thoigianhethongbatdaugame, SONGUOICHOI,
                     "No", cbnn.avatarnv[bot.indexActor],bot.indexActor);
         }
         bot1.nhanclock.setVisible(false);
         bot1.nhanthoigian.setVisible(false);
         for(int i=bot.baivuachon.size()-1;i>=0;i--) {
             int j=bot.baivuachon.get(i).vitri;
             add(bot.nhanbaira[j]);
             bot.nhanbaira[j].setVisible(false);
         }
         isKETTHUC=true;
         dkbatdauketthucgame.iwin=bot.indexActor;
        }
    }
    public void botChuyenLuot(Bot_ver_end bot) {
        bot.tatOptionBot();
        dkluot=new DieuKhienLuotDanh_ver_end(SONGUOICHOI,bot.indexActor,daKhoaLuotActor,chieucualuot);
        chuyenToiLuotActor(dkluot.nguoiDanhTiepTheoOViTri());
    }
    public void playerChuyenLuot() {
        dkluot=new DieuKhienLuotDanh_ver_end(SONGUOICHOI,player.indexActor,daKhoaLuotActor,chieucualuot);
        chuyenToiLuotActor(dkluot.nguoiDanhTiepTheoOViTri());
        player.time.stop();
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

        });
    }
}
 