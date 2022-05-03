<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">

<@c.page>
    <p>Выручка для услуг 3 ранга составляет 40% от стоимости: 60% стоимости услуги отдаётся мастеру</p>
    <p>Выручка для услуг 4-5 рангов составляет 30% от стоимости: 50% отдаётся мастеру, 20% заложено в покрытие затрат на расходники</p>
   <table class="table table-hover">

       <tr>
           <td><p>Брутто услуг 3 ранга: ${lowEarnings} руб</p></td>
           <td><p>Выплата мастерам: ${lowEarnings*0.6} руб</p></td>
           <td>Расходники: - </td>
           <td><p>Выручка: ${lowEarnings*0.4} руб</p></td>
       </tr>
       <tr>
           <td><p>Брутто услуг 4-5 ранга: ${highEarnings} руб</p></td>
           <td><p>Выплата мастерам: ${highEarnings*0.5} руб</p></td>
           <td><p>Расходники: ${highEarnings*0.2}</p></td>
           <td><p>Выручка: ${highEarnings*0.3} руб</p></td>
       </tr>
       <tr>
           <td>Общая выручка:${lowEarnings*0.4 + highEarnings*0.3}</td>
           <td></td>
           <td></td>
           <td></td>
       </tr>
   </table>

    <table class="table table-hover">
        <tr>
            <th>Id</th>
            <th>Услуга</th>
            <th>Мастер</th>
            <th>Дата</th>
            <th>Брутто</th>
            <th>Мастеру</th>
            <th>Расходники</th>
            <th>Выручка</th>
        </tr>
        <#list orders as order>
            <tr>
                <td>${order.id}</td>
                <td>${order.serviceTypeName.name}</td>
                <td>${order.master.name} ${order.master.surname}</td>
                <td>${order.orderDate}</td>
                <td>${order.serviceTypeName.price}</td>
                <#if order.serviceTypeName.rang!=3>
                <td>${order.serviceTypeName.price*0.5}</td>
                <td>${order.serviceTypeName.price*0.2}</td>
                <td>${order.serviceTypeName.price - order.serviceTypeName.price*0.7}</td>
                    <#else>
                        <td>${order.serviceTypeName.price*0.6}</td>
                        <td>-</td>
                        <td>${order.serviceTypeName.price - order.serviceTypeName.price*0.6}</td>
                </#if>
            </tr>
        <#else>
            Нет записей
        </#list>
    </table>
</@c.page>