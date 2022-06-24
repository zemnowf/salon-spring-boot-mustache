<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">

<@c.page>
    <html>
    <head>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load("current", {packages:["corechart"]});
            google.charts.setOnLoadCallback(drawChart);
            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Услуга', 'Цена'],
                    <#list map as key, value>
                    ['${key}', ${value}],
                    </#list>
                    ['Sleep',    0]
                ]);

                var options = {
                    title: 'Рентабельность услуг',
                    pieHole: 0.1,
                };

                var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
                chart.draw(data, options);
            }
        </script>
    </head>
    <body>
    <div id="donutchart" style="width: 900px; height: 500px;"></div>
    </body>
    </html>
</@c.page>