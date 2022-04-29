<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">

<@c.page>
    <div>
        <h5>Выберите Мастера</h5>
    </div>
    <div class="form-row mb-2">
        <form method="post" action="/order/submit" class="form-inline">
            <select size="1" name="serviceGroup">
                <option value="${serviceGroup}" name="serviceGroup">${serviceGroup}</option>
            </select>
            <select size="1" name="serviceType">
                    <option name="serviceType">${serviceType}</option>
            </select>
            <select size="1" name="time">
                <option name="time">${time}</option>
            </select>
            <select size="1" name="date">
                <option name="date">${date}</option>
            </select>

            <select size="1" name="master">
                <option disabled>Мастер</option>
                <#list masters as master>
                    <option name="master">${master.name}</option>
                </#list>
            </select>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-primary ml-2">Подтвердить запись</button>
        </form>
        <input hidden type="text" value="${name}">
        <input hidden type="text" name="userId "value="${userId}">

    </div>
</@c.page>