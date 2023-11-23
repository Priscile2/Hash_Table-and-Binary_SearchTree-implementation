public class Problem2Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Inserting contacts into the telephone directory
        bst.insert(new Contact("John_Edem", "0542399288"));
        bst.insert(new Contact("Ryan", "0205552334"));
        bst.insert(new Contact("David", "0532345678"));
        bst.insert(new Contact("Patience", "0205552678"));
        bst.insert(new Contact("Abigail", "0592346198"));
        bst.insert(new Contact("Priscile", "0549923288"));

        // Searching for a contact in the telephone directory
        System.out.println("Search for 'Ryan': " + bst.search("Ryan"));
        System.out.println("Search for 'Priscila': " + bst.search("Priscila"));

        // Deleting a contact from the telephone directory
        System.out.println("\nDeleting: ");
        boolean deleted = bst.delete(new Contact("Priscile", ""));
        System.out.println("Contact 'Priscile' deleted: " + deleted);

        // Printing the telephone directory
        System.out.println("\nTelephone Directory in Ascending Order:");
        bst.printInOrder();
    }
}
