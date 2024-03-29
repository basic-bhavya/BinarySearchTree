/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*in delete func reduce no. of nodes*/
package binarysearchtree;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Bhavya Jain
 */
public class BinarySearchTree {

    static int height = 0;
    int j;
    static int nodeCount = 0;

    static BinarySearchTree b;
    public boolean found = false;
    public Node foundNode;

    //coordinates of currently processing node
    public int xcoord = 500, ycoord = 100;
    String toDisplay = "";

    //component declaration
    JTextField number = new JTextField(20);
    JTextField printfield = new JTextField();
    JButton insert = new JButton("Insert Value");
    JButton delete = new JButton("Delete Value");
    JButton print = new JButton("Print Tree");
    JButton find = new JButton("Find Node");

    JLabel Height = new JLabel("Height : ");
    JLabel noOfNodes = new JLabel("                No. of nodes : ");
    JLabel HeightN = new JLabel("");
    JLabel noOfNodesN = new JLabel("");
    JPanel buttongroup = new JPanel();
    JPanel mypanel = new JPanel();
    JPanel lastpanel = new JPanel();
    JFrame f;

    public static Node root;

    public BinarySearchTree() {
        this.root = null;

        //Initialise components and panels
        drawStuff canvas = new drawStuff();
        canvas.setPreferredSize(new Dimension(1000, 800));
        f = new JFrame();
//        f.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        mypanel.add(number);
        buttongroup.add(insert);
        buttongroup.add(delete);
        buttongroup.add(print);
        buttongroup.add(find);
        buttongroup.setLayout(new GridLayout(2, 2));
        mypanel.add(buttongroup);
        mypanel.setLayout(new GridLayout(3, 1));

        lastpanel.add(Height);
        HeightN.setBackground(Color.yellow);
        lastpanel.add(HeightN);
        lastpanel.add(noOfNodes);
        noOfNodesN.setBackground(Color.yellow);
        lastpanel.add(noOfNodesN);

        printfield.setEditable(false);
        mypanel.add(printfield);

        f.add(canvas, BorderLayout.CENTER);
        f.add(mypanel, BorderLayout.NORTH);
        f.add(lastpanel, BorderLayout.PAGE_END);

        //Listeners
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                b.insert(Integer.parseInt(number.getText()));
                canvas.rootset(root);
                number.setText("");
                canvas.repaint();
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                b.delete(Integer.parseInt(number.getText()));
                canvas.rootset(root);
                number.setText("");
                canvas.repaint();
            }
        });

        number.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == 10) {
                    b.insert(Integer.parseInt(number.getText()));
                    canvas.rootset(root);
                    number.setText("");
                    canvas.repaint();
                }
            }

        });

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                display(root);
                printfield.setText(toDisplay);
                toDisplay = "";
            }
        });

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (find(Integer.parseInt(number.getText()))) {
                    printfield.setText("Number found");
                } else {
                    foundNode = null;
                    printfield.setText("Number not found");
                    found = false;
                }

                System.out.println("in button: " + found);
                canvas.repaint();
            }
        });

        /*
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (find(Integer.parseInt(number.getText()))) {
                    found = true;
                    canvas.getfound(found, foundNode);
                    System.out.println(found + " of listener");
                    found = false;
                    canvas.repaint();
                }
            }
        });*/
//           Height.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                int h=getheight(root);
//                String s=String.valueOf(h);
//                 printfield.setText(s);
//              
//            }
//        });
//            noOfNodes.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                
//                
//                String s=String.valueOf(nodeCount);
//                 printfield.setText(s);
//              
//            }
//        });
        //frame settings
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        add(addno, BorderLayout.PAGE_END);
    }

    //COMPLETE THIS
