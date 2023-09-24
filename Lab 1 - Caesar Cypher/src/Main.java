import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Boolean isRunning = true;
        Boolean messageNotValid = true;
        Boolean keyNotValid = true;
        String messageToEncrypt = "";
        String key = "";
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
                        clearScreen();
                        while (messageNotValid) {
                            System.out.println("Introduce the message that you wish to encrypt");
                            messageToEncrypt = sc.nextLine();
                            if (!cypher.checkMessageValidity(messageToEncrypt)) {
                                clearScreen();
                                System.out.println(
                                        "Please introduce a valid message that only contains characters that are between A-Z and a-z");
                            } else
                                messageNotValid = false;
                        }
                        while (keyNotValid) {
                            System.out.println("Introduce a key between 1-25");
                            key = sc.nextLine();
                            if (!cypher.checkKeyValidity(key)) {
                                clearScreen();
                                System.out.println("Please introduce a valid key");
                            } else
                                keyNotValid = false;

                        }
                        cypher.encryptMessage(messageToEncrypt);
                        isRunning = false;
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
}
