<#macro page title>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
            <title>${title?html}</title>
            <#--<script src="/webjars/jquery/jquery.min.js"></script>-->
            <#--<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>-->
            <#--<script src="/webjars/bootstrap-confirmation2/bootstrap-confirmation.min.js"></script>-->
            <#--<script src="/main.js"></script>-->
            <#--<link href="/webjars/bootstrap/css/bootstrap.min.css" rel='stylesheet' type='text/css'>-->
            <#--<script src="/webjars/bootstrap-select/bootstrap-select.min.js"></script>-->
            <#--<link href="/webjars/font-awesome/css/font-awesome.min.css" rel="stylesheet">-->
            <#--<link href="/webjars/bootstrap-select/bootstrap-select.min.css" rel="stylesheet">-->
            <#--<link href="/css/main.css" rel='stylesheet' type='text/css'>-->
        </head>
    <body>
        Hello World - Header
        <#nested/>
        Footer
    </body>
    </html>
</#macro>



