<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    
    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student index</title>
</head>
<body>

   <a href="${pagecontext.request.contextPath}/StudentServlet?action=insertStudent">Insert Student</a>
   <a href="${pagecontext.request.contextPath}/StudentServlet?action=updateStudent">Update Student</a>
   <a href="${pagecontext.request.contextPath}/StudentServlet?action=deleteStudent">Delete Student</a>
   
   <form method="get" action="${pagecontext.request.contextPath}/StudentServlet">
      <table width="700" border="1" cellpadding="20" align="center"> 
           <caption align="center">List of Students</caption>
          
             <tr>
                 <th>ID</th>
                 <th>name</th>
                 <th>RollNo</th>
                 
                </tr>
               
                <c:forEach var="student" items="${listStudent}">
                <tr>
                
                 <td><c:out value="${student.id}" /> </td>
                  <td><c:out value="${student.name}" /> </td>
                   <td><c:out value="${student.rollNo}" /> </td>
                   </tr>
                   </c:forEach>
                   
           </table>
    </form>

</body>
</html>