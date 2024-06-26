package icaruswings.utils.validator

class PhoneValidator {

    public static Boolean isValidPhoneNumber(String phoneNumber) {
        String sanitizedPhoneNumber = cleanPhoneNumber(phoneNumber)

        if(sanitizedPhoneNumber.length() != 11) return false

        String ddd = sanitizedPhoneNumber.substring(0, 2)

        if(!isValidDDD(ddd)) return false

        String digitNine = sanitizedPhoneNumber.substring(2, 3)
                
        if(!digitNine.equals("9")) return false

        return true;
    }

    public static String cleanPhoneNumber(String phoneNumber) {
        String sanitizedPhoneNumber = phoneNumber.replaceAll("[^0-9]", "")

        return sanitizedPhoneNumber
    }

    private static Boolean isValidDDD(String ddd) {
        List<String> dddCodes = [
            "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "21", "22", "24", "27", "28", "31", "32", "33", "34",
            "35", "37", "38", "41", "42", "43", "44", "45", "46",
            "47", "48", "49", "51", "53", "54", "55", "61", "62",
            "64", "63", "65", "66", "67", "68", "69", "71", "73",
            "74", "75", "77", "79", "81", "82", "83", "84", "85",
            "86", "87", "88", "89", "91", "92", "93", "94", "95",
            "96", "97", "98", "99"
        ]

        return ddd.contains(ddd)
    }
}