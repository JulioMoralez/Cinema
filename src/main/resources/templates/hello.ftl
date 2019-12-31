<#import "/spring.ftl" as spring/>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Welcome</title>
    <link rel="stylesheet"
          type="text/css" href="<@spring.url '/css/style.css'/>"/>
</head>

<body>
<h1>Welcome</h1>
<#if message??>
<h2>${message}</h2>
</#if>


<a href="<@spring.url '/personList'/>">Person List</a>

<div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        <#list persons as person>
        <tr>
            <td>${person.id}</td>
            <td>${person.name}</td>
        </tr>
        </#list>
    </table>
</div>

</body>

</html>