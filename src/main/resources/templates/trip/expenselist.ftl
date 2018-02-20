<div class="container features text-center">
    <div class="row">
        <div class="col mb-5">
            <h1>Expenses</h1>
        </div>
    </div>

    <#if hasExpenses>
    <div class="row">
        <div class="col mb-5">
            <table id="expensesGrid" class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Description</th>
                    <th scope="col">Paid By</th>
                    <th scope="col">Paid For</th>
                    <th scope="col">Amount</th>
                </tr>
                </thead>
                <tbody>
                    <#list trip.getExpenses() as expense>
                    <tr>
                        <th scope="row">${expense_index + 1}</th>
                        <td>${expense.getExpenseDescription()}</td>
                        <td>${expense.getPaidBy().getName()}</td>
                        <td>
                            <#list expense.getPaidFor() as traveller>
                                ${traveller.getName()}<#if traveller_has_next>,</#if>
                            </#list>
                        </td>
                        <td>${expense.getAmount()} USD</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <#else>
        <div class="row">
            <div class="col-lg-12 text-center">
                <span class="text-danger">No Expenses added yet</span>
            </div>
        </div>
    </#if>
</div>