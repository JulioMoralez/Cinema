<#import "parts/page.ftl" as p>

<@p.page>

<#if schedule??>
    <table border="1" align="center" cellpadding="4" table-layout="fixed">
    <#assign i = 1>
    <#list placesForForm as rows>
        <tr>
        <#assign j = 1>
        <#list rows as place>
            <#if place gt 0>
                <td align="center" width=10%" bgcolor="green">
            <#elseif place<0>
                <td align="center" width=10%" bgcolor="red">
            <#else>
                <td align="center" width=10%" bgcolor="#ddd">
            </#if>
                <a href="/hall/placeSelect/${schedule.id}x${i}x${j}">${i}x${j}</a>
            </td>
            <#assign j++>
        </#list>
        </tr>
        <#assign i++>
    </#list>
    </table>
</#if>
</@p.page>