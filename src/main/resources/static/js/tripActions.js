$(document).ready(function () {
    closeSpinner();

    //TODO : bootstrap multiselect not working. Falling back to regular select for now.
    //$('#paidforselect').multiselect();


    //
    //---------Button Handlers -------------//
    //

    $("#endTripButton").click(function (e) {
        e.preventDefault();

        //Confirm
        $.confirm({
            title: 'Confirm!',
            content: 'Are you sure you want to end this trip.',
            buttons: {
                confirm: function () {

                    $.ajax({
                        type: "POST",
                        url: window.location.pathname + '/finish',
                        success: function(result) {
                            window.location.reload(true);
                        },
                        error: function(result) {
                            console.log(result)
                            alert('Unexpected error. please try again later.')
                            $("#overlay").css("display","none");
                            $("#tripEditModal").css("display","none");
                        }
                    });
                },
                cancel: function () {
                    console.log("End trip action cancelled.")
                }
            }
        });
    });


    $("#editTripDetails").click(function (e) {
        e.preventDefault();
        $("#overlay").css("display","block");
        $("#tripEditModal").css("display","block");
    });

    $("#tripEditSubmit").click(function (e) {
        e.preventDefault();

        //Edit
        $.ajax({
            type: "POST",
            url: window.location.pathname + '/update',
            data: {
                "tripName":$('#tripNameEdit').val(),
                "tripLocation":$('#tripLocationEdit').val(),
                "tripDescription":$('#tripDescriptionEdit').val()
            },
            success: function(result) {
                window.location.reload(true);
            },
            error: function(result) {
                console.log(result)
                alert('Unexpected error. please try again later.')
                $("#overlay").css("display","none");
                $("#tripEditModal").css("display","none");
            }
        });

    });

    $("#tripEditCancel").click(function (e) {
        e.preventDefault();
        $("#overlay").css("display","none");
        $("#tripEditModal").css("display","none");
    });

    //
    //--------- Trip Page Grid Loaders-------------//
    //
});