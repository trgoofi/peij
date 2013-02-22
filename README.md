# Peij

Peij is a pagination tag library for JSP.

## Simple Usage

```jsp
<%@taglib prefix="p" uri="http://peij.org/" %>
<p:paging pages="${totalPages}" current="${currentPage}" page="p" url="/page/${p}" />
```

## Flexible Usage

Tags inside `<p:pagination>` are optional. If you don't want to show the first page's link you just omit `<p:first>`.
If you don't want unavaible links to be shown just don't write the `<p:unclickable>` tag.

```jsp
<%@taglib prefix="p" uri="http://peij.org/" %>
<p:pagination pages="${totalPages}" current="${currentPage}" window="6" page="p">
    <nav class="pagination pagination-centered"><ul>
    <p:first>
        <p:clickable><li><a href="/page/${p}">First</a></li></p:clickable>
        <p:unclickable><li class="disabled"><a>First</a></li></p:unclickable>
    </p:first>
    <p:previous>
        <p:clickable><li><a href="/page/${p}">Previous</a></li></p:clickable>
        <p:unclickable><li class="disabled"><a>Previous</a></li></p:unclickable>
    </p:previous>
    <p:number>
        <p:clickable><li><a href="/page/${p}">${p}</a></li></p:clickable>
        <p:unclickable><li class="active"><a>${p}</a></li></p:unclickable>
    </p:number>
    <p:ellipsis><li class="disabled"><a>...</a></li></p:ellipsis>
    <p:next>
        <p:clickable><li><a href="/page/${p}">Next</a></li></p:clickable>
        <p:unclickable><li class="disabled"><a>Next</a></li></p:unclickable>
    </p:next>
    <p:last>
        <p:clickable><li><a href="/page/${p}">Last</a></li></p:clickable>
        <p:unclickable><li class="disabled"><a>Last</a></li></p:unclickable>
    </p:last>
    </ul></nav>
</p:pagination>
```