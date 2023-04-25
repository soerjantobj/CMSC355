import java.io.*;
import java.util.Scanner;

/*********************
 * Module Name: ServiceBroker
 * Description: Make calls to other services
 * *******************
 * Input: language, service code, service arguments
 * Output: void
 * *********************
 * @author Bryan Soerjanto
 * @version 4/23/2023 CMSC355
 *********************/
public class ServiceBroker{
    public static void main(String[] args) throws IOException, InterruptedException {
        /*****************
         * Variables:
         * scan - Scanner object to read service text file
         * isService - flag for if service exists
         * service - service code for service
         ******************/
        String language = args[0];
        String code = args[1];
        boolean isService = false;
        Scanner scan = new Scanner(new File("service.txt"));

        /**
         * Scanner reads file line by line
         * first instance is service code
         * check if service code matches
         *  if match, set isService to true
         *  create command to run service
         *  call runService method to execute command
         */
        while(scan.hasNextLine()){
            String[] services = scan.nextLine().split(",");
            //if valid service code, run service
            if(code.compareTo(services[1]) == 0){
                isService = true;

                //build command to run services
                for(int i = 2; i < args.length; i++){
                    services[1] += " " + args[i];
                }

//                for (String serviceArgs : args) {
//                    services[1] += " " + serviceArgs;
//                }
                String cmd = services[1];
                runService(cmd);
                break;
            }
        }
        //service does not exist, send error
        if(!isService){
            String cmd = "java Error.java " + language + " 404";
            runService(cmd);
        }
    }
    /*********************
     * Module Name: ServiceBroker
     * Component: Orchestration Module
     * Description:
     * *******************
     * Input: command to call service
     * Output: void
     *********************/
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
