<#import "../common/layout.ftl" as c/>
<@c.page title="Cashpool - A Expense sharing application" needspinner=false>
    <div class="container text-center">
        <div class="jumbotron error mt-5">
            <h1>${errorTitle}</h1>
            <p>${errorMessage}</p>
        </div>
    </div>
</@c.page>