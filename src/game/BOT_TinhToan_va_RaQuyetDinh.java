package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class BOT_TinhToan_va_RaQuyetDinh extends TLMN {
    public BOT_TinhToan_va_RaQuyetDinh(ArrayList<Card_Player> arrLbaiplayerra,
                                       ArrayList<CardBot> cacbobaicuatoi, int[] SoLaActor,
                                       int soLuongActor, int index_actor, int index_actor_truoc, int index_actor_sau, int bainhonhat) {

        this.cardbot=cacbobaicuatoi;
        this.solabaitoira=arrLbaiplayerra.size();
        this.soLuongActor=soLuongActor;
        this.index_actor_sau=index_actor_sau;
        this.index_actor_truoc=index_actor_truoc;
        this.index_actor=index_actor;
        this.bainhonhat=bainhonhat;
        this.SoLaConLaiCuaActor=SoLaActor;
        int j=0;
        for(int i=0;i<solabaitoira;i++) {
            j++;
            sohieu_baidoithura[j]=arrLbaiplayerra.get(i).sohieu;
            strbaiplayerchon = arrLbaiplayerra.stream()
                    .map(card -> String.valueOf(card.sohieu))
                    .collect(Collectors.joining("$"));
            tongsohieulabaidothura+=arrLbaiplayerra.get(i).sohieu;
        }
        ctkt=chuTrinhKetThucNganNhat();
    }
    public String dapAnDiTruocMaxLength() {
        int lengthmax=1;
        String dapanmaxlength="koco";
        for (CardBot cardBot : cardbot) {
            if (cardBot.sola > lengthmax) {
                lengthmax = cardBot.sola;
                dapanmaxlength = cardBot.sola + "$" + cardBot.loaibai + "$" +
                        cardBot.daycard;
            }
        }
        if(lengthmax==1) {return "koco";}
        return dapanmaxlength;
    }
    public String dapAnDiTruocMaxLengthGiuTop() {
        String s= sanhDep();
        if(!s.equals("koco")) {
            for (CardBot cardBot : cardbot) {
                if (s.equals(cardBot.daycard)) {
                    return cardBot.sola + "$" + cardBot.loaibai + "$" +
                            cardBot.daycard;
                }
            }
        }
        int lengthmax=0;
        String dapanmaxlength="koco";
        for (CardBot cardBot : cardbot) {
            if ((cardBot.loaibai.equals("sanh")
                    || (cardBot.loaibai.equals("bacon") && cardBot.sumsohieu < 138)
                    || (cardBot.loaibai.equals("doi") && cardBot.sumsohieu < 91))
                    && khongAnhHuongDenBaiBoBaiTop(cardBot.daycard) && cardBot.sola > lengthmax) {
                lengthmax = cardBot.sola;
                dapanmaxlength = cardBot.sola + "$" + cardBot.loaibai + "$" +
                        cardBot.daycard;
            }
        }
        return dapanmaxlength;
    }
    public String sanhDep() {
        int lengthmax=0;
        int sumsohieulengthmax=0;
        String sanhdep="koco";
        for (CardBot cardBotVerEnd : cardbot) {
            if (((cardBotVerEnd.loaibai.equals("sanh")
                    && cardBotVerEnd.sola > lengthmax &&
                    khongAnhHuongDenBaiBoBaiTop(cardBotVerEnd.daycard)) ||
                    (((cardBotVerEnd.loaibai.equals("badoithong") || cardBotVerEnd.loaibai.equals("bondoithong"))
                            && SoLaConLaiCuaActor[index_actor] - cardBotVerEnd.sola == 1)))
                    && cardBotVerEnd.sola > lengthmax) {
                lengthmax = cardBotVerEnd.sola;
                sanhdep = cardBotVerEnd.daycard;
                sumsohieulengthmax = cardBotVerEnd.sumsohieu;
            }
        }
        if(SoLaConLaiCuaActor[index_actor]-lengthmax<=3) {
            return  sanhdep;}
        if(lengthmax>=SoLaConLaiCuaActor[index_actor]/2||sumsohieulengthmax>tongsohieubaiconlai()/2) {
            return sanhdep;
        }
        return "koco";
    }
    public String dapAnDiTruocMaxSoHieu() {
        int sohieumax=0;
        String dapanmaxsohieu="koco";
        for (CardBot cardBotVerEnd : cardbot) {
            if ((cardBotVerEnd.loaibai.equals("doi") || cardBotVerEnd.loaibai.equals("coc"))
                    && cardBotVerEnd.sumsohieu > sohieumax) {
                sohieumax = cardBotVerEnd.sumsohieu;
                dapanmaxsohieu = cardBotVerEnd.sola + "$" + cardBotVerEnd.loaibai + "$" +
                        cardBotVerEnd.daycard;
            }
        }
        return dapanmaxsohieu;
    }
    public String dapAnDiTruocMaxSoHieuGiuTop() {
        int sohieumax=0;
        String dapanmaxsohieu="koco";
        for (CardBot cardBot : cardbot) {
            if ((cardBot.loaibai.equals("doi") || cardBot.loaibai.equals("coc")
                    && cardBot.sumsohieu > sohieumax) &&
                    khongAnhHuongDenBaiBoBaiTop(cardBot.daycard)) {
                sohieumax = cardBot.sumsohieu;
                dapanmaxsohieu = cardBot.sola + "$" + cardBot.loaibai + "$" +
                        cardBot.daycard;
            }
        }
        return dapanmaxsohieu;
    }
    public String dapDiTruocLienQuanToiBaiNhoNhat(String bainhonhat) {
        String s=sanhDep();
        if(!s.equals("koco")) {
            if(s.charAt(0)==bainhonhat.charAt(0) &&s.charAt(1)=='$') {
                for (CardBot cardBot : cardbot) {
                    if (s.equals(cardBot.daycard)) {
                        return cardBot.sola + "$" + cardBot.loaibai + "$" +
                                cardBot.daycard;
                    }
                }
            }
        }
        String tempchua3bich="";
        int maxsola=0;
        for (CardBot bot : cardbot) {
            if ((bot.loaibai.equals("bacon") ||
                    bot.loaibai.equals("sanh")
                    || bot.loaibai.equals("doi")) &&
                    bot.daycard.charAt(0) == bainhonhat.charAt(0) &&
                    bot.daycard.charAt(1) == '$' &&
                    khongAnhHuongDenBaiBoBaiTop(bot.daycard)
                    && bot.sola > maxsola) {
                maxsola = bot.sola;
                tempchua3bich = bot.daycard;
            }
        }
        for (CardBot cardBot : cardbot) {
            if (tempchua3bich.equals(cardBot.daycard)) {
                return cardBot.sola + "$" + cardBot.loaibai + "$" +
                        cardBot.daycard;
            }
        }
        return "1$coc$"+bainhonhat;
    }
    public String dapAnMinChanBaiGiuBaiTop() {
        luuDuLieuCacBoThoaManCoTinhHonSoVoi(sohieu_baidoithura,solabaitoira);
        int min=10000;
        String dapanmin="koco";
        if(baitopdoduoc) {
            if(SoLaConLaiCuaActor[1]==1&&tongsohieulabaidothura>=49&&mucDoUuTienRaBai()==0)
                return "koco";
            for (CardBot cardBot : cardbot) {
                for (int j = 1; j <= countdapanlienquan; j++) {
                    if (dapanlienquan[j].equals(cardBot.daycard)) {
                        if (cardBot.sumsohieu < min) {

                            min = cardBot.sumsohieu;
                            dapanmin = cardBot.sola + "$" + cardBot.loaibai + "$" +
                                    cardBot.daycard;
                        }
                    }
                }
            }
        } else if(solabaitoira==1&&sohieu_baidoithura[1]<49) {
            for (CardBot cardBot : cardbot) {
                String[] elebot = cardBot.daycard.split("\\$");
                if (((cardBot.loaibai).equals("coc") ||
                        (cardBot.loaibai).equals("baile") ||
                        (cardBot.loaibai).equals("bathua") ||
                        (cardBot.loaibai).equals("badacbiet")) &&
                        sohieu_baidoithura[solabaitoira] < Integer.parseInt(elebot[0])) {
                    if (cardBot.sumsohieu < min && khongAnhHuongDenBaiBoBaiTop(cardBot.daycard)
                            && GiuDuocSanhDep(cardBot.daycard)) {
                        min = cardBot.sumsohieu;
                        dapanmin = cardBot.sola + "$" + cardBot.loaibai + "$" +
                                cardBot.daycard;
                    }
                }
            }
        } else{
            for (CardBot cardBot : cardbot) {
                for (int j = 1; j <= countdapanlienquan; j++) {
                    if (dapanlienquan[j].equals(cardBot.daycard)) {
                        if (cardBot.sumsohieu < min &&
                                khongAnhHuongDenBaiBoBaiTop(cardBot.daycard)
                                && GiuDuocSanhDep(cardBot.daycard)) {
                            min = cardBot.sumsohieu;
                            dapanmin = cardBot.sola + "$" + cardBot.loaibai + "$" +
                                    cardBot.daycard;
                        }
                    }
                }
            }
        }
        return dapanmin;
    }
    public String dapAnMinDiTruoc_naivebayes(Bot bot) throws IOException {
        String s= sanhDep();
        if(!s.equals("koco")) {
            for (CardBot cardBot : cardbot) {
                if (s.equals(cardBot.daycard)) {
                    return cardBot.sola + "$" + cardBot.loaibai + "$" +
                            cardBot.daycard;
                }
            }
        }
        ml_naivebays_bot =new NaiveBayes_Bot_LearnData(bot);
        ml_naivebays_bot.run();
        for(int i=1;i<ml_naivebays_bot.danhsachkieubaixacsuat.size();i++) {
            String temploaibainaivebayes=ml_naivebays_bot.
                    danhsachkieubaixacsuat.get(i).toString().trim();
            String[] c=temploaibainaivebayes.split("\\$");

            for (CardBot cardBot : cardbot) {
                if ((cardBot.loaibai.equals(temploaibainaivebayes) ||
                        (cardBot.loaibai.equals(c[0]) && cardBot.loaibai.equals(c[1])))
                        && khongAnhHuongDenBaiBoBaiTop(cardBot.daycard)) {
                    return cardBot.sola + "$" + cardBot.loaibai + "$" +
                            cardBot.daycard;
                }
            }
        }
        return "koco";
    }
    public String dapAnMaxChanBaiGiuBaiTop() {
        luuDuLieuCacBoThoaManCoTinhHonSoVoi(sohieu_baidoithura,solabaitoira);
        String dapanmax="koco";
        int max=0;
        for (CardBot cardBot : cardbot) {
            for (int j = 1; j <= countdapanlienquan; j++) {
                if (dapanlienquan[j].equals(cardBot.daycard)) {
                    if (cardBot.sumsohieu > max &&
                            khongAnhHuongDenBaiBoBaiTop(cardBot.daycard)
                            && GiuDuocSanhDep(cardBot.daycard)) {
                        max = cardBot.sumsohieu;
                        dapanmax = cardBot.sola + "$" + cardBot.loaibai + "$" +
                                cardBot.daycard;
                    }
                }
            }
        }
        return dapanmax;
    }
    public String dapChanBaiKhongDieuKien() {
        luuDuLieuCacBoThoaManCoTinhHonSoVoi(sohieu_baidoithura,solabaitoira);
        String dapanmax="koco";
        int max=0;
        for (CardBot cardBot : cardbot) {
            for (int j = 1; j <= countdapanlienquan; j++) {
                if (dapanlienquan[j].equals(cardBot.daycard)) {

                    if (cardBot.sumsohieu > max) {
                        max = cardBot.sumsohieu;
                        dapanmax = cardBot.sola + "$" + cardBot.loaibai + "$" +
                                cardBot.daycard;
                    }
                }
            }
        }
        return dapanmax;
    }
    public String dapDiTruocMaxGiuTop() {
        String maxdapan=dapAnDiTruocMaxLengthGiuTop();
        if(maxdapan.equals("koco")) {
            maxdapan=dapAnDiTruocMaxSoHieuGiuTop();
        }
        return maxdapan;
    }
    public String dapDiTruocKhongDieuKien() {
        String maxdapan=dapAnDiTruocMaxLength();
        if(maxdapan.equals("koco")) {
            maxdapan=dapAnDiTruocMaxSoHieu();
        }
        return maxdapan;
    }
    public boolean khongAnhHuongDenBaiBoBaiTop(String baichon) {
        int countSPTbaichon=0;
        for(int i=0;i<baichon.length();i++) {
            if(baichon.charAt(i)=='$') countSPTbaichon++;
        }
        String[] elebaichon=baichon.split("\\$");
        for (CardBot cardBot : cardbot) {
            if (cardBot.loaibai.equals("badoithong")
                    || cardBot.loaibai.equals("tuquy")
                    || cardBot.loaibai.equals("bondoithong")) {
                int countSPTbaitop = 0;
                for (int t = 0; t < cardBot.daycard.length(); t++) {
                    if (cardBot.daycard.charAt(t) == '$') countSPTbaitop++;
                }
                String[] elebaitop = cardBot.daycard.split("\\$");
                for (int a = 0; a < countSPTbaitop; a++) {
                    for (int b = 0; b < countSPTbaichon; b++) {
                        if (elebaitop[a].equals(elebaichon[b])) {
                            return false;
                        }
                    }
                }
            } else if (cardBot.daycard.equals(baichon)
                    && cardBot.sumsohieu >= 91 && cardBot.sola == 2) {
                return false;
            } else if (cardBot.daycard.equals(baichon)
                    && cardBot.sumsohieu >= 49 && cardBot.sola == 1) {
                return false;
            } else if (cardBot.daycard.equals(baichon)
                    && cardBot.sumsohieu >= 126 && cardBot.sola == 3) {
                return false;
            }
        }
        return true;
    }
    public String dapAnBotChonRanDom() {
        Random rd = new Random();
        int index_dapanrandom = rd.nextInt(cardbot.size()-1);
        for(int i=0;i<cardbot.size();i++) {
            if(i==index_dapanrandom) {
                   return cardbot.get(i).sola+"$"+cardbot.get(i).loaibai+"$"+
                           cardbot.get(i).daycard;
            }
        }
        return null;
    }
    public boolean GiuDuocSanhDep(String baichon) {
        String sanhdep=sanhDep();
        int n=0,m=0;
        for (CardBot cardBot : cardbot) {
            if (cardBot.daycard.equals(sanhdep)) {
                n = cardBot.sola;
            }
            if (cardBot.daycard.equals(baichon)) {
                m = cardBot.sola;
            }
        }
        if(sanhdep.equals("koco")) {
            return true;
        } else{
            int count=0;
            String[] s=sanhdep.split("\\$");
            String[] c=baichon.split("\\$");
            int start=0,end=0;
            for(int i=0;i<cardbot.size();i++) {
                if(cardbot.get(i).daycard.equals(s[0]+"$")&&cardbot.get(i).loaibai.equals("coc")) {
                    start=i;
                }
                if(cardbot.get(i).daycard.equals(s[n-1]+"$")&&cardbot.get(i).loaibai.equals("coc")) {
                    end=i;
                }
            }
            for(int i=start;i<=end;i++) {
                if(lamTronLen((float)(cardbot.get(i).sumsohieu)/4)-
                        lamTronLen((float)(Integer.parseInt(c[0]))/4)==0) {
                    count++;
                }
            }
            return count != m;
        }
    }
    public int mucDoUuTienRaBai() {
        if(soLuongActor==4) return mucDoUuTienRaBai4Actor();
        if(soLuongActor==3) return mucDoUuTienRaBai4Actor();
        if(soLuongActor==2) return mucDoUuTienRaBai2Actor();
        return 0;
    }
}
