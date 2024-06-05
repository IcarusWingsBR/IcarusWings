package icaruswings.utils.validator

class CpfCnpjValidator {

    public static String cleanCpfCnpj(String cpf) {
        String sanitizedCpf = cpf.replaceAll("[^0-9]", "")

        return sanitizedCpf
    }

    public static Boolean isCPF(String cpf) {
        if(allCpfDigitsAreTheSame(cpf)) return false

        if(!isValidCpf(cpf)) return false

        return true
    }

    public static Boolean isCNPJ(String cnpj) {
        if(allCnpjDigitsAreTheSame(cnpj)) return false

        if(!isValidCnpj(cnpj)) return false

        return true
    }

    private static Boolean isValidCpf(String cpf) {
        String sanitizedCpf = cleanCpfCnpj(cpf)
        String firstDigit = calculateFirstCpfDigit(sanitizedCpf)
        String secondDigit = calculateSecondCpfDigit(sanitizedCpf)

        String validCpf = "${sanitizedCpf[0..8]}${firstDigit}${secondDigit}"

        return sanitizedCpf == validCpf
    }

    private static String calculateFirstCpfDigit(String cpf) {
        String sanitizedCpf = cleanCpfCnpj(cpf)
        int cpfSum = 0
        int actualSum = 0
        int i = 0
        int regress_mult = 10

        sanitizedCpf[0..8].each { number ->
            if (i <= 8) {
                actualSum = regress_mult * Integer.parseInt(number)
                cpfSum += actualSum
                regress_mult -= 1
                i += 1
            }
        }

        int firstValidDigit = (cpfSum * 10) % 11

        if (firstValidDigit > 9) {
            firstValidDigit = 0
        }

        return firstValidDigit.toString()
    }

    private static String calculateSecondCpfDigit(String cpf) {
        String sanitizedCpf = cleanCpfCnpj(cpf)
        sanitizedCpf = "${sanitizedCpf[0..8]}${calculateFirstCpfDigit(cpf)}" //Sanitized + first digit
        int cpfSum = 0
        int actualSum = 0
        int i = 0
        int regress_mult = 11

        sanitizedCpf[0..9].each { number ->
            if (i <= 9) {
                actualSum = regress_mult * Integer.parseInt(number)
                cpfSum += actualSum
                regress_mult -= 1
                i += 1
            }
        }

        int secondValidDigit = (cpfSum * 10) % 11

        if (secondValidDigit > 9) {
            secondValidDigit = 0
        }

        return secondValidDigit.toString()
    }

    private static Boolean isValidCnpj(String cnpj) {
        String sanitizedCnpj = cleanCpfCnpj(cnpj)
        String firstDigit = calculateFirstCnpjDigit(sanitizedCnpj)
        String secondDigit = calculateSecondCnpjDigit(sanitizedCnpj)

        String validCnpj= "${sanitizedCnpj[0..11]}${firstDigit}${secondDigit}"

        return sanitizedCnpj == validCnpj
    }

    private static String calculateFirstCnpjDigit(String cnpj) {
        String sanitizedCnpj = cleanCpfCnpj(cnpj)
        int cnpjSum = 0
        int actualSum = 0
        int i = 0
        int regress_mult = 5

        sanitizedCnpj[0..11].each { number ->
            if (i <= 11) {
                actualSum = regress_mult * Integer.parseInt(number)
                cnpjSum += actualSum
                regress_mult -= 1
                if (regress_mult < 2) {
                    regress_mult = 9
                }
                i += 1
            }
        }

        int firstValidDigit = 11 - (cnpjSum % 11)

        if (firstValidDigit >= 10) {
            firstValidDigit = 0
        }

        return firstValidDigit.toString()
    }

    private static String calculateSecondCnpjDigit(String cnpj) {
        String sanitizedCnpj = cleanCpfCnpj(cnpj)
        sanitizedCnpj = "${sanitizedCnpj[0..11]}${calculateFirstCnpjDigit(cnpj)}" // Sanitized + first digit
        int cnpjSum = 0
        int actualSum = 0
        int i = 0
        int regress_mult = 6

        sanitizedCnpj[0..12].each { number ->
            if (i <= 12) {
                actualSum = regress_mult * Integer.parseInt(number)
                cnpjSum += actualSum
                regress_mult -= 1
                if (regress_mult < 2) {
                    regress_mult = 9
                }
                i += 1
            }
        }

        int secondValidDigit = 11 - (cnpjSum % 11)

        if (secondValidDigit >= 10) {
            secondValidDigit = 0
        }

        return secondValidDigit.toString()
    }

    private static Boolean allCpfDigitsAreTheSame(String cpf) {
        String sanitizedCpf = cleanCpfCnpj(cpf)
        List<String> sameDigitsCpfs = [
                "00000000000",
                "11111111111",
                "22222222222",
                "33333333333",
                "44444444444",
                "55555555555",
                "66666666666",
                "77777777777",
                "88888888888",
                "99999999999"
        ]

        return sameDigitsCpfs.contains(sanitizedCpf)
    }

    private static Boolean allCnpjDigitsAreTheSame(String cnpj) {
        String sanitizedCnpj = cleanCpfCnpj(cnpj)
        List<String> sameDigitsCnpjs = [
                "00000000000000",
                "11111111111111",
                "22222222222222",
                "33333333333333",
                "44444444444444",
                "55555555555555",
                "66666666666666",
                "77777777777777",
                "88888888888888",
                "99999999999999"
        ]

        return sameDigitsCnpjs.contains(sanitizedCnpj)
    }
}