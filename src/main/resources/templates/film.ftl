<#import "parts/page.ftl" as p>
<#import "parts/film.ftl" as f>

<@p.page>


<#if movies??>
<div>
    <#list movies as ifilm>
        <@f.filmRead value=ifilm />
    </#list>
</div>
</#if>

<p>
<#if film??>
    <@f.filmEdit value=film />
<#else>
    <@f.filmAdd/>
</#if>



</@p.page>