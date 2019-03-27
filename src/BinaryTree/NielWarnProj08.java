
package BinaryTree;

import java.util.Scanner;

public class NielWarnProj08 {

    public static void main(String[] args) {
        String rootQuestion = "Does it have legs? (y/n) ";
        String response;
        String newQuestion;
        Boolean finished = false;

        BinaryTree<String> tree1 = new BinaryTree();
        
//        BinaryTree<String> tree2 = new BinaryTree();
//        BinaryTree<String> tree3;
//        
        tree1.setRootItem(rootQuestion);
//        tree1.attachLeft("cat");
//        tree1.attachRight("snake");
        
        if (tree1.detachLeftSubtree().isEmpty() && tree1.detachRightSubtree().isEmpty()) {
            System.out.println("This is a leaf");
        } else {
            System.out.println("Not a leaf");
        }
//        tree3 = tree1.detachLeftSubtree();
//        
//        tree2.setRootItem("Does it bark? (y/n)");
//        tree2.attachLeft("dog");
//        tree2.attachRight(tree3.getRootItem());
//        
//        System.out.println("Tree3 root: " + tree3.getRootItem());
//        tree1.attachLeftSubtree(tree2);
//        
//        System.out.println("Tree1 root: " + tree1.getRootItem());
//        System.out.println("Tree1 leftChild: " + tree1.root.leftChild.item);
//        System.out.println("Tree1 rightChild: " + tree1.root.rightChild.item);
//        System.out.println("Tree1 leftChildleftChild: " + tree1.root.leftChild.leftChild.item);
//        System.out.println("Tree1 leftChildrightChild: " + tree1.root.leftChild.rightChild.item);
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
