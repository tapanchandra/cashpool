<div class="container">
    <div class="row">
        <div class="col-lg-7 col-sm-7">
            <div class="row">
                <div class="col-lg-12 col-sm-12">
                    <span class="jumbotron-heading title">${trip.getTripName()}</span>
                    <a href="#" id="editTripDetails" alt="Edit" title="Edit trip details">
                        <span class="input-group-addon"><i class="fas fa-edit fa" aria-hidden="true"></i></span>
                    </a>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6 col-sm-6">
                    <h4 class="mt-3">Status</h4>
                </div>
                <div class="col-lg-6 col-sm-6">
                    <span class="boldtext">${trip.getTripStatus().name()}</span>
                    <#if trip.getTripStatus().name() == "STARTED">
                    <a id="endTripButton" href="#" class="align-content-center ml-2 btn btn-primary btn-sm">End Trip</a>
                    </#if>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-6 col-sm-6">
                    <h4 class="mt-3">Date Created</h4>
                </div>
                <div class="col-lg-6 col-sm-6">
                    <span class="boldtext">${trip.getCreatedDate()?date}</span>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-sm-6">
                    <h4 class="mt-3">Travellers</h4>
                </div>
                <div class="col-lg-6 col-sm-6">
                    <span class="boldtext">
                        <#if (trip.getTravellers()?size > 0)>
                            <#list trip.getTravellers() as traveller>
                                ${traveller.getName()}<#if traveller_has_next>,</#if>
                            </#list>
                        <#else>
                            None
                        </#if>
                    </span>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-sm-6">
                    <h4 class="mt-3">Total Expense</h4>
                </div>
                <div class="col-lg-6 col-sm-6">
                    <span class="boldtext">${totalExpense} USD</span>
                </div>
            </div>
        </div>
        <div class="col-lg-5 col-sm-5">
            <h5>${trip.getTripLocation()?capitalize}</h5>
            <img src="https://maps.googleapis.com/maps/api/staticmap?center=<#if trip.getTripLocation()??>${trip.getTripLocation()}<#else>hyderabad</#if>&size=400x200&maptype=roadmap&key=AIzaSyCSRHix6_vJnpCPnHCOWOvjbpdnzTemWsw">
        </div>
    </div>
</div>