package lw06.unguided;

import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        BinarySearchTree bst = new BinarySearchTree();

        Scanner read = new Scanner(new File("src/lw06/unguided/treenumbers.txt"));

        String numbers = read.nextLine();
        String[] values = numbers.split("\\s+");

        for (int i = 0; i < values.length; i++) {
            bst.insert(Integer.parseInt(values[i]));
        }

        int target = read.nextInt();

        System.out.print("Preorder : ");
        bst.preOrder();

        System.out.print("Inorder : ");
        bst.inOrder();

        System.out.print("Postorder : ");
        bst.postOrder();

        System.out.println("Search " + target + " : " + bst.search(target));
        System.out.println("Minimum : " + bst.findMin());
        System.out.println("Maximum : " + bst.findMax());

        read.close();
    }
}
