import java.util.Random;
import java.util.Scanner;


public class MergeSort{
    private String strOriginalString;
    private String[] arrString;
    private String[] arrStringOrig;
    private int[] nSuffixArray;

    /**
     * This Method performs a randomizer as its strings is "acgt" it will change
     * the position of each character in the string whenever this method is called
     *
     *  @param nStrLength is obtained from the user as this will be the length of the "acgt" string
     *
     */

    public static String generateRandomString(int nStrLength){
        String randomChars = "acgt";
        int nCtr;

        Random rand = new Random();
        StringBuilder sb = new StringBuilder(nStrLength);

        /*
         * This is the randomizer loop for the randomChars string
         */

        for(nCtr = 0; nCtr < nStrLength; nCtr++){
            sb.append(randomChars.charAt(rand.nextInt(randomChars.length())));
        }
        randomChars = sb.toString();

        return randomChars;
    }
    /**
     * This Method is used for binary searching, searching for the strKey inside the arrString
     *
     * @param arrString         is the current string that is being used
     *
     * @param nLow              is the integer that represents the left most value of the arrString
     *
     * @param nHigh             is the integer that represents the right most value of the arrString
     *
     * @param strKey            is the "key" or the specific value for searching inside a string array
     *
     */

    public int find(String[] arrString, int nLow, int nHigh, String strKey){
        int nMid = nLow + (nHigh - nLow)/2;

        while(nLow <= nHigh){
            if(arrString[nMid].compareTo(strKey)<0) {
                nLow = nMid + 1;
            }
            else if(arrString[nMid].compareTo(strKey)>0) {
                nHigh = nMid - 1;
            }
            else {
                return nMid;
            }
            nMid = nLow + (nHigh - nLow)/2;
        }

        if(nLow > nHigh){
            return -1;
        }
        return -1;
    }
    /**
     * This Method is used for sorting by first dividing them all and merging them together as a complete ans sorted string
     *
     * @param arrString         is the current string that is being used
     *
     */
    public void sortByMerge(String[] arrString){
        int nCounter; // index of the arrString
        int arrStringLength = arrString.length;
        int nMidIndex = arrStringLength / 2;
        String[] arrStringLeft = new String[nMidIndex];
        String[] arrStringRight = new String[arrStringLength - nMidIndex];


        /*
            Merge Sort (Splitting)
                This part is for splitting the string into two parts, until they cannot be divided anymore.
                It utilizes a recursion for it to loop until the arrStringLength is 1
         */
        if(arrStringLength > 1) {
            for(nCounter = 0; nCounter<nMidIndex; nCounter++){
                arrStringLeft[nCounter] = arrString[nCounter];
            }
            for(nCounter = nMidIndex; nCounter<arrStringLength; nCounter++){ // loop until nCounter reaches the orig. length of the array
                arrStringRight[nCounter - nMidIndex] = arrString[nCounter];
            }
            sortByMerge(arrStringLeft);
            sortByMerge(arrStringRight);
            Merge(arrString, arrStringLeft, arrStringRight);

        }
    }

    /**
     * This method merges the split string into its  original length
     * @param arrString a String array that needs to get sorted
     * @param arrStringLeft a String array containing the left part of the array that needs to get sorted
     * @param arrStringRight - a String array containing the right part of the array that needs to get sorted
     */
    public void Merge(String[] arrString, String[] arrStringLeft, String[] arrStringRight) {

        int nCounter; // index of the arrString
        int nTemp1 = 0; //arrStringLeft index
        int nTemp2 = 0; //arrStringRight index
        int nLeftLength = arrStringLeft.length;
        int nRightLength = arrStringRight.length;
        int nMergeLength = arrStringLeft.length + arrStringRight.length;

        for (nCounter = 0; nCounter < nMergeLength; nCounter++) {
            if ((nTemp2 == nRightLength) || (nTemp1 < nLeftLength && arrStringLeft[nTemp1].compareTo(arrStringRight[nTemp2]) < 0)) {
                arrString[nCounter] = arrStringLeft[nTemp1];
                nTemp1++;
            } else {
                arrString[nCounter] = arrStringRight[nTemp2];
                nTemp2++;
            }
        }
    }
    /**
     * This method is necessary for the construction of suffix array as
     * it assigns substrings from the input text
     */

    public void generateSuffixes() { //assign substrings
        int nCounter;
        int nLength = this.strOriginalString.length();

        for(nCounter = 0; nCounter < nLength; nCounter++){
            this.arrString[nCounter] = this.strOriginalString.substring(nCounter);
            this.arrStringOrig[nCounter] = this.strOriginalString.substring(nCounter);
        }
    }

