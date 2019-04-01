
package BinaryTree;

import java.util.Scanner;
import java.util.Stack;

public class NielWarnProj08 {

    public static void main(String[] args) {
        String root1Question = "Does it have legs? ";
        String root1LeftAnswer = "cat";
        String root1RightAnswer = "snake";

        String response;
        Boolean finished;

        Stack<BinaryTree<String>> treeStack = new Stack<>();
        BinaryTree<String> tree1 = new BinaryTree();
        BinaryTree<String> curr;
        BinaryTree<String> newTree = new BinaryTree();

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Animal Guessing Computer Learning Game");

        do {
            tree1.setRootItem(root1Question.toUpperCase());
            tree1.attachLeft(root1LeftAnswer.toUpperCase());
            tree1.attachRight(root1RightAnswer.toUpperCase());
            
            System.out.println("Think of an animal and I will guess it.");
            curr = tree1;

            // loop while curr is not a leaf node
            while (!isLeaf(curr)) {
                // print the new question
                System.out.print(curr.getRootItem());
                // assign response to the user input
                response = input.nextLine();
                // validate user's yes or no response
                userYesNo(response, input);

                if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                    treeStack.push(curr);
                    curr = curr.detachLeftSubtree();
                } else {
                    treeStack.push(curr);
                    curr = curr.detachRightSubtree();
                }
            }

            // while curr is a leafNode then guess if single root item is curr root item
            System.out.print("Is it a " + curr.getRootItem() + "? ");
            response = input.nextLine();
            userYesNo(response, input);

            // use insertNewQuestion in case guess is wrong
            if ((response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y"))) {
                System.out.println("I win!");
            } else if (((response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")))) {
                insertNewQuestion(input, curr, newTree);
                curr = newTree;
            }

            while (!treeStack.isEmpty()) {
                if (hasRight(treeStack.peek())) {
                    BinaryTree<String> temp = treeStack.pop();
                    temp.attachLeftSubtree(curr);
                    curr = temp;
                } else {
                    BinaryTree<String> temp = treeStack.pop();
                    temp.attachRightSubtree(curr);
                    curr = temp;
                }
            }
            
            System.out.println("Current Binary Tree:");
            System.out.println("***************");
            printTree(curr.root);
            System.out.println("***************");

            System.out.print("Continue? ");
            response = input.nextLine();

            userYesNo(response, input);
            
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
     *  call insertNewQuestion which takes the user response, scanner input
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
     *  method prints the true in preorder order, it is used mainly 
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
