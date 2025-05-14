package src.jp.ac.ait.k24015.library;

public class LibraryMain {
    public static void main(String[] args) {
        // 本を作成
        Book[] books = {
                new Book("978-1", "Java入門", "山田太郎"),
                new Book("978-2", "Python基礎", "佐藤花子"),
                new Book("978-3", "C言語の世界", "鈴木一郎"),
                new Book("978-4", "AI時代のプログラミング", "田中次郎"),
                new Book("978-5", "データベース概論", "高橋健太"),
                new Book("978-6", "アルゴリズム大全", "井上真理"),
                new Book("978-7", "ソフトウェア設計", "中村葵"),
                new Book("978-8", "ネットワーク入門", "加藤優"),
                new Book("978-9", "情報理論", "小林渉"),
                new Book("978-10", "セキュリティと暗号", "松本恵")
        };

        // 会員作成
        LibraryMember member = new LibraryMember("M001", "佐々木優");

        // 単冊貸出
        System.out.println("1冊貸出: " + member.borrowBook(books[0]));

        // 複数冊貸出
        Book[] borrowList = { books[1], books[2], books[3], books[4], books[5] };
        int borrowedCount = member.borrowBooks(borrowList);
        System.out.println("複数冊貸出: " + borrowedCount + "冊");

        // 上限超過確認
        System.out.println("追加貸出（上限確認）: " + member.borrowBook(books[6]));

        // 会員情報表示
        System.out.println();
        member.displayMemberInfo();

        // 本の返却
        System.out.println("\n返却: " + member.returnBook(books[2]));

        // 再度貸出（返却後）
        System.out.println("再度貸出: " + member.borrowBook(books[6]));

        // 再表示
        System.out.println();
        member.displayMemberInfo();
    }
}
