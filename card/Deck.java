package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    // Constructor khởi tạo bộ bài với 52 lá (mỗi chất và giá trị của lá bài)
    public Deck() {
        cards = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    // Phương thức xáo trộn bộ bài
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Phương thức rút một lá bài và xóa nó khỏi bộ bài
    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty!");
        }
        return cards.removeLast(); // Sử dụng remove với chỉ số cuối
    }

    // Phương thức thêm một lá bài vào bộ bài
    public void addCard(Card card) {
        cards.add(card);
    }

    // Phương thức kiểm tra xem bộ bài có rỗng không
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    // Phương thức lấy kích thước (số lá bài còn lại trong bộ)
    public int size() {
        return cards.size();
    }

    // Phương thức in ra tất cả các lá bài trong bộ bài
    public void printDeck() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }

    // Phương thức xáo trộn bộ bài nhiều lần
    public void shuffleMultipleTimes(int times) {
        for (int i = 0; i < times; i++) {
            shuffle();
        }
    }

    // Phương thức lấy lá bài cuối cùng mà không xóa
    public Card peek() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty!");
        }
        return cards.getLast(); // Lấy lá bài cuối cùng mà không xóa
    }

    // Phương thức xóa tất cả các lá bài trong bộ bài
    public void clearDeck() {
        cards.clear();
    }
}
