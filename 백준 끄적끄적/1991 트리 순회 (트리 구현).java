import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder preorder_sb = new StringBuilder();
    static StringBuilder inorder_sb = new StringBuilder();
    static StringBuilder postorder_sb = new StringBuilder();

    public static class Node {
        char Node; // 현재 노드
        Node left; // 왼쪽 자식 노드
        Node right; // 오른쪽 자식 노드
        int depth; // 현재 노드 높이

        Node(char Node, int depth) {
            this.Node = Node;
            this.depth = depth;
        }
    }

    public static class Tree {

        Node root;

        public void createNode(char Node, char left, char right, int depth) {
            if (root == null) {
                root = new Node(Node, depth);

                if (left != '.')
                    root.left = new Node(left, depth + 1);
                if (right != '.')
                    root.right = new Node(right, depth + 1);
            } else
                add(root, Node, left, right, depth);
        }

        public void add(Node root, char Node, char left, char right, int depth) {

            if (root == null)
                return;

            else if (root.Node == Node) {
                if (left != '.')
                    root.left = new Node(left, depth + 1);
                if (right != '.')
                    root.right = new Node(right, depth + 1);
            }

            else {
                add(root.left, Node, left, right, depth + 1);
                add(root.right, Node, left, right, depth + 1);
            }
        }

        public int depth(Node root) {
            int depth, left_depth, right_depth;

            if (root == null)
                depth = 0;
            else {
                left_depth = depth(root.left);
                right_depth = depth(root.right);

                if (left_depth >= right_depth)
                    depth = left_depth + 1;
                else
                    depth = right_depth + 1;
            }

            return depth;

        }


        public void preorder(Node root){

            preorder_sb.append(root.Node);
            if (root.left != null)
                preorder(root.left);
            if (root.right != null)
                preorder(root.right);
            return ;

        }

        public void inorder(Node root) {
            
            if (root.left != null)
                inorder(root.left);
            inorder_sb.append(root.Node);
            if (root.right != null)
                inorder(root.right);
            return;

        }

        public void postorder(Node root) {

            if (root.left != null)
                postorder(root.left);
            if (root.right != null)
                postorder(root.right);
            postorder_sb.append(root.Node);
            return;

        }
        

    //     public void print(Node root, int space) { // 출력 만들다가 멘탈나감 안행

    //         StringBuilder sb = new StringBuilder();
    //         StringBuilder temp = new StringBuilder();
    //         Queue<Node> q = new LinkedList<Node>();

    //         q.add(root);

    //         int depth = root.depth;
    //         for (int i = 0; i < space - depth; i++)
    //             sb.append("  ");
    //         sb.append(root.Node);

    //         while (!q.isEmpty()) {
    //             Node n = q.poll();
    //             if (depth != n.depth) {
    //                 sb.append("\n");
    //                 depth = n.depth;
    //                 for (int i = 0; i < space - depth; i++)
    //                     sb.append("  ");
    //                 sb.append(temp);
    //                 temp = new StringBuilder();
    //             }

    //             // sb.append(n.Node+" ");

    //             if (n.left != null) {
    //                 q.add(n.left);
    //                 temp.append(n.left.Node);
    //             } else {
    //                 temp.append(" ");
    //             }

    //             for (int i = 0; i < space - depth; i++)
    //                 temp.append(" ");

    //             if (n.right != null) {
    //                 q.add(n.right);
    //                 temp.append(n.right.Node);
    //             } else
    //                 temp.append(" ");

    //             for (int i = 0; i < space - depth; i++)
    //                 temp.append(" ");

    //         }
    //         System.out.println(sb);

    //     }
    }

    public static void make_tree(int N) {

        Tree tree = new Tree();

        for (int i = 0; i < N; i++) {
            char Node = sc.next().charAt(0);
            char left = sc.next().charAt(0);
            char right = sc.next().charAt(0);

            tree.createNode(Node, left, right, 1);
        }

        tree.preorder(tree.root);
        System.out.println(preorder_sb);

        tree.inorder(tree.root);
        System.out.println(inorder_sb);

        tree.postorder(tree.root);
        System.out.println(postorder_sb);

    }

    public static void main(String[] args) throws IOException {

        int N = sc.nextInt();
        make_tree(N);

    }
}
