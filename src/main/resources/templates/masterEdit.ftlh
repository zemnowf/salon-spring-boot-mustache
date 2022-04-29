<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <div class="input-group mb-3 mt-3">
            <form method="post" action="/masters/edit">
                <div class="input-group mt-3">
                    <input type="text" value="${master.name}" class="form-control" name="name" placeholder="Введите имя" required/>
                </div>
                <div class="input-group mt-3">
                    <input type="text" value="${master.surname}" class="form-control" name="surname" placeholder="Введите фамилию" required>
                </div>
                <div class="input-group mt-3">
                    <select class="form-select form-select-sm" size="1" name="rang" required>
                        <option disabled value="${master.rang}">Выберите разряд</option>
                        <option value=3>Третий разряд</option>
                        <option value=4>Четвертый разряд</option>
                        <option value=5>Пятый разряд</option>
                    </select>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" value="${master.id}" name="masterId">
                <div class="input-group mt-3">
                    <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                </div>
            </form>
        </div>
    </div>
</@c.page>