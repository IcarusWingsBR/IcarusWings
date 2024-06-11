package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.adapters.PayerAdapter
import icaruswings.repositories.PayerRepository
import icaruswings.utils.validator.PostalCodeValidator
import icaruswings.utils.validator.CpfCnpjValidator
import icaruswings.utils.validator.EmailValidator
import icaruswings.utils.validator.PhoneValidator
import icaruswings.utils.string.StringUtils
import icaruswings.payment.Payment
import icaruswings.repositories.PaymentRepository
import icaruswings.payment.PaymentStatus

@Transactional
class PayerService {

    public Payer save(PayerAdapter payerAdapter) {
        Payer validatedPayer = validateSave(payerAdapter)

        if (validatedPayer.hasErrors()) throw new ValidationException("Não foi possível salvar o pagador", validatedPayer.errors)

        Payer payer = new Payer()
        payer.name = payerAdapter.name
        payer.email = payerAdapter.email
        payer.cpfCnpj = payerAdapter.cpfCnpj
        payer.postalCode = payerAdapter.postalCode
        payer.address = payerAdapter.address
        payer.province = payerAdapter.province
        payer.city = payerAdapter.city
        payer.state = payerAdapter.state
        payer.addressNumber = payerAdapter.addressNumber
        payer.addressComplement = payerAdapter.addressComplement
        payer.customer = payerAdapter.customer
        payer.phone = payerAdapter.phone
        payer.personType = payerAdapter.personType
        payer.save(failOnError: true)

        return payer
    }

    public void update(PayerAdapter payerAdapter) {
        Payer validatedPayer = validateSave(payerAdapter)

        if (validatedPayer.hasErrors()) throw new ValidationException("Não foi possível salvar o pagador", validatedPayer.errors)

        Long id = payerAdapter.id
        Payer payer = PayerRepository.get(id)
        payer.name = payerAdapter.name
        payer.email = payerAdapter.email
        payer.cpfCnpj = payerAdapter.cpfCnpj
        payer.phone = payerAdapter.phone
        payer.postalCode = payerAdapter.postalCode
        payer.address = payerAdapter.address
        payer.province = payerAdapter.province
        payer.city = payerAdapter.city
        payer.state = payerAdapter.state
        payer.addressNumber = payerAdapter.addressNumber
        payer.addressComplement = payerAdapter.addressComplement
        payer.customer = payerAdapter.customer
        payer.save(failOnError: true)
    }

    public void delete(Long id){
        Payer payer = PayerRepository.get(id)

        if (!payer) throw new RuntimeException("Esse pagador não existe")

        List<PaymentStatus> paymentStatuses = [PaymentStatus.PENDING, PaymentStatus.OVERDUE]

        List<Payment> payments = PaymentRepository.query([
            payer:id,
             "paymentStatus[in]": paymentStatuses
        ]).readOnly().list()

        if (!payments.isEmpty() && payments != null) throw new RuntimeException("Esse pagador tem cobranças pendentes")

        payer.deleted = true

        payer.save(failOnError: true)
    }

    public void deleteAllPayersForCustomer(Long customerId) {
        List<Long> payerIds = PayerRepository.query([customer: customerId]).column("id").readOnly().list()

        for (Long id : payerIds) {
            Payer.withNewTransaction { deletePayer ->
                try {
                    delete(id)
                } catch (Exception exception) {
                    log.info("deletePayer>> Erro ao excluir o pagador de id: [${id}] [Mensagem de erro]: ${exception.message}")
                }
            }
        }
    }

    public void restore(Long id){
        Payer payer = PayerRepository.query([id:id, deletedOnly:true]).get()

        if (!payer) throw new RuntimeException("Esse pagador não está deletado ou não existe")

        payer.deleted = false

        payer.save(failOnError: true)
    }

    public List<Payer> list(){
        return PayerRepository.query([:]).list()
    }

    public List<Payer> deletedList(){
        return PayerRepository.query([deletedOnly:true]).readOnly().list()
    }

    private Payer validateSave(PayerAdapter payerAdapter) {
        Payer payer = new Payer()

        if (!payerAdapter.name) {
            payer.errors.rejectValue("name", null, "O campo nome é obrigatório")
        } else if (!StringUtils.isValidString(payerAdapter.name)) {
            payer.errors.rejectValue("name", null, "O nome informado é inválido")
        }

        if (!payerAdapter.email) {
            payer.errors.rejectValue("email", null, "O campo email é obrigatório")
        } else if (!EmailValidator.isValidEmail(payerAdapter.email)) {
            payer.errors.rejectValue("email", null, "O email informado é inválido")
        }

        if (!payerAdapter.cpfCnpj) {
            payer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj é obrigatório")
        } else if (!CpfCnpjValidator.isCPF(payerAdapter.cpfCnpj) && !CpfCnpjValidator.isCNPJ(payerAdapter.cpfCnpj)) {
            payer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj está inválido")
        }

        if (!payerAdapter.phone) {
            payer.errors.rejectValue("phone", null, "O campo telefone é obrigatório")
        } else if (!PhoneValidator.isValidPhoneNumber(payerAdapter.phone)) {
            payer.errors.rejectValue("phone", null, "O numero de telefone inserido é inválido")
        }

        if (!payerAdapter.postalCode) {
            payer.errors.rejectValue("postalCode", null, "O campo cep é obrigatório")
        } else if (!PostalCodeValidator.isValid(payerAdapter.postalCode)) {
            payer.errors.rejectValue("postalCode", null, "O cep inserido é inválido")
        }

        if (!payerAdapter.address) {
            payer.errors.rejectValue("address", null, "O campo rua é obrigatório")
        }

        if (!payerAdapter.province) {
            payer.errors.rejectValue("province", null, "O campo bairro é obrigatório")
        }

        if (!payerAdapter.addressNumber) {
            payer.errors.rejectValue("addressNumber", null, "O campo número de residência é obrigatório")
        }

        if (!payerAdapter.addressComplement) {
            payer.errors.rejectValue("addressComplement", null, "O campo complemento é obrigatório")
        }

        if (!payerAdapter.city) {
            payer.errors.rejectValue("city", null, "O campo cidade é obrigatório")
        } else if (!StringUtils.isValidString(payerAdapter.city)) {
            payer.errors.rejectValue("city", null, "A cidade informado é inválida")
        }

        if (!payerAdapter.state) {
            payer.errors.rejectValue("state", null, "O campo estado é obrigatório")
        } else if (!StringUtils.isValidString(payerAdapter.state)) {
            payer.errors.rejectValue("state", null, "O estado informado é inválido")
        }

        return payer
    }
}