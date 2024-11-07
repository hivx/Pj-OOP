import card.Deck;

public class Main {
    public static void main(String[] args) {
        // Tạo một đối tượng Deck (bộ bài)
        Deck deck = new Deck();

        // In bộ bài ban đầu
        System.out.println("Bộ bài ban đầu:");
        deck.printDeck();

        // Xáo trộn bộ bài
        deck.shuffle();

        // In bộ bài sau khi xáo trộn
        System.out.println("\nBộ bài sau khi xáo trộn:");
        deck.printDeck();

        // Rút một lá bài
        System.out.println("\nRút một lá bài: " + deck.draw());

        // In bộ bài sau khi rút một lá bài
        System.out.println("\nBộ bài sau khi rút một lá:");
        deck.printDeck();

        // Kiểm tra kích thước bộ bài còn lại
        System.out.println("\nSố lá bài còn lại trong bộ: " + deck.size());
    }
}
