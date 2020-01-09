<#import "parts/page.ftl" as p>
<#import "parts/film.ftl" as f>
<#import "parts/login.ftl" as l>

<@p.page>


<#if schedule??>
    <table border="1" align="center" cellpadding="4" table-layout="fixed">
    <#assign i = 1>
    <#list placesForForm as rows>
        <tr>
        <#assign j = 1>
        <#list rows as place>
            <#if place == 0>
                <td align="center" width=10%" bgcolor="#ddd">
            <#elseif place == 1>
                <td align="center" width=10%" bgcolor="green">
            <#elseif place == 2>
                <td align="center" width=10%" bgcolor="red">
            <#elseif place == -1>
                <td align="center" width=10%" bgcolor="blue">
            </#if>
                <a href="/hall/placeSelect/${schedule.id}x${i}x${j}">${i}x${j}</a>
            </td>
            <#assign j++>
        </#list>
        </tr>
        <#assign i++>
    </#list>
    </table>
<h3>Стоимость билета: ${schedule.price}</h3>
<@f.filmReadSmall value=schedule.film />
</#if>
<h3>Билетов выбрано: ${ticketCount}. Стоимость: ${ticketCount*schedule.price}</h3>
<form method="POST" action="/order">
<div>
    <input name="schedule" value=${schedule.id} type="hidden" />
<!--    <#assign i = 1>-->
<!--    <#list placesForForm as rows>-->
<!--        <input name="placesFromForm${i}" value=${rows?join(",")} type="hidden" />-->
<!--        <#assign i++>-->
<!--    </#list>-->
    <button type="submit">Купить</button>
</>
</form>
<p>
<p>
<p>
<a href="/">Главная</a>
</@p.page>