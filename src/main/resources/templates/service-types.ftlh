<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">

<@c.page>
    <#if isAdmin>
        <div class="form-row mb-2">
            <form method="post" action="/service-types" class="form-inline">
                <input type="text" name="name" placeholder="Введите название" required/>
                <input type="number" name="price" placeholder="Введите цену" required>
                <input type="text" name="serviceGroup" placeholder="Тип услуги" required>
                <input type="text" name="description" placeholder="Описание" required>
                <select size="1" name="rang" required>
                    <option disabled>Разряд услуги</option>
                    <option value=3>Третий разряд</option>
                    <option value=4>Четвертый разряд</option>
                    <option value=5>Пятый разряд</option>
                </select>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button class="btn btn-primary ml-2" type="submit">Добавить услугу</button>
            </form>
        </div>
    </#if>

<form method="get" action="/service-types">
    <span><input type="radio" value=1 name="rang">Все</span>
    <span><input type="radio" value=3 name="rang">3</span>
    <span><input type="radio" value=4 name="rang">4</span>
    <span><input type="radio" value=5 name="rang">5</span>
    <button type="submit" class="btn btn-primary ml-2">Поиск по разряду</button>
</form>

<table class="table table-hover">
    <tr>
        <th>Id</th>
        <th>Название</th>
        <th>Цена</th>
        <th>Тип услуги</th>
        <th>Описание        </th>
        <th>Разряд</th>
        <th></th>
        <th></th>
    </tr>
    <#list serviceTypes as serviceType>
        <tr>
            <td>${serviceType.id}</td>
            <td>${serviceType.name}</td>
            <td>${serviceType.price}</td>
            <td>${serviceType.serviceGroup}</td>
            <td>${serviceType.description}</td>
            <td>${serviceType.rang}</td>
            <#if isAdmin>
            <td><a href="/service-types/${serviceType.id}/edit">Редактировать</a></td>
            <td><a href="/service-types/${serviceType.id}/delete">Удалить</a></td>
            </#if>
        </tr>
        <#else>
        Список услуг пуст
    </#list>
</table>

</@c.page>