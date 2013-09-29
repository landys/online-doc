<%@ include file="../frags/head.jsp" %>

<!-- ************* can be replaced *************** -->
	<form name="myform" method="post" action="../free/cs.zju">
	<input type="hidden" name="action" />
	<table cellSpacing="0" cellPadding="0" width="645" border="0" align="center">
		<TR>
			<TD vAlign="top" width="10" height="16"><IMG height="16" src="../theme/top_left_corner.gif" width="14"></TD>
			<TD class="wpsPortletTitle" width="600" background="../theme/background.gif" height="16">Register An Account</TD>
			<TD vAlign="top" width="26" height="16"><IMG height="16" src="../theme/Corner.gif" width="14"></TD>
		</TR>
		<TR>
			<TD width="10" background="../theme/left_side_border.gif" height="100">&nbsp;</TD>
			<TD vAlign="top" align="center" width="600" bgColor="#ffffff" height="100">
				<TABLE cellSpacing="0" cellPadding="2" width="100%" border="0">
					<tr>
						<td align="right" width="40%">Name: </td>
						<td align="left" width="60%"><input type="text" name="name" dataType="Require" msg="Empty Name!" maxlength="45" size="20"/><font color="red">*</font></td>			
					</tr>
					<tr>
						<td align="right">Password: </td>
						<td align="left"><input type="password" name="password" dataType="Require" msg="Empty Password!" maxlength="45" size="20"/><font color="red">*</font></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="Register" onclick="action.value='doRegister'; return Validator.Validate(document.myform,3)"/>&nbsp;&nbsp;
							<input type="submit" value="Cancel" onclick="action.value='home'"/>
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