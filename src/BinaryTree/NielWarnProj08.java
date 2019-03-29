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
            
            response = userYesNo(response, input);
            
            // checks yes or no to first question, does it have legs?
            if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                // if yes, it pushes curr onto the stack
                treeStack.push(curr);
                // reassigns curr to left subtree
                curr = curr.detachLeftSubtree();
                
                // loop while curr is not a leaf node
                while (!isLeaf(curr)) {
                    // print the new question
                    System.out.print(curr.getRootItem());
                    // assign response to the user input
                    response = input.nextLine();
                    // validate user's yes or no response
                    userYesNo(response, input);
                    
                    // insert the new question into the temp tree
                    insertNewQuestion(response, input, curr, temp);
                    curr = attachSubtree(curr, temp, treeStack);
                }
                
                // while still yes, if curr is a leafNode then guess if single root item is what it is
                if (isLeaf(curr)) {
                    System.out.print("Is it a " + curr.getRootItem() + "? " );
                    response = input.nextLine();
                    userYesNo(response, input);
                    
                    // use insertNewQuestion in case guess is wrong
                    insertNewQuestion(response, input, curr, temp);
                    curr = attachSubtree(curr, temp, treeStack);
                }
                
//                // assign prev to the binaryTree on the treeStack
//                prev = treeStack.pop();
//                // attach the leftSubtree
//                prev.attachLeftSubtree(temp);
                
            } else {
                treeStack.push(curr);
                curr = curr.detachRightSubtree();
                System.out.print("Is it a " + curr.getRootItem() + "? " );
                response = input.nextLine();
                userYesNo(response, input);
                
                insertNewQuestion(response, input, curr, temp);
//                
//                prev = treeStack.pop();
//                prev.attachRightSubtree(temp);
//                tree1 = prev;
//                curr = tree1;
            }
            
            // set tree1 to the item on the treeStack
            
            tree1 = curr;
            // set curr to tree1 to be used in repeat
//            curr = tree1;
            
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
    
    /*
     *  method takes a curr binaryTree and a treeStack and checks if subtree of current
     *  is empty, if so, then it will attach it to the appropriate child
     */
    public static BinaryTree<String> attachSubtree(BinaryTree<String> curr, BinaryTree<String> temp, Stack<BinaryTree<String>> treeStack) {
        BinaryTree<String> stackTree = treeStack.pop();
        
        //replace curr with temp
        if (isLeaf(curr)) {
            curr.makeEmpty();
            curr = temp;
            stackTree.attachLeftSubtree(curr);
            curr = stackTree;
        }
        return curr;
    }
    
    /*
     *  call insertNewQuestion which takes the user response, scanner input
     *  the curr binaryTree, and a temp binaryTree, manipulates the binaryTrees
     *  to then give you a binaryTree that has a new question as the root and
     *  two answers, one left child and one right child
     */
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
            newQuestion = input.nextLine().toUpperCase() + " ";

            temp.setRootItem(newQuestion);
            temp.attachRight(curr.getRootItem());
            temp.attachLeft(newAnswer);
        }
    }
    
    public static String userYesNo(String response, Scanner input) {
        while (!(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y") || 
                response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n"))) {
            System.out.println("Not a valid choice");
            System.out.print("Please enter 'yes' or 'no': ");
            response = input.nextLine();
            return response;
        }
        return response;
    }
}
