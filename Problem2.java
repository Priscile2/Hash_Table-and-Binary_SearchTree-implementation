class Contact {
    String name;
    String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}

class TNode {
    Contact key;
    TNode left, right;
    /**
     * Constructor to initialize the node with a contact.
     *
     * @param key The contact stored in the node.
     */
    public TNode(Contact key) {
        this.key = key;
        this.left = this.right = null;
    }
}
// Binary Search Tree for managing telephone directory contacts
class BinarySearchTree {
   
    private TNode root;
    /**
     * Constructor to initialize the binary search tree with no root.
     */
    public BinarySearchTree() {
        this.root = null;
    }
    /**
     * Inserts a contact into the binary search tree.
     *
     * @param contact The contact to be inserted.
     */
    public void insert(Contact contact) {
        root = insertRecursive(root, contact);
    }
    /**
     * Recursive helper method for inserting a contact.
     *
     * @param root    The root of the current subtree.
     * @param contact The contact to be inserted.
     * @return The root of the updated subtree.
     */
    private TNode insertRecursive(TNode root, Contact contact) {
        if (root == null) {
            // Create a new node for the contact if the current node is null
            return new TNode(contact);
        }

        if (contact.name.compareTo(root.key.name) < 0) {
            // Recursively insert into the left subtree
            root.left = insertRecursive(root.left, contact);
        } else if (contact.name.compareTo(root.key.name) > 0) {
            // Recursively insert into the right subtree
            root.right = insertRecursive(root.right, contact);
        }

        return root;
    }
    /**
     * Searches for a contact in the telephone directory by name.
     *
     * @param name  The name of the contact to search for.
     * @return      True if the contact is found, false otherwise.
     */

    public boolean search(String name) {
        return searchRecursive(root, name);
    }
    /**
     * Recursive method for searching a contact by name.
     *
     * @param root  The root of the current subtree.
     * @param name  The name of the contact to search for.
     * @return      True if the contact is found, false otherwise.
     */
    private boolean searchRecursive(TNode root, String name) {
        while (root != null) {
            if (name.compareTo(root.key.name) < 0) {
                // Move to the left subtree
                root = root.left;
            } else if (name.compareTo(root.key.name) > 0) {
                // Move to the right subtree
                root = root.right;
            } else {
                // Node with the name found
                return true;
            }
        }
        return false;
           // Node with the name not found
    }
    /**
     * Deletes a contact from the telephone directory.
     *
     * @param contact  The contact to be deleted.
     * @return         True if the contact is successfully deleted, false otherwise.
     */
    public boolean delete(Contact contact) {
        return deleteRecursive(null, root, contact);
    }
    /**
     * Recursive method for deleting a contact.
     *
     * @param parent   The parent of the current node.
     * @param current  The current node being examined.
     * @param contact  The contact to be deleted.
     * @return         True if the contact is successfully deleted, false otherwise.
     */
    private boolean deleteRecursive(TNode parent, TNode current, Contact contact) {
        while (current != null) {
            if (contact.name.compareTo(current.key.name) < 0) {
                // Move to the left subtree
                parent = current;
                current = current.left;
            } else if (contact.name.compareTo(current.key.name) > 0) {
                // Move to the right subtree
                parent = current;
                current = current.right;
            } else {
                // Node with the name found
                break;
            }
        }

        if (current == null) {
            // Node with the name not found
            return false;
        }

        // Case 1: Node with no children
        if (current.left == null && current.right == null) {
            if (parent == null) {
                // Deleting the root node
                root = null;
            } else if (parent.left == current) {
                 // Delete the left child of the parent
                parent.left = null;
            } else {
                 // Delete the right child of the parent
                parent.right = null;
            }
        }
        // Case 2: Node with one child
        else if (current.left == null) {
            if (parent == null) {
                // Deleting the root node
                root = current.right;
            } else if (parent.left == current) {
                  // Delete the left child of the parent
                parent.left = current.right;
            } else {
                // Delete the right child of the parent
                parent.right = current.right;
            }
        } else if (current.right == null) {
            if (parent == null) {
                  // Deleting the root node
                root = current.left;
            } else if (parent.left == current) {
                 // Delete the left child of the parent
                parent.left = current.left;
            } else {
                // Delete the right child of the parent
                parent.right = current.left;
            }
        }
        // Case 3: Node with two children
        else {
              // Find the minimum value in the right subtree
            String minValue = findMin(current.right);
            // Replace the current node's value with the minimum value
            current.key.name = minValue;
               // Recursively delete the node with the minimum value in the right subtree
            return deleteRecursive(current, current.right, new Contact(minValue, ""));
        }

        return true;
    }
    /**
     * Finds the minimum value in the binary search tree.
     *
     * @param node  The root of the subtree to find the minimum value.
     * @return      The minimum value in the subtree.
     */
    private String findMin(TNode node) {
        while (node.left != null) {
            // Move to the leftmost node in the subtree
            node = node.left;
        }
        // Return the name of the leftmost node
        return node.key.name;
    }
    /**
     * Prints the telephone directory in ascending order.
     */
    public void printInOrder() {
        inOrderTraversal(root);
    }
    /**
     * Recursive method for in-order traversal.
     *
     * @param node  The current node in the traversal.
     */
    private void inOrderTraversal(TNode node) {
        if (node != null) {
            // Traverse the left subtree
            inOrderTraversal(node.left);
            // Traverse the left subtree
            System.out.println("Name: " + node.key.name + ", Phone: " + node.key.phoneNumber);
            // Traverse the right subtree
            inOrderTraversal(node.right);
        }
    }

    
}


