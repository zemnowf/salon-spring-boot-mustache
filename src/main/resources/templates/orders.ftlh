<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">

<@c.page>
    <div>
        <h5>Выберите тип услуги</h5>
    </div>
    <div>
        <form method="get" action="/order/details">
            <select size="1" name="serviceGroup">
                <option disabled>Типы услуг</option>
                <#list serviceGroups as serviceGroup>
                    <option name="serviceGroup">${serviceGroup}</option>
                </#list>
            </select>
            <button type="submit">Далее</button>
        </form>
        <input hidden type="text" value="${name}">
        <input hidden type="text" value="${userId}">
    </div>
</@c.page>