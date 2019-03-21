package BinaryTree;


public class BinaryTree<T> extends BinaryTreeBasis<T> {

  public BinaryTree() {
  }  // end default constructor

  public BinaryTree(T rootItem) {
    super(rootItem);
  }  // end constructor

  public BinaryTree(T rootItem,
                    BinaryTree<T> leftTree,
                    BinaryTree<T> rightTree) {
    root = new TreeNode<T>(rootItem, null, null);
    attachLeftSubtree(leftTree);
    attachRightSubtree(rightTree);
  }  // end constructor

  public void setRootItem(T newItem) {
    if (root != null) {
      root.item = newItem;
    }
    else {
      root = new TreeNode<T>(newItem, null, null);
    }  // end if
  }  // end setRootItem

  public void attachLeft(T newItem) {
    if (!isEmpty() && root.leftChild == null) {
      // assertion: nonempty tree; no left child
      root.leftChild = new TreeNode<T>(newItem, null, null);
    }  // end if
  }  // end attachLeft

  public void attachRight(T newItem) {
    if (!isEmpty() && root.rightChild == null) {
      // assertion: nonempty tree; no right child
      root.rightChild = new TreeNode<T>(newItem, null, null);
    }  // end if
  }  // end attachRight

  public void attachLeftSubtree(BinaryTree<T> leftTree)
                                throws TreeException {
    if (isEmpty()) {
      throw new TreeException("TreeException:  Empty tree");
    }
    else if (root.leftChild != null) {
      // a left subtree already exists; it should have been
      // deleted first
      throw new TreeException("TreeException: " +
                           "Cannot overwrite left subtree");
    }
    else {
      // assertion: nonempty tree; no left child
      root.leftChild = leftTree.root;
      // don't want to leave multiple entry points into
      // our tree
      leftTree.makeEmpty();
    }  // end if
  }  // end attachLeftSubtree

  public void attachRightSubtree(BinaryTree<T> rightTree)
                                 throws TreeException {
    if (isEmpty()) {
      throw new TreeException("TreeException:  Empty tree");
    }
    else if (root.rightChild != null) {
      // a right subtree already exists; it should have been
      // deleted first
      throw new TreeException("TreeException: " +
                          "Cannot overwrite right subtree");
    }
    else {
      // assertion: nonempty tree; no right child
      root.rightChild = rightTree.root;
      // don't want to leave multiple entry points into
      // our tree
      rightTree.makeEmpty();
    }  // end if
  }  // end attachRightSubtree

  protected BinaryTree(TreeNode<T> rootNode) {
    root = rootNode;
  }  // end protected constructor

  public BinaryTree<T> detachLeftSubtree()
                         throws TreeException {
    if (isEmpty()) {
      throw new TreeException("TreeException:  Empty tree");
    }
    else {
      // create a new binary tree that has root's left
      // node as its root
      BinaryTree<T> leftTree;
      leftTree = new BinaryTree<T>(root.leftChild);
      root.leftChild = null;
      return leftTree;
    }  // end if
  }  // end detachLeftSubtree

  public BinaryTree<T> detachRightSubtree()
                         throws TreeException {
    if (isEmpty()) {
      throw new TreeException("TreeException:  Empty tree");
    }
    else {
      BinaryTree <T> rightTree;
      rightTree = new BinaryTree<T>(root.rightChild);
      root.rightChild = null;
      return rightTree;
    }  // end if
  }  // end detachRightSubtree
} // end BinaryTree

