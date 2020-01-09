<#macro login>
<div">
<form method="POST" action="/login">
    <div>
        <input name="username" type="text" placeholder="логин" autofocus="true"/>
        <input name="password" type="password" placeholder="пароль"/>
        <button type="submit">Войти</button>
        <a href="/registration">Зарегистрироваться</a>
    </div>
</form>

</div>
</#macro>

<#macro hello userAuth>
<#if userAuth??>
<div>
<h4>Приветствую, <a href="/user/${userAuth.id}">${userAuth.username}</a> <a href="/logout">Выйти</a></h4>
</div>
</#if>
</#macro>



