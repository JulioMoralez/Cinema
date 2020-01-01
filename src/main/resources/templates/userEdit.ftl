<#import "parts/page.ftl" as p>

<@p.page>
<div>
    <form action="/userEdit" method="post">
        <input type="hidden" name="id" value=${user.id}>
        <input type="text" name="username" value="${user.username}">
        <#if usernameError??>
        ${usernameError}
        </#if>
        <#if usernameInfo??>
        ${usernameInfo}
        </#if>
        <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role.name}" ${user.roles?seq_contains(role)?string("checked", "")}>${role.name}</label>
        </div>
        </#list>
        <button type="submit">Save</button>
    </form>
</div>

<div>
    <a href="/">Главная</a>
</div>
</@p.page>