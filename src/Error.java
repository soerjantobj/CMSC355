/*********************
 * Module Name: Error
 * Description: Service throws an error message corresponding to an error code
 * *******************
 * input: language, code
 * output: error message
 * *******************
 * @author Bryan Soerjanto
 * @version 5/1/2023 CMSC355
 *********************/
import java.io.*;

/*******************************************************
 * Class name: Error
 * Description: Service looks at an error code and
 *  throws an error message corresponding to a language
 * Input: language, code
 * Output: Error message
 * *****************************************************/
public class Error {
    /*****************************************************************
     * Variables:
     * language: spoken language
     * code: error code
     * file: name of text file with error codes and error message
     *  text format: [error code],[error message]
     * service: name of service
     * cmd: java command
     * ***************************************************************
     * Algorithm/Pseudocode:
     * 1. Create list of args as a string
     *  i. "[file] [code] [service]"
     * 2. Create java command to call TextBroker with list of args
     *  i. "java TextBroker.java " + "[file] [code] [service]"
     * 3. Call runService to run the full command
     *****************************************************************/
    public static void main(String[] args) throws IOException, InterruptedException {
        String language = args[0];
        String code = args[1];
        String file = "msg" + language + ".txt ";
        String service = " Error";

        String cmd = "java TextBroker.java " + file + code + service;
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
