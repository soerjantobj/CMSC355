import java.io.IOException;

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

        String file = base + "To" + converted + ".txt ";

        String cmd = "java TextBroker.java " + file + value;
        ServiceBroker.runService(cmd);



    }



}
