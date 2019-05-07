

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, Exception {
        BookTS b = new BookTS();
        Validate v = new Validate();
        int k;
        while (true) {
            displayMenu();
            int choice = v.checkchoice("Your Choice: ");
            switch (choice) {
                case 1:
                    b.readDataFromFile("book.txt");
                    break;
                case 2:
                    b.inputAndInsert();
                    //   b.f2();
                    break;
                case 3:
                    b.inOrder(b.root);
                    System.out.println();
                    break;
                case 4:
                    b.breadth(b.root);
                    System.out.println();
                    break;
                case 5:
                    b.inOrderToFile("Book2.txt");
                    break;
                case 6:
                    b.search(b.root, "2");
                    break;
                case 7:
                    b.deleByCopy("5");
                    break;
                case 8:
                    b.balance();
                    break;

                case 9:
                    System.out.println("Number of books: " + b.CountNumberOfBook() + "\n\n\n\n\n");
                    b.breadth(b.root);

                    break;

            }

        }

    }

    public static void displayMenu() {
        System.out.println("Book list:\n"
                + "1.1.      Load data from file\n"
                + "1.2.      Input & insert data\n"
                + "1.3.      In-order traverse\n"
                + "1.4.       Breadth-first traverse\n"
                + "1.5.        In-order traverse to file\n"
                + "1.6.      Search by bcode\n"
                + "1.7.       Delete by bcode by copying\n"
                + "1.8.        Simply balancing\n"
                + "1.9.       Count number of books\n"
        );
    }

}
