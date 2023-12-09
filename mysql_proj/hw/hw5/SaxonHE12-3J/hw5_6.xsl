<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:key name="recommendations-by-user" match="Recommendation" use="UserName"/>

<xsl:template match="/">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>HW 5.6</title>
        </head>
        <body>
            <h1>username5 Recommendations</h1>
            <table border="1">
                <tr>
                    <th>UserName</th>
                    <th>Recommendations</th>
                </tr>
                <xsl:apply-templates select="ReviewApplication/Users/User[UserName='username5']"/>
            </table>
        </body>
    </html>
</xsl:template>

<xsl:template match="User">
    <tr>
        <td><xsl:value-of select="UserName"/></td>
        <td>
            <xsl:value-of select="count(key('recommendations-by-user', UserName))"/>
        </td>
    </tr>
</xsl:template>

</xsl:stylesheet>
