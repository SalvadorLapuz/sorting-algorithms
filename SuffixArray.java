import java.util.Random;
import java.util.Scanner;

/** This class represents a suffix or a substring of a string
 *  that corresponds to an input text of length n. Along the
 *  substring itself, each suffix contains an index number 
 *  (nPosition), counting from 0, denoting its starting position
 *  in the input text.  
 */
class Suffix {
	int nPosition;
	String strSubstring;
	
	/** This constructor creates a Suffix object by supplying the 
	  * needed values of its attributes with the arguments passed.
	  * 
	  * @param nPosition         the index number or starting position number
	  *                          of the substring in the input text or string
	  * @param strSubstring      the substring itself consisting only of the letters 
	  *                          from the alphabet, {a,c,g,t}
	  */
	public Suffix(int nPosition, String strSubstring) {
		this.nPosition = nPosition;
		this.strSubstring = strSubstring;
	}
	
	/** A method that returns the index number or starting position number of the 
	  * substring in the input text or string. 
	  * 
	  * @return       the starting position number of the substring in the input text
	  */
	public int getPosition() {
		return nPosition;
	}
	
	/** A method that initializes the starting position number of the substring,
	  * with the argument passed.
	  * 
	  * @param nPosition     a number corresponding to the index or starting position
	  *                      of a substring in the input text
	  */
	public void setPosition(int nPosition) {
		this.nPosition = nPosition;
	}
	
	/** A method that returns the substring itself, which belongs to the input text of length n. 
	  * 
	  * @return      the substring or suffix of the input text
	  */
	public String getStrSubstring() {
		return strSubstring;
	}
	
	/** A method that initializes the substring or suffix of the input text,
	  * with the argument passed.
	  * 
	  * @param strSubstring    a String corresponding to a substring of the input text
	  */
	public void setStrSubstring(String strSubstring) {
		this.strSubstring = strSubstring;
	}

	/** toString() method of the class has been overridden in order to display relevant information 
	  * about a Suffix (useful upon inspecting the correctness of the sorting algorithm).
	  * 
	  *  @return    String consisting of information about a Suffix, including the substring itself, and its
	  *             starting position number in the input text
	  */
	@Override
	public String toString() {
		return "(" + nPosition + ") " + strSubstring;
	}
}

/** This class represents one algorithm of constructing a Suffix Array, or 
 *  the sorted order of the Suffixes of an input text of length n, consisting 
 *  only of their starting positions (and not the Suffixes themselves).
 *  
 *  In order to generate a Suffix Array of a given text, a String corresponding
 *  to it must be stored (strOriginalString), alongside its length such that it denotes
 *  to the number of possible substrings or Suffixes that can be extracted from it
 *  (nNumOfSuffixes). In the implementation of a sorting algorithm such as Insertion Sort,
 *  it is important that the elements to be sorted are contained in an array. Hence, an
 *  array of Suffixes must also be included in this class. To store the output of
 *  the algorithm, an integer array is also required (suffixArray). 
 */
public class SuffixArray {

	String strOriginalString;
	int nNumOfSuffixes;
	Suffix [] suffixes;
	int [] suffixArray;
	
	/** This constructor creates a SuffixArray object with the help of
	  * the information provided by the arguments passed. Not only are
	  * some attributes initialized with the arguments passed, but the
	  * actual value stored in nNumOfSuffixes represent the size needed
	  * to initialize both Suffixes object and suffixArray (i.e.
	  * they will contain the exact number of elements as the number of
	  * Suffixes).
	  * 
	  * @param strOriginalString   a String storing the input text that
	  *                            is needing to be constructed of a Suffix
	  *                            array. It consists only of letters 
	  *                            from the alphabet, {a,c,g,t}.
	  * 
	  * @param nNumOfSuffixes      a number corresponding to the length of
	  *                            the input text, which also denotes the 
	  *                            number of possible Suffixes or substrings
	  *                            that can be extracted from it. 
	  */
	public SuffixArray(String strOriginalString, int nNumOfSuffixes) {
		this.strOriginalString = strOriginalString;
		this.nNumOfSuffixes = nNumOfSuffixes;
		
		/* For the following arrays, their size or number of elements
           upon initialization, is dependent on the number of possible Suffixes 
           that can be extracted from the input text or simply, its length.      */
		this.suffixes = new Suffix[nNumOfSuffixes];
		this.suffixArray = new int[nNumOfSuffixes];
	}
	
