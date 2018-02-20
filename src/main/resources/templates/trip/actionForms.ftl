<div class="container">
    <div class="row">

        <!--                 -->
        <!-- Add a traveller -->
        <!--                 -->
        <div class="col-lg-5 col-sm-5">
            <h2 class="jumbotron-heading text-center">Add a Traveller</h2>
            <form method="post" action="/trip/${trip.getTripCode()}/traveller/add" data-toggle="validator" role="form">
                <div class="form-group">
                    <label for="email">Email address:</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>


        <div class="col-lg-2 col-sm-2">

        </div>

        <!--                 -->
        <!-- Add an expense -->
        <!--                 -->
        <div class="col-lg-5 col-sm-5">
            <h2 class="jumbotron-heading text-center">Add an Expense</h2>
            <form method="post" action="/trip/${trip.getTripCode()}/expense/add" data-toggle="validator" role="form">
                <div class="form-group">
                    <label for="payer" class="control-label">Paid By</label>
                    <select class="form-control" id="payer" name="paidBy" required>
                        <#if (trip.getTravellers()?size > 0)>
                            <#list trip.getTravellers() as traveller>
                                <option value="${traveller.getId()}">${traveller.getName()}</option>
                            </#list>
                        <#else>
                            <option disabled value="-1">None</option>
                        </#if>
                    </select>
                </div>
                <div class="form-group">
                    <label for="expenseDescription" class="control-label">Expense Description</label>
                    <input type="text" class="form-control" id="expenseDescription" name="expenseDescription" placeholder="Enter Description here">
                </div>

                <div class="form-group">
                    <label for="expenseAmount" class="control-label">Amount</label>
                    <input type="number" class="form-control" id="expenseAmount" name="amount" placeholder="0.00" required>
                </div>

                <div class="form-group">
                    <label for="paidfor" class="control-label">Paid For </label><span>( Do not forget to include yourself )</span>

                    <select class="form-control" name="paidFor" id="paidforselect" multiple="multiple" required>
                        <#if (trip.getTravellers()?size > 0)>
                            <#list trip.getTravellers() as traveller>
                                <option value="${traveller.getId()}">${traveller.getName()}</option>
                            </#list>
                        <#else>
                            <option disabled value="-1">None</option>
                        </#if>
                    </select>
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>
</div>