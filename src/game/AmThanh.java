package game;
import java.awt.Frame;
import java.io.FileInputStream;
import javax.sound.sampled.Clip;
import javax.swing.JLayer;
import javax.swing.JOptionPane;
import javazoom.jl.player.Player;

public class AmThanh{
    static Thread chaynhac;
public void playSound() {
chaynhac = new Thread(new Runnable() {
@Override
public void run() {
// TODO Auto-generated method stub
try{
} catch(Exception e){
System.out.println(e);
}
// còn đây là code phát nhạc
}
});

}
public static void main(String[] args) {
  

	
	Frame frame=new Frame();

//tùy chỉnh văn bản cho nút lệnh
Object[] options = {"OK, Tiếp Tục, please",
"No, Thoát Game","Cancel"};
int n = JOptionPane.showOptionDialog(frame,
"TRÒ CHƠI KẾT THÚC BẠN THẮNG, BẠN CÓ MUỐN TIẾP TỤC ","A Silly Question",
JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[2]);

}
}