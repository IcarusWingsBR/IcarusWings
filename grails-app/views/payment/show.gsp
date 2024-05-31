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
                    <atlas-input
                            label="Nome do pagador"
                            name="payerName"
                            value="${payment.payer.name}"
                            required="true"
                    >
                    </atlas-input>
                </atlas-col>
                <atlas-col lg="6">
                    <atlas-select
                            label="pagador"
                            name="payerId"
                            required="true"
                    >
                    <g:each var="payer" in="${ payerList }">
                        <atlas-option label="${payer.name}" value="${payer.id}"></atlas-option>
                    </g:each>
                    </atlas-select>
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
                    />
                </atlas-col>
                <atlas-col lg="6">
                    <atlas-input
                            label="Email do pagador"
                            name="email"
                            value="${payment.payer.email}"
                            required="true"
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
                    <atlas-input
                            label="Tipo de cobrança"
                            name="paymentType"
                            value="${payment.paymentType}"
                            required="true"
                    >
                    </atlas-input>
                </atlas-col>
            </atlas-row>
        </atlas-grid>
    </atlas-form-panel>
</body>
</html>