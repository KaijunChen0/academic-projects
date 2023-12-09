<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>HW 5.2</title>
        </head>
        <body>
            <h1>RestaurantId and Name</h1>
            <table border="1">
                <tr>
                    <th>RestaurantId</th>
                    <th>Name</th>
                </tr>
                <xsl:apply-templates select="ReviewApplication/Companys/Company/Restaurants/Restaurant"/>
            </table>
        </body>
    </html>
</xsl:template>

<xsl:template match="Restaurant">
    <tr>
        <td><xsl:value-of select="RestaurantId"/></td>
        <td><xsl:value-of select="Name"/></td>
    </tr>
</xsl:template>

</xsl:stylesheet>
