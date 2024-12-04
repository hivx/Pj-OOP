



package game;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.Timer;


public class DieuKhienBatDauVaKetThucGame_ver_end {

    int SONGUOICHOI,iwin,isTurn,bodem=3;
     JLabel timechuanbivaogame=new JLabel("3");
    Player_ver_end2 player2,player3,player4;
    Player_ver_end player;
    Timer time;
    GT_TLMN_PhanNhomBai_ver_end gtpnpl,gtpnbot1,gtpnbot2,gtpnbot3;
    String bainhonhat,dapantoitrang,thoigianhethongbatdaugame,toitrangne;
     int[] soluonglaconlaicuaActor;
     JLabel thongbaochuanbivaogame=new JLabel();
     
       ConnectDataBaseTLMN_NetBeans kn=new ConnectDataBaseTLMN_NetBeans();
        Connection cn=kn.getConnectdatabase();
             Statement stm=cn.createStatement(); 
    public boolean nhac_on,istoitrang;
    /*public DieuKhienBatDauVaKetThucGame_ver_end(int SONGUOICHOI,
            Player_ver_end player,Bot_ver_end bot1,Bot_ver_end bot2,Bot_ver_end bot3) throws SQLException{
        this.SONGUOICHOI=SONGUOICHOI;
        this.bot1=bot1;
        this.bot2=bot2;
        this.bot3=bot3;
        this.player=player;
          gtpnpl= new GT_TLMN_PhanNhomBai_ver_end(player.mycard);
           gtpnpl.xayDungDataCacLoaiBoBaiChoBot();
           gtpnbot1= new GT_TLMN_PhanNhomBai_ver_end(bot1.mycard);
           gtpnbot1.xayDungDataCacLoaiBoBaiChoBot();
           gtpnbot2= new GT_TLMN_PhanNhomBai_ver_end(bot2.mycard);
           gtpnbot2.xayDungDataCacLoaiBoBaiChoBot();
           gtpnbot3= new GT_TLMN_PhanNhomBai_ver_end(bot3.mycard);
           gtpnbot3.xayDungDataCacLoaiBoBaiChoBot();
    }*/
    public DieuKhienBatDauVaKetThucGame_ver_end(int SONGUOICHOI, Player_ver_end player,Player_ver_end2 player2,Player_ver_end2 player3,Player_ver_end2 player4) throws SQLException{
        this.SONGUOICHOI=SONGUOICHOI;
        this.player2=player2;
        this.player3=player3;
        this.player4=player4;
        this.player=player;
        gtpnpl= new GT_TLMN_PhanNhomBai_ver_end(player.mycard);
        gtpnpl.xayDungDataCacLoaiBoBaiChoBot();
        gtpnbot1= new GT_TLMN_PhanNhomBai_ver_end(player2.mycard);
        gtpnbot1.xayDungDataCacLoaiBoBaiChoBot();
        gtpnbot2= new GT_TLMN_PhanNhomBai_ver_end(player3.mycard);
        gtpnbot2.xayDungDataCacLoaiBoBaiChoBot();
        gtpnbot3= new GT_TLMN_PhanNhomBai_ver_end(player4.mycard);
        gtpnbot3.xayDungDataCacLoaiBoBaiChoBot();
    }
     public void thongBaoChuanBi()
    {
         thongbaochuanbivaogame.setFont(new Font("Arial", Font.BOLD, 28));//Kiểu cỡ chữ thời gian
      thongbaochuanbivaogame.setForeground(Color.getHSBColor((float) 0.5, (float)0.9, (float) 0.6));
      thongbaochuanbivaogame.setBounds(400, 300, 200, 50);
       thongbaochuanbivaogame.setText("Chuẩn bị");
       timechuanbivaogame.setVisible(true);
    }
     public void timeChuanBi()
    {
         timechuanbivaogame.setFont(new Font("Arial", Font.BOLD, 28));//Kiểu cỡ chữ thời gian
      timechuanbivaogame.setForeground(Color.getHSBColor((float) 0.5, (float)0.9, (float) 0.6));
      timechuanbivaogame.setBounds(550, 300, 200, 50);
      timechuanbivaogame.setVisible(true);
    }
    public int timNguoiCoBaiNhoNhat()
    {
        if(SONGUOICHOI==4)
        {
        String temp1[]=player.listBaiDuocChia.split("\\$");
        String temp2[]=player2.listBaiDuocChia.split("\\$");
        String temp3[]=player3.listBaiDuocChia.split("\\$");
         String temp4[]=player4.listBaiDuocChia.split("\\$");
         int min=1000;
       int tempmin[]={Integer.parseInt(temp1[0]),Integer.parseInt(temp2[0]),
             Integer.parseInt(temp3[0]),Integer.parseInt(temp4[0])};
       for(int i=0;i<4;i++)
       {
           if(tempmin[i]<min)
           {
               min=tempmin[i];
           }
       }
       bainhonhat=String.valueOf(min);
            //System.out.println("MIN"+min);
            if(temp1[0].equals(String.valueOf(min)))
            {
                if(min==1)
                player.nhanthongbao3bichditruoc.setVisible(true);
                else  player.nhanthongbaobainhoditruoc.setVisible(true);
                return 1;
            }
           
           else if(temp2[0].equals(String.valueOf(min)))
            {
                if(min==1)
                player2.nhanthongbao3bichditruoc.setVisible(true);
                else  player2.nhanthongbaobainhoditruoc.setVisible(true);
                return 2;
            }
          
           else if(temp3[0].equals(String.valueOf(min)))
            {
               if(min==1)
                player3.nhanthongbao3bichditruoc.setVisible(true);
                else  player3.nhanthongbaobainhoditruoc.setVisible(true);
                return 3;
            }
        
           else if(temp4[0].equals(String.valueOf(min)))
            {
               if(min==1)
                player4.nhanthongbao3bichditruoc.setVisible(true);
                else  player4.nhanthongbaobainhoditruoc.setVisible(true);
                return 4;
            }
        }
        else if(SONGUOICHOI==3)
        {
       String temp1[]=player.listBaiDuocChia.split("\\$");
        String temp2[]=player2.listBaiDuocChia.split("\\$");
        String temp3[]=player3.listBaiDuocChia.split("\\$");
         int min=1000;
       int tempmin[]={Integer.parseInt(temp1[0]),Integer.parseInt(temp2[0]),
             Integer.parseInt(temp3[0])};
       
       for(int i=0;i<3;i++)
       {
           if(tempmin[i]<min)
           {
               min=tempmin[i];
           }
       }
       bainhonhat=String.valueOf(min);
            if(temp1[0].equals(String.valueOf(min)))
            {
               if(min==1)
                player.nhanthongbao3bichditruoc.setVisible(true);
                else  player.nhanthongbaobainhoditruoc.setVisible(true);
                return 1;
            }
           
           else if(temp2[0].equals(String.valueOf(min)))
            {
                if(min==1)
                player2.nhanthongbao3bichditruoc.setVisible(true);
                else  player2.nhanthongbaobainhoditruoc.setVisible(true);
                return 2;
            }
          
           else if(temp3[0].equals(String.valueOf(min)))
            {
                if(min==1)
                player3.nhanthongbao3bichditruoc.setVisible(true);
                else  player3.nhanthongbaobainhoditruoc.setVisible(true);
                return 3;
            }
        
          
        }
        else if(SONGUOICHOI==2)
        {
        String temp1[]=player.listBaiDuocChia.split("\\$");
        String temp2[]=player2.listBaiDuocChia.split("\\$");
   
         int min=1000;
       int tempmin[]={Integer.parseInt(temp1[0]),Integer.parseInt(temp2[0]),
           };
       for(int i=0;i<2;i++)
       {
           if(tempmin[i]<min)
           {
               min=tempmin[i];
           }
       }
       bainhonhat=String.valueOf(min);
            if(temp1[0].equals(String.valueOf(min)))
            {
                if(min==1)
                player.nhanthongbao3bichditruoc.setVisible(true);
                else  player.nhanthongbaobainhoditruoc.setVisible(true);
                return 1;
            }
           
           else if(temp2[0].equals(String.valueOf(min)))
            {
               if(min==1)
                player2.nhanthongbao3bichditruoc.setVisible(true);
                else  player2.nhanthongbaobainhoditruoc.setVisible(true);
                return 2;
            }
        }
        return 0;
    }
    public void xuLyKetThucGame() throws SQLException  
    {
        String[] eleCOT=new String[4];
        eleCOT[0]=null;
        int count=0;
        if(SONGUOICHOI==2)
         {
   for(int i=player2.mycard.size()-1;i>=0;i--)
  {int j=player2.mycard.get(i).vitri;player2.nhanmycard[j].setVisible(true);}
         }
       else if(SONGUOICHOI==3)
        {
for(int i=player2.mycard.size()-1;i>=0;i--)
{int j=player2.mycard.get(i).vitri;player2.nhanmycard[j].setVisible(true);}
for(int i=0;i<player3.mycard.size();i++)
{int j=player3.mycard.get(i).vitri;player3.nhanmycard[j].setVisible(true);}
        }
       else if(SONGUOICHOI==4)
        {
for(int i=player2.mycard.size()-1;i>=0;i--)
{int j=player2.mycard.get(i).vitri;player2.nhanmycard[j].setVisible(true);}
for(int i=0;i<player3.mycard.size();i++)
{int j=player3.mycard.get(i).vitri;player3.nhanmycard[j].setVisible(true);}
for(int i=player4.mycard.size()-1;i>=0;i--)
{int j=player4.mycard.get(i).vitri;player4.nhanmycard[j].setVisible(true);}}
          player.tatOptionPlayer();
          if(iwin==1) player.winner.setVisible(true);
          else if(iwin==2)  player2.winner.setVisible(true);
           else if(iwin==3)  player3.winner.setVisible(true);
           else if(iwin==4)  player4.winner.setVisible(true);
              if(istoitrang==false)
              {
          if(isTurn==1)
          {
              
           player.time.stop();
          }
            if(isTurn==2)
          player2.time.stop();
             if(isTurn==3)
          player3.time.stop();
              if(isTurn==4)
          player4.time.stop();
          }
              time.stop();
    }
    
