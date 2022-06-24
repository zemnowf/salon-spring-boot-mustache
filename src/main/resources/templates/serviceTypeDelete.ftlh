<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="form-row mb-2">
        <form method="post" action="/service-types/delete" class="form-inline">
            <input disabled type="text" value="${serviceType.name}" name="name" placeholder="Введите название" required/>
            <input disabled type="number" value="${serviceType.price}" name="price" placeholder="Введите цену" required>
            <input disabled type="text" value="${serviceType.serviceGroup}" name="serviceGroup" placeholder="Тип услуги" required>
            <input disabled type="text" value="${serviceType.description}" name="description" placeholder="Описание" required>
            <select disabled size="1" name="rang" required>
                <option disabled value="${serviceType.rang}">Разряд услуги</option>
                <option value=3>Третий разряд</option>
                <option value=4>Четвертый разряд</option>
                <option value=5>Пятый разряд</option>
            </select>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" value="${serviceType.id}" name="serviceTypeId">
            <button class="btn btn-primary ml-2" type="submit">Удалить услугу</button>
        </form>
    </div>


</@c.page>