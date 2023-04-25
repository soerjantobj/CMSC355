import java.util.Scanner;
import java.io.*;

/*********************
 * Module Name: Translator
 * Description: Service to translate an English word
 * *******************
 * input: language, word
 * output: translated word
 * *******************
 * @author Bryan Soerjanto
 * @version 4/24/2023 CMSC355
 *********************/
public class Translator{
    public static void main(String[] args) throws IOException, InterruptedException{
        String language = args[0];
        String word = args[1];

        String file = language + ".txt ";
        String service = " Translate";

        String cmd = "java TextBroker.java " + file + word + service;
        runService(cmd);

//        boolean flag = false;
//
//        try {
//            Scanner scan = new Scanner(new File(language + ".txt"));
//
//            while (scan.hasNextLine()) {
//                String[] line = scan.nextLine().split(",");
//                if (word.compareTo(line[1]) == 0) {
//                    System.out.println(line[1]);
//                    break;
//                }
//            }
//            if (!flag) {
//                //run no word found error
//                ServiceBroker.runService("java -jar Error.jar 813");
//            }
//        }
//        catch(FileNotFoundException e){
//            //run language not found error
//            ServiceBroker.runService("java -jar Error.jar 805");
//        }

    }
    public static void runService(String cmd) throws IOException, InterruptedException {
        /*********************************
         * Variables:
         * run - process to run command
         * input - service output stream
         * error - service error stream
         * inRead - service output reader
         * errRead - error reader
         * brIN - line read from service
         * brERR - error line read
         * line - output line
         *********************************/
        Process run = Runtime.getRuntime().exec(cmd);
        run.waitFor();

        InputStreamReader inRead = new InputStreamReader(run.getInputStream());
        InputStreamReader errRead = new InputStreamReader(run.getErrorStream());

        BufferedReader brIN = new BufferedReader(inRead);
        BufferedReader brERR = new BufferedReader(errRead);

        String line;
        while((line = brIN.readLine()) != null){
            System.out.println(line);
        }

        while((line = brERR.readLine()) != null){
            System.out.println(line);
        }
        //close stream
        brIN.close();
    }
}