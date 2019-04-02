/*
 *  Warner Nielsen
 *  April 1, 2019
 *  CS 2420
 *  Project 08
 *  Garth Sorenson
 */

/*
 *  This program implements the author's code for a BinaryTree. It 
 *  uses the methods to attach/detach Right and Left Subtrees, as it
 *  builds a binary tree to store questions and answers about an
 *  animal that the user has thought of and that the computer will
 *  try to guess as it "learns" more animals.
 */
package BinaryTree;

import java.util.Scanner;
import java.util.Stack;

public class NielWarnProj08 {

    public static void main(String[] args) {
        // initial root values to start the game the same way every time
        String root1Question = "Does it have legs? ";
        String root1LeftAnswer = "cat";
        String root1RightAnswer = "snake";

        String response; // user response to questions
        Boolean finished; // boolean to keep track if user is finished or not

        Stack<BinaryTree<String>> treeStack = new Stack<>(); // stack for storing binaryTrees
        BinaryTree<String> tree1 = new BinaryTree(); // original binaryTree
        BinaryTree<String> curr; // temp tree to be used throughout program
        BinaryTree<String> newTree = new BinaryTree(); // newTree to be used in newQuestion method

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Animal Guessing Computer Learning Game");

        do {
            // set up original binaryTree with root question and answers
            tree1.setRootItem(root1Question.toUpperCase());
            tree1.attachLeft(root1LeftAnswer.toUpperCase());
            tree1.attachRight(root1RightAnswer.toUpperCase());
            
            System.out.println("Think of an animal and I will guess it.");
            // set curr to the original tree
            curr = tree1;

            // loop while curr is not a leaf node
            while (!isLeaf(curr)) {
                // print the new question
                System.out.print(curr.getRootItem());
                // assign response to the user input
                response = input.nextLine();
                // validate user's yes or no response
                userYesNo(response, input);

                // if the user response is yes, push curr on to the stack and set curr 
                // to the leftSubtree of curr
                if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                    treeStack.push(curr);
                    curr = curr.detachLeftSubtree();
                // otherwise, if the user response is no, push the curr binaryTree on the stack
                // and set curr to its rightSubtree
                } else {
                    treeStack.push(curr);
                    curr = curr.detachRightSubtree();
                }
            }

            // the program will guess what animal the user is thinking and validate it
            // with the userYesNo() method
            System.out.print("Is it a " + curr.getRootItem() + "? ");
            response = input.nextLine();
            userYesNo(response, input);

            // if guess is correct print to screen that the computer wins
            if ((response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y"))) {
                System.out.println("I win!");
            // use insertNewQuestion in case guess is wrong, then set 
            // curr = newTree returned from insert() method
            } else if (((response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")))) {
                insertNewQuestion(input, curr, newTree);
                curr = newTree;
            }

            // while the treeStack is not empty
            while (!treeStack.isEmpty()) {
                // take a peek at the object on the tree stack and 
                // if it has a right Subtree
                if (hasRight(treeStack.peek())) {
                    // assign new binaryTree temp to the top element of the stack
                    BinaryTree<String> temp = treeStack.pop();
                    // attach curr to the leftSubtree of temp
                    temp.attachLeftSubtree(curr);
                    // assign binaryTree temp to curr
                    curr = temp;
                // if the top element on the stack does not have a right subtree
                } else {
                    // assign new binaryTree to the top element of the stack
                    BinaryTree<String> temp = treeStack.pop();
                    // attach curr to the rightSubtree of temp
                    temp.attachRightSubtree(curr);
                    // assign temp to curr
                    curr = temp;
                }
            }

            // ask user if they want to continue
            System.out.print("Continue? ");
            response = input.nextLine();

            userYesNo(response, input); // use the userYesNo() method to validate response
            
            // started to write something to save the game to a file and decided to
            // leave it for future me
            if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                finished = false;
            } else {
                finished = true;
                System.out.println("Saving game status...");
                
            }

        } while (!finished);
    }

    /*
     *  method returns a boolean if a node is a LeafNode, it takes
     *  in a binaryTree and uses it to determine the leafiness of a node
     */
    public static boolean isLeaf(BinaryTree<String> currTree) {
        BinaryTree<String> tempTreeLeft = currTree.detachLeftSubtree();
        BinaryTree<String> tempTreeRight = currTree.detachRightSubtree();
        boolean isLeafNode;
        
        isLeafNode = (tempTreeLeft.isEmpty() && tempTreeRight.isEmpty());
        currTree.attachLeftSubtree(tempTreeLeft);
        currTree.attachRightSubtree(tempTreeRight);
        return isLeafNode;
    }

    /*
     *  method returns a boolean, if a tree has a right subtree
     *  then it should attach a left subtree, if not then attach
     *  the tree to the right subtree
     */
    public static boolean hasRight(BinaryTree<String> currTree) {
        BinaryTree<String> tempTreeRight = currTree.detachRightSubtree();
        boolean hasRightTree;
        
        hasRightTree = !(tempTreeRight.isEmpty());
        currTree.attachRightSubtree(tempTreeRight);
        return hasRightTree;
    }

    /*
     *  call insertNewQuestion which takes the scanner input
     *  the curr binaryTree, and a newTree binaryTree, manipulates the binaryTrees
     *  to then give you a binaryTree that has a new question as the root and
     *  two answers, one left child and one right child
     */
    public static void insertNewQuestion(Scanner input, BinaryTree<String> curr, BinaryTree<String> newTree) {
        String newQuestion, newAnswer;
        BinaryTree<String> answerTree = new BinaryTree();
        
        System.out.print("I give up. What is it? ");
        newAnswer = input.nextLine().toUpperCase();

        System.out.println("Please type a question whose answer is yes for " + newAnswer
                + " and no for " + curr.getRootItem() + ":");
        newQuestion = input.nextLine().toUpperCase() + " ";

        newTree.setRootItem(newQuestion);
        newTree.attachRightSubtree(curr);
        answerTree.setRootItem(newAnswer);
        newTree.attachLeftSubtree(answerTree);
        
    }

    /*
     *  method returns a String, it takes in as parameters a response
     *  from a user and a scanner input, it validates if a users response
     *  is anything other than yes or no or y or n
     */
    public static String userYesNo(String response, Scanner input) {
        while (!(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")
                || response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n"))) {
            System.out.println("Not a valid choice");
            System.out.print("Please enter 'yes' or 'no': ");
            response = input.nextLine();
            return response;
        }
        return response;
    }
    
    /*
     *  method prints the true in preorder order, it is used mainly to
     *  debug my code and make sure I get the correct tree attaching/detaching
     */
    public static void printTree(TreeNode<String> root) {
        if (root != null) {
            System.out.println(root.item);
            printTree(root.leftChild);
            printTree(root.rightChild);
        }
    }
}
