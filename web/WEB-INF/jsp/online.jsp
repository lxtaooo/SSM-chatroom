<%--
  Created by IntelliJ IDEA.
  User: 李晓涛
  Date: 2020/2/22
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.lixiaotao.service.*" %>
<%@ page import="java.util.*" %>

    <table>
        <c:forEach var="entry" items="${ userMap }">
            <tr>
                <td>
                    <a href="#" onclick="set('${entry.key.user}')">${entry.key.user}</a>
                </td>
            </tr>
        </c:forEach>
    </table>
