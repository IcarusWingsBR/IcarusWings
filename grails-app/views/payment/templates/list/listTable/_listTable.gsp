<atlas-table has-actions>
    <g:render template="templates/list/listTable/columnHeaders"/>
    <g:render template="templates/list/listTable/tableBody" model="${[paymentList: paymentList]}" />
</atlas-table>