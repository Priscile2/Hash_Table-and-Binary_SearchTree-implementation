public class Problem1Main {
    public static void main(String[] args) {
        // Create a hash table with an initial capacity
        HashTable hashTable = new HashTable(2);

        // Inserting new entries
        System.out.println("---------------------------Inserting-----------------------------");
        System.out.println(hashTable.insert("John_Edem", 85.5)); 
        System.out.println(hashTable.insert("David", 90.0)); 
        System.out.println(hashTable.insert("Ryan", 75.8));   
        System.out.println(hashTable.insert("Patience", 88.2)); 
        System.out.println(hashTable.insert("Priscile", 89.2));
       

        //Searching
        System.out.println("\n------------------------Searching---------------------------------------------");
        System.out.println("Ryan's Grade: " + hashTable.search("Ryan")); 
        System.out.println("Dave's Grade: " + hashTable.search("Dave"));   

        //Deletion
        System.out.println("\n--------------------------------Deleting---------------------------------------");
        System.out.println("Delete Priscile: " + hashTable.delete("Priscile"));  
        System.out.println("Delete Dave: " + hashTable.delete("Dave"));    

    
        hashTable.insert("Favour", 91.7);
        hashTable.insert("Aloy", 78.4);
        hashTable.insert("Abigail", 89.9);
        hashTable.insert("Mabel", 93.2);
        hashTable.insert("Nicholas", 85.6); 
        hashTable.insert("Nichelas", 87.6);

        // Printing the final state of the hash table
        System.out.println("\n----------------Final Hash Table State---------------------------");
        hashTable.printTable();
    }
}
