import java.io.*;
import java.util.Scanner;

/*****************
 * TextBroker Module
 * ***************
 * Description: look up word in text file and gives corresponding output
 * ***************
 * Input: text file name, key, service
 * Output: output from text file or error
 * ***************
 * @author Bryan Soerjanto
 * @version 4/24/2023 CMSC355
 *****************/
public class TextBroker {
    public static void main(String[] args) throws IOException, InterruptedException {
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
                runService("");
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
