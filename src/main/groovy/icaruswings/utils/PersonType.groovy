package icaruswings.utils

enum PersonType {
    NATURAL,

    LEGAL

    public static PersonType convert(String personType) {
        try {
            if (personType instanceof String) personType = personType.toUpperCase()
            return personType as PersonType
        } catch(Exception e) {
            return null
        }
    }
}