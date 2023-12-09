<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>HW 5.3</title>
        </head>
        <body>
            <h1>Company and Restaurants</h1>
            <table border="1">
                <tr>
                    <th>CompanyName</th>
                    <th>Restaurants</th>
                </tr>
                <xsl:apply-templates select="ReviewApplication/Companys/Company"/>
            </table>
        </body>
    </html>
</xsl:template>

<xsl:template match="Company">
    <tr>
        <td><xsl:value-of select="CompanyName"/></td>
        <td>
            <xsl:for-each select="Restaurants/Restaurant">
                <xsl:value-of select="Name"/>
                <xsl:if test="position() != last()">
                    <xsl:text> </xsl:text>
                </xsl:if>
            </xsl:for-each>
        </td>
    </tr>
</xsl:template>

</xsl:stylesheet>
