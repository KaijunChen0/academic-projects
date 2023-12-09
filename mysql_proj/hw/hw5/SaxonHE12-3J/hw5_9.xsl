<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:key name="recommendations-by-restaurant" match="Recommendation" use="RestaurantId"/>

<xsl:template match="/">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>HW 5.9</title>
        </head>
        <body>
            <h1>Reviews with Recommendations</h1>
            <table border="1">
                <tr>
                    <th>RestaurantId</th>
                    <th>UserName</th>
                    <th>Rating</th>
                    <th>Recommendations</th>
                </tr>
                <xsl:apply-templates select="ReviewApplication/Reviews/Review"/>
            </table>
        </body>
    </html>
</xsl:template>

<xsl:template match="Review">
    <tr>
        <td><xsl:value-of select="RestaurantId"/></td>
        <td><xsl:value-of select="UserName"/></td>
        <td><xsl:value-of select="Rating"/></td>
        <td>
            <xsl:value-of select="count(key('recommendations-by-restaurant', RestaurantId)[UserName=current()/UserName])"/>
        </td>
    </tr>
</xsl:template>

</xsl:stylesheet>
