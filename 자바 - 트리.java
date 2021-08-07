import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

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

        public void print(Node root, int space) { // 출력 만들다가 멘탈나감 안행

            StringBuilder sb = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            Queue<Node> q = new LinkedList<Node>();

            q.add(root);

            int depth = root.depth;
            for (int i = 0; i < space - depth; i++)
                sb.append("  ");
            sb.append(root.Node);

            while (!q.isEmpty()) {
                Node n = q.poll();
                if (depth != n.depth) {
                    sb.append("\n");
                    depth = n.depth;
                    for (int i = 0; i < space - depth; i++)
                        sb.append("  ");
                    sb.append(temp);
                    temp = new StringBuilder();
                }

                // sb.append(n.Node+" ");

                if (n.left != null) {
                    q.add(n.left);
                    temp.append(n.left.Node);
                } else {
                    temp.append(" ");
                }

                for (int i = 0; i < space - depth; i++)
                    temp.append(" ");

                if (n.right != null) {
                    q.add(n.right);
                    temp.append(n.right.Node);
                } else
                    temp.append(" ");

                for (int i = 0; i < space - depth; i++)
                    temp.append(" ");

            }
            System.out.println(sb);

        }
    }

    public static void make_tree(int N) {

        Tree tree = new Tree();

        for (int i = 0; i < N; i++) {
            char Node = sc.next().charAt(0);
            char left = sc.next().charAt(0);
            char right = sc.next().charAt(0);

            tree.createNode(Node, left, right, 1);
        }

        tree.print(tree.root, tree.depth(tree.root));

    }

    public static void main(String[] args) throws IOException {

        int N = sc.nextInt();
        make_tree(N);

    }
}
