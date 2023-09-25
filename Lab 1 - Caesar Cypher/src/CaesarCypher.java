import java.util.HashMap;

public class CaesarCypher {
    HashMap<Integer, Character> alphabet = new HashMap<Integer, Character>();

    public CaesarCypher() {
        char currentLetter = 'A';
        for (int i = 0; i < 26; i++) {
            alphabet.put(i, currentLetter);
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

    public Boolean checkKey2Validity(String key) {
        if (containsOnlyLatinCharacters(key) && key.length() >= 7)
            return true;
        return false;
    }

    private boolean containsOnlyLatinCharacters(String str) {
        return str.matches("[a-zA-Z]+");
    }

    private void modifyAlphabet(String key2) {
        HashMap<Integer, Character> alphabetCopy = new HashMap<Integer, Character>();
        int position = 0;
        for (char c : key2.toCharArray()) {
            if (!alphabetCopy.containsValue(Character.toUpperCase(c))) {
                alphabetCopy.put(position, Character.toUpperCase(c));
                position++;
            }
        }
        char currentLetter = 'A';
        for (int i = 0; i < 26; i++) {
            if (!alphabetCopy.containsValue(currentLetter)) {
                alphabetCopy.put(position, currentLetter);
                position++;
            }
            currentLetter++;
        }
        alphabet.clear();
        alphabet.putAll(alphabetCopy);
        System.out.print("Modified alphabet:");
        for (HashMap.Entry<Integer, Character> entry : alphabet.entrySet()) {
            Character value = entry.getValue();
            System.out.print(value);
        }
        System.out.println("");
    }

    public void processMessage(String message, String key, boolean isEncrypting, String key2) {
        StringBuilder resultMessage = new StringBuilder();
        String transformedMessage = message.replaceAll("\\s", "").toUpperCase();
        if (!key2.equals("1")) {
            modifyAlphabet(key2);
        }
        System.out.println("Message to " + (isEncrypting ? "encrypt" : "decrypt") + ": " + transformedMessage);
        int shift = Integer.parseInt(key);
        if (!isEncrypting) {
            shift = 26 - shift;
        }
        Integer target = 0;
        for (char c : transformedMessage.toCharArray()) {
            for (HashMap.Entry<Integer, Character> entry : alphabet.entrySet()) {
                Character value = entry.getValue();
                Integer charOrder = entry.getKey();
                if (c == value) {
                    target = charOrder;
                    break;
                }
            }
            char processedChar = alphabet.get((target + shift) % 26);
            resultMessage.append(processedChar);
        }
        System.out.println((isEncrypting ? "Encrypted" : "Decrypted") + " message: " + resultMessage.toString());
    }

    public void encryptMessage(String message, String key, String key2) {
        processMessage(message, key, true, key2);
    }

    public void decryptMessage(String message, String key, String key2) {
        processMessage(message, key, false, key2);
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
