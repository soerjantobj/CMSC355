/*********************
 * Module Name: NumConverter
 * Description: Service converts a base 10 number into a different base
 * *******************
 * input: base, value
 * output: converted number
 * *******************
 * @author Bryan Soerjanto
 * @version 5/1/2023 CMSC355
 *********************/
import java.io.*;

/*******************************************************
 * Class name: NumConverter
 * Description: Service converts a number into a different base
 * Input: base, newBase, value
 * Output: converted value
 * *****************************************************/
public class NumConverter {
    /************************************************************
     * Variables:
     * base: base of value
     * newBase: the base the value will be converted to
     * value: value to be converted
     * service: name of service
     * file: name of file
     * cmd: java command
     * **********************************************************
     * Pseudocode:
     * 1. Construct file name
     *  i. "[base]To[newBase].txt"
     * 2. Create list of args as a string
     *  i. "[file] [value] [service]"
     * 3. Create java command to call TextBroker
     *  i. "java TextBroker.java " + "[file] [value] [service]"
     * 4. call runService to run full command
     ***********************************************************/
    public static void main(String[] args) throws IOException, InterruptedException {
        String base = args[0];
        String newBase = args[1];
        String value = args[2];
        String service = " NumConverter";

        String file = base + "To" + newBase + ".txt ";

        String cmd = "java TextBroker.java " + file + value + service;
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
