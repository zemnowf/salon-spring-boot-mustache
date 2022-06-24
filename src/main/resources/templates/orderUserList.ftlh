<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">

<@c.page>
    <table class="table table-hover">
        <tr>
            <th>Id</th>
            <th>Услуга</th>
            <th>Мастер</th>
            <th>Дата</th>
            <th>Время</th>
            <th>Статус</th>
        </tr>
        <#list orders as order>
            <tr>
                <td>${order.id}</td>
                <td>${order.serviceTypeName.name}</td>
                <td>${order.master.name} ${order.master.surname}</td>
                <td>${order.orderDate}</td>
                <td>${order.orderTime}</td>
                <td>${order.orderStatus}</td>
            </tr>
        <#else>
            Вы еще не делали записей.
        </#list>
    </table>
</@c.page>