<%@ include file="../frags/head.jsp" %>

<!-- ************* can be replaced *************** -->

	<table cellSpacing="0" cellPadding="0" width="645" border="0" align="center">
		<TR>
			<TD vAlign="top" width="10" height="16"><IMG height="16" src="../theme/top_left_corner.gif" width="14"></TD>
			<TD class="wpsPortletTitle" width="600" background="../theme/background.gif" height="16">Operation Results</TD>
			<TD vAlign="top" width="26" height="16"><IMG height="16" src="../theme/Corner.gif" width="14"></TD>
		</TR>
		<TR>
			<TD width="10" background="../theme/left_side_border.gif" height="100">&nbsp;</TD>
			<TD vAlign="top" align="center" width="600" bgColor="#ffffff" height="100">
				<TABLE cellSpacing="0" cellPadding="2" width="60%" border="0">
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td><%=request.getAttribute("message") %></td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td>
<%
	String[][] actions = (String[][])request.getAttribute("actions");
	for (int i=0; i<actions.length; i++)
	{
%>
						<a href="../free/cs.zju?action=<%=actions[i][1] %>"><%=actions[i][0] %></a>&nbsp;&nbsp;
<%
	}
%>						
						</td>
					</tr>
				</table>
			</TD>
			<TD width="26" background="../theme/right_side_border.gif" height="100">&nbsp;</TD>
		</TR>
		<TR>
			<TD vAlign="top" width="10" height="2"><IMG height="12" src="../theme/bottom_left_corner.gif" width="14"></TD>
			<TD width="600" background="../theme/bottom_border.gif" height="2">&nbsp;</TD>
			<TD vAlign="top" width="26" bgColor="#ffffff" height="2"><IMG height="12" src="../theme/bottom_right_corner.gif" width="14"></TD>
		</TR>
	</table>
	
<!-- ************* end of can be replaced *************** -->

<%@ include file="../frags/foot.jsp" %>