	public static void main(String[] args) {
		
		boolean isValidInput = false;
		SuffixArray suffixArray;
		int nStringLength = 0;
		String strInputString;
		
		/* The variables of long datatype below are declared for the purpose of 
	       estimating the average running time of the algorithm.               */
		long midTime, endTime, timeElapsed;
		
		/* A new Scanner object is declared and initialized in order to prompt 
		   user of the desired length, n, by which a random String or text shall
		   be generated, consisting only of letters from the alphabet, {a,c,g,t}. */
		Scanner scanner = new Scanner(System.in);
		System.out.println("INPUT LENGTH: ");
		
		/* This do-while loop iterates for as long as user enters invalid input for the
		   length, such as input which do not correspond to an integer value (i.e. String, 
		   double, etc.).                                                                 */
		do{
			/* The try block executes code that scans the user's input for the desired length
			   while also checking if their input as String can be parsed into an integer for 
			   validity checking. This is done to avoid any errors beforehand, upon implementing
			   the algorithm.                                                                   */
			try {
				strInputString = scanner.next();
				nStringLength = Integer.parseInt(strInputString);
				
				/* The boolean attribute below is modified to true once their input is valid
				   (i.e. representing an integer).                                             */
				isValidInput = true;
				
				/* However, the same attribute is modified to false again once their input is not
				   representing a positive integer (i.e. either zero or negative). An informative 
				   error message is also displayed, and the procedure of scanning input repeats. */
				if(nStringLength <= 0) {
				    System.out.println("Invalid Input! Not a positive integer.");
				    isValidInput = false;
				}			
			    /* If the String cannot be parsed into an integer, NumberFormatException is caught.
			       An informative error message will be displayed, allowing the user to enter new 
			       input, as the do-while loop iterates.                                         */
			} catch(NumberFormatException e) {
				System.out.println("Invalid Input! Not an integer.");
			}

		} while(!isValidInput);
		
		/* Once user's input of desired length is finally valid, the static method of generating a 
		   random String from the class shall be called, to which its return value corresponding to 
		   the input text for the algorithm is stored in the appropriate variable (strInputString).  */
		strInputString = generateRandomString(nStringLength);
		
		/* The random String generated is displayed for the purpose of assuring its validity as
		   consisting only of letters from the alphabet, {a,c,g,t}, and of a fixed length, n.    */
		System.out.println("Random String of Length " + nStringLength + ": " + strInputString);
	
		/* The midTime variable stores the current value of the most precise available system timer, 
		   in nanoseconds, as it deems essential to calculate the execution time of the algorithm soon. */                                                                            
		midTime = System.nanoTime();
		
		/* NOTE: The time to be measured starts from the time a new SuffixArray object is created, in order
		   to implement the algorithm of constructing a Suffix Array on an input text, randomly generated.  */
		/* Here, the string length is passed, resemblance of the number of possible Suffixes or substrings 
		   that can be generated from the input text.                                                       */
		suffixArray = new SuffixArray(strInputString, nStringLength);
		
		/* The method "generateSuffixes()" is called on the SuffixArray object created, as the name implies,
		   to extract the possible Suffixes or substrings of the input text, store them in an array, and proceed 
		   to implementing the sorting algorithm (i.e. sortByInsertion()) that helps to construct the Suffix Array
		   of the input text.                                                                                     */
		suffixArray.generateSuffixes();
		suffixArray.sortByInsertion();
		
		System.out.println("\nOUTPUT: ");
		
		/* The integer array belonging to the object now consists of the constructed Suffix Array of the input text,
		   after implementing the algorithm. It is displayed along the suffixes by calling the appropriate method of the object.     */ 
		suffixArray.display();
		
		/* The endTime variable stores the current value of the most precise available system timer, 
		   in nanoseconds, as it deems essential to calculate the execution time of the algorithm soon. */                                                                   
		/* NOTE: The time to be measured here marks the ending time of the algorithm. The algorithm ends
		   after the output, Suffix Array of the input text, is displayed, along the sorted suffixes.   */
		endTime = System.nanoTime();
		
         /* The elapsed time or duration of the algorithm on the input length is calculated by the subtraction 
            of the ending time of the algorithm by the time the algorithm started its process (upon creation 
            of the new SuffixArray object)                                                                    */
		timeElapsed = endTime - midTime;
		 
		/* The execution time in nanoseconds and seconds are displayed for the purpose of estimating the 
		   average running time of the algorithm across k samples in the report.                              */
	    System.out.println("\n\nExecution time in nanoseconds: " + timeElapsed);
	    System.out.println("Execution time in seconds: " + timeElapsed/(double)1000000000);
	    
	    /* The method "close" on the Scanner object is called, upon termination of the program. */
		scanner.close();
	}
	