     public int indexActorKetThucLuonDoToiTrang()
     {
         gtpnpl.indexactor=1;
         gtpnbot1.indexactor=2;
         gtpnbot2.indexactor=3;
         gtpnbot3.indexactor=4;
   int index[]={tuQuy3(),baDoiThongChua3Bich(),tuQuy2(),sanhRong(),lucPheBon(),dongHoa()};
         for(int i=0;i<6;i++)
         {
        if(index[i]!=0) return index[i];
         }
        return 0;
     }
   public int tuQuy3()
   {
        if(gtpnpl.baiCoTuQuy3()==true) 
   {
       player.baitoitrang=gtpnpl.dapantoitrang;
       player.toitrang=true;
       player.xuLyRaBai(player);
       
       return 1;
   }
   else if(gtpnbot1.baiCoTuQuy3()==true) 
   {
      
       dapantoitrang=gtpnbot1.dapantoitrang;
       return 2;
   }
   else if(gtpnbot2.baiCoTuQuy3()==true) 
   {
      dapantoitrang=gtpnbot2.dapantoitrang;
       return 3;
   }
   else if(gtpnbot3.baiCoTuQuy3()==true) 
   {
       dapantoitrang=gtpnbot3.dapantoitrang;
       return 4;
   }
        return 0;
   }
   public void luuTrangThaiNhac(boolean nhacon) throws SQLException
   {
       
 
       if(nhacon==true)
       {
  String temp="UPDATE OTHER SET SOUND = 'on'";
             stm.executeUpdate(temp);
       }
       else
       {
  String temp="UPDATE OTHER SET SOUND = 'off'";
             stm.executeUpdate(temp);
       }
   }
   public void DieuKhienTrangThaiNhacDauVanGame() throws SQLException
   {
        ResultSet rs=stm.executeQuery("SELECT SOUND FROM OTHER");
   String trangthainhac = "";
    while(rs.next())
        {
            trangthainhac=rs.getString(1);
        }
    if(trangthainhac.equals("on")) 
               {
                  player.nhansoundon.setVisible(false);player.nhansoundoff.setVisible(true);
               }
         else  {player.nhansoundoff.setVisible(false);player.nhansoundon.setVisible(true);}
        
    }
    public boolean nhacDangBat() throws SQLException
   {
        ResultSet rs=stm.executeQuery("SELECT SOUND FROM OTHER");
   String trangthainhac = "";
    while(rs.next())
        {
            trangthainhac=rs.getString(1);
        }
    if(trangthainhac.equals("on")) 
               {
               return true;
               }
        return false;
         
        
    }

