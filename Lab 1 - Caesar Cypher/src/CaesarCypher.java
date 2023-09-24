public class CaesarCypher {
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

    public void encryptMessage(String message) {
        String transformedMessage = message.replaceAll("\\s", "").toUpperCase();

        System.out.println(transformedMessage);
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
