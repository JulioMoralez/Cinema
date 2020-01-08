<#import "parts/page.ftl" as p>

<@p.page>
<div>
    <form action="/userEdit" method="post">
        <input type="hidden"  name="id" value=${user.id}>
        ${user.id}
        <input type="text" name="username" value="${user.username}">
        ${usernameError?ifExists}
        ${usernameInfo?ifExists}
        <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role.name}" ${user.roles?seqContains(role)?string("checked", "")}>${role.name}</label>
        </div>
        </#list>
        <button type="submit">Save</button>
    </form>
</div>

<h3>Список заказов</h3>
<#if orders??>
    <#list orders as order>
        <a href="/order/${order.id}">${order.id}</a>
    </#list>
</#if>

<div>
    <a href="/">Главная</a>
</div>
</@p.page>