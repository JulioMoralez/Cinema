<#import "parts/page.ftl" as p>

<@p.page>
<div>
    <h2>Новости <br> Только для залогинившихся пользователей. 123</h2>
    <a href="/">Главная</a>
</div>

<#if username??>
    <h2>${username}</h2>
    <div>
        <form action="/logout" method="post">
            <input type="submit" value="Sign out"/>
        </form>
    </div>
</#if>

<#if messages??>
<div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>text</th>
            <th>author</th>
        </tr>
        <#list messages as message>
        <tr>
            <td>${message.id}</td>
            <td>${message.text}</td>
            <td>${message.author.username}</td>
        </tr>
    </#list>
    </table>
</div>
</#if>

<#if moderator??>
    <div>
        <form method="POST">
            <input type="text" name="text" placeholder="Введите сообщение">
            <button type="submit">Отправить</button>
        </form>
    </div>
</#if>

</@p.page>