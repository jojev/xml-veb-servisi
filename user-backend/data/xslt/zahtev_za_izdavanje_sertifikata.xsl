<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.ftn.uns.ac.rs/zahtev_za_sertifikat" version="2.0">

    <xsl:template match="b:zahtev_za_izdavanje_sertifikata">
        
        <html>
        	<head>
            	<style type="text/css">
            	html, body {
    				height: 100%;
				}

				html {
    				display: table;
    				margin: auto;
				}
				
				body {
					margin-right: 10em;
					margin-left: 10em;
				}
	
            	h2 {
            		margin-top: 5em;
            		text-align: center;
            	}
            
            	h3 {
            		text-align: center;
            	}
            	
            	.paragraph {
            		margin-top: 3em;
            		margin-bottom: 2em;
            		text-indent: 2em;
            	}
			
				.signature {
					float: right;
					margin-top: 1em;
					width: 30%;
					border-top: 1px solid black;
				}
				
				.signature:after {
                    content: "Потпис";
                    display: block;
                    text-align: center;
                 }
                 
				.reason-hint:after {
					content: "(навести што прецизнији разлога за подношење захтева за издавање дигиталног пасоша)";
                    display: block;
                    font-size: 12px;
                    text-align: center;
				}
				
                 .container {
                 	display: flex;
                 	width: 100%;
                 }
                 
                 .label {
                 	margin-right: 0.25em;
                 }
                 
                 .value {
                 	flex-grow: 1;
                 	border-bottom: 1px dotted black;
                 }
                 
                 .reason {
                 	margin-top: 1em;
                 }
                 
                 .reason .label {
                 	margin-bottom: 0.5em;
                 }
                 
                 .place {
                 	margin-top: 4em;
                 	margin-bottom: 1em;
                 }
            	</style>
        	</head>
            <body>
            	<h2>ЗАХТЕВ</h2>
            	<h3>за издавање дигиталног зеленог сертификата</h3>
            	<p class="paragraph">
            		У складу са одредбом Републике Србије о издавању дигиталног зеленог 
					сертификата као потврде о извршеној вакцинацији против COVID-19, резултатима 
					тестирања на заразну болест SARS-CoV-2 или опоравку од болести COVID-19, 
					подносим захтев за издавање дигиталног зеленог сертификата. 
            	</p>
          
            	<p>Подносилац захтева:</p>
            	<div class="container">
            		<div class="label">
            			Име и презиме:
            		</div>
            		<div class="value">
            			<xsl:value-of select="b:podnosilac_zahteva/b:ime_prezime"></xsl:value-of>
            		</div>
            	</div>
            	
            	<div class="container">
            		<div class="label">
            			Датум рођења:
            		</div>
            		<div class="value">
            			<xsl:value-of select="b:podnosilac_zahteva/b:datum_rodjenja"></xsl:value-of>
            		</div>
            	</div>
            	
            	<div class="container">
            		<div class="label">
            			Пол:
            		</div>
            		<div class="value">
            			<xsl:value-of select="b:podnosilac_zahteva/b:pol"></xsl:value-of>
            		</div>
            	</div>
            	
            	<div class="container">
            		<div class="label">
            			Јединствени матични број грађанина:
            		</div>
            		<div class="value">
            			<xsl:value-of select="b:podnosilac_zahteva/b:jmbg"></xsl:value-of>
            		</div>
            	</div>
            	<div class="container">
            		<div class="label">
            			Број пасоша:
            		</div>
            		<div class="value">
            			<xsl:value-of select="b:podnosilac_zahteva/b:broj_pasosa"></xsl:value-of>
            		</div>
            	</div>
            	<div class="reason">
            		<div class="label">
            			Разлог за подношење захтева: 

            		</div>
            		<div class="value" id="razlog">
						<xsl:variable name="vrednost"
									 select="b:razlog_za_podnosenje"/>
            		</div>
               	</div>
               	
               	<div class="reason-hint">
               	</div>
            	
            	<div class="place">
            		У <u><xsl:value-of select="b:mesto_podnosenja_zahteva"></xsl:value-of></u> ,
            	</div>
            	<div class="date">
            		дана <u><xsl:value-of select="b:datum_podnosenja_zahteva"></xsl:value-of></u> године
            	</div>
            	<div class="signature">
            		
            	</div>
				<script>  document.getElementById("razlog").innerHTML ="{$vrednost}"</script>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
