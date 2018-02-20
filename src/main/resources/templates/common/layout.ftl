<#macro page title needspinner>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
    <head>
        <title>${title?html}</title>

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta charset="utf-8">

        <link href="/webjars/bootstrap/css/bootstrap.min.css" rel='stylesheet' type='text/css'>
        <link href="/css/main.css" rel="stylesheet" type="text/css">

        <script src="/webjars/jquery/jquery.min.js"></script>
        <link href="/webjars/font-awesome/on-server/css/fontawesome.min.css" rel="stylesheet" type="text/css">
        <script src="/webjars/underscorejs/underscore-min.js"></script>
        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

        <script src="/webjars/bootstrap-multiselect/js/bootstrap-multiselect.js"></script>
        <link href="/webjars/bootstrap-multiselect/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css" />
        <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

        <link href="https://cdnjs.cloudflare.com/ajax/libs/6pac-slickgrid/2.3.12/slick.grid.css" rel="stylesheet" type="text/css" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/6pac-slickgrid/2.3.12/slick-default-theme.min.css" rel="stylesheet" type="text/css" />

        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css" />
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>

        <#if needspinner>
            <link href="/css/loader.css" rel="stylesheet" type="text/css">
            <script src="/js/loader.js"></script>
        </#if>
    </head>
    <body>
        <header>
            <!-- Fixed navbar -->
            <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
                <a class="navbar-brand" href="/">Cashpool</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </nav>
        </header>

        <!-- Begin page content -->
        <#nested>
        <!-- end page content -->

        <footer class="footer">
            <div class="container">
                <span class="text-muted">© Cashpool Inc 2018.</span>
            </div>
        </footer>
    </body>
</html>
</#macro>



