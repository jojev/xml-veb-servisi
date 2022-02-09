<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.ftn.uns.ac.rs/izvestaj_o_imunizaciji" version="2.0">

    <xsl:template match="b:izvestaj_o_imunizaciji">
        
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
	
            	h3 {
            		margin-top: 5em;
            		text-align: center;
            	}
            	
            	.interval {
            		margin-top: 2em;
            		margin-bottom: 2em;
            	}
            	
            	.period {
            		margin-top: 4em;
            	}
            	
            	.order {
            		margin-top: 4em;
            	}
            	
            	table {
            		width: 100%;
            		margin-top: 1.5em;
            	}
            	
            	table, th, td {
  					border: 1px solid black;
  					border-collapse: collapse;
				}
				
				td {
  					text-align: center;
				}
				
				ul {
					margin-top: 0em;
				}
				
				.footer {
					margin-top: 4em;
					width: 100%;
					display: flex;
					justify-content: space-between;
				}
				
				.signature {
					margin-top: 1em;
					width: 30%;
					border-top: 1px solid black;
				}
				
				.signature:after {
                    content: "Потпис";
                    display: block;
                    text-align: center;
                 }
            	</style>
        	</head>
            <body>
            	<h3>Извештај о имунизацији</h3>
            	<div class="period">
            		Извјештај се односи на период од <strong><xsl:value-of select="b:period/b:od"></xsl:value-of></strong> до <strong><xsl:value-of select="b:period/b:do"></xsl:value-of></strong>
            	</div>
            	<div class="interval">
            		У напоменутом временском интервалу је:
            		<ul>
            			<li>поднето <strong><xsl:value-of select="b:broj_interesovanja_o_imunizaciji"></xsl:value-of></strong> докумената о интересовању за имунизацију; </li>
            	
            			<li>примљено <strong><xsl:value-of select="b:broj_zahteva_za_digiatlni_sertifikat"></xsl:value-of></strong> захтева за дигитални зелени сертификат, од којих је 
            				<strong><xsl:value-of select="b:broj_izdatih_digitalnih_sertifikata"></xsl:value-of></strong> издато.</li>
            		</ul> 
            	</div>
            	<div>
            		Дато је <strong><xsl:value-of select="b:broj_primljenih_doza_vakcina"></xsl:value-of></strong> доза вакцине против COVID-19 вируса у следећој количини: 
					<table>
						<tr>
							<th>Редни број дозе</th>
							<th>Број датих доза</th>
						</tr>
						<xsl:for-each select="b:raspodela_po_dozama/b:doza">
							<tr>
								<td><strong><xsl:value-of select="b:redni_broj"></xsl:value-of></strong></td>
								<td><xsl:value-of select="b:kolicina"></xsl:value-of></td>
							</tr>
						</xsl:for-each>
					</table>
            	</div>
            	<div class="order">
            		Расподела по произвођачима је:
            		<ul>
            			<xsl:for-each select="b:raspodela_proizvodjaca/b:vakcina">
            				<li><strong><xsl:value-of select="@tip"></xsl:value-of> - <xsl:value-of select="."></xsl:value-of></strong> доза;</li>
            			</xsl:for-each>
            		</ul> 
            	</div>
            	<div class="footer">
            		<div class="date"> 
						Датум издавања: <u><xsl:value-of select="b:datum_izdavanja"></xsl:value-of></u> године 
            		</div>
            		<div class="signature">
            			
            		</div>
            	</div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
