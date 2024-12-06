package game;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class Menu_Main extends JFrame {
    ConnectDataBaseTLMN_NetBeans kn = new ConnectDataBaseTLMN_NetBeans();
    Connection cn = kn.getConnectdatabase();
    Statement stm = cn.createStatement();
    JLabel nhanchoingay = new JLabel();
    JLabel nhanhuongdan = new JLabel();
    JLabel nhantuychon = new JLabel();
    JLabel nhanthoat = new JLabel();

    public Menu_Main() throws SQLException {
        taoCuaSo();
        taoNutChoiNgay();
        taoNutHuongDan();
        taoNutTuyChon();
        taoNutThoat();
    }
    public void taoCuaSo() {
        setResizable(true);
        setTitle("Game chơi bài 52 lá");
        setBounds(50, 0, 650, 697);
        setLocation(300, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL url = Menu_Main.class.getResource("/ima_TLMN/bg_menu.png");
        assert url != null;
//        ImageIcon icon = new ImageIcon(url);
        JPanel panel = new BackgroundGame(new ImageIcon(url).getImage());
        setContentPane(panel);
        panel.setLayout(null);
    }
    public void taoNutChoiNgay() {
        URL url = Menu_Main.class.getResource("/ima_TLMN/choingay.png");
        assert url != null;
        ImageIcon icon = new ImageIcon(url);
        nhanchoingay.setIcon(icon);
        nhanchoingay.setBounds(270, 430, icon.getIconWidth(), icon.getIconHeight());
        add(nhanchoingay);
        nhanchoingay.addMouseListener(mouseAdapterUserChonOptionMenu);
    }
    public void taoNutHuongDan() {
        URL url = Menu_Main.class.getResource("/ima_TLMN/huongdan.png");
        assert url != null;
        ImageIcon icon = new ImageIcon(url);
        nhanhuongdan.setIcon(icon);
        nhanhuongdan.setBounds(270, 500, icon.getIconWidth(), icon.getIconHeight());
        add(nhanhuongdan);
        nhanhuongdan.addMouseListener(mouseAdapterUserChonOptionMenu);
    }

    public void taoNutTuyChon() {
        URL url = Menu_Main.class.getResource("/ima_TLMN/tuychon.png");
        assert url != null;
        ImageIcon icon = new ImageIcon(url);
        nhantuychon.setIcon(icon);
        nhantuychon.setBounds(400, 430, icon.getIconWidth(), icon.getIconHeight());
        add(nhantuychon);
        nhantuychon.addMouseListener(mouseAdapterUserChonOptionMenu);
    }

    public void taoNutThoat() {
        URL url = Menu_Main.class.getResource("/ima_TLMN/thoat.png");
        assert url != null;
        ImageIcon icon = new ImageIcon(url);
        nhanthoat.setIcon(icon);
        nhanthoat.setBounds(400, 500, icon.getIconWidth(), icon.getIconHeight());
        add(nhanthoat);
        nhanthoat.addMouseListener(mouseAdapterUserChonOptionMenu);
    }

    public MouseAdapter mouseAdapterUserChonOptionMenu = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == nhanchoingay) {
                setVisible(false);
                try {
                    String temp2 = "UPDATE OTHER SET SOUND = 'on'";
                    stm.executeUpdate(temp2);
                    ResultSet rs;
                    String[] temp = new String[30];

                    rs = stm.executeQuery("SELECT avatar FROM TABLETLMNTUYCHON");
                    while (rs.next()) {
                        temp[1] = rs.getString(1).trim();
                    }
                    rs = stm.executeQuery("SELECT chieucualuot FROM TABLETLMNTUYCHON");
                    while (rs.next()) {
                        temp[2] = rs.getString(1).trim();
                    }
                    rs = stm.executeQuery("SELECT soluongmay FROM TABLETLMNTUYCHON");
                    while (rs.next()) {
                        temp[3] = rs.getString(1).trim();
                    }
                    rs = stm.executeQuery("SELECT chedochoi FROM TABLETLMNTUYCHON");
                    while (rs.next()) {
                        temp[4] = rs.getString(1).trim();
                    }
                    rs = stm.executeQuery("SELECT nhac FROM TABLETLMNTUYCHON");
                    while (rs.next()) {
                        temp[5] = rs.getString(1).trim();
                    }
                    rs = stm.executeQuery("SELECT thoigiantremayrabai FROM TABLETLMNTUYCHON");
                    while (rs.next()) {
                        temp[6] = rs.getString(1).trim();
                    }

                    setVisible(false);
                    InGame frame = new InGame(temp[1], temp[2],
                            Integer.parseInt(temp[3]), temp[4], temp[5], Integer.parseInt(temp[6]));
                    frame.setVisible(true);
                } catch (SQLException ex) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "An SQL exception occurred", ex); // Log lỗi thay vì in stack trace
                }

            } else if (e.getSource() == nhanthoat) {
                System.exit(WIDTH);
            } else if (e.getSource() == nhanhuongdan) {
                setVisible(false);
                HuongDan n = new HuongDan();
                n.m();
            } else if (e.getSource() == nhantuychon) {
                setVisible(false);
                TuyChon n;
                try {
                    n = new TuyChon();
                    n.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Menu_Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Menu_Main frame = new Menu_Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "An SQL exception occurred", e); // Log lỗi thay vì in stack trace
                }

            }
        });
    }
}
