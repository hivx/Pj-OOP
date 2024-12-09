package game;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CardBot {
    public String loaibai;
    public int sola;
    public int sumsohieu;
    public String daycard;
    public String fullnamecard;

    public CardBot(String loaibai, int sola, String daycard, int sumsohieu) {
        this.loaibai = loaibai;
        this.sola = sola;
        this.daycard = daycard;
        this.sumsohieu = sumsohieu;

        giaiMaSoHieuRaFullNameCard();
    }

    public void giaiMaSoHieuRaFullNameCard() {
        Map<Integer, String> cardMap = Map.<Integer, String>ofEntries(
                Map.entry(1, "3B"), Map.entry(2, "3T"), Map.entry(3, "3R"), Map.entry(4, "3C"),
                Map.entry(5, "4B"), Map.entry(6, "4T"), Map.entry(7, "4R"), Map.entry(8, "4C"),
                Map.entry(9, "5B"), Map.entry(10, "5T"), Map.entry(11, "5R"), Map.entry(12, "5C"),
                Map.entry(13, "6B"), Map.entry(14, "6T"), Map.entry(15, "6R"), Map.entry(16, "6C"),
                Map.entry(17, "7B"), Map.entry(18, "7T"), Map.entry(19, "7R"), Map.entry(20, "7C"),
                Map.entry(21, "8B"), Map.entry(22, "8T"), Map.entry(23, "8R"), Map.entry(24, "8C"),
                Map.entry(25, "9B"), Map.entry(26, "9T"), Map.entry(27, "9R"), Map.entry(28, "9C"),
                Map.entry(29, "XB"), Map.entry(30, "XT"), Map.entry(31, "XR"), Map.entry(32, "XC"),
                Map.entry(33, "JB"), Map.entry(34, "JT"), Map.entry(35, "JR"), Map.entry(36, "JC"),
                Map.entry(37, "QB"), Map.entry(38, "QT"), Map.entry(39, "QR"), Map.entry(40, "QC"),
                Map.entry(41, "KB"), Map.entry(42, "KT"), Map.entry(43, "KR"), Map.entry(44, "KC"),
                Map.entry(45, "AB"), Map.entry(46, "AT"), Map.entry(47, "AR"), Map.entry(48, "AC"),
                Map.entry(49, "2B"), Map.entry(50, "2T"), Map.entry(51, "2R"), Map.entry(52, "2C")
        );

        // Chuyển daycard thành mảng số nguyên
        String[] s = daycard.split("\\$");
        fullnamecard = Arrays.stream(s).map(string -> {
                    int value = Integer.parseInt(string);
                    return cardMap.getOrDefault(value, ""); // Lấy giá trị từ cardMap
        })
                .filter(str -> !str.isEmpty()) // Bỏ qua chuỗi rỗng
                .collect(Collectors.joining("$")); // Ghép chuỗi với "$"
    }
}
