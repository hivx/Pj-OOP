package game.menu;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import game.ConnectDataBase;
import game.InGame;
import javazoom.jl.player.Player;

public class TuyChon extends JFrame{
    JLabel nhanoktab1=new JLabel();
    JLabel nhanoktab2=new JLabel();
    JLabel nhanoktab3=new JLabel();
    int indexgreen;
    JPanel jp3=new JPanel();
    JPanel jp4=new JPanel();
    JLabel[] avtnv=new JLabel[31];
    JLabel[] tickgreen=new JLabel[31];
    JRadioButton cb1,cb2,cb3,cb4,cb5,cb6,cb7, cb8, cb9;
    ConnectDataBase kn=new ConnectDataBase();
    Connection cn=kn.getConnectdatabase();
    Statement stm=cn.createStatement();
    JLabel nhantestnow=new JLabel();
    JComboBox<String> jcb;
    String nhactest;
    boolean nhac_on;
    int indexnhactemp;
    public TuyChon() throws SQLException {
        setTitle("Game chơi bài 52 lá");
        setBounds(50, 0, 700, 697);
        setLocation(300, 0);
        setVisible(false);
        setResizable(true);
        JTabbedPane jtp = new JTabbedPane();
        jtp.addTab("Avatar", caiDatNhanVat());
        jtp.addTab("Tùy Chỉnh Game", tuyChinhGame());
        jtp.addTab("Tùy Chỉnh Nhạc",caiDatAmThanh());
        jtp.addTab("Copyright",copyRight());
        getContentPane().add(jtp);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JPanel caiDatNhanVat() throws SQLException{
        ResultSet rs=stm.executeQuery("SELECT AVATAR FROM TABLETLMNTUYCHON");
        while(rs.next()) {
            indexgreen=rs.getInt(1);
        }
        System.out.println("--"+indexgreen);
        for(int i=1;i<=30;i++) {
            avtnv[i] = new JLabel();
            tickgreen[i] = new JLabel();
        }
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=100;
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(new JLabel("Chọn nhân vật"), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=20;
        gbc.ipadx=20;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(tickgreen[1], gbc);
        panel.add(NhanNV(1), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(tickgreen[2], gbc);
        panel.add(NhanNV(2), gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        panel.add(tickgreen[3], gbc);
        panel.add(NhanNV(3), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(tickgreen[4], gbc);
        panel.add(NhanNV(4), gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(tickgreen[5], gbc);
        panel.add(NhanNV(5), gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        panel.add(tickgreen[6], gbc);
        panel.add(NhanNV(6), gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(tickgreen[7], gbc);
        panel.add(NhanNV(7), gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(tickgreen[8], gbc);
        panel.add(NhanNV(8), gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
        panel.add(tickgreen[9], gbc);
        panel.add(NhanNV(9), gbc);

        tickgreen[indexgreen].setVisible(true);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(taoNutOK(), gbc);
        add(panel);

        return panel;
    }
    public JLabel NhanNV(int i) {
        ImageIcon Iconavt= new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/nhanvat/" + i + ".png")));
        ImageIcon Icontickgreen = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/tickgreen.png")));
        avtnv[i].setIcon(Iconavt);
        avtnv[i].addMouseListener(mouseAdapterUserClickTab1);
        tickgreen[i].setIcon(Icontickgreen);
        tickgreen[i].setVisible(false);

        return avtnv[i];
    }
    public JLabel taoNutOK() {
        ImageIcon Iconplay = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/ok.png")));
        nhanoktab1.setIcon(Iconplay);
        nhanoktab1.setBounds(200, 200, Iconplay.getIconWidth(), Iconplay.getIconHeight());

        add(nhanoktab1);
        nhanoktab1.addMouseListener(mouseAdapterUserClickTab1);

        return nhanoktab1;
    }
    private final SoundPlayer soundPlayer = new SoundPlayer();
    public MouseAdapter mouseAdapterUserClickTab1 = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == nhanoktab1) {
                if (nhac_on) {
                    soundPlayer.stopSound(); // Dừng nhạc an toàn
                    nhac_on = false;
                }

                tickgreen[indexgreen].setVisible(true);
                try {
                    Menu_Main m = new Menu_Main();
                    setVisible(false);
                    m.setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi cơ sở dữ liệu: " + ex.getMessage(),
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    SetOKCheDoChoi();
                } catch (SQLException ex) {
                    Logger.getLogger(TuyChon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Kiểm tra avatar selection nếu người dùng chọn
            for (int i = 1; i <= 9; i++) {
                if (e.getSource() == avtnv[i]) {
                    handleAvatarSelection(i);
                }
            }
        }
    };
    private void handleAvatarSelection(int avatarIndex) {
        setLayout(null);
        tickgreen[indexgreen].setVisible(false);
        indexgreen = avatarIndex;
        tickgreen[avatarIndex].setVisible(true);
        String temp = "UPDATE TABLETLMNTUYCHON SET avatar = " + avatarIndex;
        try {
            stm.executeUpdate(temp);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi cập nhật avatar: " + ex.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    JButton nuttang,nutgiam;
    JLabel textthoigiantre;
    public JPanel tuyChinhGame() throws SQLException {
        JLabel text1,text2,text3, text4;
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 1;
        gbc.gridy = 8;
        text1 =new JLabel("Chọn chiều của lượt");
        text1.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(text1, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 1;
        gbc.gridy = 9;
        cb1 = new JRadioButton("Chiều tay trái");
        panel.add(cb1,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 2;
        gbc.gridy = 9;
        cb2 = new JRadioButton("Chiều tay phải");
        panel.add(cb2,gbc);
        ButtonGroup bg = new ButtonGroup();
        bg.add(cb1);
        bg.add(cb2);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 1;
        gbc.gridy = 4;
        text2 =new JLabel("Số lượng người chơi");
        text2.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(text2, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 1;
        gbc.gridy = 5;
        cb3 = new JRadioButton("2 người");
        panel.add(cb3,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 2;
        gbc.gridy = 5;
        cb4 = new JRadioButton("3 người");
        panel.add(cb4,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 3;
        gbc.gridy = 5;
        cb5 = new JRadioButton("4 người");
        panel.add(cb5,gbc);
        ButtonGroup slmay = new ButtonGroup();
        slmay.add(cb3);
        slmay.add(cb4);
        slmay.add(cb5);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 1;
        gbc.gridy = 6;
        text3 =new JLabel("Chế độ chơi");
        text3.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(text3, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 1;
        gbc.gridy = 7;
        cb6 = new JRadioButton("Chơi với người");
        panel.add(cb6,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 2;
        gbc.gridy = 7;
        cb7 = new JRadioButton("Chơi với máy");
        panel.add(cb7,gbc);
        ButtonGroup chedochoi1 = new ButtonGroup();
        chedochoi1.add(cb6);
        chedochoi1.add(cb7);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 1;
        gbc.gridy = 2;
        text4 =new JLabel("Chọn trò chơi");
        text4.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(text4, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 1;
        gbc.gridy = 3;
        cb8 = new JRadioButton("Tiến lên miền nam");
        panel.add(cb8,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 2;
        gbc.gridy = 3;
        cb9 = new JRadioButton("Tiến lên miền bắc");
        panel.add(cb9,gbc);

        ButtonGroup chedochoi2 = new ButtonGroup();
        chedochoi2.add(cb8);
        chedochoi2.add(cb9);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 1;
        gbc.gridy = 0;
        ResultSet rs=null;
        try {
            rs=stm.executeQuery("SELECT thoigiantremayrabai FROM TABLETLMNTUYCHON");
            while(rs.next()) {
                thoigiantre=rs.getInt(1);
                //System.out.println("chieu: "+temp);
            }
        } catch (SQLException ignored) {
        }
        textthoigiantre =new JLabel("Thời gian máy ra quyết định: "+thoigiantre+" giây");
        textthoigiantre.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textthoigiantre, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        nuttang=new JButton("Tăng ");
        // nuttang.addMouseListener(mouseAdapterUserClickTangGiam);
        panel.add(nuttang);
        nuttang.addMouseListener(mouseAdapterUserClickTangGiam);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
        nutgiam=new JButton("Giảm ");
        panel.add(nutgiam);
        nutgiam.addMouseListener(mouseAdapterUserClickTangGiam);
        // nuttang.addMouseListener(mouseAdapterUserClickTangGiam);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady=30;
        gbc.gridx = 1;
        gbc.gridy = 10;
        try {
            panel.add(taoNutOKTab2(),gbc) ;
        } catch (SQLException ex) {
            //System.out.println(ex);
        }
        add(panel);
        String temp="";
        try {
            rs=stm.executeQuery("SELECT chieucualuot FROM TABLETLMNTUYCHON");
        } catch (SQLException ex) {
            //System.out.println(ex);
        }
        try {
            while(true) {
                assert rs != null;
                if (!rs.next()) break;
                temp=rs.getString(1).trim();
                //System.out.println("chieu: "+temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TuyChon.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(temp.equals("cungchieu")) {
            cb1.setSelected(true);
        }
        else cb2.setSelected(true);
        rs=stm.executeQuery("SELECT soluongmay FROM TABLETLMNTUYCHON");
        while(rs.next()) {
            temp=rs.getString(1).trim();
            //System.out.println("chieu: "+temp);
        }
        switch (temp) {
            case "1" -> cb3.setSelected(true);
            case "2" -> cb4.setSelected(true);
            case "3" -> cb5.setSelected(true);
        }
        rs=stm.executeQuery("SELECT chedochoi FROM TABLETLMNTUYCHON");
        while(rs.next()) {
            temp=rs.getString(1).trim();
            //System.out.println("chieu: "+temp);
        }
        if(temp.equals("tapluyen")) {
            cb6.setSelected(true);
        } else if(temp.equals("thucchien")) {
            cb7.setSelected(true);
        }
        return panel;
    }
    int thoigiantre;
    public MouseAdapter mouseAdapterUserClickTangGiam= new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            if(e.getSource()==nuttang) {
                if(thoigiantre+1>10) return;
                thoigiantre++;
                textthoigiantre.setText("Thời gian máy ra quyết định: "+thoigiantre+" giây");
                try {
                    stm.executeUpdate("UPDATE TABLETLMNTUYCHON SET thoigiantremayrabai = "+(thoigiantre));
                } catch (SQLException ex) {
                    Logger.getLogger(TuyChon.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getSource()==nutgiam) {
                if(thoigiantre-1<1) return;
                thoigiantre--;
                textthoigiantre.setText("Thời gian máy ra quyết định: "+thoigiantre+" giây");
                try {
                    stm.executeUpdate("UPDATE TABLETLMNTUYCHON SET thoigiantremayrabai = "+(thoigiantre));
                } catch (SQLException ex) {
                    Logger.getLogger(TuyChon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
    public JLabel taoNutOKTab2() throws SQLException {
        ImageIcon Iconplay = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/ok.png")));
        nhanoktab2.setIcon(Iconplay);
        nhanoktab2.addMouseListener(mouseAdapterUserClickTab2);
        return nhanoktab2;
    }
    public MouseAdapter mouseAdapterUserClickTab2= new MouseAdapter(){
        public void mousePressed(MouseEvent e) {
            if(e.getSource()==nhanoktab2) {
                try {
                    SetOKCheDoChoi();
                    setOKSound();
                } catch (SQLException ignored) {

                }
                if(nhac_on) {
                    soundPlayer.stopSound(); // Dừng nhạc an toàn
                    nhac_on=false;
                }
                try {
                    Menu_Main  m = new Menu_Main();
                    setVisible(false);
                    m.setVisible(true);
                } catch (SQLException ignore) {

                }
            }
        }
    };
    public void SetOKCheDoChoi() throws SQLException {
        if(cb1.isSelected()) {
            String temp="UPDATE TABLETLMNTUYCHON SET chieucualuot = 'cungchieu'";
            try {
                stm.executeUpdate(temp);
            } catch (SQLException ex) {
                Logger.getLogger(TuyChon.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println("update chieu: "+temp);
        } else if(cb2.isSelected()) {
            String temp="UPDATE TABLETLMNTUYCHON SET chieucualuot = 'nguocchieu'";
            try {
                stm.executeUpdate(temp);
            } catch (SQLException ex) {
                Logger.getLogger(TuyChon.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println("update chieu: "+temp);
        }
        if(cb3.isSelected()) {
            String temp="UPDATE TABLETLMNTUYCHON SET soluongmay = '1'";
            stm.executeUpdate(temp);
        } else if(cb4.isSelected()) {
            String temp="UPDATE TABLETLMNTUYCHON SET soluongmay = '2'";
            stm.executeUpdate(temp);
        } else if(cb5.isSelected()) {
            String temp="UPDATE TABLETLMNTUYCHON SET soluongmay = '3'";
            stm.executeUpdate(temp);
        }
        if(cb6.isSelected()) {
            String temp="UPDATE TABLETLMNTUYCHON SET chedochoi = 'tapluyen'";
            stm.executeUpdate(temp);
        } else if(cb7.isSelected()) {
            String temp="UPDATE TABLETLMNTUYCHON SET chedochoi= 'thucchien'";
            stm.executeUpdate(temp);
            //System.out.println("update SL may "+temp);
        }
    }
    public void setOKSound() throws SQLException {
        if(jcb.getSelectedIndex()==0) {
            String temp="UPDATE TABLETLMNTUYCHON SET nhac= 'sound1'";
            stm.executeUpdate(temp);
        } else if(jcb.getSelectedIndex()==1) {
            String temp="UPDATE TABLETLMNTUYCHON SET nhac = 'sound2'";
            stm.executeUpdate(temp);
        } else if(jcb.getSelectedIndex()==2) {
            String temp="UPDATE TABLETLMNTUYCHON SET nhac = 'sound3'";
            stm.executeUpdate(temp);
        } else if(jcb.getSelectedIndex()==3) {
            String temp="UPDATE TABLETLMNTUYCHON SET nhac = 'sound4'";
            stm.executeUpdate(temp);
        } else if(jcb.getSelectedIndex()==4) {
            String temp="UPDATE TABLETLMNTUYCHON SET nhac = 'sound5'";
            stm.executeUpdate(temp);
        } else if(jcb.getSelectedIndex()==5) {
            String temp="UPDATE TABLETLMNTUYCHON SET nhac = 'sound6'";
            stm.executeUpdate(temp);
        }
    }
    public JPanel caiDatAmThanh() {
        try {
            JLabel text=new JLabel("Chọn nhạc nền trong game");
            text.setFont(new Font("Arial", Font.BOLD, 20));
            text.setBounds(200,50,500,30);
            jp3.add(text);
            jcb = new JComboBox<>();
            jcb.addItem("sound 1");
            jcb.addItem("sound 2");
            jcb.addItem("sound 3");
            jcb.addItem("sound 4");
            jcb.addItem("sound 5");
            jcb.addItem("sound 6");
            ResultSet rs;
            rs=stm.executeQuery("SELECT nhac FROM TABLETLMNTUYCHON");
            String temp="";
            while(rs.next()) {
                temp=rs.getString(1).trim();
            }
            switch (temp) {
                case "sound1" -> jcb.setSelectedIndex(0);
                case "sound2" -> jcb.setSelectedIndex(1);
                case "sound3" -> jcb.setSelectedIndex(2);
                case "sound4" -> jcb.setSelectedIndex(3);
                case "sound5" -> jcb.setSelectedIndex(4);
                case "sound6" -> jcb.setSelectedIndex(5);
            }
            jcb.setBounds(200, 150, 120, 30);
            jp3.add(jcb);
            jp3.add(taoNutTestNow());
            jp3.add(taoNutOKTab3());
            add(jp3);
            return jp3;
        } catch (SQLException ex) {
            Logger.getLogger(TuyChon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public JLabel taoNutTestNow() {
        ImageIcon Iconplay = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/testsound.png")));
        nhantestnow.setIcon(Iconplay);
        nhantestnow.setBounds(200, 250, Iconplay.getIconWidth(), Iconplay.getIconHeight());
        nhantestnow.addMouseListener(mouseAdapterUserClickTab3);
        return nhantestnow;
    }
    public JLabel taoNutOKTab3() {
        ImageIcon Iconplay = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/ok.png")));
        nhanoktab3.setIcon(Iconplay);
        nhanoktab3.setBounds(250, 250, Iconplay.getIconWidth(), Iconplay.getIconHeight());
        nhanoktab3.addMouseListener(mouseAdapterUserClickTab3);
        return nhanoktab3;
    }
    public void ganNhac() {
//        SoundPlayer soundPlayer = new SoundPlayer(); // Tạo đối tượng SoundPlayer
        if(jcb.getSelectedIndex()==0) nhactest="sound1";
        else if(jcb.getSelectedIndex()==1) nhactest="sound2";
        else if(jcb.getSelectedIndex()==2) nhactest="sound3";
        else if(jcb.getSelectedIndex()==3) nhactest="sound4";
        else if(jcb.getSelectedIndex()==4) nhactest="sound5";
        else if(jcb.getSelectedIndex()==5) nhactest="sound6";
        soundPlayer.stopSound();  // Dừng nhạc cũ trước khi phát nhạc mới
        soundPlayer.playSound(nhactest);
    }
    public MouseAdapter mouseAdapterUserClickTab3= new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            if(e.getSource()==nhanoktab3) {
                try {
                    if(nhac_on) {
                        soundPlayer.stopSound(); // Dừng nhạc an toàn
                        nhac_on=false;
                    }
                    SetOKCheDoChoi();
                    setOKSound();
                    Menu_Main m = new Menu_Main();
                    setVisible(false);
                    m.setVisible(true);
                }catch(Exception ignore) {
                }
            } else if(e.getSource() == nhantestnow) {
                if (nhac_on) {
                    if (jcb.getSelectedIndex() != indexnhactemp) {
                        soundPlayer.stopSound(); // Dừng nhạc an toàn
                        ganNhac(); // Gọi hàm phát nhạc mới
                    } else {
                        soundPlayer.stopSound(); // Dừng nhạc an toàn
                        nhac_on = false; // Tắt nhạc
                    }
                } else {
                    ganNhac(); // Phát nhạc
                    nhac_on = true; // Bật nhạc
                }
                indexnhactemp = jcb.getSelectedIndex(); // Cập nhật index
            }
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

                        // Kiểm tra hủy khi đang chơi nhạc
                        while (!isCancelled() && running) {
                            play.play(); // Phát nhạc một lần
                        }
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
    public JPanel copyRight() {
        JLabel text1=new JLabel("GAME CHƠI BÀI 52 LÁ");
        JLabel text2=new JLabel("Chào mừng mọi người đến với phòng chơi bài của team OOP.");
        JLabel text3=new JLabel("Tạo bởi: TEAM-OOP");
        text1.setFont(new Font("Arial", Font.BOLD, 24));
        text2.setFont(new Font("Arial", Font.PLAIN, 18));
        text3.setFont(new Font("Arial", Font.PLAIN, 24));
        text1.setForeground(Color.orange);
        text2.setForeground(Color.blue);
        text3.setForeground(Color.black);
        jp4.setLayout(null);
        text1.setBounds(50,50,500,30);
        text2.setBounds(50,100,5000,30);
        text3.setBounds(50,150,500,30);
        jp4.add(text1);
        jp4.add(text2);
        jp4.add(text3);
        add(jp4);
        return jp4;
    }
    public static void main(String[] args) throws SQLException {
        TuyChon a=new TuyChon();
        a.setVisible(true);
    }
}