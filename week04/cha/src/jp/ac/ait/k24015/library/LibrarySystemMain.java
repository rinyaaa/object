package jp.ac.ait.k24015.library;

public class LibrarySystemMain {
    public static void main(String[] args) {
        Library library = new Library();

        // 本を登録
        Book[] books = {
                new Book("978-1", "Java入門", "山田太郎"),
                new Book("978-2", "Python基礎", "佐藤花子"),
                new Book("978-3", "C言語の世界", "鈴木一郎"),
                new Book("978-4", "AI時代のプログラミング", "田中次郎"),
                new Book("978-5", "データベース概論", "高橋健太")
        };

        for (Book book : books) {
            library.addBook(book);
        }

        // 会員を登録
        LibraryMember member1 = new LibraryMember("M001", "佐々木優");
        LibraryMember member2 = new LibraryMember("M002", "田村遥", 3);

        library.registerMember(member1);
        library.registerMember(member2);

        // 貸出業務
        library.lendBookToMember("M001", "978-1");
        library.lendBookToMember("M001", "978-2");
        library.lendBookToMember("M001", "978-3");
        library.lendBookToMember("M001", "978-4");
        library.lendBookToMember("M001", "978-5");

        // 上限超過（member1の上限は5）
        System.out.println("--- 上限超過テスト ---");
        library.lendBookToMember("M001", "978-1");

        // 返却業務
        library.receiveBookFromMember("M001", "978-2");

        // 再度貸出（返却後）
        library.lendBookToMember("M001", "978-1"); // 成功するか確認

        // 書籍検索（部分一致）
        System.out.println("--- 書籍検索（\"入門\"） ---");
        Book[] searchResults = library.searchBook("入門");
        for (Book b : searchResults) {
            System.out.println(b.getTitle() + "\n");
        }

        // 蔵書表示
        library.displayAllBooks();
        library.displayAvailableBooks();

        // 会員情報表示
        library.displayAllMembersWithBorrowedBooks();

        // エラーケース：存在しない本の返却
        System.out.println("--- 存在しないISBNの返却 ---");
        library.receiveBookFromMember("M001", "999-9");

        // エラーケース：貸出中の本の削除
        System.out.println("--- 貸出中の本の削除試行 ---");
        library.removeBook("978-1");

        // 本の返却後の削除
        library.receiveBookFromMember("M001", "978-1");
        System.out.println("--- 貸出解除後の削除 ---");
        library.removeBook("978-1");

        // 退会処理（貸出中）
        System.out.println("--- 貸出中会員の退会 ---");
        library.unregisterMember("M001");

        // 全返却後退会
        library.receiveBookFromMember("M001", "978-3");
        library.receiveBookFromMember("M001", "978-4");
        library.receiveBookFromMember("M001", "978-5");
        System.out.println("--- 全返却後の退会 ---");
        library.unregisterMember("M001");
    }
}
