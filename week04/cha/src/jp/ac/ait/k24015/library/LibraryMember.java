package Copy.jp.ac.ait.k24015.library;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private final String memberId;
    private String name;
    private List<Book> borrowedBooks;
    private int maxBorrowLimit;

    public LibraryMember(String memberId, String name) {
        this(memberId, name, 5);
    }

    public LibraryMember(String memberId, String name, int maxBorrowLimit) {
        if (maxBorrowLimit <= 0) {
            System.err.println("警告: 最大貸出冊数が無効です。1冊に設定します。");
            maxBorrowLimit = 1;
        }
        this.memberId = memberId;
        this.name = name;
        this.maxBorrowLimit = maxBorrowLimit;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public int getMaxBorrowLimit() {
        return maxBorrowLimit;
    }

    public int getCurrentBorrowCount() {
        return borrowedBooks.size();
    }

    public boolean canBorrowMore() {
        return getCurrentBorrowCount() < maxBorrowLimit;
    }

    public boolean borrowBook(Book book) {
        if (!canBorrowMore()) {
            System.err.println("貸出上限に達しています。");
            return false;
        }
        if (book.isBorrowed()) {
            System.err.println("本がすでに貸出中です: " + book.getTitle());
            return false;
        }
        if (book.borrowBook()) {
            borrowedBooks.add(book);
            return true;
        }
        return false;
    }

    public int borrowBooks(Book[] booksToBorrow) {
        int count = 0;
        for (Book book : booksToBorrow) {
            if (borrowBook(book)) {
                count++;
            }
        }
        return count;
    }

    public boolean returnBook(Book book) {
        if (!borrowedBooks.contains(book)) {
            System.err.println("この本は会員が借りていません: " + book.getTitle());
            return false;
        }
        if (book.returnBook()) {
            borrowedBooks.remove(book);
            return true;
        }
        return false;
    }

    public void displayMemberInfo() {
        System.out.println("会員ID: " + memberId);
        System.out.println("名前: " + name);
        System.out.println("最大貸出冊数: " + maxBorrowLimit);
        System.out.println("現在の貸出冊数: " + getCurrentBorrowCount());
        System.out.println("貸出中の本:");
        for (Book book : borrowedBooks) {
            System.out.println(" - " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")");
        }
    }
}
