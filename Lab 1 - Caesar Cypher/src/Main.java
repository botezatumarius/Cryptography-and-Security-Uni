import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Boolean isRunning = true;
        EncryptionData data = new EncryptionData();
        clearScreen();
        while (isRunning) {
            try (Scanner sc = new Scanner(System.in)) {
                System.out.println("Menu\n1 - Encryption\n2 - Decryption\n3 - Exit");
                String option = sc.nextLine();
                CaesarCypher cypher = new CaesarCypher();
                switch (option) {
                    case "3":
                        isRunning = false;
                        break;
                    case "1":
                        isRunning = checkMessageAndKeyValidity(data, cypher, sc);
                        cypher.encryptMessage(data.messageToEncrypt, data.key);
                        break;
                    case "2":
                        isRunning = checkMessageAndKeyValidity(data, cypher, sc);
                        cypher.decryptMessage(data.messageToEncrypt, data.key);
                        break;
                    default:
                        clearScreen();
                        System.out.println("Please choose one of the valid options");
                }
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Boolean checkMessageAndKeyValidity(EncryptionData data, CaesarCypher cypher, Scanner sc) {
        clearScreen();
        while (data.messageNotValid) {
            System.out.println("Introduce the message");
            data.messageToEncrypt = sc.nextLine();
            if (!cypher.checkMessageValidity(data.messageToEncrypt)) {
                clearScreen();
                System.out.println(
                        "Please introduce a valid message that only contains characters that are between A-Z and a-z");
            } else
                data.messageNotValid = false;
        }
        while (data.keyNotValid) {
            System.out.println("Introduce a key between 1-25");
            data.key = sc.nextLine();
            if (!cypher.checkKeyValidity(data.key)) {
                clearScreen();
                System.out.println("Please introduce a valid key");
            } else
                data.keyNotValid = false;

        }
        return false;
    }
}

class EncryptionData {
    Boolean messageNotValid = true;
    String messageToEncrypt = "";
    Boolean keyNotValid = true;
    String key = "";
}
