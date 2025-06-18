import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
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

    public void borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
            JOptionPane.showMessageDialog(null, "You have successfully borrowed the book: " + title);
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, the book is already borrowed.");
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            JOptionPane.showMessageDialog(null, "You have successfully returned the book: " + title);
        } else {
            JOptionPane.showMessageDialog(null, "This book was not borrowed.");
        }
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Borrowed: " + isBorrowed;
    }
}

class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        JOptionPane.showMessageDialog(null, "Book added: " + book.getTitle());
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

public class LibraryManagementSystemGUI extends JFrame {
    private Library library;
    private JTextArea displayArea;

    public LibraryManagementSystemGUI() {
        library = new Library();
        setTitle("Library Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton addButton = new JButton("Add Book");
        JButton viewButton = new JButton("View Books");
        JButton borrowButton = new JButton("Borrow Book");
        JButton returnButton = new JButton("Return Book");
        JButton exitButton = new JButton("Exit");

        panel.add(addButton);
        panel.add(viewButton);
        panel.add(borrowButton);
        panel.add(returnButton);
        panel.add(exitButton);

        add(panel, BorderLayout.EAST);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter book title:");
                String author = JOptionPane.showInputDialog("Enter book author:");
                library.addBook(new Book(title, author));
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBooks();
            }
        });

        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter the title of the book to borrow:");
                Book book = library.findBookByTitle(title);
                if (book != null) {
                    book.borrowBook();
                } else {
                    JOptionPane.showMessageDialog(null, "Book not found.");
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter the title of the book to return:");
                Book book = library.findBookByTitle(title);
                if (book != null) {
                    book.returnBook();
                } else {
                    JOptionPane.showMessageDialog(null, "Book not found.");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void displayBooks() {
        ArrayList<Book> books = library.getBooks();
        displayArea.setText("");
        if (books.isEmpty()) {
            displayArea.setText("No books available in the library.");
        } else {
            for (Book book : books) {
                displayArea.append(book.toString() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryManagementSystemGUI().setVisible(true);
            }
            });
        }
    }
        


