<#import "parts/page.ftl" as p>
<#import "parts/order.ftl" as o>

<@p.page>


<@o.orderRead value=order />


<#if orders??>
<table border="1" align="center" cellpadding="4" table-layout="fixed">
    <#list orders as order>
        <@o.orderRead value=order />
    </#list>
</table>
</#if>
<p>
<p>
<p>
<a href="/">Главная</a>

</@p.page>