<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="layout" content="main">
    <title>Mostrar Cobrança</title>
    <asset:javascript src="payment/PaymentShowController.js"/>
    <asset:javascript src="delete/DeleteHandler.js"/>
    <asset:stylesheet href="payment/show.css"/>
</head>
<body page-title="Detalhes da Cobrança">
    <atlas-form-panel action="${createLink(controller: "payment", action: "update")}" header="Detalhes da cobrança - ${payment.id}" class="js-edit-payment-form">
        <atlas-input
            value="${payment.id}"
            name="id"
            hidden
        >
        </atlas-input>
        <g:if test="${ !payment.deleted }">
            <atlas-button-group slot="actions" group-after="3">
                <g:if test="${ payment.paymentStatus.toString() != 'PAYED' }">
                    <atlas-button description="Editar" data-panel-start-editing></atlas-button>
                    <atlas-button
                        icon="check"
                        theme="primary"
                        description="Confirmar Pagamento"
                        class="js-open-confirm-received-modal-button"
                    ></atlas-button>
                </g:if>
                <atlas-button
                    icon="trash"
                    theme="primary"
                    description="Excluir cobrança"
                    class="js-open-delete-modal-button"
                ></atlas-button>
            </atlas-button-group>
        </g:if>
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
                        value="${formatTagLib.formatValue(value: payment.value)}"
                    />
                </atlas-col>
            </atlas-row>
            <atlas-row>
                <atlas-col lg="6">
                    <atlas-input
                        label="Data de criação"
                        name="dateCreated"
                        value="${formatTagLib.formatedDate(date: payment.dateCreated)}"
                        required="true"
                        readonly
                    />
                </atlas-col>
                <atlas-col lg="6">
                    <atlas-select
                        label="Forma de cobrança"
                        name="paymentType"
                        placeholder="${payment.paymentType}"
                        value="${payment.paymentType}"
                        required="true"
                    >
                        <atlas-option label="Boleto" value="BANK_SLIP"/></atlas-option>
                        <atlas-option label="Cartão" value="CARD"/></atlas-option>
                        <atlas-option label="Pix" value="PIX"></atlas-option>
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
                        value="${formatTagLib.formatedDate(date: payment.dueDate)}"
                    />
                </atlas-col> 
            </atlas-row>
        </atlas-grid>
        <atlas-modal header="Excluir Cobrança" class="js-delete-modal">
            <atlas-form method="POST" action="${createLink(controller: "payment", action: "delete", id: "${payment.id}")}">
                Você realmente quer excluir essa cobrança?
                <atlas-button class="margin-modal-button" submit description="Excluir" theme="danger"></atlas-button>
            </atlas-form>
        </atlas-modal>
        <atlas-modal header="Confirmar Pagamento" class="js-close-confirm-received-modal">
            <atlas-form method="POST" action="${createLink(controller: "payment", action: "confirmPaymentReceived", id: "${payment.id}")}">
                Você realmente quer confirmar essa cobrança como paga?
                <atlas-button class="margin-modal-button" submit description="Confirmar Pagamento" theme="primary"></atlas-button>
            </atlas-form>
        </atlas-modal>
        <g:if test="${flash.message}">
            <atlas-modal header="${flash.type == "success" ? "Cobrança editada" : "Erro"}" open="">${flash.message}</atlas-modal>
        </g:if>
    </atlas-form-panel>
</body>
</html>