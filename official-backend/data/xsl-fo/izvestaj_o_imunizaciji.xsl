<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:b="http://www.ftn.uns.ac.rs/izvestaj_o_imunizaciji" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    
    <xsl:template match="b:izvestaj_o_imunizaciji">
    <fo:root font-family="Times New Roman">
    	<fo:layout-master-set>
        	<fo:simple-page-master master-name="report-page">
            	<fo:region-body margin="1.15in"/>
            </fo:simple-page-master>
		</fo:layout-master-set>
    
    	<fo:page-sequence master-reference="report-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="15px" margin-bottom="40px" text-align="center" font-weight="bold">
                        Извештај о имунизацији
                    </fo:block>
                    <fo:block margin-bottom="25px">
						Извјештај се односи на период од <fo:inline font-weight="bold"> <xsl:value-of select="b:period/b:od"></xsl:value-of></fo:inline> до <fo:inline font-weight="bold"> <xsl:value-of select="b:period/b:do"></xsl:value-of></fo:inline>                    
                    </fo:block>
                    
                    <fo:block>
                    	У напоменутом временском интервалу је:
                    	<fo:list-block>
							<fo:list-item >
								<fo:list-item-label text-indent="15px">
								<fo:block>●</fo:block>
								</fo:list-item-label>
								<fo:list-item-body>
								<fo:block text-indent="30px"> поднето <fo:inline font-weight="bold"><xsl:value-of select="b:broj_interesovanja_o_imunizaciji"></xsl:value-of></fo:inline> докумената о интересовању за имунизацију; </fo:block>
								</fo:list-item-body>
							</fo:list-item>
								
							<fo:list-item>
								<fo:list-item-label text-indent="15px">
								<fo:block>●</fo:block>
								</fo:list-item-label>
								<fo:list-item-body>
								<fo:block text-indent="30px"> примљено <fo:inline font-weight="bold"><xsl:value-of select="b:broj_zahteva_za_digiatlni_sertifikat"></xsl:value-of></fo:inline> захтева за дигитални зелени сертификат, од којих је 
            						<fo:inline font-weight="bold"><xsl:value-of select="b:broj_izdatih_digitalnih_sertifikata"></xsl:value-of></fo:inline> издато.</fo:block>
								</fo:list-item-body>
							</fo:list-item>
								
						</fo:list-block> 
                    </fo:block>
                    <fo:block margin-top="35px" margin-bottom="10px">
                    	Дато је <fo:inline font-weight="bold"><xsl:value-of select="b:broj_primljenih_doza_vakcina"></xsl:value-of></fo:inline> доза вакцине против COVID-19 вируса у следећој количини: 
                    </fo:block>
                    <fo:block margin-bottom="50px">
                    	
						<fo:table border="1px">
						<fo:table-column column-width="50%"/>
						<fo:table-column column-width="50%"/>
						
						<fo:table-header>
						  <fo:table-row border="1px solid black">
						    <fo:table-cell border="1px solid black"  text-align="center">
						      <fo:block font-weight="bold">Редни број дозе</fo:block>
						    </fo:table-cell>
						    <fo:table-cell border="1px solid black" text-align="center">
						      <fo:block font-weight="bold">Број датих доза</fo:block>
						    </fo:table-cell>
						  </fo:table-row>
						</fo:table-header>
						
						<fo:table-body>
						<xsl:for-each select="b:raspodela_po_dozama/b:doza"> 
						  <fo:table-row border="1px solid black">
						    <fo:table-cell border="1px solid black" text-align="center">
						      <fo:block font-weight="bold"><xsl:value-of select="b:redni_broj"></xsl:value-of></fo:block>
						    </fo:table-cell>
						    <fo:table-cell border="1px solid black" text-align="center">
						      <fo:block><xsl:value-of select="b:kolicina"></xsl:value-of></fo:block>
						    </fo:table-cell>
						  </fo:table-row>
						</xsl:for-each>
						</fo:table-body>
						
						</fo:table>
				
                    </fo:block>
                    <fo:block margin-top="30px">
                    Расподела по произвођачима је:
                    </fo:block>
                    
                    <fo:block margin-bottom="80px">
                    	<fo:list-block>
                    	<xsl:for-each select="b:raspodela_proizvodjaca/b:vakcina">
							<fo:list-item>
								<fo:list-item-label text-indent="15px">
								<fo:block>●</fo:block>
								</fo:list-item-label>
								<fo:list-item-body text-indent="30px">
								<fo:block > <fo:inline font-weight="bold"><xsl:value-of select="@tip"></xsl:value-of> - <xsl:value-of select="."></xsl:value-of> </fo:inline> доза;</fo:block>
								</fo:list-item-body>
							</fo:list-item>
						</xsl:for-each>
						</fo:list-block> 
                    </fo:block>
                    <fo:block>
                    	<fo:inline>
                    		Датум издавања: <fo:inline text-decoration="underline"> <xsl:value-of select="b:datum_izdavanja"></xsl:value-of></fo:inline> године 
                    	</fo:inline>
                    </fo:block>
                    <fo:block margin-left="70%" border-top="1px solid black" margin-top="-3px" text-align="center">
                    	Потпис
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
    </fo:root>
    
    </xsl:template>
</xsl:stylesheet>