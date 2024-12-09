package game;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CSDL_Naivebayes {
    String[] ele=new String[30];
    ArrayList<String> KQ=new ArrayList<>();
    double S;
    int indexActor;
    String TENBANG;
    double[] T=new double[30];
    ArrayList<double[]> danhsachxacxuat = new ArrayList<>();
    ConnectDataBase kn=new ConnectDataBase();
    Connection cn=kn.getConnectdatabase();
    private final int SOCOT_or_SOHANG;
    String[] COT;
    int COUNT;
    public CSDL_Naivebayes(String[] COT, String TENBANG, String[] eleCot, int SOCOT_or_SOHANG) {
        this.SOCOT_or_SOHANG=SOCOT_or_SOHANG;
        //System.out.println("-"+ele[i]);
        if (SOCOT_or_SOHANG >= 0) System.arraycopy(eleCot, 1, this.ele, 1, SOCOT_or_SOHANG);
        this.COT=COT;
        this.TENBANG=TENBANG;
        KQ.add("0");
    }
    public int count2DieuKien(String tencot,String dieukien1,String dieukien2) {
        try {
            Statement stm=cn.createStatement();
            String k="SELECT COUNT("+
                    tencot+")  FROM "+TENBANG+" Where "+tencot+"='"+dieukien1
                    +"'and "+COT[SOCOT_or_SOHANG]+"='"+dieukien2+"'";
            //System.out.println("hi"+k);
            ResultSet rs=
                    stm.executeQuery(k);
            int result = 0;
            while (rs.next()) {
                result = rs.getInt(1); // Lưu giá trị vào biến result
            }
            return result;
        } catch(Exception ignored) {

        }
        return 0;
    }
    public int count1DieuKien(String tencot,String dieukien1) {
        try {
            Statement stm=cn.createStatement();
            String k="SELECT COUNT("+
                    tencot+")  FROM "+TENBANG+" Where "+tencot+"='"+dieukien1+"'";
            ResultSet rs= stm.executeQuery(k);
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch(Exception ignored) {

        }
        return 0;
    }
    public int count0DieuKien(String tencot) {
        try {
            Statement stm=cn.createStatement();
            String k="SELECT COUNT("+
                    tencot+")  FROM "+TENBANG;
            ResultSet rs= stm.executeQuery(k);
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch(Exception ignored) {

        }
        return 0;
    }
    public double tinhXSthanhphan(int i) {
        double S=1.0;
        for(int j=1;j<SOCOT_or_SOHANG;j++) {
            S*=count2DieuKien(COT[j],ele[j], KQ.get(i));
        }
        return S/(Math.pow(count1DieuKien(COT[SOCOT_or_SOHANG], KQ.get(i)), SOCOT_or_SOHANG-1))
                *count1DieuKien(COT[SOCOT_or_SOHANG], KQ.get(i))/COUNT;
    }
    public void  loccacloaiKQ() {
        try {
            Statement stm=cn.createStatement();
            String k="SELECT DISTINCT "+COT[SOCOT_or_SOHANG]+" FROM "+TENBANG;
            ResultSet rs= stm.executeQuery(k);
            while(rs.next()) {
                KQ.add(rs.getString(1));
            }
        } catch(Exception ignored) {

        }
    }
    public void tinhXSKetQua() {
        loccacloaiKQ();
        COUNT=count0DieuKien(COT[SOCOT_or_SOHANG]);
        for(int i=1;i<=KQ.size()-1;i++) {
            S+=tinhXSthanhphan(i);
        }
        for(int i=1;i<=KQ.size()-1;i++) {
            T[i]=tinhXSthanhphan(i)/S;
            danhsachxacxuat.add(T);
        }
        double tg1;
        String tg2;
        for(int i = 1; i < KQ.size()-1 ; i++){
            for(int j = i + 1; j < KQ.size()-1; j++){
                if(T[i] < T[j]){
                    // Hoan vi 2 so a[i] va a[j]
                    tg1 = T[i];tg2= KQ.get(i);
                    T[i] = T[j];KQ.set(i, KQ.get(j));
                    T[j] = tg1; KQ.set(j, tg2);
                }
            }
        }
        System.out.println("\n\n========Machine Learning NaiveBayes. Máy "+indexActor+" đi trước==========");
        for(int i=1;i<=KQ.size()-1;i++) {
            System.out.println("Xác suất với lựa chọn "+ KQ.get(i) +" là: "+T[i]);
        }
    }
    public static void main(String[] args) {

    }
}
