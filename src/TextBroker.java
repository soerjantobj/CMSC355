/*******************************************************
 * Module Name: TextBroker
 * Description: Services rely on TextBroker for outputs
 * *****************************************************
 * input: file, key, service, error
 * output: result from key or error message
 * *****************************************************
 * @author Bryan Soerjanto
 * @version 5/1/2023 CMSC355
 *******************************************************/
import java.io.*;
import java.util.Scanner;

/********************************************
 * Class name: TextBroker
 * Description: Service reads a given file
 *  and outputs the result the key maps to
 * Input: base, newBase, value
 * Output: converted value
 * ******************************************/
public class TextBroker {
    /************************************************************
     * Variables:
     * file: name of file from service that called TextBroker
     * key: determines which result to output for service callee
     * service: name of called service
     * language: spoken language to output error message
     * error: error code
     * flag: checks if key exists in file
     * scan: Scanner object to read file
     * line: current line read
     * cmd: java command to call Error service
     * **********************************************************/

    public static void main(String[] args) throws IOException, InterruptedException {
        /***********************************************************
         * Algorithm/Pseudocode:
         * 1. Create scanner to read file for service code
         * 2. if file not found, call Error service with file not found error code
         * 3. otherwise, read service file corresponding to callee service until end of file
         * WHILE(file has next line):
         *  i. read next line in file
         *  ii. IF(service is Translate, NumConverter, or Error):
         *      a. IF(first part of line = key):
         *              A. set flag to true
         *              B. print second part of line
         *              C. BREAK WHILE
         *          END IF
         *      END IF
         *  iii. ELSE IF(service is Crew List):
         *      a. FOR(number of members):
         *          IF(first part of line = key):
         *              A. set flag to true
         *              B. print second part of line
         *              C. BREAK WHILE
         *          END IF
         *      END ELSE IF
         *  iv. ELSE:
         *          a. set flag to true
         *          b. call error with service not found error code
         *      END ELSE
         *  END WHILE
         * 4. IF(flag is false):
         *      i. set error to corresponding key not found error code
         *      ii. call runService to call Error service with error code
         *    END IF
         ***********************************************************/
        String file = args[0];
        String key = args[1];
        String service = args[2];

        String language = "Eng";
        String error;

        boolean flag = false;
    try {
        Scanner scan = new Scanner(new File(file));

        while (scan.hasNextLine()) {
            String[] line = scan.nextLine().split(",");
            if (service.compareTo("Translate") == 0 ||
                service.compareTo("NumConverter") == 0 ||
                service.compareTo("Error") == 0){

                if (line[0].compareTo(key) == 0) {
                    flag = true;
                    System.out.println(line[1]);
                    break;
                }
            }
            else if(service.compareTo("CrewList") == 0){
                for(int i = 0; i < line.length; i++)
                    if (line[i].compareTo(key) == 0) {
                        flag = true;
                        System.out.println(line[0]);
                    }
                break;
            }
            else{
                //service not found
                flag = true;
                error = " 703";
                String cmd = "java Error.java " + language + error;
                runService(cmd);
            }
        }
        if(!flag){
            //key not found
            switch (service) {
                case "Translate": error = " 901"; break;
                case "NumConverter": error = " 902"; break;
                case "CrewList": error = " 903"; break;
                case "Error": error = " 506"; break;
                default: error = " 404"; break;
            }
            String cmd = "java Error.java " + language + error;
            runService(cmd);
        }
    }
    catch(FileNotFoundException e){
        //file not found
        switch (service) {
            case "Translate": error = " 805"; break;
            case "NumConverter": error = " 806"; break;
            case "CrewList": error = " 807"; break;
            case "Error": error = " 505"; break;
            default: error = " 404"; break;
        };
        String cmd = "java Error.java " + language + error;
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
