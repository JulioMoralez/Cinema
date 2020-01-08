<#macro orderRead value>

Номер заказа ${value.id}
<p>
<a href="/film/${value.schedule.film.id}">${value.schedule.film.name}</a>
<p>
<h3>Стоимость ${value.price}</h3>
<h3>${value.schedule.date}</h3>

<#if (value.schedule.time % 60)<10>
    Время ${(value.schedule.time / 60)?floor} : 0${value.schedule.time % 60}
<#else>
    Время ${(value.schedule.time / 60)?floor} : ${value.schedule.time % 60}
</#if>
<p>
<h3> Зал ${value.schedule.hall.name}</h3>
<p>
<table border="1" cellpadding="4" table-layout="fixed">
    <tr>
        <th>Ряд</th>
        <th>Место</th>
    </tr>
    <#list value.places as place>
    <tr>
        <td>${place.row}</td>
        <td>${place.place}</td>
     </tr>
    </#list>
</table>

<h2>${value.key}</h2>

</#macro>