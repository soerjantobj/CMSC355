import java.io.*;
import java.util.Scanner;

/*********************
 * Module Name: ServiceBroker
 * Description: Make calls to other services
 * *******************
 * Input: service code, service arguments
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
        String service = args[0];
        String language = "Eng";
        boolean flag = false;

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
