<%@ page language="java" pageEncoding="UTF-8" %>  
<%@ page import="dinamica.util.DateHelper"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="curversion" value="123458"/>
<%
String time=DateHelper.getTimeString();
String today=DateHelper.getDateString("yyyy-MM-dd");
String tom=DateHelper.addDay(today, 1);
String tom2=DateHelper.addDay(today, 2);
String sitename=application.getInitParameter("sitename");
%>