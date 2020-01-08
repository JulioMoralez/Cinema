<#macro scheduleRead value>
    <tr>
        <td>${value.id}</td>
        <td><a href="/film/${value.film.id}">${value.film.name}</a></td>
        <td>${value.hall.name}</td>
        <td>${value.date}</td>
        <td>${value.price}</td>
        <td>${value.time}</td>
    </tr>
</#macro>

<#macro scheduleAdd>
<div>
<form method="POST" action="/addSchedule">
<select name="filmCombo">
    <#if movies??>
    <#list movies as film>
    <option value="${film.id}">${film.name}</option>
</#list>
<#else>
<option value="-1">empty</option>
</#if>
        </select>

<select name="hallCombo">
<#if halls??>
<#list halls as hall>
<option value="${hall.id}">${hall.name}</option>
</#list>
<#else>
<option value="-1">empty</option>
        </#if>
        </select>

<select name="daysCombo">
<#if days??>
<#list days as day>
<option value="${day.dayOfWeek}">${day.dayOfMonth} ${day.monthName}</option>
</#list>
<#else>
<option value="-1">empty</option>
        </#if>
        </select>

<select name="hoursCombo">
<#if hours??>
<#list hours as hour>
<option value="${hour}">${hour}</option>
</#list>
<#else>
<option value="-1">empty</option>
        </#if>
        </select>

<select name="minsCombo">
<#if mins??>
<#list mins as min>
<option value="${min}">${min}</option>
</#list>
<#else>
<option value="-1">empty</option>
        </#if>
        </select>

<input name="price" type="text" placeholder="Стоимость"/>
<button type="submit">Добавить сеанс</button>
        </form>
</div>
</#macro>

