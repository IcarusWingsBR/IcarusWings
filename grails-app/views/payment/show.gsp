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
        <g:render template="templates/show/mainActions" model="${[payment: payment]}" />
        <g:render template="templates/show/mainInfo" model="${[payment: payment, payer: payer]}" />
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