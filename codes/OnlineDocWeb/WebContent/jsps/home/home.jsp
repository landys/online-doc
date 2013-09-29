<%@ include file="../frags/head.jsp" %>

<!-- ************* can be replaced *************** -->
	<%@page import="zju.onlinedoc.meta.stubs.FileRecord"%>
<script language="javascript">   
	function checkOK(msg)
	{   
		var a = document.getElementsByName("fileIds");
		for (var i=0; i<a.length; i++)
		{   
			if (a[i].checked)
			{   
				return true;
			} 
		}
		alert(msg);
		return false;
	}   
</script> 
	<form name="myform" method="post" action="../free/cs.zju">
	<input type="hidden" name="action" />
	<table cellSpacing="0" cellPadding="0" width="645" border="0" align="center">
		<TR>
			<TD vAlign="top" width="10" height="16"><IMG height="16" src="../theme/top_left_corner.gif" width="14"></TD>
			<TD class="wpsPortletTitle" width="600" background="../theme/background.gif" height="16">Your Home Page</TD>
			<TD vAlign="top" width="26" height="16"><IMG height="16" src="../theme/Corner.gif" width="14"></TD>
		</TR>
		<TR>
			<TD width="10" background="../theme/left_side_border.gif" height="100">&nbsp;</TD>
			<TD vAlign="top" align="center" width="600" bgColor="#ffffff" height="100">
				<TABLE cellSpacing="0" cellPadding="2" width="100%" border="0">
					<TR>
						<TD width="100%">
				<TABLE align="center" width="100%" height="10%" border="0" cellspacing="1" cellpadding="1">
					<TR align="center" valign="top">
						<th width="56%" height="16" align="left" nowrap class="wpsTableHead">File Name</th>
						<th width="14%" align="left" nowrap class="wpsTableHead">Authorization</th>
						<th width="23%" align="left" nowrap class="wpsTableHead">Create Date</th>
						<th width="7%" align="left" nowrap class="wpsTableHead">Action</th>
					</tr>
<%
	FileRecord[] fileRecords = (FileRecord[])request.getAttribute("fileRecords");
	boolean flag = fileRecords != null && fileRecords.length > 0;
	if (flag)
	{
		for (FileRecord record : fileRecords)
		{
%>
					<tr>
						<td height="16" align="left" nowrap>
							<input type="checkbox" name="fileIds" value="<%=record.getFileId()%>" <%=record.getFileType()==1 ? "" : "disabled=\"disabled\"" %>/><%=record.getFileName()%>
						</td>
						<td align="left" nowrap>
<%
			switch (record.getFileType())
			{
			case 1:
			    out.print("Owner");
			    break;
			case 2:
			    out.print("Share");
			    break;
			case 3:
				out.print("View");
				break;
			}
%>
						</td>
						<td align="left" nowrap><%=record.getFileCreateDate().substring(0, 19)%></td>
						<td align="left" nowrap><a href="../free/cs.zju?action=update&fId=<%=record.getFileId()%>&fName=<%=record.getFileName()%>&fType=<%=record.getFileType()%>"><%=record.getFileType()==1 || record.getFileType()==2 ? "Edit" : "View" %></a></td>
					</tr>
<%
		}
%>
					<tr>
						<td colspan="4">
							<input type="submit" value="Create" onclick="action.value='create'"/>&nbsp;&nbsp;
							<input type="submit" value="Share" onclick="action.value='share'; return checkOK('Choose files first, please!')"/>&nbsp;&nbsp;
							<input type="submit" value="Delete" onclick="action.value='doDelete'; return checkOK('Choose files first, please!')""/>&nbsp;&nbsp;
							<input type="submit" value="Refresh" onclick="action.value='home'"/>
						</td>
					</tr>
<%
	}
	else
	{
%>
					<tr><td colspan="4">&nbsp;</td></tr>
					<tr><td colspan="4">You get no files recently.</td></tr>
					<tr><td colspan="4">&nbsp;</td></tr>
					<tr>
						<td colspan="4">
							<input type="submit" value="Create" onclick="action.value='create'"/>&nbsp;&nbsp;
							<input type="submit" value="Refresh" onclick="action.value='home'"/>
						</td>
					</tr>
<%
	}
%>
				</TABLE>
						</TD>
					</TR>
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