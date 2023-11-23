/**
 * Represents a key-value pair for student ID and grade in the hash table.
 */
class Entry {
    private String key;    // student ID
    private double value;  // grade

    /**
     * Constructor to initialize an entry with a student ID and grade.
     *
     * @param key   The student ID.
     * @param value The grade.
     */
    public Entry(String key, double value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets the student ID.
     *
     * @return The student ID.
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets the grade.
     *
     * @return The grade.
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the grade.
     *
     * @param value The new grade to set.
     */
    public void setValue(double value) {
        this.value = value;
    }
}

/**
 * Hash table implementation using double hashing for student grades.
 */
class HashTable {
    private int capacity;
    private int size;
    private Entry[] table;
 /**
 * Hash table implementation using double hashing for student grades.
 */
    public HashTable(int capacity){
        this.capacity=capacity;
        this.table = new Entry[capacity];
        size=0;
    }
     /**
     * Custom hash function for Strings.
     * 
     * @param key The String key to hash.
     * @return The hashed value.
     */
    private int hashFunction1(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * 29 + key.charAt(i)) % capacity;
        }
        return hash;
    }

/**
 * Custom secondary hash function for Strings.
 * 
 * @param key The String key to hash.
 * @return The hashed step size.
 */
private int hashFunction2(String key) {
    int baseHash = hashFunction1(key);
    
    // Ensure that the result is never zero to avoid infinite loops
    return (baseHash % (capacity - 1)) + 1;
}


    /**
     * Inserts a new entry into the hash table.
     *
     * @param key   The student ID.
     * @param value The grade to insert.
     */

    public String insert(String key, double value) {
        if (size >= capacity) {
            resizeTable();
        }

        int primaryIndex = hashFunction1(key);
        int secondaryIndex = hashFunction2(key);
        int newIndex = -1;
        int i = 0;

        while (i < capacity) {
            int index = (primaryIndex + i * secondaryIndex) % capacity;

            if (table[index] == null) {
                newIndex = index;
                break;
            } else if (table[index].getKey().equals(key)) {
                table[index].setValue(value);
                return "Value updated";
            }

            i++;
        }

        table[newIndex] = new Entry(key, value);
        size++;
        return "Value inserted";
    }

    /**
     * Searches for the grade associated with a given student ID.
     *
     * @param key The student ID to search for.
     * @return true if found, false otherwise.
     */
   public boolean search(String key) {
    int primaryIndex = hashFunction1(key);
    int secondaryIndex = hashFunction2(key);
    int i = 0;

    while (i < capacity) {
        int index = (primaryIndex + i * secondaryIndex) % capacity;

        if (table[index] != null && table[index].getKey().equals(key)) {
            return true;  // Key found
        }

        i++;
    }

    return false;  // Key not found
}

    /**
 * Deletes an entry from the hash table based on the given key.
 *
 * @param key The key (student ID) of the entry to be deleted.
 * @return true if the entry is successfully deleted, false if the key is not found.
 */
public boolean delete(String key) {
    // Calculate the primary and secondary hash indices for the given key
    int primaryIndex = hashFunction1(key);
    int secondaryIndex = hashFunction2(key);
    
    // Initialize the loop counter
    int i = 0;

    // Iterate through the hash table using double hashing
    while (i < capacity) {
        // Calculate the current index using double hashing
        int index = (primaryIndex + i * secondaryIndex) % capacity;

        // Check if the current slot is occupied and has the matching key
        if (table[index] != null && table[index].getKey().equals(key)) {
            // Delete the entry by setting the slot to null
            table[index] = null;

            // Decrement the size of the hash table
            size--;

            // Indicate that the key is successfully deleted
            return true; // Key deleted
        }

        // Move to the next iteration of double hashing
        i++;
    }

    // If the loop completes without finding the key, indicate that the key is not found
    return false; // Key not found
}

/**
 * Resizes the hash table to accommodate more entries and rehashes existing entries.
 * Called when the table becomes full to ensure efficient handling of collisions.
 */
private void resizeTable() {
    int newCapacity = getNextPrime(2 * capacity); // Choose a new capacity, often double the current capacity

    Entry[] newTable = new Entry[newCapacity];
    int newTableSize = 0;

    // Rehash existing entries into the new table
    for (Entry entry : table) {
        if (entry != null) {
            int primaryIndex = hashFunction1(entry.getKey());
            int secondaryIndex = hashFunction2(entry.getKey());
            int i = 0;

            while (i < newCapacity) {
                int newIndex = (primaryIndex + i * secondaryIndex) % newCapacity;

                if (newTable[newIndex] == null) {
                    newTable[newIndex] = entry;
                    newTableSize++;
                    break;
                }

                i++;
            }
        }
    }

    // Update the hash table with the new configuration
    table = newTable;
    capacity = newCapacity;
    size = newTableSize;
}

/**
 * Gets the next prime number greater than or equal to the given number.
 *
 * @param n The starting number.
 * @return The next prime number.
 */
private int getNextPrime(int n) {
    // Increment the number until a prime number is found
    while (!isPrime(n)) {
        n++;
    }
    return n;
}

/**
 * Checks if a given number is prime.
 *
 * @param n The number to check.
 * @return true if the number is prime, false otherwise.
 */
private boolean isPrime(int n) {
    // Numbers less than or equal to 1 are not prime
    if (n <= 1) {
        return false;
    }

    // Check for divisibility by numbers up to the square root of n
    for (int i = 2; i <= Math.sqrt(n); i++) {
        if (n % i == 0) {
            return false; // Not prime if divisible by any number
        }
    }

    // If no divisors are found, the number is prime
    return true;
}

/**
 * Prints the contents of the hash table, showing index, key, and value information.
 */
public void printTable() {
    for (int i = 0; i < capacity; i++) {
        if (table[i] != null) {
            System.out.println("Index " + i + ": " + table[i].getKey() + " : " + table[i].getValue());
        } else {
            System.out.println("Index " + i + ": Empty");
        }
    }
}
}