	/** As the name implies, a method that generates a random String, given user's desired 
	  * length of it and inclusion of letters from the alphabet, {a,c,g,t}.  It takes advantage 
	  * of creating objects of pre-defined classes in Java, such as StringBuilder class and
	  * Random Class, in order to complete its procedure. 
	  * 
	  * @param nStringLength    the desired length, n, of the input text which also represents the 
	  *                         number of possible Suffixes or substrings that can be extracted from it
	  */
	public static String generateRandomString(int nStringLength) {
		
		int nCounter;
		String randomString;
		
		/* This String object holds the value of the sole possible characters that could
		   be included in the random String to be generated.                            */
		String strChars = "acgt";
		
		/* A new StringBuilder object is created (given desired length) which represents a
		   mutable sequence of characters. As mutable, we are allowed to manually append 
		   characters to it, as shown in the process below.                               */
		
		StringBuilder sb = new StringBuilder(nStringLength);
		
		/* A new Random object is created, which helps in generating random numbers. 
		   This would be useful in generating a random index applicable to strChars String. */  
		Random randomGenerator = new Random();
		
		/* For loop is constructed to manually append characters to the StringBuilder object
		   created, or allow for the generation of a random String.                          */
		for(nCounter = 0; nCounter<nStringLength; nCounter++) 
		{
			/* To simulate the generation of a random String, the Random object is used 
			   to return a random index applicable to strChars String (0 inclusive and
			   strChars.length() is exclusive in the numbers that can be randomly returned), 
			   and locate the given character of the returned index in strChars by the 
			   "charAt(int index)" method. The located character can be any character from 
			   the alphabet, {a,c,g,t}. After which, the located character is appended to 
			   the sequence so far created.                                                  */
			sb.append(strChars.charAt(randomGenerator.nextInt(strChars.length())));
		}
		
		/* Random string generated is appropriately converted to a String object and stored
		   in a separate variable that shall be returned as shown below.                   */
		randomString = sb.toString();
		
		/* The random String generated is returned to the caller of the method (main method). */
		return randomString;
	}

	/** A method that allows for the extraction of the substrings from the input text, 
	  * and creation of new Suffix objects (Suffixes) to be stored in the suffixes array
	  * It is important to store these Suffix objects (Suffixes) in an array, in order to 
	  * sort them appropriately by the chosen algorithm (i.e. Insertion Sort) and construct 
	  * the Suffix Array of the input text.
	  */
	public void generateSuffixes() {
		
		int nCounter;
		
		/* Based on the length of the input text or number of possible Suffixes that can be 
		   generated from it, new Suffix objects are created and stored in the suffixes array.                           
	       For this, the "substring()" method of the String class is also called, passing as an
		   argument, the counter variable,(nCounter), as it increments per iteration, and 
		   represents a starting position of a new substring of the input text. The substring
		   returned by the method begins with the character indexed at the counter variable and 
		   extends to the end of the input text.                                                */
		for(nCounter=0; nCounter<nNumOfSuffixes; nCounter++) {
			suffixes[nCounter] = new Suffix (nCounter, strOriginalString.substring(nCounter));
		}
	}
	
	/** A method that implements the chosen algorithm (i.e. Insertion Sort) to sort all suffixes
	  * of a given String or input text, and construct its Suffix Array. After the call to this 
	  * method, it is true that substrings in the suffixes array are sorted, and that the integer
	  * array, Suffix Array, consists of elements (each corresponding to an index of a substring),
	  * arranged according to the sorted order of the suffixes.
	  * 
	  * NOTE: As the suffixes are sorted, the index number of each Suffix object (nPosition attribute) 
	  * is not modified. This certifies that the index number of each Suffix object still corresponds
	  * to its starting position number in the input text.
	  */
	public void sortByInsertion() {

		int nCounter, i;
		Suffix currentSuffix;
		
			/* For loop iterates for as long as each element (i.e. Suffix object) from
			   the unsorted part of the suffixes array can be sorted accordingly, or
			   inserted into its proper place at the sorted part.                    
			                                                                         
			   By default, a single element or Suffix object is already sorted in its own.
			   Hence, the element at index 0 is already contained in the sorted part of the 
			   suffixes array. Counter variable allows element at index 1 to be the first
			   suffix to be manipulated by the algorithm.                                  */
			for(nCounter=1; nCounter<nNumOfSuffixes; nCounter++) 
			{
				currentSuffix = suffixes[nCounter];
				/* i variable stores the decremented value of the current value of the counter
				   variable, as suffixes preceding the current Suffix object shall be compared
				   with the current one, in order to find the latter's final position in the 
				   sorted part of the suffixes array.                                                  */
				i = nCounter-1;
				
				/* While loop iterates for as long as suffixes preceding the current Suffix object
				   contain String values, which are lexicographically greater than the String value
				   of the current Suffix object. Hence, "compareTo()" method of the String class is
				   called in order to assist in the comparison of String values. It returns a value
				   greater than 0 once the String value is lexicographically greater than the 
				   String argument passed in its parameter. (i.e. the String contained in suffixes[i]
				   is lexicographically greater than the String of the current Suffix object that is 
				   to be inserted into the sorted part of the suffixes array.)                        */
				
				/* NOTE: The "compareTo()" method of the String class works in a way that characters
				   of both the Strings are converted into Unicode values for comparison.              
				   It does a sequential comparison of characters in the String that have the same 
				   position (index number).                                                           */
				while(i>=0 && suffixes[i].getStrSubstring().compareTo(currentSuffix.getStrSubstring()) > 0)
				{
						/* If above case holds true, the Suffix object preceding the current one is 
						   shifted to the right. (i.e. assigned to index number, i+1 of the suffixes array)  */                                  
					    suffixes[i+1] = suffixes[i];
						
					    /* For each iteration of the while loop, i is decremented to inspect each preceding
					       Suffix object to the current one. Its value must be greater than or equal to 0
					       (the minimum index number of an element).                                         */
					    i--;
				}
					
				/* Whenever the inner loop above stops its iteration, it is assumed that a Suffix object preceding
				   the current Suffix object contains a String value that is actually NOT lexicographically greater
				   than it. This means that the value may either be lexicographically equal or less than it. Hence,
				   in this instance, the Suffix object is finally inserted into its proper place in the sorted part
				   of the suffixes array, which would just be right after that element.                            
				    
				   For this, the index i needs to be increased by 1 to store the current Suffix object right after it. */
				suffixes[i+1] = currentSuffix;
		   }
			
			/* After the sorting of the suffixes array, the Suffix Array of the input text is then constructed, by
			   accessing the nPosition attribute of each Suffix object in the modified (sorted) suffixes array. Do
			   note that as we sorted the suffixes, we did not modify the nPosition attribute of each Suffix object.
			   Hence, their original index number in the input text is retained, to which we access that to the 
			   purpose of constructing the Suffix array.                                                           */
			for(nCounter=0; nCounter<nNumOfSuffixes;nCounter++) 
			{
				/* The starting position number or index number of each Suffix object found in the newly modified 
				   (i.e. sorted) suffixes array is stored in the Suffix array. This gives us the impression that
				   we are arranging elements of the Suffix Array, accordingly by the sort of the suffixes array.   */
				suffixArray[nCounter] = suffixes[nCounter].getPosition();
			}	
	}

