<#import "parts/page.ftl" as p>

<@p.page>
<div>
  <table>
    <thead>
    <th>ID</th>
    <th>UserName</th>
    <th>Password</th>
    <th>Roles</th>
    </thead>
    <#list users as user>
      <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.password}</td>
        <td>
            <#list user.roles as role>
                ${role.name}<#sep>,
            </#list>
        </td>
        <td>
          <a href="/user/${user.id}">edit</a>
        </td>
        <td>
          <form action="/admin" method="post">
            <input type="hidden" name="id" value=${user.id} />
            <input type="hidden" name="action" value="delete" />
            <button type="submit">Delete</button>
          </form>

        </td>

      </tr>
    </#list>
  </table>
  <a href="/">Главная</a>
</div>

</@p.page>