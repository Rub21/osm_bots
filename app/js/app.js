function mm_file_user(file_user, callback) {

    var url = 'http://rub21.github.io/osm_bots/bots_java/build/web/' + file_user + '.json?callback=callback';
    console.log(url);
    reqwest({
        url: url,
        type: 'jsonp',
        jsonpCallback: 'callback',
        success: response,
        error: response
    });


    function response(x) {
        return callback(x);

    };
};


(function() {


    // $('#normal-toggle-button').toggleButtons();
    // Format number string

    function format(nStr) {
        nStr += '';
        x = nStr.split('.');
        x1 = x[0];
        x2 = x.length > 1 ? '.' + x[1] : '';
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + ',' + '$2');
        }
        return x1 + x2;
    }

    // Set up drop down
    var suma = 0;
    var o = '';
    for (var i = 0; i < topmappers.length; i++) {
        suma += topmappers[i].num_edit;
        var num_obj_changeset = format(topmappers[i].num_edit);
        var num_obj_changeset = format(topmappers[i].num_obj_changes);
        o += '<li  id="' + topmappers[i].user_id + '">' +
            '<a class="users" href="#' + topmappers[i].osm_user + '">' + topmappers[i].num_post + '. ' + topmappers[i].osm_user + ' (' + num_obj_changeset + ' Changeset)' + ' (' + num_obj_changeset + ' Obj Changes)' +
            '</a>' +
            '</li>';
    };



    $('#userlayers').append(o);
    $('#userlayers').on('click', 'li', function(e) {

        var check = $('input:checked').is(':checked');
        var id = 'user' + $(this).attr('id');


        if (check) {

            $('.draw_area_changeset').show();
            $('.draw_area_obj_changeset').show();

            //$('#draw_area').empty();
            $('.draw_area').hide();

            



            mm_file_user(id, stadistis_changest);

        } else {

            $('.draw_area').show();

            $('.draw_area_changeset').hide();
            $('.draw_area_obj_changeset').hide();
     



            mm_file_user(id, stadistis);

        }


        $('#userlayers li').removeClass('active');
        $('.dropdown-toggle').html($(this).text() + '<b class="caret "></b>');

        $(this).addClass('active');



    });

})();



var list_usser = [];



google.load("visualization", "1", {
    packages: ["corechart"]
});


function stadistis_changest(f) {

    var rowArray = [];
    for (var i = 0; i < f[0].editions.length; i++) {
        rowArray.push([f[0].editions[i].date, f[0].editions[i].num_changeset]);
    };

    drawChart();

    function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Date');
        data.addColumn('number', 'Num Editions');
        data.addRows(rowArray);
        var chart = new google.visualization.AreaChart(document.getElementById('draw_area_changeset'));

        var options = {
            height: 320,
            title: 'Num Changeset',
            hAxis: {
                title: 'Date by Day',
                titleTextStyle: {
                    color: '#404040'
                },
                fontSize: 8

            },
            vAxis: {
                title: 'Num Editions',
                titleTextStyle: {
                    color: '#404040'
                }
            },
            legend: 'none',
            chartArea: {
                left: 25,
                top: 20,
                width: "98%",
                height: "70%"
            },
            backgroundColor: 'transparent',
            colors: ['#B75C30']
        };

        chart.draw(data, options);
    }
    $('.draw').addClass('well');

    stadistis_obj_changes(f);
};

function stadistis_obj_changes(f) {

    var rowArray = [];
    for (var i = 0; i < f[0].editions.length; i++) {
        rowArray.push([f[0].editions[i].date, f[0].editions[i].num_obj_changes]);
    };

    drawChart();

    function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Date by day');
        data.addColumn('number', 'Num Editions');
        data.addRows(rowArray);
        var chart = new google.visualization.AreaChart(document.getElementById('draw_area_obj_changeset'));

        var options = {
            height: 320,
            title: 'Num Object Chanches',
            hAxis: {
                title: 'Date by Day',
                titleTextStyle: {
                    color: '#404040'
                }
            },
            vAxis: {
                title: 'Num Obj Chanches',
                titleTextStyle: {
                    color: '#404040'
                }
            },
            legend: 'none',
            chartArea: {
                left: 25,
                top: 20,
                width: "98%",
                height: "70%"
            },
            backgroundColor: 'transparent',
            colors: ['#659E51']
        };

        chart.draw(data, options);
    }
    $('.draw').addClass('well');
};


/*for two graps*/

function stadistis(f) {

    var rowArray = [];
    for (var i = 0; i < f[0].editions.length; i++) {
        rowArray.push([f[0].editions[i].date, f[0].editions[i].num_changeset, f[0].editions[i].num_obj_changes]);
    };

    drawChart();

    function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Date');
        data.addColumn('number', 'Num Editions');
        data.addColumn('number', 'Num Object Changes');
        data.addRows(rowArray);
        var chart = new google.visualization.AreaChart(document.getElementById('draw_area'));

        var options = {
            height: 600,
            title: 'Edit from user : ' + f[0].osm_user,
            hAxis: {
                title: 'Date',
                titleTextStyle: {
                    color: '#404040'
                }
            },
            vAxis: {
                title: 'Num Changeset and Object Chanches',
                titleTextStyle: {
                    color: '#404040'
                }
            },
            legend: 'none',
            chartArea: {
                left: 25,
                top: 20,
                width: "98%",
                height: "70%"
            },
            backgroundColor: 'transparent',
            colors: ['#B75C30', '#659E51']
        };

        chart.draw(data, options);
    }
    $('.draw').addClass('well');

};