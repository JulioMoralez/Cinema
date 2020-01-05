<#macro film value>
<div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>name</th>
            <th>year</th>
            <th>genre</th>
        </tr>
        <tr>
            <td>${value.id}</td>
            <td>${value.name}</td>
            <td>${value.year}</td>
            <td>${value.genre.name}</td>
            <td><a href="/film/delete/${value.id}">delete</a></td>
            <#if value.picPath??>
            <img src="/img/${value.picPath}"/>
            </#if>
        </tr>
    </table>
</div>
</#macro>