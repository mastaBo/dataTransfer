<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : transform.xsl
    Created on : February 7, 2016, 12:19 AM
    Author     : bobdee
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/list">
        <root>
            <xsl:apply-templates select="item"/>
        </root>
    </xsl:template>
    <xsl:template match="item">
        <item>
            <xsl:copy-of select="(node()|@*)[name()='id' or name()='qty_free' or name()='name' or name()='article' or name()='available']" />
        </item>
    </xsl:template>
</xsl:stylesheet>
