package Copy.jp.ac.ait.k24015.library;

import java.util.ArrayList;
import java.util.Locale;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<LibraryMember> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public boolean addBook(Book book) {
        for (Book b : books) {
            if (b.getIsbn().equals(book.getIsbn())) {
                System.out.println("すでに同じISBNの本が存在します。");
                return false;
            }
        }
        books.add(book);
        return true;
    }

    public boolean removeBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                if (b.isBorrowed()) {
                    System.out.println("この本は貸出中のため削除できません。");
                    return false;
                }
                books.remove(b);
                return true;
            }
        }
        System.out.println("該当する本が見つかりません。");
        return false;
    }

    public Book findBookByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                return b;
            }
        }
        return null;
    }

    public boolean registerMember(LibraryMember member) {
        for (LibraryMember m : members) {
            if (m.getMemberId().equals(member.getMemberId())) {
                System.out.println("同じ会員IDの会員がすでに存在します。");
                return false;
            }
        }
        members.add(member);
        return true;
    }

    public boolean unregisterMember(String memberId) {
        for (LibraryMember m : members) {
            if (m.getMemberId().equals(memberId)) {
                if (m.getCurrentBorrowCount() > 0) {
                    System.out.println("本を借りているため退会できません。");
                    return false;
                }
                members.remove(m);
                return true;
            }
        }
        System.out.println("該当する会員が見つかりません。");
        return false;
    }

    public LibraryMember findMemberById(String memberId) {
        for (LibraryMember m : members) {
            if (m.getMemberId().equals(memberId)) {
                return m;
            }
        }
        return null;
    }

    public boolean lendBookToMember(String memberId, String isbn) {
        LibraryMember member = findMemberById(memberId);
        Book book = findBookByIsbn(isbn);
        if (member == null) {
            System.out.println("会員が存在しません。");
            return false;
        }
        if (book == null) {
            System.out.println("本が存在しません。");
            return false;
        }
        return member.borrowBook(book);
    }

    public boolean receiveBookFromMember(String memberId, String isbn) {
        LibraryMember member = findMemberById(memberId);
        Book book = findBookByIsbn(isbn);
        if (member == null) {
            System.out.println("会員が存在しません。");
            return false;
        }
        if (book == null) {
            System.out.println("本が存在しません。");
            return false;
        }
        return member.returnBook(book);
    }

    public Book[] searchBook(String keyword) {
        ArrayList<Book> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase(Locale.ROOT);
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(lowerKeyword) ||
                    b.getAuthor().toLowerCase().contains(lowerKeyword)) {
                results.add(b);
            }
        }
        return results.toArray(new Book[0]);
    }

    public void displayAllBooks() {
        System.out.println("--- 蔵書一覧 ---");
        for (Book b : books) {
            System.out.println(b.getTitle() + "\n");
        }
    }

    public void displayAvailableBooks() {
        System.out.println("--- 貸出可能な本一覧 ---");
        for (Book b : books) {
            if (!b.isBorrowed()) {
                System.out.println(b.getTitle() + "\n");
            }
        }
    }

    public void displayAllMembersWithBorrowedBooks() {
        System.out.println("--- 会員と貸出中の本一覧 ---");
        for (LibraryMember m : members) {
            m.displayMemberInfo();
        }
    }
}