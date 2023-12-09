<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:key name="user-by-username" match="User" use="UserName"/>
<xsl:key name="restaurant-by-id" match="Restaurant" use="RestaurantId"/>

<xsl:template match="/">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>HW 5.10</title>
        </head>
        <body>
            <h1>Reviews with Details</h1>
            <table border="1">
                <tr>
                    <th>RestaurantName</th>
                    <th>FirstName</th>
                    <th>Rating</th>
                </tr>
                <xsl:apply-templates select="ReviewApplication/Reviews/Review"/>
            </table>
        </body>
    </html>
</xsl:template>

<xsl:template match="Review">
    <tr>
        <td>
            <xsl:value-of select="key('restaurant-by-id', RestaurantId)/Name"/>
        </td>
        <td>
            <xsl:value-of select="key('user-by-username', UserName)/FirstName"/>
        </td>
        <td>
            <xsl:value-of select="Rating"/>
        </td>
    </tr>
</xsl:template>

</xsl:stylesheet>
