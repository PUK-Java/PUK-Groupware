<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Projects</title>
</head>
<body>
    <h2>All Projects</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
        </tr>
        <c:forEach var="project" items="${projects}">
            <tr>
                <td>${project.id}</td>
                <!-- 프로젝트 이름에 링크 추가 -->
                <td><a href="/projectView/${project.id}">${project.projectName}</a></td>
                <td>${project.projectDescription}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>