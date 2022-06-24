<#import "parts/common.ftlh" as c>

<@c.page>
    Список пользователей
<table class="table table-hover">
    <thead>
    <tr>
        <th>Логин</th>
        <th>Доступ</th>
        <th>Имя</th>
        <th>Номер</th>
        <th>Почта</th>
    </tr>
    </thead>

    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td>
                <#list user.roles as role>
                    ${role}<#sep>,
                </#list>
            </td>
            <td>
                ${user.clientName!}

            </td>
            <td>${user.number!}</td>
            <td>${user.mail!}</td>
            <td><a href="/user/${user.id}/edit">Редактировать</a></td>
            <td><a href="/user/${user.id}/delete">Удалить</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>