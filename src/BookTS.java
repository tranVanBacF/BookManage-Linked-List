

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class BookTS {

    Validate v = new Validate();
    NodeBook root;

    BookTS() {
        root = null;
    }

    boolean isEmpty() {
        return (root == null);
    }

    void clear() {
        root = null;
    }

    void insert(Book x) {
        if (isEmpty()) {
            root = new NodeBook(x);
            return;
        }
        NodeBook f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.getBcode().equalsIgnoreCase(x.getBcode())) {
                System.out.println("The key " + x + " already exists, no insertion");
                return;
            }
            f = p;
            if (p.info.getBcode().compareToIgnoreCase(x.getBcode()) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (f.info.getBcode().compareToIgnoreCase(x.getBcode()) > 0) {
            f.left = new NodeBook(x);
        } else {
            f.right = new NodeBook(x);
        }
    }

    //
    void readDataFromFile(String fname) throws IOException {
        RandomAccessFile f = new RandomAccessFile(fname, "r");
        String s;
        String[] a;
        String Bcode, Tittle;
        int Quantity, Lended;
        double Price;
        Book x;
        while (true) {
            s = f.readLine();
            if (s == null || s.trim().equals("")) {
                break;
            }
            a = s.split("[|]");
            Bcode = a[0].trim();
            Tittle = a[1].trim();
            Quantity = Integer.parseInt(a[2].trim());
            Lended = Integer.parseInt(a[3].trim());
            Price = Double.parseDouble(a[4].trim());
            x = new Book(Bcode, Tittle, Quantity, Lended, Price);
            insert(x);
        }
        f.close();
    }

    void inOrderFile(NodeBook n, RandomAccessFile f) throws IOException {
        if (n == null) {
            return;
        }
        inOrderFile(n.left, f);
        String formatStr = "%5s |%14s      |%6d    |%5d   |%6.1f %n";
        f.writeBytes(String.format(formatStr, n.info.getBcode(), n.info.getTitle(), n.info.getQuantity(), n.info.getLended(), n.info.getPrice()));
        inOrderFile(n.right, f);
    }

    void inOrderToFile(String fname) {
        RandomAccessFile f;
        NodeBook n = root;
        try {
            System.out.println("Writing data...");
            f = new RandomAccessFile(fname, "rw");
            f.setLength(0);
            f.writeBytes(" Code |        Title       | Quantity | Lended | Price |  Value\r\n");
            f.writeBytes("_________________________________________________________________\r\n");
            inOrderFile(n, f);
            f.close();
            System.out.println("File written successfully!\n\n\n\n\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
///1.6
    NodeBook search(NodeBook p, String bcode) {
        if (p == null) {
            return null;
        }
        if (p.info.getBcode().equalsIgnoreCase(bcode)) {
            return p;
        }
        if (p.info.getBcode().compareToIgnoreCase(bcode) > 0) {
            return search(p.left, bcode);
        } else {
            return search(p.right, bcode);
        }
    }

    // search and change
    void searchChange(String bcode) {
        NodeBook q = search(root, bcode);
        q.info.setQuantity(99);
    }

//1.2
    void inputAndInsert() {
        String bcode;
        String title;
        int quantity;
        int lended;
        double price;
        bcode = v.checkString("Enter bcode:");
        if (search(root, bcode) != null) {
            System.out.println("bcode exists");
            return;
        }
        title = v.checkString("Enter title: ");
        quantity = v.checkInt("Enter quantity: ");
        lended = v.checkInt("Enter lended: ");
        price = v.checkDouble("Enter price: ");

        NodeBook q = new NodeBook(new Book(bcode, title, quantity, lended, price));

        if (isEmpty()) {
            root = q;
            return;
        }
        NodeBook f, p;
        f = null;
        p = root;
        while (p != null) {

            f = p;
            if (p.info.getBcode().compareToIgnoreCase(bcode) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (f.info.getBcode().compareToIgnoreCase(bcode) > 0) {
            f.left = q;
        } else {
            f.right = q;
        }
    }
    
//1.3.      In-order traverse

    void visit(NodeBook p) {
        if (p != null) {
            System.out.print(p.info + " ");
        }
    }

    void inOrder(NodeBook p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    //1.4 breadth-first traverse
    void breadth(NodeBook p) {
        if (p == null) {
            return;
        }
        MyQueue q = new MyQueue();
        q.enqueue(p);
        NodeBook r;
        while (!q.isEmpty()) {
            r = q.dequeue();
            visit(r);
            if (r.left != null) {
                q.enqueue(r.left);
            }
            if (r.right != null) {
                q.enqueue(r.right);
            }
        }
    }
    
    //delete by bycode
    void deleByCopy(String bcode) {
        if (isEmpty()) {
            return;
        }
        NodeBook f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.getBcode().equalsIgnoreCase(bcode)) {
                break;
            }
            f = p;
            if (p.info.getBcode().compareToIgnoreCase(bcode) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p == null) {
            return; // not found
        }   // p is a leaf-node
        if (p.left == null && p.right == null) {
            if (f == null) { // p is a root
                root = null;
            } else {
                if (p == f.left) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }

        // p has left son ony
        if (p.left != null && p.right == null) {
            if (f == null) { // p is a root
                root = p.left;
            } else {
                if (p == f.left) {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }

        // p has right son ony
        if (p.left == null && p.right != null) {
            if (f == null) { // p is a root
                root = p.right;
            } else {
                if (p == f.left) {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }

        // p has both 2 sons
        if (p.left != null && p.right != null) {
            NodeBook q = p.left; // q is the root of he left bub-tree
            NodeBook frp, rp;
            frp = null;
            rp = q;
            // find the right-most node in the left-subtree
            while (rp.right != null) {
                frp = rp;
                rp = rp.right;
            }
            // rp now is the right-most node 
            p.info = rp.info; // replace p's content with rp's content
            if (frp == null) { // q is the right-mst node
                p.left = q.left;
            } else {
                frp.right = rp.left;
            }
        }
        System.out.println("sucessful");
    }


    void balance(ArrayList<Book> t, int i, int j) {
        if (i > j) {
            return;
        }
        int k = (i + j) / 2;
        insert(t.get(k));
        balance(t, i, k - 1);
        balance(t, k + 1, j);
    }

    void balance() {
        ArrayList<Book> t = new ArrayList<Book>();
        inOrder2(t, root);
        clear();
        int n = t.size();
        balance(t, 0, n - 1);
    }

    //1.9
    int CountNumberOfBook() {
        ArrayList<Book> t = new ArrayList<Book>();
        inOrder2(t, root);
        return (t.size());
    }
    
    ///
    void inOrder2(ArrayList<Book> t, NodeBook p) {
        if (p == null) {
            return;
        }
        inOrder2(t, p.left);
        t.add(p.info);
        inOrder2(t, p.right);
    }
   

   
    
}
