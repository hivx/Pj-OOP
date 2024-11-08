package src.card;

import java.util.Objects;

public class Card {

    // Enum Rank định nghĩa các giá trị của lá bài
    public enum Rank {
        THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE, TWO
    }

    // Enum Suit định nghĩa các chất của lá bài
    public enum Suit {
        SPADES, HEARTS, DIAMONDS, CLUBS
    }

    // Các thuộc tính rank và suit của lá bài được bảo vệ (private)
    private Rank rank;
    private Suit suit;

    // Constructor khởi tạo Card với rank và suit
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Getter cho rank
    public Rank getRank() {
        return rank;
    }

    // Setter cho rank
    public void setRank(Rank rank) {
        this.rank = rank;
    }

    // Getter cho suit
    public Suit getSuit() {
        return suit;
    }

    // Setter cho suit
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    // Phương thức toString() trả về chuỗi biểu diễn của lá bài
    @Override
    public String toString() {
        return "Lá " + getValueString() + getSuitSymbol();
    }

    // Phương thức so sánh thứ tự của hai lá bài
    public int compareTo(Card otherCard) {
        if (this.rank.ordinal() != otherCard.rank.ordinal()) {
            return this.rank.ordinal() - otherCard.rank.ordinal();
        } else {
            return this.suit.ordinal() - otherCard.suit.ordinal();
        }
    }

    // Phương thức equals để so sánh hai lá bài
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return rank == card.rank && suit == card.suit;
    }

    // Phương thức hashCode hỗ trợ các cấu trúc dữ liệu như HashMap
    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    // Phương thức trả về ký hiệu chất của lá bài
    private String getSuitSymbol() {
        return switch (this.suit) {
            case SPADES -> "♠";
            case HEARTS -> "♥";
            case DIAMONDS -> "♦";
            case CLUBS -> "♣";
        };
    }

    // Phương thức trả về chuỗi giá trị của lá bài (ví dụ: A, 2, 3, J, Q, K)
    private String getValueString() {
        // Trả về ký hiệu tương ứng cho mỗi lá bài
        return switch (this.rank) {
            case ACE -> "A"; // Lá bài ACE
            case JACK -> "J"; // Lá bài JACK
            case QUEEN -> "Q"; // Lá bài QUEEN
            case KING -> "K"; // Lá bài KING
            case TWO -> "2"; // Lá bài TWO
            case THREE -> "3"; // Lá bài THREE
            case FOUR -> "4"; // Lá bài FOUR
            case FIVE -> "5"; // Lá bài FIVE
            case SIX -> "6"; // Lá bài SIX
            case SEVEN -> "7"; // Lá bài SEVEN
            case EIGHT -> "8"; // Lá bài EIGHT
            case NINE -> "9"; // Lá bài NINE
            case TEN -> "10"; // Lá bài TEN
        };
    }
}
