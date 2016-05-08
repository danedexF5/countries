import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * main class for this project, Main
 */

/**
 * This class reads and parses the country.txt file and creates the
 * map of arrays we need. It also prompts the user for a letter and
 * writes out a file containing those countries
 */
public class Main {

    public static void main(String[] args) throws Exception {

        // Create a HashMap that can hold an array list of countries indexed by a letter.
        HashMap<String, ArrayList<Country>> map =
                new HashMap<String, ArrayList<Country>>();

        // Create a File object pointing to the countries.txt file
        File countries = new File("countries.txt");

        // Create a Scanner to read the file line by line
        Scanner scanner = new Scanner(countries);


        /*
        The for loop below addresses this requirement:
        Read and parse the "countries.txt" file into an HashMap<String, ArrayList<Country>>
        where the key is a single letter and the value is a list of countries whose names
        start with that letter.
         */

        // Loop over the contents of the countries file, one line at a time
        while (scanner.hasNext()) {


            // read the next line of content from the scanner
            String line = scanner.nextLine();


            // Create a new instance of our Country object and set it into a variable named country
            Country country = new Country();

            // Create a array of Strings variable. Split the line of text we just read into an array of two String values and set it into this variables.
            // Hint: check out the .split() method on the String class
            String[] countrySplit = line.split("\\|");

            // Create a String variable for the abbreviation and set it to the first (0) element in the array we just made
            country.abbreviation = countrySplit[0];

            // Create a String variable for the country name and set it to the second (1) element in the array we just made
            country.name = countrySplit[1];

            // Create a String variable and set it to the first letter from the country name
            // Hint: check out the substring() method on String
            String firstLetter = country.name.substring(0, 1);

            // Update the variable we just created and set it to the lowercase version
            // Hint: check out toLowerCase() on the String class.
            firstLetter = firstLetter.toLowerCase();


            ArrayList<Country> listOfCountries;
            // check if our HashMap does NOT have a key for this letter
            if (!map.containsKey(firstLetter)) {


                // if not, create an empty ArrayList that can hold Country objects
                listOfCountries = new ArrayList<Country>();
                listOfCountries.add(country);

                // put this new empty ArrayList into the HashTable at the correct index (IE: the first letter of the country
                // name. We determined this earlier and stored it in a variable)
                map.put(firstLetter, listOfCountries);
            }


            // Create a variable of type ArrayList and set it to the ArrayList in the HashMap for the first letter of the country we're working with
            else {
                listOfCountries = map.get(firstLetter);
                listOfCountries.add(country);
            }

            // Add our current Country object into the ArrayList we just got
        }

            // close the scanner that's reading the countries.txt file
            scanner.close();

        /*
        The next block of code addresses this requirement:
        Ask the user to type a letter (if they don't type a single letter, throw an exception).
         */

            // Create a new Scanner instance that we will use for user input (when we ask them for a letter)
            Scanner scan2 = new Scanner(System.in);

            // Prompt the user to enter a letter
            System.out.println("Please type a letter");

            // Use our new Scanner instance to read the input
            String readInput = scan2.next();

            // Check if the length of the input is not equal to 1.
            // See the "throw" keyword.
            if (readInput.length() != 1) {


                // If not, throw an exception
                throw new Exception();

            }
            // update the existing variable holding the letter, setting it to the lower case version of the letter. (see .toLowerCase())
            readInput = readInput.toLowerCase();

        /*
        The next section handles this requirement:
        Save an "X_countries.txt" file, where X is the letter they typed, which only lists the countries starting with that letter.
         */

            // Create a new FileWriter instance that we will use to write out to the new x_countries file.
            // Hint: the FileWriter class has a constructor you can use that accepts a String. The string is the name of the file to write to.
            // Hint 2: Overwrite the file it if already exists. IE: don't append.
            File x = new File(readInput + "_countries.txt");
            FileWriter fw = new FileWriter(x);


            // Create an ArrayList variable and set it to the ArrayList from the HashMap for the letter the user entered

            ArrayList<Country> tempListofCountries = map.get(readInput);

            // loop over this array list of countries that we just got. Note: All of these countries start with the letter the user entered
            // Hint: use the for-each syntax to loop over this array
            for (Country c : tempListofCountries) {


                // Use the FileWriter instance we just created and write out a line of text to the x_countries.txt file.
                // This line should contain the abbreviation and name for the current country.
                // Don't forget to add a line break for each line
                // Hint: check out the .append() method of FileWriter
                fw.append(c.getAbbreviation() + " " + c.getName() + "\n");
            }
            fw.close();
            // close the FileWriter
            // Hint: see the .close() method on FileWriter


            // and you're done!!!!




    }
}



/*

            static void writeFile(String fileName, String fileContent) throws IOException {
            //creating file/accessing countries list
            File newCountries = new File("countries.txt");

            System.out.println(newCountries.getAbsolutePath());

            // create a filewriter
            FileWriter fw = new FileWriter(newCountries);

            //write to file
            fw.write("AF|afghanistan\n");
            fw.close();

            //create scanner
            Scanner scanner = new Scanner(newCountries);
            while (scanner.hasNext()){
                String line = scanner.nextLine();
                System.out.println(line);
            }

            scanner = new Scanner(newCountries);
            scanner.useDelimiter("\\Z");
            String contents = scanner.next();

            System.out.println(contents);
        }

    }
}*/


