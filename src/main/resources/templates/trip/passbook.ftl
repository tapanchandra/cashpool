<div class="container features text-center">
    <div class="row">
        <div class="col mb-5">
            <h1>Summary</h1>
        </div>
    </div>

    <div class="row">
        <div class="col mb-5">
            <table id="passbookGrid" class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Settlements</th>
                </tr>
                </thead>
                <tbody>
                    <#list trip.getPassbookEntries() as entry>
                    <tr>
                        <td> ${entry.getPayer().getName()} will pay ${entry.getReceiver().getName()} ${entry.getAmount()} USD</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>