	/** A method that displays the Suffix Array constructed, along the sorted suffixes,
	  * after the implementation of the algorithm on the input text of length n. 
	  */
	public void display() {
		int nCounter;
		
		System.out.print("Suffix Array:\n");
		
		/* For loop prints each element stored in the Suffix Array constructed
		   followed by the corresponding suffix starting that position.
		   Each element corresponds to a valid index number of the input text.  */
		for(nCounter=0; nCounter<nNumOfSuffixes; nCounter++)
			System.out.println(suffixArray[nCounter] + ": " + suffixes[nCounter].getStrSubstring()); 
	}
	
	/** A method that returns the original input text whose Suffix Array has to be constructed. 
	  * 
	  * @return      a String consisting only of letters from the alphabet, {a,c,g,t}
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

	/** A method that returns the number of Suffixes or substrings that an input text has. The number 
	  *  directly corresponds to the input text's length, as each Suffix object is created one at a time,
	  *  time, starting from any character, and extending up to the end of the input text.
	  * 
	  * @return      a number representing the number of existing substrings belonging to an input text
	  */
	public int getNumOfSuffixes() {
		return nNumOfSuffixes;
	}

	/** A method that initializes the number of Suffixes or substrings that an input text has. The number 
	  *  with the argument passed.
	  * 
	  * @param nNumOfSuffixes     a number representing the number of existing substrings belonging to an input text
	  */
	public void setNumOfSuffixes(int nNumOfSuffixes) {
		this.nNumOfSuffixes = nNumOfSuffixes;
	}

	/** A method that returns the generated suffixes array or the array containing all substrings of the input text.
	  * 
	  * @return      an array of type Suffix which stores all substrings of the input text. It may be sorted, or
	  *              unsorted depending on the stage reached in the algorithm's implementation.
	  */
	public Suffix[] getSuffixes() {
		return suffixes;
	}

	/** A method that initializes the suffixes array of the input text, with the argument passed.
	  * 
	  * @param suffixes     an array of type Suffix which stores all substrings of the input text
	  */
	public void setSuffixes(Suffix[] suffixes) {
		this.suffixes = suffixes;
	}

	/** A method that returns the Suffix Array of the input text (upon algorithm implementation for its construction).
	  * 
	  * @return      an integer array which stores the sorted order of the suffixes, by their starting positions or index
	  */
	public int[] getSuffixArray() {
		return suffixArray;
	}

	/** A method that initializes the suffix array of the input text, with the argument passed.
	  * 
	  * @param suffixArray      an integer array whose elements (i.e. indices of each Suffix) are arranged according to the
	  *                         sorted order of the suffixes
	  */
	public void setSuffixArray(int[] suffixArray) {
		this.suffixArray = suffixArray;
	}

}
