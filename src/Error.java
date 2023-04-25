import java.util.Scanner;
import java.io.*;

/**
 * Input: language, code
 */
public class Error {
    public static void main(String[] args) throws IOException, InterruptedException {
        String language = args[0];
        String code = args[1];
        boolean isError = false;

        Scanner scan = new Scanner(new File("msg" + language + ".txt"));
        while (scan.hasNextLine()) {
            String[] line = scan.nextLine().split(",");
            if (code.compareTo(line[0]) == 0) {
                isError = true;
                System.out.println(line[0]);
                break;
            }
        }
        if(!isError){
            String cmd = "java Error.jar /src" + language + " 404";
            runService(cmd);
        }
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
