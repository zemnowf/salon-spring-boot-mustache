<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">

<@c.page>
    <table class="table table-hover">
        <tr>
            <th>Услуга</th>
            <th>Количество записей</th>
        </tr>
        <#list map as key, value>
        <tr>
            <td>${key}</td>
            <td>${value}</td>
        </tr>
        </#list>
    </table>
</@c.page>