    /**
     * This constructor creates the MergeSort object that has preset values from the storage parameter which is the
     * strOriginalString as this stores the suffixes
     *
     * @param strOriginalString         is a String that stores the input text that requires
     *                                  the numbering of a Suffix for the indication of the
     *                                  length of the text. This string consists only of letters
     *                                  from the alphabet, {a,c,g,t}.
     *
     * @param nNumofSuffixes           is a number corresponding to the length of
     *                                 the input text, which also denotes the
     *                                 number of possible Suffixes or substrings
     *                                 that can be extracted from it.
     *
     */

    public MergeSort(String strOriginalString, int nNumofSuffixes){ // passes values
        this.strOriginalString = strOriginalString;
        this.arrString = new String[strOriginalString.length()];
        this.arrStringOrig = new String[strOriginalString.length()];
        this.nSuffixArray = new int[strOriginalString.length()];
    }
    /**
     * The main method for the MergeSort class, performs actions such as asking
     *  the user what the "desired" input length is
     *
     *  This will then redirect to the method for generating a random string by generateRandomString() method
     *
     *  Values will then be set inside the constructor "MergeSort" as this will proceed with the code
     *  as this calls on the generateSuffixes and sortByMerge methods
     *
     *  After those steps, it will proceed with the printing of the out of the mergesort, this will then
     *  use a loop, that will input the values it obtained throughout the process, this is also where the
     *  construction of suffixes with the use of find() or binary search algorithm. Lastly it will call on
     *  display method to show the Suffix Array together with the sorted string
     *
     *  For the final procedures, the code will then direct to the Time Elapsed which is necessary for the
     *  empirical running time process of the MergeSort algorithm
     */

    public static void main(String[] args) {
        String strInput;
        int nStrLength = 0;
        int nCounter = 0;
        long midTime, endTime, timeElapsed;
        MergeSort suffixArray;

        Scanner sc = new Scanner(System.in);
        System.out.println("Input Length: ");

        nStrLength = sc.nextInt();
        strInput = generateRandomString(nStrLength);

        System.out.println("Random String Length " + nStrLength + " :" + strInput);

        midTime = System.nanoTime();

        suffixArray = new MergeSort(strInput, nStrLength);
        int arrStringLength  = suffixArray.arrString.length;
        suffixArray.generateSuffixes();
        suffixArray.sortByMerge(suffixArray.arrString);

        System.out.println("\n OUTPUT: ");
        for(nCounter = 0; nCounter<arrStringLength; nCounter++){
            suffixArray.nSuffixArray[suffixArray.find(suffixArray.arrString, 0,
                    suffixArray.arrStringOrig.length-1, suffixArray.arrStringOrig[nCounter])] = nCounter;
        }
        suffixArray.display();

        endTime = System.nanoTime();
        timeElapsed = endTime - midTime;
        System.out.println("\n\nExecution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in seconds: " + timeElapsed/(double)1000000000);

        sc.close();



    }
    /**
     * This method displays the Suffix Array, which are the specific steps of the merge sort
     */
    public void display() {
        int nCounter;
        int nLength = strOriginalString.length();

        System.out.print("Suffix Array: ");

		/* For loop prints each element stored in the Suffix Array constructed
		   followed by a space. Each element corresponds to a valid index number
		   of the input text.                                                   */
        for(nCounter=0; nCounter<nLength; nCounter++)
            System.out.printf ("\n%d: %s ", this.nSuffixArray[nCounter], this.arrString[nCounter]);
    }

    /** Getters and Setters **/

    /**
     * @returns the String that contains the input text that is composed of the letters (a, c, g, t)
     */
    public String getStrOriginalString() {
        return strOriginalString;
    }

    /** A method that initializes the input text, with the argument passed.
     *
     * @param strOriginalString     a String consisting only of letters from the alphabet, {a,c,g,t}
     */
    public void setStrOriginalString(String strOriginalString) {
        this.strOriginalString = strOriginalString;
    }

    /**
     *
     * @return the String array that contains the generated randomized group of letters that is already sorted
     */
    public String[] getArrString() {
        return arrString;
    }

    /**
     *
     * @param arrString a sorted String array consisting the randomized group of letters (a,c,g,t)
     */

    public void setArrString(String[] arrString) {
        this.arrString = arrString;
    }

    /**
     *
     * @return the String array that contains the generated randomized group of letters to be sorted
     * This method is also utilized in order to construct suffix array
     */
    public String[] getArrStringOrig() {
        return arrStringOrig;
    }

    /**
     *
     * @param arrStringOrig a string array consisting the randomized group of letters (a,c,g,t) before
     *                      it is sorted thru merge sort algorithm
     */
    public void setArrStringOrig(String[] arrStringOrig) {
        this.arrStringOrig = arrStringOrig;
    }

    /**
     *
     * @return the index of the constructed suffix array
     */
    public int[] getnSuffixArray() {
        return nSuffixArray;
    }

    /**
     *
     * @param nSuffixArray is an integer that consist the index of the constructed suffix array
     */

    public void setnSuffixArray(int[] nSuffixArray) {
        this.nSuffixArray = nSuffixArray;
    }

}