   public int tuQuy2()
   {
       if(gtpnpl.baiCoTuQuy2()==true) 
   {
       player.baitoitrang=gtpnpl.dapantoitrang;
       player.toitrang=true;
       player.xuLyRaBai(player);
       return 1;
   }
   else if(gtpnbot1.baiCoTuQuy2()==true) 
   {
       dapantoitrang=gtpnbot1.dapantoitrang;
       return 2;
   }
   else if(gtpnbot2.baiCoTuQuy2()==true) 
   {
       dapantoitrang=gtpnbot2.dapantoitrang;
       return 3;
   }
   else if(gtpnbot3.baiCoTuQuy2()==true) 
   {
       dapantoitrang=gtpnbot3.dapantoitrang;
       return 4;
   }
        return 0;
   }
   public int baDoiThongChua3Bich()
   {
       if(gtpnpl.baiCo3DoiThongChua3Bich()==true)
   {
       player.baitoitrang=gtpnpl.dapantoitrang;
       player.toitrang=true;
       player.xuLyRaBai(player);
       return 1;
   }
         else if(gtpnbot1.baiCo3DoiThongChua3Bich()==true)
             {
       dapantoitrang=gtpnbot1.dapantoitrang;
       return 2;
            }
          else if(gtpnbot2.baiCo3DoiThongChua3Bich()==true)
            {
       dapantoitrang=gtpnbot2.dapantoitrang;
       return 3;
          }
          else if(gtpnbot3.baiCo3DoiThongChua3Bich()==true)
        {
       dapantoitrang=gtpnbot3.dapantoitrang;
       return 4;
         }
        return 0;
   }
   public int sanhRong()
   {
       if(gtpnpl.baiCoSanhRong()==true)
   {
       player.baitoitrang=gtpnpl.dapantoitrang;
       player.toitrang=true;
       player.xuLyRaBai(player);
       return 1;
   }
         else if(gtpnbot1.baiCoSanhRong()==true)
             {
       dapantoitrang=gtpnbot1.dapantoitrang;
       return 2;
            }
          else if(gtpnbot2.baiCoSanhRong()==true)
            {
       dapantoitrang=gtpnbot2.dapantoitrang;
       return 3;
          }
          else if(gtpnbot3.baiCoSanhRong()==true)
        {
       dapantoitrang=gtpnbot3.dapantoitrang;
       return 4;
         }
        return 0;
   }
   public int dongHoa()
   {
       if(gtpnpl.baiCoDongHoa()==true)
   {
       player.baitoitrang=gtpnpl.dapantoitrang;
       player.toitrang=true;
       player.xuLyRaBai(player);
       return 1;
   }
         else if(gtpnbot1.baiCoDongHoa()==true)
             {
                
       dapantoitrang=gtpnbot1.dapantoitrang;
       return 2;
            }
          else if(gtpnbot2.baiCoDongHoa()==true)
            {
       dapantoitrang=gtpnbot2.dapantoitrang;
       return 3;
          }
          else if(gtpnbot3.baiCoDongHoa()==true)
        {
       dapantoitrang=gtpnbot3.dapantoitrang;
       return 4;
         }
        return 0;
   }
   public int lucPheBon()
   {
       if(gtpnpl.baiCoLucPheBon()==true)
   {
       player.baitoitrang=gtpnpl.dapantoitrang;
       player.toitrang=true;
       player.xuLyRaBai(player);
       return 1;
   }
         else if(gtpnbot1.baiCoLucPheBon()==true)
             {
       dapantoitrang=gtpnbot1.dapantoitrang;
       return 2;
            }
          else if(gtpnbot2.baiCoLucPheBon()==true)
            {
       dapantoitrang=gtpnbot2.dapantoitrang;
       return 3;
          }
          else if(gtpnbot3.baiCoLucPheBon()==true)
        {
       dapantoitrang=gtpnbot3.dapantoitrang;
       return 4;
         }
        return 0;
   }
   public void luuLichSuDau(String thoigianhethongbatdaugame,
           int SONGUOICHOI,String toitrangne,String chisoavatarLOL,int iwin) throws SQLException
   {
       //Đếm số bản ghi
       ResultSet rs=null;
       rs = stm.executeQuery("select count(*) as total  from tablelichsudau");
      int stt=0;
       while(rs.next())
       {
           stt=rs.getInt(1);
       }
        stt++;
         String temp="";
         if(iwin==1) 
         {
        temp="insert into tablelichsudau values("+
        stt+",'"+thoigianhethongbatdaugame+"',"
       +SONGUOICHOI+",'"+toitrangne+"',"+"'You'"+")";
         }
         else 
         {
          temp="insert into tablelichsudau values("+
        stt+",'"+thoigianhethongbatdaugame+"',"
       +SONGUOICHOI+",'"+toitrangne+"',"+"'"+getNameTuongLol(Integer.parseInt(chisoavatarLOL))+"')";

         }
          stm.executeUpdate(temp);
   }
   public String getNameTuongLol(int chisoavatarLOL)
   {
       String[] k={null,"aphelios","lulu","taric","yasuo"
               ,"talon","amumu","alistar","annie","anivia"};
       int i;
       for(i=1;i<=9;i++)
       {
           if(chisoavatarLOL==i) return k[i];
       }
        return null;
       
   }
   
  
}