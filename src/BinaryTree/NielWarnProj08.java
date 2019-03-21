
package BinaryTree;

import java.util.Scanner;

public class NielWarnProj08 {

    public static void main(String[] args) {
        String questionRoot = "Does it have legs? (y/n) ";
        String questionFirstNo = "Is it a snake? (y/n) ";
        String questionFirstYes = "Is it a cat? (y/n) ";
        String response;
        Boolean finished = false;

        BinaryTree tree1 = new BinaryTree();
        BinaryTree tree2 = new BinaryTree();
        BinaryTree tree3 = new BinaryTree();
        
        tree1.setRootItem(questionRoot);
        tree1.attachLeft(questionFirstYes);
        tree1.attachRight(questionFirstNo);
        
        tree2.setRootItem(questionFirstYes);
        tree2.attachLeft("Does it swim? (y/n) ");
        tree2.attachRight("Does it live underground? (y/n) ");
        
        tree3 = tree1.detachLeftSubtree();
        System.out.println("Tree3 root: " + tree3.getRootItem());
        tree1.attachLeftSubtree(tree2);
        
        System.out.println("Tree1 root: " + tree1.getRootItem());
        System.out.println("Tree2 root: " + tree2.getRootItem());
//        
//        Scanner input = new Scanner(System.in);
//        
//        do {
//            System.out.println("Think of an animal and I will guess it.");
//            System.out.print(newTree.getRootItem());
//            response = input.next();
//            if (response.compareToIgnoreCase("n") == 0) {
//                System.out.println(questionFirstNo);
//                
//            }
//            finished = true;
//        } while (!finished);
    }
    
}
