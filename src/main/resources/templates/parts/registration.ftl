<#macro registration>
<div>
    <form method="POST" action="/registration">
        <h2>Регистрация</h2>
        <div>
            <#if username??>
            <input name="username" type="text" value=${username} placeholder="Username"/>
            <#else>
            <input name="username" type="text" placeholder="Username"/>
            </#if>
        <#if usernameError??>
        ${usernameError}
        </#if>
        </div>
        <div>
            <input name="password" type="password" placeholder="Password"/>
        </div>
        <div>
            <input name="passwordConfirm" type="password" placeholder="Confirm your password"/>
            <#if passwordError??>
            ${passwordError}
            </#if>
        </div>
        <button type="submit">Зарегистрироваться</button>
    </form>
</div>
</#macro>