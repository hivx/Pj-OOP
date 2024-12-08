package game;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NaiveBayes_player_tranningData_ditruoc {
Player player;
String thongbaokieubairahople;
    ArrayList danhsachkieubaixacsuat=new ArrayList();
    private final int SO_COT=13;
     boolean NOT_LEARNED;
    public NaiveBayes_player_tranningData_ditruoc(Player player) {
        this.player=player;
    }
    public void run() throws IOException
    {
        
           String[] eleCot =new String[30];
        PhanNhomBai gtbot=new PhanNhomBai(this.player.mycard);
        gtbot.xayDungDataCacLoaiBoBaiChoBot();
            int sumMyCard=0,dodaisanhmax=0,tong2vaA=0,tonglabaiovitridacbiettoptren=0,countDB=0
         ,sumTuyenTren=0,sumTuyenDuoi=0,countDOIdoclap = 0,countALLDOI=0,countBACONdoclap = 0,
          countBaiLe=0,countDoi=0,countSanh=0,count3con=0,countTuQuy=0,
    countBaDoiThong=0,countBonDoiThong=0,sumDoiMax = 0,sumDoiMin=0,
           sumSanhMax = 0,sumSanhMin=0,sumBaCon=0;
            int tongsolaplayer=player.mycard.size();
            if(tongsolaplayer==13||tongsolaplayer<=2) return;//bỏ qua 13 lá
        for(int i=0;i<tongsolaplayer;i++)
        {
            int j=i+1;
  if(gtbot.vitrilabaiLQtoidoi[j].equals("d")&&
     gtbot.vitrilabaiLQtoibacon[j].equals("x")&&
     gtbot.vitrilabaiLQtoituquy[j].equals("x")&&
     gtbot.vitrilabaiLQtoibadoithong[j].equals("x")&&
     gtbot.vitrilabaiLQtoibondoithong[j].equals("x")&&
     gtbot.vitrilabaiLQtoisanh[j].equals("x"))
                 countDOIdoclap++;
   if(gtbot.vitrilabaiLQtoibacon[j].equals("bc")&&
     gtbot.vitrilabaiLQtoituquy[j].equals("x")&&
     gtbot.vitrilabaiLQtoibadoithong[j].equals("x")&&
     gtbot.vitrilabaiLQtoibondoithong[j].equals("x")
            )
                 countBACONdoclap++;
           
    if(gtbot.vitrilabaidacbietsanhdoi[j].equals("dbsanhdoi"))
         {
            countDB++;    
         }
    if(i<lamTronLen((float)player.mycard.size()/4)) sumTuyenDuoi+=player.mycard.get(i).sohieu;
    if(i>=player.mycard.size()-lamTronLen((float)player.mycard.size()/4)) sumTuyenTren+=player.mycard.get(i).sohieu;
        }
        // //////System.out.println("Số đôi độc lập: "+countDOIdoclap/2);
        // //////System.out.println("Số 3 con độc lập: "+countBACONdoclap/3);
    
  
      
        for(int i=0;i<gtbot.cardbot.size();i++)
        {
           // //////System.out.println(""+gtbot.cardbot.get(i).daycard);
            if(gtbot.cardbot.get(i).loaibai.equals("baile")) countBaiLe++;
            if(gtbot.cardbot.get(i).loaibai.equals("doi")) countALLDOI++;
                if(gtbot.cardbot.get(i).loaibai.equals("tuquy")) countTuQuy++;
                 if(gtbot.cardbot.get(i).loaibai.equals("badoithong")) countBaDoiThong++;
                  if(gtbot.cardbot.get(i).loaibai.equals("bondoithong")) countBonDoiThong++;
                  if(gtbot.cardbot.get(i).loaibai.equals("bacon")) 
                  {
                      sumBaCon+=gtbot.cardbot.get(i).sumsohieu;
                  }
       if(gtbot.cardbot.get(i).loaibai.equals("sanh")&& gtbot.cardbot.get(i).sola>dodaisanhmax)
            {
               dodaisanhmax=gtbot.cardbot.get(i).sola;
            }
    if(gtbot.cardbot.get(i).loaibai.equals("doi")&& gtbot.cardbot.get(i).sumsohieu>sumDoiMax)
            {
               sumDoiMax=gtbot.cardbot.get(i).sumsohieu;
            }
    if(gtbot.cardbot.get(i).loaibai.equals("sanh")&& gtbot.cardbot.get(i).sumsohieu>sumSanhMax)
            {
               sumSanhMax=gtbot.cardbot.get(i).sumsohieu;
            }
        }
         ////////System.out.println("SUM 3 con "+sumBaCon);
        eleCot[1]=String.valueOf(countDB);
       eleCot[2]=String.valueOf(countBaiLe);
        
       eleCot[3]=String.valueOf(dodaisanhmax);
      eleCot[4]=String.valueOf(countDOIdoclap/2);
      eleCot[5]=String.valueOf(countALLDOI);
      eleCot[6]=String.valueOf(countBACONdoclap/3);
      if(sumBaCon<=50) eleCot[7]="0-50";
      else if(sumBaCon>=51&&sumBaCon<=100) eleCot[7]="51-100";
      else if(sumBaCon>=101&&sumBaCon<=150) eleCot[7]="101-150";
      else if(sumBaCon>=151&&sumBaCon<=200) eleCot[7]="151-200";
        else if(sumBaCon>=201) eleCot[7]=">=201";
         sumDoiMin=sumDoiMax;
         sumSanhMin=sumSanhMax;
        for(int i=0;i<gtbot.cardbot.size();i++)
        {
            if(gtbot.cardbot.get(i).loaibai.equals("doi")&& gtbot.cardbot.get(i).sumsohieu<sumDoiMin)
            {
               sumDoiMin=gtbot.cardbot.get(i).sumsohieu;
            }
            if(gtbot.cardbot.get(i).loaibai.equals("sanh")&& gtbot.cardbot.get(i).sumsohieu<sumSanhMin)
            {
               sumSanhMin=gtbot.cardbot.get(i).sumsohieu;
            }
        }
          
          if(count3con!=0&&countTuQuy!=0) count3con=0;
           if(countTuQuy==0&&countBaDoiThong==0&&countBonDoiThong==0) {eleCot[8]="ko";}
 else eleCot[8]="co";

           int lechtuyendaucuoi=sumTuyenTren-sumTuyenDuoi;
           /*System.out.println("Lệch trên  "+sumTuyenTren);
           System.out.println("Lệch dưới  "+sumTuyenDuoi);
            System.out.println("Lệch trên dưới "+lechtuyendaucuoi);*/
          if(lechtuyendaucuoi<=50)  eleCot[9]="0-50";
          else if(lechtuyendaucuoi>=51&&lechtuyendaucuoi<=100)  eleCot[9]="50-100";
           else if(lechtuyendaucuoi>=101&&lechtuyendaucuoi<=150)  eleCot[9]="101-150";
          else if(lechtuyendaucuoi>=151&&lechtuyendaucuoi<=200)  eleCot[9]="151-200";
           int lechdoi=sumDoiMax-sumDoiMin;
            /*System.out.println("Lệch đôi max  "+sumDoiMax);
           System.out.println("Lệch đôi min  "+sumDoiMin);
           System.out.println("Lệch đôi "+lechdoi);*/
          if(lechdoi<=10)  eleCot[10]="0-10";
          else if(lechdoi>=10&&lechdoi<=20)  eleCot[10]="11-20";
           else if(lechdoi>=21&&lechdoi<=30)  eleCot[10]="21-30";
          else if(lechdoi>=31&&lechdoi<=40)  eleCot[10]="31-40";
          else if(lechdoi>=41&&lechdoi<=50)  eleCot[10]="41-50";
           else if(lechdoi>=51&&lechdoi<=60)  eleCot[10]="51-60";
          else if(lechdoi>=61&&lechdoi<=70)   eleCot[10]="61-70";
           else if(lechdoi>=71&&lechdoi<=80)    eleCot[10]="71-80";
           else if(lechdoi>=81&&lechdoi<=90)     eleCot[10]="81-90";
           else if(lechdoi>=91&&lechdoi<=100)  eleCot[10]="91-100";
           int lechsanh =sumSanhMax-sumSanhMin;
         /*   System.out.println("sảnh max "+sumSanhMax);
             System.out.println("sảnh min"+sumSanhMin);
           System.out.println("Lệch sảnh "+lechsanh);*/
         if(lechsanh<=50)  eleCot[11]="0-50";
          else if(lechsanh>=51&&lechsanh<=100)  eleCot[11]="51-100";
           else if(lechsanh>=101&&lechsanh<=150)  eleCot[11]="101-150";
          else if(lechsanh>=151&&lechsanh<=200)  eleCot[11]="151-200";
          else if(lechsanh>=201&&lechsanh<=250)  eleCot[11]="201-250";
         else if(lechsanh>=251&&lechsanh<=250)  eleCot[11]="251-300";
        
        eleCot[12]=String.valueOf(player.mycard.size());
     
         eleCot[13]=player.KQ_PlayerTranning;
        /* String strKQ=eleCot[1]+"\t\t"+eleCot[2]+"\t\t"+eleCot[3]+"\t\t"+eleCot[4]
         +"\t\t"+eleCot[5]+"\t\t"+eleCot[6]+"\t\t"+eleCot[7]+"\t\t"
         +eleCot[8]+"\t\t"+eleCot[9]+"\t\t"+eleCot[10]+"\t\t"+eleCot[11]+"\t\t"+eleCot[12]+"\t\t"+eleCot[13];
         System.out.println("1)Số lá đặc biệt 2)Số lá bài lẻ 3)Sảnh dài nhất "
  + "4)Số đôi độc lập 5)Tổng số lượng đôi 6)Số ba con độc lập 7)Tổng điểm ALL ba con 8)Số bộ chặn heo "
  + "9)lệch lá tuyến trên và tuyến dưới 10)Lệch đôi max đôi min 11)Lệch sảnh max và sảnh min" 
              +"12)Số lá bài còn lại 13)quyết định");
         System.out.println(strKQ);*/
           eleCot[14]="";
         for(int i=0;i<player.mycard.size();i++)
         {
         eleCot[14]+= player.mycard.get(i).sohieu +"$";
         }
         //Xem xác xuất dự đoán tr của máy 
         String[] COT ={null,"cot1","cot2","cot3",
         "cot4","cot5","cot6","cot7","cot8"
             ,"cot9","cot10","cot11","cot12","cot13"};
           String TENBANG="TABLETLMNNAIVEBAYES";
         CSDL_Naivebayes nBayes=new CSDL_Naivebayes(COT,TENBANG,eleCot,SO_COT);
         nBayes.tinhXSKetQua();
         // Mở tranning 
       //  nBayes.luuBoDaTaTranning("player");
        
         
         
    }
     public int lamTronLen(float kqchia)
    {
       
       // //////System.out.println("Lay"+kqchia+"-"+(int)kqchia);
       
        if(kqchia-(int)kqchia<0.01) return (int)kqchia;    
      
        return (int)kqchia+1;
    }
    
    
}
