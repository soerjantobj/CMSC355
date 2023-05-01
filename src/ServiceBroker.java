/*******************************************************
 * Module Name: ServiceBroker
 * Description: Service calls other services
 * *****************************************************
 * input: service code
 * output: output of called service
 * *****************************************************
 * @author Bryan Soerjanto
 * @version 5/1/2023 CMSC355
 *******************************************************/
import java.io.*;
import java.util.Scanner;

/********************************************
 * Class name: ServiceBroker
 * Description: Service calls other services
 * Input: service code
 * Output: output of service
 * ******************************************/
public class ServiceBroker{
    /**************************************************************************************************
     * Variables:
     * service: service code to determine which service to call
     * language: language to output errors in
     * flag: checks if service exists
     * scan: Scanner object to read lines in service file
     * cmd: java command to run
     **************************************************************************************************
     * Pseudocode:
     * 1. Create scanner object to read service.txt file
     * 2. WHILE(file has next line):
     *      i. read line in file
     *      IF(first part of line = service):
     *          a. set flag to true
     *          b. set command to second part of line, which is the java command to call a service
     *          c. concatenate arguments to java command
     *          d. run java command with arguments
     *          e. BREAK WHILE
     *      END IF
     *    END WHILE
     * 4. IF(flag is false)
     *      i. create erorr file name
     *          FORMAT: msg[language].txt
     *      ii. set service code
     *      iii. set error code
     *      iv. create java command to call TextBroker to output error message
     *      v. call runProcess to run java program
     *     END IF
     **************************************************************************************************/
    public static void main(String[] args) throws IOException, InterruptedException {
        String service = args[0];
        String language = "Eng";
        boolean flag = false;

        Scanner scan = new Scanner(new File("service.txt"));

        while(scan.hasNextLine()){
            String[] services = scan.nextLine().split(",");
            //if valid service code, run service
            if(service.compareTo(services[0]) == 0){
                flag = true;
                String cmd = services[1];

                for(int i = 1; i < args.length; i++){
                    cmd += " " + args[i];
                }

                runService(cmd);
                break;
            }
        }
        if(!flag){
            //service not found error
            String file = "msg" + language + ".txt";
            String errorService = "Error";
            String code = " 703 ";

            String cmd = "java TextBroker.java " + file + code + errorService;
            runService(cmd);
        }
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
