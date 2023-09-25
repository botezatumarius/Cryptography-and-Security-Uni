public class CaesarCypher {
    private char[] uppercaseLetters = new char[26];

    public CaesarCypher() {
        char currentLetter = 'A';
        for (int i = 0; i < 26; i++) {
            uppercaseLetters[i] = currentLetter;
            currentLetter++;
        }
    }

    public boolean checkMessageValidity(String message) {
        for (char c : message.toCharArray()) {
            if (((c < 'A' || c > 'Z') && (c < 'a' || c > 'z'))) {
                if (c != ' ')
                    return false;
            }
        }
        return true;
    }

    public boolean checkKeyValidity(String key) {
        int keyCopy = 0;
        if (isValidIntString(key))
            keyCopy = Integer.parseInt(key);
        if (keyCopy >= 1 && keyCopy <= 25)
            return true;
        return false;
    }

    public void processMessage(String message, String key, boolean isEncrypting) {
        StringBuilder resultMessage = new StringBuilder();
        String transformedMessage = message.replaceAll("\\s", "").toUpperCase();
        System.out.println("Message to " + (isEncrypting ? "encrypt" : "decrypt") + ": " + transformedMessage);
        int shift = Integer.parseInt(key);
        if (!isEncrypting) {
            shift = 26 - shift;
        }
        for (char c : transformedMessage.toCharArray()) {
            char processedChar = (char) ('A' + (c - 'A' + shift) % 26);
            resultMessage.append(processedChar);
        }
        System.out.println((isEncrypting ? "Encrypted" : "Decrypted") + " message: " + resultMessage.toString());
    }

    public void encryptMessage(String message, String key) {
        processMessage(message, key, true);
    }

    public void decryptMessage(String message, String key) {
        processMessage(message, key, false);
    }

    private boolean isValidIntString(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        if (!str.matches("-?\\d+")) {
            return false;
        }

        try {
            int intValue = Integer.parseInt(str);
            return intValue >= Integer.MIN_VALUE && intValue <= Integer.MAX_VALUE;
            // Rela e cute ʕ•́ᴥ•̀ʔっ
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
