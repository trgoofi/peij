# Peij

Peij is a pagination tag library for JSP.

## Simple Usage

    <%@taglib prefix="p" uri="http://peij.org/" %>
    <p:paging pages="${totalPages}" current="${currentPage}" page="p" url="/page/${p}" />
