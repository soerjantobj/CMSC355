/*******************************************************
 * Module Name: Translator
 * Description: Services translates English word to another language
 * *****************************************************
 * input: language, word
 * output: translated word
 * *****************************************************
 * @author Bryan Soerjanto
 * @version 5/1/2023 CMSC355
 *******************************************************/
import java.io.*;

/********************************************
 * Class name: Translator
 * Description: translates english word to another language
 * Input: language, word
 * Output: translated word
 * ******************************************/
public class Translator{

    public static void main(String[] args) throws IOException, InterruptedException{
        String language = args[0];
        String word = args[1];

        String file = language + ".txt ";
        String service = " Translate";

        String cmd = "java TextBroker.java " + file + word + service;

        runService(cmd);

    }
    public static void runService(String cmd) throws IOException, InterruptedException {
        /*********************************
         * Variables:
         * run - process to run command
         * brIN - line read from service
         * brERR - error line read
         * line - output line
         * ******************************************
         * Pseudocode:
         * 1. Create process to run a java command
         * 2. thread waits until process is terminated
         * 3. Create BufferReader to read outputs from new InputStreamReader
         * 4. print all outputs from process
         * 5. close streams
         *********************************/
        Process run = Runtime.getRuntime().exec(cmd);
        run.waitFor();

        BufferedReader brIN = new BufferedReader(new InputStreamReader(run.getInputStream()));
        BufferedReader brERR = new BufferedReader(new InputStreamReader(run.getErrorStream()));

        String line;
        while((line = brIN.readLine()) != null){
            System.out.println(line);
        }

        while((line = brERR.readLine()) != null){
            System.out.println(line);
        }
        //close stream
        brIN.close();
        brERR.close();
    }
}