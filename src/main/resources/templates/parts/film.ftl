<#macro filmReadFull value>

        <tr>
            <td>${value.id}</td>
            <td>${value.name}</td>
            <td>${value.year}</td>
            <td>
            <#if value.genres??>
                <#list value.genres as genre>
                    ${genre.name}<#sep>,
                </#list>
            </#if>
            </td>
            <td><a href="/film/edit/${value.id}">edit</a></td>
            <td><a href="/film/delete/${value.id}">delete</a></td>
            <td>
            <#if value.picPath??>
            <img src="/img/${value.picPath}"/>
            </#if>
            </td>
        </tr>
</#macro>

<#macro filmReadLine value>
<tr>
<td><a href="/film/${value.id}">${value.name}</a></td>
<td>
    <#list schedules as schedule>
        <#if value.id==schedule.film.id>
            <#if (schedule.time % 60)<10>
                <a href="/hall/${schedule.id}">${(schedule.time / 60)?floor} : 0${schedule.time % 60}  </a>
            <#else>
                <a href="/hall/${schedule.id}">${(schedule.time / 60)?floor} : ${schedule.time % 60}  </a>
            </#if>
        </#if>
    </#list>
</td>
</tr>
</#macro>

<#macro filmReadSmall value>
<td>
    <#if value.picPath??>
    <img src="/img/${value.picPath}"/>
    </#if>
    <p>
    ${value.name}
    <p>
        <#if value.genres??>
        <#list value.genres as genre>
        ${genre.name}<#sep>,
    </#list>
    </#if>
</td>
</#macro>

<#macro filmEdit value>
<div>
    <form action="/filmEdit" method="post" enctype="multipart/form-data">
        <input type="hidden"  name="id" value=${value.id}>
        ${value.id}
        <input type="text" name="name" value="${value.name}">
        <input type="text" name="year" value="${value.year}">
        <#list genres as genre>
        <div>
            <label><input type="checkbox" name="${genre.name}" ${value.genres?seqContains(genre)?string("checked", "")}>${genre.name}</label>
        </div>
        </#list>
        <input type="file" name="picPath">
        <#if value.picPath??>
        <img src="/img/${value.picPath}"/>
        </#if>
        <button type="submit">Save</button>
    </form>
</div>
</#macro>

<#macro filmAdd>
<div>
    <form action="/filmAdd" method="post" enctype="multipart/form-data">

        <input type="text" name="name">
        <input type="text" name="year">
        <input type="file" name="picPath">
        <#list genres as genre>
        <div>
            <label><input type="checkbox" name="${genre.name}">${genre.name}</label>
        </div>
        </#list>
        <button type="submit">Save</button>
    </form>
</div>
</#macro>


