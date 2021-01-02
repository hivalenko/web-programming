<%@ page import="domain.Point" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Result</title>
    <link rel="shortcut icon" href="img/favicon.ico">
</head>
<body>
<div class="header">
    Results
</div>
<div class="content">
    <table>
        <tr>
            <th>X coordinate</th>
            <th>Y coordinate</th>
            <th>Radius</th>
            <th>Result</th>
        </tr>
        <tr>
            <%
            Object pointObject = pageContext.getServletContext().getAttribute("point");
            Point point = (Point) pointObject;%>
            <td><%= point.getX()%></td>
            <td><%= point.getY()%></td>
            <td><%= point.getR()%></td>
            <td>
                <%

                    if (request.getAttribute("correct") == null) {
                        if (point.isMatched()) {
                            out.println("Point is in bounds");
                        } else {
                            out.println("Point is out of bounds");
                        }
                    }
                    else{
                        out.println("Incorrect values");
                    }

                %>
            </td>
        </tr>
    </table>
</div>
<footer>
    <a href="index.jsp">Back to main page</a>
    <div>Университет ИТМО, 2019</div>
</footer>
</body>
</html>