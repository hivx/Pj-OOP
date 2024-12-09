package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TLMB {
    int countcard;
    int[] card=new int[14];
    boolean co2Co;
    int countdapanlienquan;
    int countdapanlienquansum;
    int[] dapanlienquansum=new int[100];
    String[] dapanlienquan=new String[100];
    boolean baitopdoduoc;
    int solabaitoira;
    String strbaiplayerchon;
    int[] sohieu_baidoithura=new int[30];
    int index_actor;
    int bainhonhat;
    int countbaimanh,tongsohieubaimanh;
    int indexuutien;
    int tongsohieulabaidothura;
    int soLuongActor;
    int[] SoLaConLaiCuaActor=new int[30];
    int index_actor_truoc,index_actor_sau,minactor,maxactor,tongSoLaDoiThu;
    NaiveBayes_Bot_LearnData ml_naivebays_bot;
    ArrayList<CardBot> cardbot =new ArrayList<>();
    public TLMB() {

    }

    public boolean LaCoc()
    {
        return countcard == 1;
    }
    public boolean LaSanh() {
        if (countcard < 3) return false; // Sảnh phải có ít nhất 3 lá
        for (int i = 1; i < countcard; i++) {
            // Kiểm tra các lá bài phải liền kề về số và cùng chất
            if (lamTronLen((float) card[i + 1] / 4) - lamTronLen((float) card[i] / 4) != 1
                    || card[i] % 4 != card[i + 1] % 4) {
                // Không cùng chất
                return false;
            }
        }
        return true; // Tất cả các lá bài đều liền kề và cùng chất
    }
    public boolean LaBaCon() {
        return (lamTronLen((float) card[3] / 4) - lamTronLen((float) card[1] / 4) == 0)
                && countcard == 3;
    }
    public boolean LaDoi() {
        return lamTronLen((float) card[2] / 4) == lamTronLen((float) card[1] / 4) // Cùng số
                && ((card[1] - 1) % 4 / 2 == (card[2] - 1)% 4 / 2)
                && countcard == 2;
    }
    public boolean LaTuQuy() {
        return (lamTronLen((float) card[4] / 4) ==
                lamTronLen((float) card[1] / 4))
                && countcard == 4;
    }
    public String timKieuBai(int countcard,int[] card) {
        this.card=card;
        this.countcard=countcard;
        if(LaCoc()) {
            return "coc";
        } else if(LaDoi()) {
            return "doi";
        } else if(LaBaCon()) {
            return "bacon";
        } else if(LaTuQuy()) {
            return "tuquy";
        } else if(LaSanh()) {
            return "sanh";
        }
        return "khonghople";
    }
    public int lamTronLen(float kqchia)
    {
        if(kqchia-(int)kqchia<0.01) return (int)kqchia;

        return (int)kqchia+1;
    }
    public void luuDuLieuCacBoThoaManCoTinhHonSoVoi(int[] carddp, int countdp) {
        String kieubai=timKieuBai(countdp, carddp);
        for(int i=0;i<cardbot.size();i++) {
            String[] elebot=cardbot.get(i).daycard.split("\\$");
            if(cardbot.get(i).loaibai.equals(kieubai)
                    &&cardbot.get(i).sola==countdp&&
                    carddp[countdp]<Integer.parseInt(elebot[countdp-1])) {
                luuDuLieuCacDapAnLienQuan(i);
            }
        }
        if(kieubai.equals("coc")&&carddp[countdp]>=49) {
            for(int i=0;i<cardbot.size();i++) {
                if(cardbot.get(i).loaibai.equals("tuquy")) {
                    baitopdoduoc=true;
                    luuDuLieuCacDapAnLienQuan(i);
                }
            }
        }
    }
    public boolean BiVaCham2(Vector<String> local, String B) {
        StringBuilder bien1= new StringBuilder();
        int count1=0,count2=0;
        for (String s : local) {
            bien1.append(s);
        }
        for(int i=0;i<bien1.length();i++) {
            if(bien1.charAt(i)=='$') count1++;
        }
        for(int i = 0; i< B.length(); i++) {
            if(B.charAt(i)=='$') count2++;
        }

        String[] temp1 = bien1.toString().split("\\$");
        String[] temp2 = B.split("\\$");

        for(int i=0;i<count1;i++) {
            for(int j=0;j<count2;j++) {
                if(temp2[j].equals(temp1[i])) {
                    return true;}
            }
        }
        return false;
    }
    boolean cochutrinhketthuc;
    int chutrinhketthuc;
    void unique_combination(int l, String sumString, String K,
                            Vector<String> local,Vector<String> local2,
                            Vector<String> A, Vector<String> B) {
        if(cochutrinhketthuc) return;
        if (sapXepGiaTriTheoThuTuTangDanString(sumString).equals(K)) {
            cochutrinhketthuc=true;
            return;
        }

        // For all other combinations
        for (int i = l; i < A.size(); i++) {
            // Check if the sum exceeds K
            if (sumString.length() + A.get(i).length() > K.length())
                continue;

            // Check if it is repeated or not
            if (i > l && Objects.equals(A.get(i), A.get(i - 1))) continue;
            if(local.contains(A.get(i))) continue;
            if(BiVaCham2(local2,B.get(i))) continue;
            if(local.size()>(chutrinhketthuc-1)) continue;

            local.add(A.get(i));
            local2.add(B.get(i));

            // Recursive call
            unique_combination(i + 1, sumString + A.get(i), K,
                    local,local2, A,B);

            // Remove element from the combination
            local.removeLast();
            local2.removeLast();
        }
    }

    // Function to find all combination
    // of the given elements
    void Combination(Vector<String> A,Vector<String> B, String K) throws IOException {
        // To store combination
        Vector<String> local = new Vector<>();
        Vector<String> local2 = new Vector<>();

        unique_combination(0, "", K, local,local2, A,B);
    }
    public String sapXepGiaTriTheoThuTuTangDanString (String str) {
        if(str.isEmpty()) return "";
        int count=0;

        for(int t=0;t<str.length();t++) {
            if(str.charAt(t)=='$') {
                count++;
            }
        }
        String[] s=str.split("\\$");
        int[] temgiatricuachuoi=new int[30];
        for(int j=1;j<=count;j++) {
            temgiatricuachuoi[j]=Integer.parseInt(s[j-1]);
        }
        int tg;
        for(int u = 1; u < count ; u++){
            for(int v = u + 1; v < count+1; v++){
                if(temgiatricuachuoi[u] > temgiatricuachuoi[v]){
                    tg = temgiatricuachuoi[u];
                    temgiatricuachuoi[u] = temgiatricuachuoi[v];
                    temgiatricuachuoi[v] = tg;
                }
            }
        }
        StringBuilder strBuilder = new StringBuilder();
        for(int j = 1; j<=count; j++) {
            strBuilder.append(temgiatricuachuoi[j]).append("$");
        }
        str = strBuilder.toString();
        return str;
    }
    String[] tempfullnameString =new String[5000];
    String[] tempdaycard =new String[5000];
    int[] tempsola =new int[5000];
    int maxla=0;
    public void sort() {
        for(int i=0;i<cardbot.size();i++) {
            tempfullnameString[i]=cardbot.get(i).fullnamecard;
            tempdaycard[i]=cardbot.get(i).daycard;
            tempsola[i]=cardbot.get(i).sola;
            if(tempsola[i]>maxla)
                maxla=tempsola[i];
        }
        String tg1,tg2;int tg3;
        for(int u = 0; u < cardbot.size()-1 ; u++){
            for(int v = u + 1; v < cardbot.size(); v++){
                if(tempsola[u] > tempsola[v]){
                    tg1 = tempfullnameString[u];
                    tempfullnameString[u] = tempfullnameString[v];
                    tempfullnameString[v] = tg1;
                    tg2 = tempdaycard[u];
                    tempdaycard[u] = tempdaycard[v];
                    tempdaycard[v] = tg2;

                    tg3 = tempsola[u];
                    tempsola[u] = tempsola[v];
                    tempsola[v] = tg3;
                }
            }
        }
    }
    public void duyetmoitohop() {
        try {
            String K = baiconlai();

            sort();

            java.util.List<String> list = new ArrayList<>();
            java.util.List<String> list2 = new ArrayList<>();
            for(int i=0;i<cardbot.size();i++) {
                list.add(tempdaycard[i]);
                list2.add(tempfullnameString[i]);
            }
            Vector<String> A = new Vector<>(list);
            Vector<String> B = new Vector<>(list2);

            Combination(A,B, K);
        } catch (IOException ex) {
            Logger.getLogger(BOT_TinhToan_va_RaQuyetDinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int chuTrinhKetThucNganNhat() {
        int i;
        int start;int end;

        end=3;
        start=1;
        for(i=start;i<=end;i++) {
            this.chutrinhketthuc=i;
            duyetmoitohop();
            if(cochutrinhketthuc) {
                return i;
            }
        }
        return 13;
    }
    public int tongsohieubaiconlai() {
        int sum=0;
        for (CardBot cardBot : cardbot) {
            if (cardBot.loaibai.equals("coc")) {
                sum += cardBot.sumsohieu;
            }
        }
        return sum;
    }
    public String baiconlai() {
        StringBuilder sum= new StringBuilder();
        for (CardBot cardBot : cardbot) {
            if (cardBot.loaibai.equals("coc")) {
                //   System.out.print(cardbot.get(i).daycard);
                sum.append(cardBot.daycard);
            }
        }
        return sum.toString();
    }
    public int tongSoLaConLai() {
        int sum=0;
        for (CardBot cardBot : cardbot) {
            if (cardBot.loaibai.equals("coc")) {
                sum++;
            }
        }
        return sum;
    }
    public void luuDuLieuCacDapAnLienQuan(int i) {
        countdapanlienquan++;
        dapanlienquan[countdapanlienquan]=cardbot.get(i).daycard;
        dapanlienquansum[countdapanlienquansum]=cardbot.get(i).sumsohieu;
    }
    public void xacDinhCacThongSoLienQuan() {
        minactor=10000;
        maxactor=0;
        for(int i=1;i<=soLuongActor;i++) {
            if(i!=index_actor) {
                tongSoLaDoiThu+=SoLaConLaiCuaActor[i];
                if(SoLaConLaiCuaActor[i]<minactor) {
                    minactor=SoLaConLaiCuaActor[i];
                } else if(SoLaConLaiCuaActor[i]>maxactor) {
                    maxactor=SoLaConLaiCuaActor[i];
                }
            }
        }
        for (CardBot cardBot : cardbot) {
            if (cardBot.loaibai.equals("coc") && cardBot.sumsohieu >= 45) {
                countbaimanh++;
                tongsohieubaimanh += cardBot.sumsohieu;
            }
            if (cardBot.loaibai.equals("tuquy")) {
                countbaimanh++;
                tongsohieubaimanh += cardBot.sumsohieu;
            }
            if (cardBot.loaibai.equals("coc") && cardBot.sumsohieu == 52) {
                co2Co = true;
            }
        }
    }
    int ctkt;
    public int mucDoUuTienRaBaiChung() {
        xacDinhCacThongSoLienQuan();
        for (CardBot cardBot : cardbot) {
            if (cardBot.loaibai.equals("coc") && cardBot.sumsohieu == bainhonhat) {
                indexuutien = 1;
                return 999;
            }
        }
        if(ctkt>4) System.out.println("Bot "+index_actor+" co chu trinh > 3");
        else System.out.println("Chu trinh ket thuc ngan nhat cua bot "+index_actor+" la: "+ctkt);
        if((ctkt==1)||(tongSoLaConLai()>=10&&ctkt<=3&&countbaimanh>=3)
                ||(SoLaConLaiCuaActor[index_actor_sau]==1)
                ||(minactor==1&&(ctkt<=3))||((minactor==1)&&countbaimanh>=2)) {
            System.out.print("uu tien chu trinh toc chien max lenght");
            System.out.println(" tại 4");return 99;
        }
        return 0;
    }
    public int mucDoUuTienRaBai4Actor() {
        int mdutrbc=mucDoUuTienRaBaiChung();
        if(mdutrbc!=0) return mdutrbc;
        if(ctkt<=2) {
            if(tongSoLaConLai()==3&&countbaimanh>=1) {
                System.out.println("uu Tien chu trinh < 2"); System.out.println(" tại 1");return 99;
            }
            if(ctkt<=tongSoLaConLai()&&tongSoLaConLai()!=2) {
                System.out.println("uu tien chu trinh toc chien so la nhieu hon 2");
                System.out.println(" tại 2");return 99;
            }
            if(ctkt<=tongSoLaConLai()&&tongSoLaConLai()==2&& co2Co) {
                System.out.println("uu tien chu trinh toc chien co 2 co");
                System.out.println(" tại 3");return 99;
            }
        }
        if(solabaitoira==1&&ctkt<=3&&countbaimanh>=3)
        {System.out.println("tại 5"); return 99;}
        if(SoLaConLaiCuaActor[index_actor_sau]==1) {
            if(SoLaConLaiCuaActor[index_actor_truoc]==1) {  indexuutien=2;  System.out.println("tại 6");return 99; }
            else if(countbaimanh>=2&&SoLaConLaiCuaActor[index_actor]>=4) {
                Random rd=new Random();
                int index_dapanrandom = rd.nextInt(3);
                if(index_dapanrandom==0) {System.out.println("random=9");return 9;}
                else {System.out.println("random=99");System.out.println("tại 7");return 99;}
            } else if(index_actor_sau==1) return 99;
        }
        if(SoLaConLaiCuaActor[index_actor_truoc]==1&&tongSoLaDoiThu>=91) return 99;
        if(soLuongActor==2&&(SoLaConLaiCuaActor[index_actor_sau]==2)) return 99;
        if(tongsohieulabaidothura>=50&& co2Co
                &&index_actor_truoc==1&&solabaitoira==1) {
            System.out.println("TM");System.out.println("tại 8");return 99;
        }
        if(SoLaConLaiCuaActor[1]<=2) {
            if(countbaimanh>=3) return 99;
            else return 98;
        } else if(minactor<=3&&countbaimanh<3) {
            if(SoLaConLaiCuaActor[index_actor]>=maxactor||
                    ((SoLaConLaiCuaActor[index_actor]>tongSoLaDoiThu/(soLuongActor-1)
                            &&tongSoLaDoiThu<(soLuongActor-1)*11))) {
                indexuutien=13;  return 8;
            }
        }
        return 0;
    }
    public int mucDoUuTienRaBai2Actor() {
        int mdutrbc=mucDoUuTienRaBaiChung();

        if(mdutrbc!=0) return mdutrbc;
        //System.out.println("LCK");

        if((ctkt==2&&countbaimanh>=2)) return 99;
        if (countbaimanh>=3&&SoLaConLaiCuaActor[index_actor_sau]<=2) {
            if(SoLaConLaiCuaActor[index_actor]>SoLaConLaiCuaActor[index_actor_sau])
                return 99;
        }
        System.out.println("cout ctkt: "+ctkt+", count bai manh: "
                +countbaimanh+",SoLaConLaiCuaActor[index_actor] "+SoLaConLaiCuaActor[index_actor]
                +",SoLaConLaiCuaActor[index_actor_sau]: "+SoLaConLaiCuaActor[index_actor_sau]
                +",Tong so la doi thu: "+tongSoLaDoiThu);

        //Xử lý tạm chốt được
        if(ctkt<=3&&tongsohieubaimanh>=50&&SoLaConLaiCuaActor[index_actor]>=7) return 99;

        //Xử lý bài yếu
        if(countbaimanh<=2 &&SoLaConLaiCuaActor[index_actor]>=8) return 99;

        //Xử lý chống ra 2 tép trở lên
        if(tongsohieulabaidothura>=50) return 99;

        //Xử lý còn 2 lá
        if(tongsohieubaimanh>=49&&SoLaConLaiCuaActor[index_actor]==2
                &&tongsohieulabaidothura<49) return 99;
        return 0;
    }
}


