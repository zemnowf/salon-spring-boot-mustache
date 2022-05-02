<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

    <#if known>

        <#assign
            user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
            name = user.getUsername()
            mail = user.getMail()
            userId = user.getId()
            isAdmin = user.isAdmin()
            >
    <#else>
        <#assign
            name = "Вы не авторизованы"
            isAdmin = false
        >
    </#if>