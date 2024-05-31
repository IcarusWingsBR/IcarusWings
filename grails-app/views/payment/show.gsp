<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="layout" content="main">
    <title>Criar Cobrança</title>
</head>
<body page-title="Detalhes da Cobrança">
    <atlas-form-panel action="${createLink(controller: "payment", action: "update")}" header="Detalhes da cobrança - ${payment.id}">
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
                        label="pagador"
                        name="payerId"
                        value="${payment.payer.id}"
                        required="true"
                    >
                        <g:each var="payer" in="${ payerList }">
                            <atlas-option label="${payer.name}" value="${payer.id}"></atlas-option>
                        </g:each>
                    </atlas-select>
                </atlas-col>
                <atlas-col lg="6">
                    <atlas-input
                        label="Nome do pagador"
                        name="payerName"
                        value="${payment.payer.name}"
                        required="true"
                        readonly
                    />
                </atlas-col>
            </atlas-row>
            <atlas-row>
                <atlas-col lg="6">
                    <atlas-masked-input
                        label="Cpf/Cnpj do pagador"
                        name="cpfCnpj"
                        mask-alias="cpf-cnpj"
                        value="${payment.payer.cpfCnpj}"
                        required="true"
                        readonly
                    />
                </atlas-col>
                <atlas-col lg="6">
                    <atlas-input
                            label="Email do pagador"
                            name="email"
                            value="${payment.payer.email}"
                            required="true"
                            readonly
                    />
                </atlas-col>
            </atlas-row>
            <atlas-row>
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
                <atlas-col lg="6">
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
                    <atlas-input
                        label="Data de criação"
                        name="dateCreated"
                        value="${formatTagLib.formatedDateCreated(date: payment.dateCreated)}"
                        required="true"
                        readonly
                    />
                </atlas-col>
            </atlas-row>
            <atlas-row>
                <atlas-col lg="6">
                    <atlas-datepicker 
                            label="Data de vencimento"
                            name="dueDate"
                            required="true"
                            value="${formatTagLib.formatedDateCreated(date: payment.dueDate)}"
                    />
                </atlas-col>
                <atlas-col lg="6">
                    <atlas-input 
                        label="Data da última modificação" 
                        name="lastUpdated"
                        required="true"
                        value="${formatTagLib.formatedDateCreated(date: payment.lastUpdated)}"
                        readonly
                    />
                </atlas-col>
            </atlas-row>
        </atlas-grid>
    </atlas-form-panel>
</body>
</html>