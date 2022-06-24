<#macro registration>
    <form action="/registration" method="post">
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Логин: </label>
            <div class="col-sm-3">
                <input type="text" name="username" class="form-control" placeholder="Логин" required/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Пароль: </label>
            <div class="col-sm-3">
                <input type="password" name="password" class="form-control" placeholder="Пароль" required minlength="8"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Имя: </label>
            <div class="col-sm-3">
                <input type="text" name="clientName" class="form-control" placeholder="Имя" required/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Номер: </label>
            <div class="col-sm-3">
                <input type="text" name="number" class="form-control" placeholder="375XXXXXXXXX" required
                       pattern="375[0-9]{2}[0-9]{7}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Почта: </label>
            <div class="col-sm-3">
                <input type="email" name="mail" class="form-control" placeholder="Почта" required/>
            </div>
        </div>
        <!-- token-->
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Зарегистрироваться"></div>
    </form>
</#macro>