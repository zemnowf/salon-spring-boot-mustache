<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<#include "parts/security.ftlh">

<@c.page>
<div class="form-row mb-2">
    <div class="form-group col-md-6">
    <form method="get" action="/masters" class="form-inline">
        <span><input type="radio" class="form-check-input" value=1 name="rang">Все</span>
        <span><input type="radio" class="form-check-input" value=3 name="rang">3</span>
        <span><input type="radio" class="form-check-input" value=4 name="rang">4</span>
        <span><input type="radio" class="form-check-input" value=5 name="rang">5</span>
        <button type="submit" class="btn btn-primary ml-2">Поиск по разряду</button>
    </form>
    </div>
</div>

    <#if isAdmin>
    <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавить мастера
    </a>
    </#if>
    <div class="collapse" id="collapseExample">
        <div class="input-group mb-3 mt-3">
            <form method="post" action="/masters">
                <div class="input-group mt-3">
                    <input type="text" class="form-control" name="name" placeholder="Введите имя" required/>
                </div>
                <div class="input-group mt-3">
                    <input type="text" class="form-control" name="surname" placeholder="Введите фамилию" required>
                </div>
                <div class="input-group mt-3">
                    <select class="form-select form-select-sm" size="1" name="rang" required>
                        <option disabled>Выберите разряд</option>
                        <option value=3>Третий разряд</option>
                        <option value=4>Четвертый разряд</option>
                        <option value=5>Пятый разряд</option>
                    </select>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="input-group mt-3">
                    <button type="submit" class="btn btn-primary">Добавить мастера</button>
                </div>
            </form>
        </div>
    </div>


    <table class="table table-hover">
        <tr>
            <th>Id</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Разряд</th>
            <th></th>
            <th></th>
        </tr>
        <#list masters as master>
            <tr>
                <td>${master.id}</td>
                <td>${master.name}</td>
                <td>${master.surname}</td>
                <td>${master.rang}</td>

                <#if isAdmin>
                <td><a href="/masters/${master.id}/edit">Редактировать</a></td>
                <td><a href="/masters/${master.id}/delete">Удалить</a></td>
                </#if>
            </tr>
        <#else>
            Список мастеров пуст
        </#list>
    </table>
</@c.page>