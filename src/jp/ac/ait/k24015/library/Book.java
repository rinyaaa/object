package src.jp.ac.ait.k24015.library;

public class Book {
    private final String isbn;
    private String title;
    private String author;
    private boolean isBorrowed = false;

    public Book(String isbn, String title, String author) {

        // ISBN、タイトル、著者は必須とし、nullや空の場合は初期値を設定して警告する（System.err.printlnを使用する）。
        if (isbn == null || isbn.isEmpty()) {
            System.err.println("警告: ISBNが無効です。");
            isbn = "UNKNOWN_ISBN";
        }
        if (title == null || title.isEmpty()) {
            System.err.println("警告: タイトルが無効です。");
            title = "無題";
        }
        if (author == null || author.isEmpty()) {
            System.err.println("警告: 著者が無効です。");
            author = "不明";
        }

        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public boolean borrowBook() {
        if (isBorrowed) {
            System.err.println("この本はすでに貸出中です: " + title);
            return false;
        }
        isBorrowed = true;
        return true;
    }

    public boolean returnBook() {
        if (!isBorrowed) {
            System.err.println("この本はすでに返却済みです: " + title);
            return false;
        }
        isBorrowed = false;
        return true;
    }

    public String getBookDetails() {
        return String.format("ISBN: %s, タイトル: %s, 著者: %s, 状況: %s",
                isbn, title, author, isBorrowed ? "貸出中" : "貸出可能");
    }
}
