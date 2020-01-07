<#import "parts/page.ftl" as p>
<#import "parts/schedule.ftl" as sc>
<#import "parts/film.ftl" as f>

<@p.page>

<@sc.scheduleAdd/>
<p>
<br>
<p>
<#if film??>
    ${film.year}
</#if>

<p>

<@p.calendar/>

<#if schedules??>
<div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>film</th>
            <th>hall</th>
            <th>day</th>
            <th>time</th>
        </tr>
    <#list schedules as schedule>
        <@sc.scheduleRead value=schedule />
    </#list>
</table>
</div>
</#if>

<#if moviesTodayBest??>
    <table>
    <tr>
    <#list moviesTodayBest as filmToday>
        <@f.filmReadSmall value=filmToday />
    </#list>
    </tr>
    </table>
</#if>

<#if moviesToday??>
    <table>
    <#list moviesToday as filmToday>
        <@f.filmReadLine value=filmToday />
    </#list>
    </table>
</#if>

</@p.page>