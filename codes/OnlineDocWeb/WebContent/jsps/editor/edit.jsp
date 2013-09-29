<%@ include file="../frags/head.jsp" %>

<!-- ************* can be replaced *************** -->
<%
	Integer fId = (Integer)session.getAttribute("fId");
	String fName = (String)session.getAttribute("fName");
	String fContent = (String)session.getAttribute("fContent");
	String[] infos = (String[])session.getAttribute("userInfos");
	Integer fType = (Integer)session.getAttribute("fType");
	String hasRight = (String)request.getAttribute("hasRight");
	if (fId != null)
	{
	    session.removeAttribute("fId");
	}
	if (fName != null)
	{
	    session.removeAttribute("fName");
	}
	if (fContent != null)
	{
	    session.removeAttribute("fContent");
	}
	if (fType != null)
	{
	    session.removeAttribute("fType");
	}
	if (infos != null)
	{
	    session.removeAttribute("userInfos");
	}
%>
	<form name="myform" method="post" action="../free/cs.zju">
	<input type="hidden" name="action" />
	<input type="hidden" name="fId" <%=fId != null ? "value=\""+fId.toString()+"\"" : ""%>/>
	<input type="hidden" name="fType" <%=fType != null ? "value=\""+fType.toString()+"\"" : ""%>/>

	<table cellSpacing="0" cellPadding="0" width="645" border="0" align="center">
		<TR>
			<TD vAlign="top" width="10" height="16"><IMG height="16" src="../theme/top_left_corner.gif" width="14"></TD>
			<TD class="wpsPortletTitle" width="600" background="../theme/background.gif" height="16"><%="no".equalsIgnoreCase(hasRight) || (fType != null && fType != 1 && fType != 2) ? "View Your File Without Edit Right" : "Edit Your File Freely" %></TD>
			<TD vAlign="top" width="26" height="16"><IMG height="16" src="../theme/Corner.gif" width="14"></TD>
		</TR>
		<TR>
			<TD width="10" background="../theme/left_side_border.gif" height="100">&nbsp;</TD>
			<TD vAlign="top" align="center" width="600" bgColor="#ffffff" height="100">
				<TABLE cellSpacing="0" cellPadding="2" width="100%" border="0">
					<tr>
						<td align="left" width="60">File Name: </td>
						<td align="left"><input type="text" name="fName" <%=fName != null ? "value=\""+fName+"\"" : ""%> <%="no".equalsIgnoreCase(hasRight) || (fType != null && fType != 1 && fType != 2) ? "readonly=\"readonly\"" : "" %>  dataType="Require" msg="Empty File Name!" maxlength="255" size="80"/><font color="red">*</font></td>
					</tr>
<%
	if (infos != null && infos.length > 0)
	{
%>
					<tr valign="top">
						<td align="left" width="20">Users: </td>
						<td align="left">
							<table>
								<tr><td>Owner: <%=infos[0]%></td></tr>
								<tr><td>Sharers: <%=infos[1]%></td></tr>
								<tr><td>Viewers: <%=infos[2]%></td></tr>
							</table>
						</td>			
					</tr>
<%
	}
%>
					<tr>
						<td colspan="2" align="left">
							<TEXTAREA name="fContent" rows="20" cols="111" <%="no".equalsIgnoreCase(hasRight) || (fType != null && fType != 1 && fType != 2) ? "readonly=\"readonly\"" : "" %>><%=fContent != null ? fContent : ""%></TEXTAREA>
						</td>
					</tr>
					<tr>
						<td colspan="2">
<%
	if (!"no".equalsIgnoreCase(hasRight) && (fType == null || fType == 1 || fType == 2))
	{
%>	
							<input type="submit" value="<%=fId != null ? "Update" : "Create"%>" onclick="action.value='doSave'; return Validator.Validate(document.myform,3)"/>&nbsp;&nbsp;
<%
	}
	if (fId != null)
	{
%>					
							<input type="submit" value="Refresh" onclick="action.value='update'"/>&nbsp;&nbsp;
<%
	}
	else
	{
%>
							<input type="button" value="Clear" onclick="fName.value=''; fContent.value=''"/>&nbsp;&nbsp;
<%
	}
%>
							<input type="submit" value="Return" onclick="action.value='home'"/>
						</td>
					</tr>
				</TABLE>
			</TD>
			<TD width="26" background="../theme/right_side_border.gif" height="100">&nbsp;</TD>
		</TR>
		<TR>
			<TD vAlign="top" width="10" height="2"><IMG height="12" src="../theme/bottom_left_corner.gif" width="14"></TD>
			<TD width="600" background="../theme/bottom_border.gif" height="2">&nbsp;</TD>
			<TD vAlign="top" width="26" bgColor="#ffffff" height="2"><IMG height="12" src="../theme/bottom_right_corner.gif" width="14"></TD>
		</TR>
	</table>
	</form>
<!-- ************* end of can be replaced *************** -->

<%@ include file="../frags/foot.jsp" %>
<%
	if ("no".equalsIgnoreCase(hasRight) && (fType == null || fType == 1 || fType == 2))
	{
	    out.print("<script language=\"javascript\">alert('Someone else is editing the file, you can read it instead of edit it.');</script>");
	}
%>