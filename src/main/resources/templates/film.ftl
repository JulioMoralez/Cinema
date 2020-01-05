<#import "parts/page.ftl" as p>
<#import "parts/film.ftl" as f>

<@p.page>
<#if movies??>
<div>
    <#list movies as film>
        <@f.film value=film />
    </#list>
</div>
</#if>


</@p.page>