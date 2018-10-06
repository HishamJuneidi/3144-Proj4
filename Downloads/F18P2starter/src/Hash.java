
/**
 * Stub for hash table class
 *
 * @author CS3114 staff
 * @version August 2018
 */

public class Hash {
    private String name;
    private int hashTableSize;
    private ArrayRecord[] hashArray;
    //private SparseMatrix sm;

    private int currSize = 0;
    private  ArrayRecord tombStone = new ArrayRecord("", false);


    /**
     * Constructor
     */
    public Hash() {
        this(100, null);
    }


    /**
     * Creates new hash object
     * @param hashSize initial memory amount
     * @param name name of hash table
     */
    public Hash(int hashSize, String name) {
        // Nothing here yet
        this.name = name;
        hashTableSize = hashSize;
        hashArray = new ArrayRecord[hashTableSize];
    }


    /**
     * Compute the hash function. Uses the "sfold" method from the OpenDSA
     * module on hash functions
     *
     * @param s
     *            The string that we are hashing
     * @param m
     *            The size of the hash table
     * @return The home slot for that string
     */
    // You can make this private in your project.
    // This is public for distributing the hash function in a way
    // that will pass milestone 1 without change.
    public int h(String s, int m) {
        // m = hashTableSize;
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % m);
    }


    /**
     * add fun
     * 
     * @param value
     *            handle
     * @return int for testing and statement if it is added or not
     */
    public int hashInsert(ArrayRecord value, SparseMatrix sm) {
        String key = value.getName();
        if (this.ifRecordExist(key) != null)
        		return -1;
        expand(); 
        int intialStratPos = h(key, hashTableSize);
        int probFun = intialStratPos;
        for (int i = 1; hashArray[probFun] != null && 
                !hashArray[probFun].equals(tombStone); i++) {
            if (key.equals(hashArray[probFun].getName())) {
//                System.out.println("|" + key + "| "
//                    + "duplicates a record already in the Name database.");
                return -1;
            }

            probFun = (intialStratPos + (i * i)) % hashTableSize;
        }
        
       
        hashArray[probFun] = value;
        currSize++;
        if (value.isMovie()) {
        	MovieNode mn = sm.addMovie(value.getName());
        		
        	value.setMovie(mn);
        }
        else {
        	ReviewerNode rn = sm.addReviewer(value.getName());
        	value.setReviewer(rn);
        }
            //System.out.println("|" + key + "| "
            //    + "has been added to the Name database.");

        
        return probFun;
    }
    
    public void addRating(String movie, String reviewer, int rating, SparseMatrix sm, Hash rh) {
    		ArrayRecord m;
    		ArrayRecord r;
    		m = this.ifRecordExist(movie);
    		if (m == null) {
    			m = new ArrayRecord(movie, true);
    			this.hashInsert(m, sm);
    		}
    		r = rh.ifRecordExist(reviewer);
    		if (r == null) {
    			r = new ArrayRecord(reviewer, false);
    			rh.hashInsert(r, sm);
    		}
    		sm.addRating(m.movie(), r.reviewer(), rating);
    }


    /**
     * expand the HT
     */
    private void expand() {
        if ((currSize) < (hashTableSize / 2)) {
            return;
        }

        hashTableSize = hashTableSize * 2;
        ArrayRecord[] newHashArray = new ArrayRecord[hashTableSize];
        if (currSize != 0) {
            for (int i = 0; i < hashArray.length; i++) {
                if (hashArray[i] != null && hashArray[i] != tombStone) {
                    int newIndex = h(hashArray[i].getName(), hashTableSize);
                    int count = 1;
                    while (newHashArray[newIndex] != null) {
                        newIndex = (newIndex + (count * count))
                            % newHashArray.length;
                        count++;
                    }
                    newHashArray[newIndex] = hashArray[i];

                }

            }
            // check movie or reviewer 
            System.out.println(this.name + " hash table size doubled to "
                + hashTableSize + " slots.");
            hashArray = newHashArray;
        }
    }


    /**
     * print HT
     */
    public void hashPrint() {

        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i] != null && hashArray[i] != tombStone) {
                System.out.println("|" + hashArray[i].getName() + "| " + i);
            }
        }
        System.out.println("Total records: " + currSize);
    }


    /**
     * delete entity
     * 
     * @param arg
     *            to be deleted
     * @return if it is deleted or not ( found)
     */
    public boolean hashDelete(String arg, SparseMatrix sm) {
        int intialStratPos = h(arg, hashTableSize);
        int probFun = intialStratPos;
        for (int i = 1; hashArray[probFun] != null; i++) {
            if (arg.equals(hashArray[probFun].getName())) {
                if (hashArray[probFun].isMovie()) {
                    sm.deleteMovie(hashArray[probFun]);
                }
                else {
                    sm.deleteReviewer(hashArray[probFun]);
                }
                hashArray[probFun] = tombStone;
                currSize--;
                return true;
            }
            probFun = (intialStratPos + (i * i)) % hashTableSize;
        }
        
        
        return false;
    }


    /**
     * UpdateDelete
     * 
     * @param arg
     *            string to be deleted
     */
    /*
    public void hashUpdateDelete(String arg) {
        String[] args = arg.split("<SEP>");
        String keyName = args[0];
        String secName = args[1];
        int initalPos = h(keyName, hashTableSize);
        int count = 1;
        int targetIndex = initalPos;
        while (hashArray[targetIndex] != null && !hashArray[targetIndex]
            .getName().equals(keyName)) {
            targetIndex = (initalPos + count * count) % hashTableSize;
            count++;
        }
        if (hashArray[targetIndex] != null) {
            String original = hashArray[targetIndex].getData();
            String[] splitt = original.split("<SEP>");
            StringBuilder result = new StringBuilder();
            result.append(splitt[0]);
            boolean found = false;
            for (int keyIndex = 1; keyIndex < splitt.length; keyIndex += 2) {
                int valueIndex = keyIndex + 1;
                if (!splitt[keyIndex].equals(secName)) {
                    result.append("<SEP>").append(splitt[keyIndex]).append(
                        "<SEP>").append(splitt[valueIndex]);

                }
                else {

                    found = true;
                }

            }
            if (found) {
                hashArray[targetIndex].setData(result.toString());
                System.out.println("Updated Record: |" + hashArray[targetIndex]
                    .getData() + "|");

            }
            else {
                System.out.println("|" + keyName
                    + "| not updated because the field |" + secName
                    + "| does not exist");
            }

        }
        else {
            System.out.println("|" + keyName
                + "| not updated because it does not"
                + " exist in the Name database.");
        }

    }
    */


    /**
     * hashUpdate
     * 
     * @param arg
     *            update HT
     * @return true if is added
     */
    /*
    public boolean hashUpdate(String arg) {
        String[] args = arg.split("<SEP>");
        String keyName = args[0];
        String secName = args[1];
        String thirdName = args[2];
        int initalPos;
        int count = 1;
        boolean updeated = false;
        int targetIndex = initalPos = h(keyName, hashTableSize);
        while (hashArray[targetIndex] != null && !hashArray[targetIndex]
            .getName().equals(keyName)) {
            targetIndex = (initalPos + count * count) % hashTableSize;
            count++;
        }
        if (hashArray[targetIndex] != null) {
            String original = hashArray[targetIndex].getData();
            String[] splitt = original.split("<SEP>");
            StringBuilder result = new StringBuilder();
            result.append(splitt[0]);
            for (int keyIndex = 1; keyIndex < splitt.length; keyIndex += 2) {
                int valueIndex = keyIndex + 1;
                if (!splitt[keyIndex].equals(secName)) {
                    result.append("<SEP>").append(splitt[keyIndex]).append(
                        "<SEP>").append(splitt[valueIndex]);
                }
            }
            result.append("<SEP>").append(secName).append("<SEP>").append(
                thirdName);
            hashArray[targetIndex].setData(result.toString());
            System.out.println("Updated Record: |" + hashArray[targetIndex]
                .getData() + "|");
            
            updeated = true;
        }
        else {
            System.out.println("|" + keyName + "| "
                + "not updated because it does not"
                + " exist in the Name database.");
        }
        return updeated;
    }
    */


    /**
     * get size
     * 
     * @return size
     */
    public int getSize() {
        return currSize;
    }

    /**
     * checks if hash contains a record
     * @param key key being searched for
     * @return whether or not record is in hash
     */
    public ArrayRecord ifRecordExist(String key) {
        int intialStratPos = h(key, hashTableSize);
        int probFun = intialStratPos;
        for (int i = 1; hashArray[probFun] != null; i++) {
            if (key.equals(hashArray[probFun].getName())) {
                return hashArray[probFun];
            }
            probFun = (intialStratPos + (i * i)) % hashTableSize;
        }
        return null;
    }
    
    
    /**
     * retrieves record from hash given a key
     * @param key key being searched for
     * @return ArrayRecord being looked for
     */
    public ArrayRecord retrieveRecord(String key) {
        int intialStratPos = h(key, hashTableSize);
        int probFun = intialStratPos;
        for (int i = 1; hashArray[probFun] != null; i++) {
            if (key.equals(hashArray[probFun].getName())) {
                break;
                
            }
            probFun = (intialStratPos + (i * i)) % hashTableSize;
        }
        return hashArray[probFun];
    }
}
