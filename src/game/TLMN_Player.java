package game;

import java.util.ArrayList;

public class TLMN_Player extends TLMN {
 int solabaibotra;
 boolean coDapAn;
   int[] baibotra=new int[30];
   int solabaiplayerchon;
   String strbaitoichon;
   int[] labaiplayerchon=new int[30];
    public TLMN_Player(
            ArrayList<Card_Player> arrLbaibotra,
            ArrayList<Card_Player> arrLbaitoichon,
            ArrayList<CardBot> cacbobaicuatoi ) {
        int j=0;
        this.solabaiplayerchon=arrLbaitoichon.size();
        this.solabaibotra=arrLbaibotra.size();
        this.cardbot=cacbobaicuatoi;
        strbaitoichon="";
        for(int i=0;i<solabaiplayerchon;i++)
        {
            j++;
            labaiplayerchon[j]=arrLbaitoichon.get(i).sohieu;  
            strbaitoichon+=arrLbaitoichon.get(i).sohieu+"$";
        }
        j=0;
         for(int i=0;i<solabaibotra;i++)
        {
            j++;
            baibotra[j]=arrLbaibotra.get(i).sohieu;  
        }
    }
    public String nhanThongBaoTinhHopLeBaiDaChonCuaToi()
    {
       return timKieuBai(solabaiplayerchon,labaiplayerchon);
    }
    public boolean coDapAn()
    {
         luuDuLieuCacBoThoaManCoTinhHonSoVoi(baibotra,solabaibotra);
        return countdapanlienquan != 0;
    }
    public boolean chanDuocBaiBot()
    {
        luuDuLieuCacBoThoaManCoTinhHonSoVoi(baibotra,solabaibotra);
                 for(int i=1;i<=countdapanlienquan;i++)
                 {
                    // //System.out.println("--"+dapanlienquan[i]);
                   if(strbaitoichon.equals(dapanlienquan[i])) return true;
                 }
         return false;
    }
    public boolean baiChonChuaBaiNhoNhat(String bainhonhat)
    {
        if(strbaitoichon.equals("")) return false;
        String[] temp=strbaitoichon.split("\\$");
        return temp[0].equals(bainhonhat);
    }
    public boolean boBaiChuaBaiNhoNhat(String bainhonhat)
    {
        for(int i=0;i<cardbot.size();i++)
        {
            if(cardbot.get(i).sumsohieu==Integer.parseInt(bainhonhat))
            {
                return true;
            }
        }
        return false;
    }
 
}