<#import "/parts/common.ftlh" as c>
<#import "/parts/login.ftlh" as l>

<@c.page>
<div>
    <@l.logout />
    <span><a href="/user">User list</a></span>
</div>
<form method = "post" action="add">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="text" name="text" placeholder="Enter the message">
    <input type="text" name="tag" placeholder="tag">
    <button type="submit">Add</button>
</form>
<div>Список сообщений</div>
<form method="get" action="/main">
    <input type="text" name="filter" value="${filter!}">
    <button type="submit">Найти</button>
</form>
<#list messages as message>
<div>
    <b>${message.id}</b>
    <span>${message.text}</span>
    <i>${message.tag}</i>
    <strong>${message.authorName}</strong>
</div>
<#else>
No message
</#list>
</@c.page>