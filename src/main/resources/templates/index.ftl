<#import "common/layout.ftl" as c/>
<@c.page title="Cashpool - A Expense sharing application" needspinner=true>
    <div id="overlay"></div>
    <div id="loader"></div>
    <main role="main" class="container-fluid mt-5">

        <section class="jumbotron mt-5">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6 col-sm-1">
                        <h1 class="jumbotron-heading">Travel  Unhindered</h1>
                        <p class="lead">
                            Cashpool allows you to enjoy your trip in peace without having to constantly fret about who pays for what.
                            Just enter your expenses and sit back as Cashpool makes all the calculations for you and splits the expenses
                            amongst the group.
                        </p>
                        <p class="lead">
                            <a id="startTripButton" href="#" class="btn btn-lg btn-secondary">Start a new Trip</a>
                        </p>
                    </div>
                    <div class="col-lg-6 col-sm-1">
                        <img class="img-fluid" src="/images/pexels-free-photo-533330.jpeg">
                    </div>
                </div>
            </div>
        </section>
        <section>
            <div class="container features text-center">
                <div class="row">
                    <div class="col mb-5">
                        <h1>Features</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <img class="rounded-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
                        <p>Start a trip and add your co-travellers</p>
                    </div>
                    <div class="col-lg-4">
                        <img class="rounded-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
                        <p>Enter your expenses as you progress</p>
                    </div>
                    <div class="col-lg-4">
                        <img class="rounded-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
                        <p>Sit back while Cashpool calculates the total expenses and everyone's share.</p>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <script type="text/javascript">
        $(document).ready(function () {
            closeSpinner();

            $("#startTripButton").click(function(e) {
                e.preventDefault();

                //Open Spinner
                openSpinner();

                $.ajax({
                    type: "POST",
                    url: "/trip/new",
                    success: function(result) {
                        closeSpinner()
                        $(location).attr('href','/trip/' + result.tripCode);
                    },
                    error: function(result) {
                        closeSpinner()
                        $(location).attr('href','/error');
                    }
                });
            });

        });
    </script>
</@c.page>