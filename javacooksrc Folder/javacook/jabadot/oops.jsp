<%@page isErrorPage="true" import="jabadot.*" %>
<!-- 
  -- $Id: oops.jsp,v 1.10 2002/05/18 20:42:42 ian Exp $
  -->
<html>
<head>
	<title>Ummm...</title>
</head>
<body>
<!-- Don't include header.html since we're probably already on a page? -->
<font size="+6"><h1>Oh dear!</h1></font>
Somehow, your interactions with JabaDot have
led to an error being reported.
<p>It would help us if you could 
<a href="mailto: 
	<%= JDConstants.getProperty("jabadot.mail_comments_to") %>">
report what you were doing when this error occurred</a>.
<!--
  <p>The page that blew is
  <%= (String)request.getAttribute("sourcePage") %>
  -->
<p>The exception is:
<PRE>
<% exception.printStackTrace(new java.io.PrintWriter(out)); %>
</PRE>
</FONT>
</body>
</html>
