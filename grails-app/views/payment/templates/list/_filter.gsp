<atlas-filter >
    <atlas-form slot="simple-filter" action="${createLink(customer: "payment", action: "list")}">
        <atlas-filter-group header="Listar Cobranças" name="paymentList">
            <atlas-radio value="active" checked class="js-filter-options">Cobranças Ativas</atlas-radio>
            <atlas-radio value="deleted" class="js-filter-options">Cobranças Excluídas</atlas-radio>
        </atlas-filter-group>
        <atlas-button class="js-filter-button" submit description="Filtrar"></atlas-button>
    </atlas-form>
</atlas-filter>