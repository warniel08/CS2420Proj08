
package BinaryTree;

import java.util.Scanner;
import java.util.Stack;

public class NielWarnProj08 {

    public static void main(String[] args) {
        String root1Question = "Does it have legs? ";
        String root1LeftAnswer = "cat";
        String root1RightAnswer = "snake";
        
        String response;
        Boolean finished = false;
        
        Stack<BinaryTree<String>> treeStack = new Stack<>();

        BinaryTree<String> tree1 = new BinaryTree();
        
        BinaryTree<String> curr = tree1;
//        
        tree1.setRootItem(root1Question);
        tree1.attachLeft(root1LeftAnswer);
        tree1.attachRight(root1RightAnswer);
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome to the Animal Guessing Computer Learning Game");
        do {
            System.out.println("Think of an animal and I will guess it.");
            System.out.print(tree1.getRootItem());
            response = input.next();
            
            userYesNo(response, input);
            
            if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                treeStack.push(curr);
                curr = curr.detachLeftSubtree();
            } else {
                treeStack.push(curr);
                curr = curr.detachRightSubtree();
            }
            
            System.out.print("Continue? ");
            response = input.next();
            userYesNo(response, input);
            
            finished = true;
        } while (!finished);
        
        
        
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
    }
    
    public static boolean isLeaf(BinaryTree<String> tree1) {
            return tree1.detachLeftSubtree().isEmpty() && tree1.detachRightSubtree().isEmpty();
    }
    
    public static void userYesNo(String response, Scanner input) {
        while (!(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y") || response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n"))) {
            System.out.println("Not a valid choice");
            System.out.print("Please enter 'yes' or 'no': ");
            response = input.next();
        }
    }
}
