import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Translator {
    public static Scanner openFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        return new Scanner(file);
    }

    public static void main(String[] args) {

        //Create scanner to take in user input for word
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a word to be translated");
        String word = in.nextLine();

        try {
            //Create File object from given file
            String pathName = "src/french.txt"; //args[0]
            Scanner scan = openFile(pathName);
            String translated = "";
            //Scan through file until
            while(scan.hasNextLine()){
                //text file format: [word],[translated]
                String line = scan.nextLine();
                String[] arr = line.split(",");
                if(word.compareTo(arr[0]) == 0){
                    translated = arr[1];
                    break;
                }
            }
            if(translated.compareTo("") > 0){
                System.out.println("The translated word is " + translated);
            }
            else{
                System.out.println("There is no translation for the word " + word);
            }
        }
        //If file not found, throw exception
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}