<#include "security.ftlh">
<#import "login.ftlh" as l>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if name != "Вы не авторизованы">
        <button class="btn btn-primary ml-3" type="submit">Выйти</button>
        </#if>
    </form>
</#macro>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Salon</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">Главная</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/masters">Мастера</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/service-types">Услуги</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/order">Оформить запись</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/order/orders">Мои записи</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active " aria-current="page"> </a>
                </li>

                <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/user">ПОЛЬЗОВАТЕЛИ</a>
                </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/orderAdmin">УПРАВЛЕНИЕ ЗАПИСЯМИ</a>
                    </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/statistics/charts">СТАТИСТИКА</a>
                    </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/statistics/earnings">ВЫРУЧКА</a>
                    </li>
                </#if>
            </ul>

            <div class="navbar-text mr-3" >${name}</div>
                <@logout/>
        </div>
     </div>
</nav>