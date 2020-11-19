<%@ page import="domain.Point" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Input</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico">

</head>

<body>
<table>
  <tr>

    <td id="header" style="background-color: #de8d47 !important" colspan="3">
      <h1 >Коваленко Егор Иванович P3211 вариант: 189899</h1>
    </td>
  </tr>
  <tr>
    <td class="sidebar">
    </td>
    <td class="content" align="center">

      <form name="form" id="form" method="get" action="controllerServlet">
        <p>
        <
          choose x
        </h3>
        <select name="x" size="1">
          <option>-3</option>
          <option>-2</option>
          <option>-1</option>
          <option>0</option>
          <option>1</option>
          <option>2</option>
          <option>3</option>
          <option>4</option>
          <option>5</option>
        </select>
        </p>
        <p>
        <h3>
          choose y
        </h3>
        <input class="A" maxlength="15" type="text" name="y" required="true"> (-3...5)
        <div class="errorMessage" id="a"></div>
        </p>
        <p>
        <h3>
          choose R
          <select name="r" id="r_selector" size="1">
            <option> 1 </option>
            <option> 2 </option>
            <option> 3 </option>
            <option> 4 </option>
            <option> 5 </option>
          </select>
        </h3>
        </p>
        <p>You can choose point on graph:</p>
        <canvas id="myCanvas" width="500" height="500"></canvas>
        <input type="submit" name="SubmitButton" value="Send">
      </form>

    </td>

    <td class="filler">
    </td>
  </tr>
  <tr>
    <td colspan="3" id="mobilesidebar" class="mobile" align="center"> </td>
  </tr>
  <tr>
    <td class="sidebar"></td>
    <td class="content">
     <div style=" height:200px; overflow:auto;">
      <table id="history">
        <tr>
          <th>Date</th>
          <th>X coordinate</th>
          <th>Y coordinate</th>
          <th>Radius</th>
          <th>Point is</th>
        </tr>
        <%
          Object resultsObjects = pageContext.getServletContext().getAttribute("previousResults");
          String idOfCurrantSession = pageContext.getSession().getId();
          List<Point> points;
          if(resultsObjects == null){
            points = new ArrayList<Point>();
            pageContext.setAttribute("points", points);
          }else{
            points = (ArrayList<Point>) resultsObjects;
          }

          for(Point point : points){
            if(point.getSessionId().equals(idOfCurrantSession)) {
              out.println("<tr>");
              String[] fields = point.getAllValues().split("#");
              for (int i = 0; i < fields.length; i++) {
                out.print("<td");
                if (i == 1) {
                  out.println(" class = \"x_coord\">");
                } else if (i == 2) {
                  out.println(" class = \"y_coord\">");
                } else if (i == 3) {
                  out.println(" class = \"r\">");
                } else if (i == 4) {
                  out.println(" class = \"result\">");
                  if (fields[i].equals("true")) {
                    fields[i] = "In Bounds";
                  } else {
                    fields[i] = "Out of Bounds";
                  }
                } else {
                  out.println(">");
                }
                out.println(fields[i]);
                out.println("</td>");
              }
              out.println("</tr>");
            }
          }


        %>
      </table>
     </div>
    </td>
    <td class="filler"></td>
  </tr>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/listeners.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/areaCanvas.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/validationForm.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/initCanvas.js"></script>
</body>

</html>