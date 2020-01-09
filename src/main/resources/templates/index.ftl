<#import "parts/page.ftl" as p>
<#import "parts/login.ftl" as l>
<#import "parts/schedule.ftl" as sc>
<#import "parts/film.ftl" as f>

<@p.page>

<#if user??>
    <@l.hello userAuth=user/>
<#else>
    <@l.login/>
</#if>

<@sc.scheduleAdd/>

<p>

<@p.calendar/>

<#if schedules??>
<div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>film</th>
            <th>hall</th>
            <th>date</th>
            <th>price</th>
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