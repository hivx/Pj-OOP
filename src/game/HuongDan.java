package game;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.SQLException;
import javax.swing.*;
public class HuongDan extends JFrame {
    JPanel card1;
    JPanel card2;
    JPanel card3;
    JPanel card4;
    JPanel card5;
    JFrame frame;
    public void m() {
        final JPanel cards; //a panel that uses CardLayout
        final String FIRST = "FIRST";
        final String NEXT = "NEXT";
        final String PREVIOUS = "PREVIOUS";
        final String LAST = "LAST";
        final String BACK = "BACK";
        frame = new JFrame("CardLayout Demo");
        URL url = HuongDan.class.getResource("/ima_TLMN/luatgame1.png");
        assert url != null;
        card1=new BackgroundGame(new ImageIcon(url).getImage());
        setContentPane(card1);
         
        url = HuongDan.class.getResource("/ima_TLMN/luatgame2.png");
        assert url != null;
        card2=new BackgroundGame(new ImageIcon(url).getImage());
        setContentPane(card2);
         
        url = HuongDan.class.getResource("/ima_TLMN/luatgame3.png");
        assert url != null;
        card3=new BackgroundGame(new ImageIcon(url).getImage());
        setContentPane(card3);
         
        url = HuongDan.class.getResource("/ima_TLMN/luatgame4.png");
        assert url != null;
        card4=new BackgroundGame(new ImageIcon(url).getImage());
        setContentPane(card4);
         
        url = HuongDan.class.getResource("/ima_TLMN/luatgame5.png");
        assert url != null;
        card5=new BackgroundGame(new ImageIcon(url).getImage());
        setContentPane(card5);
      
        cards = new JPanel(new CardLayout());
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        class ControlActionListenter implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                String cmd = e.getActionCommand();
                switch (cmd) {
                    case FIRST -> cl.first(cards);
                    case NEXT -> cl.next(cards);
                    case PREVIOUS -> cl.previous(cards);
                    case LAST -> cl.last(cards);
                    case BACK -> {
                        try {
                            Menu_Main m = new Menu_Main();
                            m.setVisible(true);
                        } catch (SQLException ignored) {
                        }
                        frame.setVisible(false);
                    }
                }
            }
        }
        ControlActionListenter cal = new ControlActionListenter();

        JButton btn1 = new JButton("Trang đầu");
        btn1.setActionCommand(FIRST);
        btn1.addActionListener(cal);

        JButton btn3 = new JButton("Trang trước");
        btn3.setActionCommand(PREVIOUS);
        btn3.addActionListener(cal);

        JButton btn2 = new JButton("Trang sau");
        btn2.setActionCommand(NEXT);
        btn2.addActionListener(cal);

        JButton btn4 = new JButton("Trang cuối");
        btn4.setActionCommand(LAST);
        btn4.addActionListener(cal);
        
        JButton btn5 = new JButton("Quay lại menu");
        btn5.setActionCommand(BACK);
        btn5.addActionListener(cal);

        JPanel controlButtons = new JPanel();
        controlButtons.add(btn1);
        controlButtons.add(btn3);
        controlButtons.add(btn2);
        controlButtons.add(btn4);
        controlButtons.add(btn5);

        Container pane = frame.getContentPane();
        pane.add(cards, BorderLayout.CENTER);
        pane.add(controlButtons, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 650);
        frame.setLocation(240, 30);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
      
    }
}