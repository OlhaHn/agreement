<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html  lang="pl">
<meta charset="UTF-8"/>
<head>
    <link href="../../resources/css/appCss.css" rel="stylesheet" type="text/css"/>
    <link href="../../resources/css/headerMenu.css" rel="stylesheet" type="text/css">
    <title><tiles:getAsString name="title"/></title>
</head>
<body>

    <header id="header">
        <tiles:insertAttribute name="header" />
    </header>

    <section id="menu">
        <tiles:insertAttribute name="menu" />
    </section>

    <section id="site-content">
        <tiles:insertAttribute name="body" />
    </section>

    <footer id="footer">
        <tiles:insertAttribute name="footer" />
    </footer>
</body>
</html>