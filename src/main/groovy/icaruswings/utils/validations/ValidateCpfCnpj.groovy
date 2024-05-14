package icaruswings.utils.validations

import icaruswings.Customer

class ValidateCpfCnpj {

    public static Boolean isCPF(String cpf) {
        return checkForCpfFormat(cpf) && !allCpfDigitsAreTheSame(cpf) && isValidCpf(cpf)  && checkIfCpfExists(cpf)
    }

    public static Boolean isCNPJ(String cnpj) {
        return checkForCnpjFormat(cnpj) && !allCnpjDigitsAreTheSame(cnpj) && isValidCnpj(cnpj) && checkIfCnpjExists(cnpj)
    }

    public static checkIfCpfExists(String cpf) {
        String sanitizedCpf = cleanCpf(cpf)
        Customer customer = Customer.findByCpfCnpj(sanitizedCpf)

        if(customer == null) {
            return true
        }

        return false
    }

    public static checkIfCnpjExists(String cnpj) {
        String sanitizedCnpj = cleanCnpj(cnpj)
        Customer customer = Customer.findByCpfCnpj(sanitizedCnpj)

        if(customer == null) {
            return true
        }

        return false
    }

    public static Boolean checkForCpfFormat(String cpf) {
        if (!cpf) return false

        return (cpf.matches(~/\d{3}\.\d{3}\.\d{3}-\d{2}/) || (cpf.matches("\\d{11}")))
    }

    public static Boolean allCpfDigitsAreTheSame(String cpf) {
        String sanitizedCpf = cleanCpf(cpf)
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

    public static String cleanCpf(String cpf) {
        String sanitizedCpf = cpf.replace(".", "").replace("-", "")

        return sanitizedCpf
    }

    public static String calculateFirstCpfDigit(String cpf) {
        String sanitizedCpf = cleanCpf(cpf)
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

    public static String calculateSecondCpfDigit(String cpf) {
        String sanitizedCpf = cleanCpf(cpf)
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

    public static Boolean isValidCpf(String cpf) {
        String sanitizedCpf = cleanCpf(cpf)
        String firstDigit = calculateFirstCpfDigit(sanitizedCpf)
        String secondDigit = calculateSecondCpfDigit(sanitizedCpf)

        String validCpf = "${sanitizedCpf[0..8]}${firstDigit}${secondDigit}"

        return sanitizedCpf == validCpf
    }

    public static Boolean checkForCnpjFormat(String cnpj) {
        if (!cnpj) return false

        return (cnpj.matches(/^\d{2}\.\d{3}\.\d{3}\/\d{4}\-\d{2}$/) || (cnpj.matches("\\d{14}")))
    }


    public static String cleanCnpj(String cnpj) {
        String sanitizedCnpj = cnpj.replace(".", "").replace("-", "").replace("/", "")

        return sanitizedCnpj
    }

    public static Boolean allCnpjDigitsAreTheSame(String cnpj) {
        String sanitizedCnpj = cleanCnpj(cnpj)
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

    public static String calculateFirstCnpjDigit(String cnpj) {
        String sanitizedCnpj = cleanCnpj(cnpj)
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

    public static String calculateSecondCnpjDigit(String cnpj) {
        String sanitizedCnpj = cleanCnpj(cnpj)
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

    public static Boolean isValidCnpj(String cnpj) {
        String sanitizedCnpj = cleanCnpj(cnpj)
        String firstDigit = calculateFirstCnpjDigit(sanitizedCnpj)
        String secondDigit = calculateSecondCnpjDigit(sanitizedCnpj)

        String validCnpj= "${sanitizedCnpj[0..11]}${firstDigit}${secondDigit}"

        return sanitizedCnpj == validCnpj
    }
}