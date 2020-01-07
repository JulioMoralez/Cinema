<#import "parts/page.ftl" as p>
<#import "parts/film.ftl" as f>

<@p.page>


<#if movies??>
<div>
<table border="1">
    <tr>
        <th>ID</th>
        <th>name</th>
        <th>year</th>
        <th>genre</th>
    </tr>
    <#list movies as ifilm>
        <@f.filmReadFull value=ifilm />
    </#list>
</table>
</div>
</#if>

<p>
<#if film??>
    <@f.filmEdit value=film />
<#else>
    <@f.filmAdd/>
</#if>



</@p.page>