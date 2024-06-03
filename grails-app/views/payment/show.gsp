<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="layout" content="main">
    <title>Criar Cobrança</title>
    <asset:javascript src="payment/PaymentShowController.js"/>
</head>
<body page-title="Detalhes da Cobrança">
    <atlas-form-panel action="${createLink(controller: "payment", action: "update")}" header="Detalhes da cobrança - ${payment.id}" class="js-edit-payment-form">
        <atlas-input
            value="${payment.id}"
            name="id"
            hidden
        >
        </atlas-input>
        <atlas-button slot="actions" description="Editar" data-panel-start-editing></atlas-button>
        <atlas-grid>
            <atlas-row>
                <atlas-col lg="6">
                    <atlas-select
                        label="Pagador"
                        name="payerId"
                        value="${payment.payer.id}"
                        required="true"
                        class="js-payer"
                    >
                        <g:each var="payer" in="${ payerList }">
                            <atlas-option label="${payer.name}" 
                                value="${payer.id}" 
                                name="${payer.name}"
                                email="${payer.email}"
                                cpfCnpj="${payer.cpfCnpj}"
                                >
                            </atlas-option>
                        </g:each>
                    </atlas-select>
                </atlas-col>
                <atlas-col lg="6">
                    <atlas-masked-input
                        label="Cpf/Cnpj do pagador"
                        name="cpfCnpj"
                        mask-alias="cpf-cnpj"
                        value="${payment.payer.cpfCnpj}"
                        required="true"
                        readonly
                        class="js-payer-cpfCnpj"
                    />
                </atlas-col>
            </atlas-row>
            <atlas-row>
                <atlas-col lg="6">
                   <atlas-input
                        label="Email do pagador"
                        name="email"
                        value="${payment.payer.email}"
                        required="true"
                        readonly
                        class="js-payer-email"
                    />
                </atlas-col>
                <atlas-col lg="6">
                    <atlas-float-input 
                        label="Valor" 
                        name="value"
                        required="true"
                        max-value="100000000"
                        max-value-error-message="O valor é maior que R$ 100.000.000,00"
                        value="${payment.value}"
                    />
                </atlas-col>
            </atlas-row>
            <atlas-row>
                <atlas-col lg="6">
                    <atlas-input
                        label="Data de criação"
                        name="dateCreated"
                        value="${formatTagLib.formatedDateCreated(date: payment.dateCreated)}"
                        required="true"
                        readonly
                    />
                </atlas-col>
                <atlas-col lg="6">
                    <atlas-select 
                        label="Forma de cobrança" 
                        name="paymentType"
                        placeholder="${payment.paymentType}"
                        value="${message(code: 'PaymentType.' + payment.paymentType + '.label')}"
                        required="true"
                    >
                        <atlas-option label="Boleto" value="boleto"/></atlas-option>
                        <atlas-option label="Cartão" value="bankslip"/></atlas-option>
                        <atlas-option label="Pix" value="Pix"></atlas-option>
                    </atlas-select>
                </atlas-col>
            </atlas-row>
            <atlas-row>
                <atlas-col lg="6">
                    <atlas-input 
                        label="Status da cobrança" 
                        name="paymentStatus"
                        required="true"
                        value="${message(code: 'PaymentStatus.' + payment.paymentStatus + '.label')}"
                        readonly
                    />
                </atlas-col>
                <atlas-col lg="6">
                    <atlas-datepicker 
                        label="Data de vencimento"
                        name="dueDate"
                        required="true"
                        value="${formatTagLib.formatedDateCreated(date: payment.dueDate)}"
                    />
                </atlas-col>
            </atlas-row>
        </atlas-grid>
    </atlas-form-panel>
</body>
</html>