<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">

<@c.page>
    <div>
        <h5>Удалить запись</h5>
    </div>
    <div class="form-row mb-2">
        <form method="post" action="/orderAdmin/delete" class="form-inline">

            <select size="1" name="serviceGroup">
                <option value="${order.serviceTypeName.serviceGroup}" name="serviceGroup">${order.serviceTypeName.serviceGroup}</option>
            </select>
            <select size="1" name="serviceType">
                <option name="serviceType">${order.serviceTypeName.name}</option>
            </select>
            <select size="1" name="time">
                <option name="time">${order.orderTime}</option>
            </select>
            <select size="1" name="date">
                <option name="date">${order.orderDate}</option>
            </select>

            <select size="1" name="master">
                    <option name="master">${order.master.name}</option>
            </select>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-primary ml-2">Удалить запись</button>
            <input type="hidden" value="${order.id}" name="orderId">
        </form>
    </div>
</@c.page>