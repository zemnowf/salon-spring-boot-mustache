<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <form action="/user/delete" method="post">
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Логин: </label>
            <div class="col-sm-3">
                <input disabled type="text" value="${user.username!}" name="username" class="form-control" placeholder="Логин" required/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Имя: </label>
            <div class="col-sm-3">
                <input disabled type="text" value="${user.clientName!}" name="clientName" class="form-control" placeholder="Имя" required/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Номер: </label>
            <div class="col-sm-3">
                <input disabled type="text" value="${user.number!}" name="number" class="form-control" placeholder="375XXXXXXXXX" required
                       pattern="375[0-9]{2}[0-9]{7}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Почта: </label>
            <div class="col-sm-3">
                <input disabled type="email" value="${user.mail!}" name="mail" class="form-control" placeholder="Почта" required/>
            </div>
        </div>
        <#list roles as role>
            <div>
                <label><input class="form-check-input" type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>

        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">

        <button type="submit">Удалить</button>
    </form>
</@c.page>