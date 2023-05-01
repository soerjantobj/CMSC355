/*********************
 * Module Name: CrewList
 * Description: Service that searches for a specific skill from a list of crew members
 *  and outputs members with that skill
 * *******************
 * input: skill
 * output: names with that skill
 * *******************
 * @author Bryan Soerjanto
 * @version 5/1/2023 CMSC355
 *********************/
import java.io.*;

/*********************
 * Class name: CrewList
 * Description: Class takes in a skill parameter and sends it to TextBroker
 * Input: skill
 * Output:
 * **********************/
public class CrewList {
    public static void main(String[] args) throws IOException, InterruptedException {
        /********************************************************************
         * Variables:
         * skill: skill name from args
         * service: name of service
         * file: name of text file with names and skills
         *  text format: [name],[skill1] [skill 2] [skill ...]
         * cmd: java command
         * ******************************************************************
         * Algorithm/Pseudocode:
         * 1. List arguments in a string:
         *  i. "[file] [skill] [service]"
         * 2. Create java command to call TextBroker with list of arguments
         *  i. "java TextBroker.java " + "[file] [skill] [service]"
         * 3. call runService to run the full command
         ********************************************************************/
        String skill = args[0];
        String service = " CrewList";
        String file = "CrewList.txt ";

        String cmd = "java TextBroker.java " + file + skill + service;
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
         * 3. Create BufferReader to read outputs from process
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