<#import "parts/page.ftl" as p>

<@p.page>
<form method="POST" action="/selectFilm">
    <select name="combo">
        <#if movies??>
        <#list movies as film>
            <option value="${film.id}">${film.name}</option>
        </#list>
        <#else>
            <option value="-1">empty</option>
        </#if>
    </select>
    <button type="submit">Выбрать фильм</button>
</form>
<p>
<br>
<p>
<#if film??>
    ${film.year}
</#if>
</@p.page>