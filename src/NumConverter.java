import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Inputs: base, converted, value
 *  base: binary, hex, octal, etc.
 *  converted: binary, hex, octal, etc.
 * Output: converted number
 */
public class NumConverter {
    public static void main(String[] args) throws IOException, InterruptedException {
        String base = args[0];
        String converted = args[1];
        String value = args[2];
        String service = " NumConverter";

        String file = base + "To" + converted + ".txt ";

        String cmd = "java TextBroker.java " + file + value + service;
        runService(cmd);

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
        brERR.close();
    }



}
