package icaruswings.utils

class ValidateCpfCnpj {
    public static Boolean isCPF(String cpf) {
        if (!checkCpfFormat(cpf)){
            println("O CPF não está formatado")
            return false
        }else if (allDigitsAreTheSame(cpf)){
            println("Todos os dígitos são iguais")
        } else if (!isValidCpf(cpf)){
            println("O cpf não é válido")
        }
        return true
    }

    public static Boolean checkCpfFormat(String cpf) {
        println(cpf)
        if (!cpf) return false

        return cpf.matches(~/\d{3}\.\d{3}\.\d{3}-\d{2}/) || (cpf.matches("\\d{11}"))
    }

    public static Boolean allDigitsAreTheSame(String cpf) {
        cpf = cleanCpf(cpf)
        //substituir uma nova variável
        //corrigir boolean
        List<String> allCpfs = [
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
        return allCpfs.contains(cpf)
    }

    public static String cleanCpf(String cpf){
        String cpfLimpo = cpf.replace(".", "").replace("-", "");
        return cpfLimpo
    }

    public static String calculateFirstDigit(String cpf){
        cpf = cleanCpf(cpf)
        int cpfSum = 0
        int actualSum = 0
        int i = 0
        int regress_mult = 10

        cpf[0..8].each { number ->
            if (i <= 8){
                actualSum = regress_mult * Integer.parseInt(number)
                cpfSum += actualSum
                regress_mult -= 1
                i += 1
            }
        }
        int firstValidDigit = (cpfSum * 10) % 11
        if (firstValidDigit > 9){
            firstValidDigit = 0
        }
        return firstValidDigit.toString()
    }

    public static String calculateSecondDigit(String cpf){
        cpf = "${cleanCpf(cpf[0..8])}${calculateFirstDigit(cpf)}"
        int cpfSum = 0
        int actualSum = 0
        int i = 0
        int regress_mult = 11

        cpf[0..9].each { number ->
            if (i <= 9) {
                actualSum = regress_mult * Integer.parseInt(number)
                cpfSum += actualSum
                regress_mult -= 1
                i += 1
            }
        }
        int secondValidDigit = (cpfSum * 10) % 11

        if (secondValidDigit > 9){
            secondValidDigit = 0
        }
        return secondValidDigit.toString()
    }


    public static Boolean isValidCpf(String cpf) {
        cpf = cleanCpf(cpf)
        String firstDigit = calculateFirstDigit(cpf)
        String secondDigit = calculateSecondDigit(cpf)

        String validCpf = "${cpf[0..8]}${firstDigit}${secondDigit}"

        if (cpf == validCpf) {
            System.out.println("O CPF é válido.")
            return true
        } else {
            System.out.println("O CPF é inválido")
            return false
        }
    }
}