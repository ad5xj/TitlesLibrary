<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>*
<%--
Document   : index
Author     : ken
--%>

<sql:query dataSource="jdbc:mariadb://localhost:3306/libraryDB" var="authors">
  SELECT authid, firstName, lastName, authNote  FROM Author
</sql:query>

<table id="authmaint">
  <!-- column headers -->
  <tr>
    <c:forEach var="columnName" items="${subjects.columnNames}">
      <th><c:out value="${columnName}"/></th>
    </c:forEach>
  </tr>
  <!-- column data -->
  <c:forEach var="row" items="${subjects.rowsByIndex}">
    <tr>
      <c:forEach var="column" items="${row}">
         <td><c:out value="${column}"/></td>
      </c:forEach>
    </tr>
  </c:forEach>
</table>*

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>