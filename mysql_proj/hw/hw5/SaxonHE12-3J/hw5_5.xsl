<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:key name="reviews-by-user" match="Review" use="UserName"/>

<xsl:template match="/">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>HW 5.5</title>
        </head>
        <body>
            <h1>UserName and Reviews</h1>
            <table border="1">
                <tr>
                    <th>UserName</th>
                    <th>Reviews</th>
                </tr>
                <xsl:apply-templates select="ReviewApplication/Users/User"/>
            </table>
        </body>
    </html>
</xsl:template>

<xsl:template match="User">
    <tr>
        <td><xsl:value-of select="UserName"/></td>
        <td>
            <xsl:value-of select="count(key('reviews-by-user', UserName))"/>
        </td>
    </tr>
</xsl:template>

</xsl:stylesheet>
