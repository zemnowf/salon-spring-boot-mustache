<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">

<@c.page>
    <div>
        <h5>Выберите услугу и время</h5>
    </div>
    <div class="form-row mb-2">
        <form method="get" action="/order/chooseMaster" class="form-inline">
            <select size="1" name="serviceGroup">
                <option value="${serviceGroup}" name="serviceGroup">${serviceGroup}</option>
            </select>
            <select size="1" name="serviceType">
                <#list serviceTypes as serviceType>
                    <option name="serviceType">${serviceType.name}</option>
                </#list>
            </select>
            <input type="time" value="9:00" name="time" min="09:00" max="19:00" step="1800" required>

            <input type="date" value="2022-05-05" name="date" min="2022-05-05" required>

            <button type="submit">Далее</button>
        </form>
        <input hidden type="text" value="${name}">
        <input hidden type="text" value="${userId}">

    </div>
</@c.page>