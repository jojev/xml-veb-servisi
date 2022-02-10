<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:b="http://www.ftn.uns.ac.rs/zahtev_za_sertifikat" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    
    <xsl:template match="b:zahtev_za_izdavanje_sertifikata">
    <fo:root font-family="Times New Roman">
    	<fo:layout-master-set>
        	<fo:simple-page-master master-name="report-page">
            	<fo:region-body margin="1.15in"/>
            </fo:simple-page-master>
		</fo:layout-master-set>
    
    	<fo:page-sequence master-reference="report-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="18px" margin-bottom="3px" margin-top="20px" text-align="center" font-weight="bold">
                       ЗАХТЕВ
                    </fo:block>
                    <fo:block font-size="14px" margin-bottom="25px" text-align="center" font-weight="bold">
                    	за издавање дигиталног зеленог сертификата
                    </fo:block>
                    <fo:block margin-bottom="25px" text-indent="25px">
						У складу са одредбом Републике Србије о издавању дигиталног зеленог 
					сертификата као потврде о извршеној вакцинацији против COVID-19, резултатима 
					тестирања на заразну болест SARS-CoV-2 или опоравку од болести COVID-19, 
					подносим захтев за издавање дигиталног зеленог сертификата.          
                    </fo:block>
                    
                    <fo:block margin-bottom="20px">
                    	Подносилац захтева:
                    </fo:block>
                    
                    <fo:block>
                    	<fo:inline>Име и презиме:</fo:inline> 
                    	<fo:block border-bottom="1px dotted black" margin-left="85px" margin-top="-16px" margin-bottom="7px" padding-bottom="-3px"><xsl:value-of select="b:podnosilac_zahteva/b:ime_prezime"></xsl:value-of></fo:block>
                    </fo:block>
                    
                    <fo:block>
                    	<fo:inline>Датум рођења:</fo:inline> 
                    	<fo:block border-bottom="1px dotted black" margin-left="80px" margin-top="-16px" margin-bottom="7px" padding-bottom="-3px"><xsl:value-of select="b:podnosilac_zahteva/b:datum_rodjenja"></xsl:value-of></fo:block>
                    </fo:block>
                    
                    <fo:block>
                    	<fo:inline>Пол:</fo:inline> 
                    	<fo:block border-bottom="1px dotted black" margin-left="29px" margin-top="-16px" margin-bottom="7px" padding-bottom="-3px"><xsl:value-of select="b:podnosilac_zahteva/b:pol"></xsl:value-of></fo:block>
                    </fo:block>
                    
                    <fo:block>
                    	<fo:inline>Јединствени матични број грађанина:</fo:inline> 
                    	<fo:block border-bottom="1px dotted black" margin-left="197px" margin-top="-16px" margin-bottom="7px" padding-bottom="-3px"><xsl:value-of select="b:podnosilac_zahteva/b:jmbg"></xsl:value-of></fo:block>
                    </fo:block>
                    
                    <fo:block margin-bottom="15px">
                    	<fo:inline>Број пасоша:</fo:inline> 
                    	<fo:block border-bottom="1px dotted black" margin-left="72px" margin-top="-16px" margin-bottom="10px" padding-bottom="-3px"><xsl:value-of select="b:podnosilac_zahteva/b:broj_pasosa"></xsl:value-of></fo:block>
                    </fo:block>
                    
                    <fo:block>
                    	<fo:inline>Разлог за подношење захтева:</fo:inline> 
                    	<fo:block border-bottom="1px dotted black" padding-bottom="-3px"><xsl:value-of select="b:razlog_za_podnosenje"></xsl:value-of></fo:block>
                    </fo:block>
                    
                    <fo:block font-size="10px" text-align="center" margin-top="10px" margin-bottom="40px">
                    (навести што прецизнији разлога за подношење захтева за издавање дигиталног пасоша) 
                    </fo:block>
                    
                    <fo:block margin-bottom="20px">
                   		У <fo:inline text-decoration="underline"><xsl:value-of select="b:mesto_podnosenja_zahteva"></xsl:value-of></fo:inline>,
                   	</fo:block>
                   	
                    <fo:block margin-bottom="20px">
                    	<fo:inline>
                    		дана <fo:inline text-decoration="underline"> <xsl:value-of select="b:datum_podnosenja_zahteva"></xsl:value-of></fo:inline> године 
                    	</fo:inline>
                    </fo:block>
                    <fo:block margin-left="70%" border-top="1px solid black" margin-top="10px" text-align="center">
                    	Потпис
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
    </fo:root>
    
    </xsl:template>
</xsl:stylesheet>