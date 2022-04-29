<#macro login path>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Логин: </label>
            <div class="col-sm-3">
                <input type="text" name="username" class="form-control" placeholder="Логин" required/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Пароль: </label>
            <div class="col-sm-3">
                <input type="password" name="password" class="form-control" placeholder="Пароль" required/>
            </div>
        </div>


        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <a href="/registration">Регистрация</a>
        <button class="btn btn-primary" type="submit">Войти</button>
    </form>
</#macro>

<#macro main>
    <form action="/" method="get">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Выйти</button>
    </form>
</#macro>