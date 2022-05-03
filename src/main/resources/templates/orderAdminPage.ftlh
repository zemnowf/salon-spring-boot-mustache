<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">

<@c.page>
    <table class="table table-hover">
        <tr>
            <th>Id</th>
            <th>Услуга</th>
            <th>Клиент</th>
            <th>Мастер</th>
            <th>Дата</th>
            <th>Время</th>
            <th>Статус</th>
            <th>Редактировать</th>
            <th>Удалить</th>
        </tr>
        <#list orders as order>
            <tr>
                <td>${order.id}</td>
                <td>${order.serviceTypeName.name}</td>
                <td>${order.client.clientName!} (${order.client.username!})</td>
                <td>${order.master.name} ${order.master.surname}</td>
                <td>${order.orderDate}</td>
                <td>${order.orderTime}</td>
                <td>${order.orderStatus}</td>
                <td><a href="/orderAdmin/${order.id}/edit">Редактировать</a></td>
                <td><a href="/orderAdmin/${order.id}/delete">Удалить</a></td>
            </tr>
        <#else>
            Нет записей
        </#list>
    </table>
</@c.page>