//    public Node delete2(Node node, int id){
//        if(node==null)
//            return null;
//    }
    public boolean find(int id) {
        Node current = root;
        while (current != null) {
            if (current.data == id) {
                foundNode = current;
                return true;
            } else if (current.data > id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        Stack<Node> temp = new Stack<>();

        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;
        while (current.data != id) {
            parent = current;
            if (current.data > id) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }
        //if i am here that means we have found the node
        temp.push(current);
        //Case 1: if node to be deleted has no children
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild == true) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } //Case 2 : if node to be deleted has only one child
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                while (!temp.isEmpty()) {
                    Node trNode = temp.peek();
                    temp.pop();

                    trNode.Nx += 60;
                    trNode.Ny -= 50;

                    if (trNode.left != null) {
                        temp.push(trNode.left);
                    }
                    if (trNode.right != null) {
                        temp.push(trNode.right);
                    }
                }
                parent.left = current.left;
            } else {
                while (!temp.isEmpty()) {
                    Node trNode = temp.peek();
                    temp.pop();

                    trNode.Nx += 60;
                    trNode.Ny -= 50;

                    if (trNode.left != null) {
                        temp.push(trNode.left);
                    }
                    if (trNode.right != null) {
                        temp.push(trNode.right);
                    }
                }
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                while (!temp.isEmpty()) {
                    Node trNode = temp.peek();
                    temp.pop();

                    trNode.Nx -= 60;
                    trNode.Ny -= 50;

                    if (trNode.left != null) {
                        temp.push(trNode.left);
                    }
                    if (trNode.right != null) {
                        temp.push(trNode.right);
                    }
                }
                parent.left = current.right;
            } else {
                while (!temp.isEmpty()) {
                    Node trNode = temp.peek();
                    temp.pop();

                    trNode.Nx -= 60;
                    trNode.Ny -= 50;

                    if (trNode.left != null) {
                        temp.push(trNode.left);
                    }
                    if (trNode.right != null) {
                        temp.push(trNode.right);
                    }
                }

                parent.right = current.right;
            }
        } else if (current.left != null && current.right != null) {

            //now we have found the minimum element in the right sub tree
            Node successor = getSuccessor(current);
            System.out.println(successor.data);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                successor.Nx = current.Nx;
                successor.Ny = current.Ny;
                while (!temp.isEmpty()) {
                    Node trNode = temp.peek();
                    temp.pop();
                    if (trNode.left != null) {
                        temp.push(trNode.left);
                    }
                    if (trNode.right != null) {
                        temp.push(trNode.right);
                    }
                }
                parent.left = successor;
            } else {
                successor.Nx = current.Nx;
                successor.Ny = current.Ny;
                while (!temp.isEmpty()) {
                    Node trNode = temp.peek();
                    temp.pop();
                    if (trNode.left != null) {
                        temp.push(trNode.left);
                    }
                    if (trNode.right != null) {
                        temp.push(trNode.right);
                    }
                }
                parent.right = successor;
            }
            successor.left = current.left;
        }

        nodeCount--;
        HeightN.setText(getheight(root) + "");
        System.out.println(getheight(root));
        noOfNodesN.setText(nodeCount + "");

        return true;
    }

    public Node getSuccessor(Node deleleNode) {
        Node successsor = null;
        Node successsorParent = null;
        Node current = deleleNode.right;
        while (current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
//		successsorParent
        if (successsor != deleleNode.right) {
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }

    public void insert(int id) {

        Node newNode = new Node(id);
        nodeCount++;
        if (root == null) {
            root = newNode;
            root.Nx = 500;
            root.Ny = 10;

            HeightN.setText(getheight(root) + "");
            System.out.println(getheight(root));
            noOfNodesN.setText(nodeCount + "");
//            newNode.Nx = getWidth() / 2;
//            newNode.Ny = y;
//            f.add(new Circle(root.Nx, root.Ny, root.data));
            return;
        }

        Node current = root;
        Node parent = null;
        while (true) {
            parent = current;
            if (id < current.data) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    newNode.Nx = parent.Nx - 60;
                    newNode.Ny = parent.Ny + 50;

                    HeightN.setText(getheight(root) + "");
                    System.out.println(getheight(root));
                    noOfNodesN.setText(nodeCount + "");
//                    f.add(new Circle(newNode.Nx + 25, newNode.Ny + 25, newNode.data));
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    newNode.Nx = parent.Nx + 60;
                    newNode.Ny = parent.Ny + 50;

                    HeightN.setText(getheight(root) + "");
                    System.out.println(getheight(root));
                    noOfNodesN.setText(nodeCount + "");
//                    add(new Circle(newNode.Nx + 25, newNode.Ny + 25, newNode.data));
                    return;
                }
            }
        }
    }

    //Used in PRINT function
    public void display(Node root) {
        if (root != null) {
            display(root.left);
            System.out.print(" " + root.data);
            toDisplay = toDisplay + " " + root.data;
            display(root.right);

            //MY CODE
        }
    }

//    void initialize() {
//        addno.add(number);
//        addno.add(insert);
//        f.add(addno);
//    }
//    public void start() {
//        f = new JFrame("Binary Search Tree");
//        
//        f.setLayout(new FlowLayout());
//        BinarySearchTree b = new BinarySearchTree();
//        f.add(new drawStuff());
//    }
    //MAIN FUNCTION
    public static void main(String arg[]) {
        b = new BinarySearchTree();
    }

    int getheight(Node node) {
        if (node == null) {
            return 0;
        }

        int lheight = getheight(node.left);
        int rheight = getheight(node.right);
        return 1 + Math.max(lheight, rheight);

    }

    void updatexy() {
//        Stack<Node> nodestack = new Stack<>();
//        nodestack.push(root);
//        while (nodestack.isEmpty() == false) {
//            Node mynode = nodestack.peek();
//            System.out.println("going");
//
//            nodestack.pop();
//
//            if (mynode.right != null) {
//                mynode.right.Nx = mynode.Nx + 60;
//                mynode.right.Ny = mynode.Ny + 50;
//                nodestack.push(mynode.right);
//            }
//
//            if (mynode.left != null) {
//                mynode.left.Nx = mynode.Nx - 60;
//                mynode.right.Ny = mynode.Ny + 50;
//                nodestack.push(mynode.left);
//            }
//        }

    }

    //Drawstuff is the Class for Canvas, or tree Display on screen
    class drawStuff extends JPanel {

        Node drawroot;
        boolean check;
        Stack<Node> nodestack = new Stack<>();

        void rootset(Node root) {
            drawroot = root;
            nodestack.push(drawroot);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
//        System.out.println("Hello");
            while (nodestack.isEmpty() == false) {

                System.out.println(found + " " + foundNode);
                //print the number on screen
                Node mynode = nodestack.peek();

                if (found == true && mynode == foundNode) {
                    g.drawString("-->", mynode.Nx - 50, mynode.Ny);
                    found = false;
                }

                g.setColor(Color.yellow);
                g.fillOval(mynode.Nx - 20, mynode.Ny - 27, 50, 50);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(mynode.data), mynode.Nx, mynode.Ny);

                nodestack.pop();

                if (mynode.left != null) {
                    nodestack.push(mynode.left);
                    g.drawLine(mynode.Nx - 17, mynode.Ny + 14, mynode.left.Nx + 7, mynode.left.Ny);
                }

                if (mynode.right != null) {
                    nodestack.push(mynode.right);
                    g.drawLine(mynode.Nx + 24, mynode.Ny + 14, mynode.right.Nx, mynode.right.Ny);
                }
            }
//            if (mynode.left != null) {
//                nodestack.push(mynode.left);
//            }
        }

    }
}

//Node Class
class Node {

    int data;
    int Nx, Ny;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
