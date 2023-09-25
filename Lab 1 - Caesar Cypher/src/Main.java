import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Boolean isRunning = true;
        Boolean optionNotChosen = true;
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
                        clearScreen();
                        while (optionNotChosen) {
                            System.out.println("1 - 1 key\n2 - 2 keys");
                            String option2 = sc.nextLine();
                            switch (option2) {
                                case "1":
                                    isRunning = checkMessageAndKeyValidity(data, cypher, sc);
                                    cypher.encryptMessage(data.messageToEncrypt, data.key, "1");
                                    optionNotChosen = false;
                                    break;
                                case "2":
                                    isRunning = checkMessageAndKeyValidity(data, cypher, sc);
                                    checkKey2Validity(data, cypher, sc);
                                    cypher.encryptMessage(data.messageToEncrypt, data.key, data.key2);
                                    optionNotChosen = false;
                                    break;
                                default:
                                    System.out.println("Please choose either 1 or 2 keys");
                                    break;
                            }
                        }
                        break;
                    case "2":
                        clearScreen();
                        while (optionNotChosen) {
                            System.out.println("1 - 1 key\n2 - 2 keys");
                            String option2 = sc.nextLine();
                            switch (option2) {
                                case "1":
                                    isRunning = checkMessageAndKeyValidity(data, cypher, sc);
                                    cypher.decryptMessage(data.messageToEncrypt, data.key, "1");
                                    optionNotChosen = false;
                                    break;
                                case "2":
                                    isRunning = checkMessageAndKeyValidity(data, cypher, sc);
                                    checkKey2Validity(data, cypher, sc);
                                    cypher.decryptMessage(data.messageToEncrypt, data.key, data.key2);
                                    optionNotChosen = false;
                                    break;
                                default:
                                    System.out.println("Please choose either 1 or 2 keys");
                                    break;
                            }
                        }
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
                System.out.println("Please introduce a valid key (1-25)");
            } else
                data.keyNotValid = false;

        }
        return false;
    }

    public static void checkKey2Validity(EncryptionData data, CaesarCypher cypher, Scanner sc) {
        System.out.println("Introduce a second key with only latin characters, and a size of at least 7");
        while (data.key2NotValid) {
            data.key2 = sc.nextLine();
            if (!cypher.checkKey2Validity(data.key2)) {
                clearScreen();
                System.out.println("Please introduce a valid key with only latin characters, and a size of at least 7");
            } else
                data.key2NotValid = false;
        }
    }
}

class EncryptionData {
    Boolean messageNotValid = true;
    String messageToEncrypt = "";
    Boolean keyNotValid = true;
    Boolean key2NotValid = true;
    String key = "";
    String key2 = "";
}
