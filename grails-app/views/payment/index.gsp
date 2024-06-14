<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="layout" content="main">
    <title>Criar Cobrança</title>
</head>
<body page-title="Criar Cobrança">
    <atlas-panel>
        <atlas-form method="POST" action="${createLink(customer: "payer", action: "save")}">
            <atlas-grid>
                <atlas-row>
                    <atlas-col lg="6">
                        <atlas-select
                                label="Payer"
                                name="payerId"
                                required="true"
                        >
                            <g:each var="payer" in="${ payerList }">
                                <atlas-option label="${payer.name}" value="${payer.id}"></atlas-option>
                            </g:each>
                        </atlas-select>
                    </atlas-col>
                    <atlas-col lg="6">
                        <atlas-select
                            label="Forma de cobrança"
                            name="paymentType"
                            placeholder="Selecione uma opção"
                            required="true"
                        >
                            <atlas-option label="Boleto" value="bank_slip"/></atlas-option>
                            <atlas-option label="Cartão" value="card"/></atlas-option>
                            <atlas-option label="Pix" value="pix"></atlas-option>
                        </atlas-select>
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
                        />
                    </atlas-col>
                    <atlas-col lg="6">
                        <atlas-datepicker
                            label="Data de vencimento"
                            name="dueDate"
                            required="true"
                        />
                    </atlas-col>
                </atlas-row>
            </atlas-grid>
            <atlas-button submit description="Salvar"></atlas-button>
        </atlas-form>
        <g:if test="${flash.message}">
            <atlas-modal header="${flash.type == "succes" ? "Cobrança editada" : "Erro"}" open="">${flash.message}</atlas-modal>
        </g:if>
    </atlas-panel>
</body>
</html>