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
        boolean flag = false;
    try {
        Scanner scan = new Scanner(new File(file));

        while (scan.hasNextLine()) {
            String[] line = scan.nextLine().split(",");
            if (service.compareTo("Translate") == 0 || service.compareTo("NumConverter") == 0 || service.compareTo("CrewList") == 0) {
                if (line[0].compareTo(key) == 0) {
                    flag = true;
                    System.out.println(line[1]);
                    break;
                }
            }
            else{
                //Service not found
                String cmd = "java Error.java Eng 703";
            }
        }
    }
    catch(FileNotFoundException e){
        String cmd = "java Error.java 404";
        ServiceBroker.runService(cmd);
    }

        if(!flag){
            String cmd = "java Error.java 404";
            ServiceBroker.runService(cmd);
        }
    }
}
