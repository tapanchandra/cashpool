<#import "common/layout.ftl" as c/>
<@c.page title="Cashpool - A Expense sharing application" needspinner=true>
    <div id="overlay"></div>
    <div id="loader"></div>
    <div id="tripEditModal">
        <form action="#">
            <div class="form-group text-center">
                <h2>Edit Trip Details</h2>
            </div>
            <div class="form-group">
                <label for="name">Trip Name</label>
                <input type="text" class="form-control" id="tripNameEdit" name="tripName" value="<#if trip.getTripName()??>${trip.getTripName()}</#if>">
            </div>
            <div class="form-group">
                <label for="name">Location</label>
                <input type="text" class="form-control" id="tripLocationEdit" name="tripLocation" value="<#if trip.getTripLocation()??>${trip.getTripLocation()}</#if>">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <input type="text" class="form-control" id="tripDescriptionEdit" name="tripDescription" value="<#if trip.getTripDescription()??>${trip.getTripDescription()}</#if>">
            </div>
            <button type="submit" class="btn btn-default" id="tripEditSubmit">Submit</button>
            <button class="btn btn-default" id="tripEditCancel">Cancel</button>
        </form>
    </div>
    <main role="main" class="container mt-5">
        <section class="jumbotron mt-5">
            <#include "trip/tripInfo.ftl" parse=true>
        </section>

        <#if trip.getTripStatus().name() == "STARTED">
            <section class="jumbotron mt-5">
            <#include "trip/actionForms.ftl" parse=true>
            </section>
        </#if>

        <#if haspassbook>
        <section class="jumbotron mt-5">
            <#include "trip/passbook.ftl" parse=true>
        </section>
        </#if>

        <section class="jumbotron mt-5">
            <#include "trip/expenselist.ftl" parse=true>
        </section>
    </main>

    <script type="text/javascript" src="/js/tripActions.js"></script>
</@c.page>