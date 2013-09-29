<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
	<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<LINK href="../theme/Styles.css" type=text/css rel=styleSheet>
	<META content="MSHTML 6.00.2800.1561" name=GENERATOR>
<%
	String refresh = (String)request.getAttribute("refresh");
	if (refresh != null)
	{
%>
		<meta http-equiv="refresh" content="<%=refresh%>"></meta>
<%
	}
%>
	<SCRIPT language="JavaScript" type="text/javascript" src="../Validator.js"></SCRIPT>
	<TITLE>
<%
	String title = (String)request.getAttribute("title");
	if (title != null)
	{
	    out.print(title);
	}
	else
	{
	    out.print("Online Document ZJU-CS");
	}
%>
	</TITLE>
</HEAD>

<BODY >
	<TABLE border="1" bordercolor="#3399FF" frame="box" width="800" rules="none" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" align="center">
		<!-- banner -->
	  <TR valign=top>
			<TD align=center><img src="../theme/new_masthead.jpg" alt="Header image"  width="100%" height="110" border="0"></TD>
	  </TR>
	  <!-- end of banner -->
	  
	  <!-- main part -->
	  <TR valign=top >
		  <TD>
				<TABLE width="100%"  border=0 align=center >
		       <TR>
		       
<%@ include file="left.jsp"%>
						
						
						<!-- right part of main part -->
						<TD valign=top>