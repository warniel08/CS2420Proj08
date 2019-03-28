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
        BinaryTree<String> prev = new BinaryTree();
        BinaryTree<String> temp = new BinaryTree();
        
        tree1.setRootItem(root1Question.toUpperCase());
        tree1.attachLeft(root1LeftAnswer.toUpperCase());
        tree1.attachRight(root1RightAnswer.toUpperCase());
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome to the Animal Guessing Computer Learning Game");
        
        do {
            System.out.println("Think of an animal and I will guess it.");
            System.out.print(tree1.getRootItem());
            response = input.nextLine();
            
            userYesNo(response, input);
            
            if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                treeStack.push(curr);
                curr = curr.detachLeftSubtree();
                while (!isLeaf(curr)) {
                    System.out.print(curr.getRootItem());
                    response = input.nextLine();
                    userYesNo(response, input);

                    insertNewQuestion(response, input, curr, temp);
                }
                
                if (isLeaf(curr)) {
                    System.out.print("Is it a " + curr.getRootItem() + "? " );
                    response = input.nextLine();
                    userYesNo(response, input);

                    insertNewQuestion(response, input, curr, temp);
                }
                
                prev = treeStack.pop();
                prev.attachLeftSubtree(temp);
                tree1 = prev;
                curr = tree1;
            } else {
                treeStack.push(curr);
                curr = curr.detachRightSubtree();
                System.out.print("Is it a " + curr.getRootItem() + "? " );
                response = input.nextLine();
                userYesNo(response, input);
                
                insertNewQuestion(response, input, curr, temp);
                
                prev = treeStack.pop();
                prev.attachRightSubtree(temp);
                tree1 = prev;
                curr = tree1;
            }
            
            System.out.print("Continue? ");
            response = input.nextLine();

            userYesNo(response, input);
            
            finished = !(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y"));
            
        } while (!finished);
    }
    
    public static boolean isLeaf(BinaryTree<String> tree1) {
            return tree1.detachLeftSubtree().isEmpty() && tree1.detachRightSubtree().isEmpty();
    }
    
    /*
     *  method returns a boolean, if a tree has a right subtree
     *  then it should attach a left subtree, if not then attach
     *  the tree to the right
     */
    public static boolean hasRight(BinaryTree<String> curr, BinaryTree<String> temp) {
        if (curr.detachRightSubtree().isEmpty()) {
            
        }
        return curr.detachLeftSubtree().isEmpty();
    }
    
    public static void insertNewQuestion(String response, Scanner input, 
            BinaryTree<String> curr, BinaryTree<String> temp) {
        String newQuestion, newAnswer;
        if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
            System.out.println("I win!");
        } else {
            System.out.print("I give up. What is it? ");
            newAnswer = input.nextLine().toUpperCase();

            System.out.println("Please type a question whose answer is yes for " + newAnswer +
                    " and no for " + curr.getRootItem() + ":");
            newQuestion = input.nextLine().toUpperCase();

            temp.setRootItem(newQuestion);
            temp.attachRight(curr.getRootItem());
            temp.attachLeft(newAnswer);
        }
    }
    
    public static void userYesNo(String response, Scanner input) {
        while (!(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y") || 
                response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n"))) {
            System.out.println("Not a valid choice");
            System.out.print("Please enter 'yes' or 'no': ");
            response = input.nextLine();
        }
    }
}
