		       			<!-- left part of main part, can be replaced -->
	       				<TD style="background-color:#e2eaf9"  valign="top" width="140">
							<TABLE width="80%" align="center">
								<TR>
									<TD><BR/>
<%
	String userName = (String)session.getAttribute("loginUserName");
	if (userName == null)
	{    
%>
										Not login.<br/>
									</TD>
								</TR>
								<TR><TD colspan="3">&nbsp;</TD></TR>
								<TR>
									<TD width="140" valign="top">
		<TABLE border="0" cellspacing="0" cellpadding="0" width="145"
			id="navigation">
			<TR>
				<TD><IMG src="../theme/arrow.gif"></TD>
				<TD width="140" colspan="2"><a href="../free/cs.zju?action=login">Login</a></TD>
			</TR>
<%
	}
	else
	{
%>
										Welcome <%=userName %>!<br/>
									</TD>
								</TR>
								<TR><TD colspan="3">&nbsp;</TD></TR>
								<TR>
									<TD width="140" valign="top">
		<TABLE border="0" cellspacing="0" cellpadding="0" width="145" id="navigation">	
			<!-- 
			<TR>
				<TD><IMG src="../theme/arrow.gif"></TD>
				<TD width="140" colspan="2"><a href="../free/cs.zju?action=login">ReLogin</a></TD>
			</TR>
			-->
			<TR>
				<TD><IMG src="../theme/arrow.gif"></TD>
				<TD width="140" colspan="2"><a href="../free/cs.zju?action=doLogout">Logout</a></TD>
			</TR>
<%	    
	}
%>
			<TR>
				<TD><IMG src="../theme/arrow.gif"></TD>
				<TD width="140" colspan="2"><a href="../free/cs.zju?action=register">Register</a></TD>
			</TR>
		</TABLE>
		<BR><HR size="1"><BR>
<%
	if (userName != null)
	{
%>
		<TABLE border="0" cellspacing="0" cellpadding="0" width="145" id="navigation">
			<TR>
				<TD><IMG src="../theme/arrow.gif"></TD>
				<TD width="140" colspan="2"><a href="../free/cs.zju?action=home">Editor Home</a></TD>
			</TR>
			<TR>
				<TD><IMG src="../theme/arrow.gif"></TD>
				<TD width="140" colspan="2"><a href="../free/cs.zju?action=create">Create File</a></TD>
			</TR>
			<!-- 
			<TR>
				<TD width="5">&nbsp;</TD>
				<TD><IMG src="../theme/arrow.gif"></TD>
				<TD width="140"><a href="../free/cs.zju?action=home">Editor Home</a></TD>
			</TR>
			-->
		</TABLE>
		<BR><HR size="1"><BR>
<%
	}
	else
	{
%>	
		<TABLE border="0" cellspacing="0" cellpadding="0" width="145" id="navigation">
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
		</TABLE>
<%
	}
%>
		<TABLE border="0" cellspacing="0" cellpadding="0" width="145" id="navigation">
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR><TD colspan="3">&nbsp;</TD></TR>
			<TR>
				<TD></TD>
				<TD colspan="2"><a href="mailto:tonywjd@gmail.com;szh141@gmail.com?subject=Onlinedoc Bug Report"><img src="../theme/contact_us1.gif" border="0"></a></TD>
			</TR>
		</TABLE>
									</TD>
								</TR>
							</TABLE>
						</TD>
						<!-- end of left part of main part -->