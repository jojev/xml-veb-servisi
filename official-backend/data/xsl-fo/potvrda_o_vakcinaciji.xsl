<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.ftn.uns.ac.rs/potvrda_o_vakcinaciji"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="b:potvrda_o_vakcinaciji">
        <fo:root font-family="Times New Roman">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="report-page">
                    <fo:region-body margin="0.5in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="report-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:table>
                        <fo:table-column column-width="45%"/>
                        <fo:table-column column-width="55%"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block>
                                        <fo:external-graphic src="url(https://euprava.gov.rs/media/logos/Batut.gif)" content-width="100px"
                                                             content-height="85px" scaling="uniform" margin-right="0px"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center">
                                    <fo:block font-weight="bold">Институт за јавно здравље Србије</fo:block>
                                    <fo:block font-weight="bold">"Др Милан Јовановић Батут"</fo:block>
                                    <fo:block color="grey">INSTITUT ZA JAVNO ZDRAVLJE SRBIJE</fo:block>
                                    <fo:block color="grey">"Dr Milan Jovanović Batut"</fo:block>
                                    <fo:block color="grey">INSTITUTE OF PUBLIC HEALTH OF SERBIA</fo:block>
                                    <fo:block color="grey">"Dr Milan Jovanovic Batut"</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block font-weight="bold">
                        Шифра потврде вакцинације: <xsl:value-of select="b:sifra_potvrde_vakcinacije"/>
                    </fo:block>
                    <fo:block color="grey">
                        Šifra potvrde / Confirmation code
                    </fo:block>

                    <fo:block font-weight="bold" font-size="14px" text-align="center" margin-top="17px">
                        ПОТВРДА О ИЗВРШЕНОЈ ВАКЦИНАЦИЈИ ПРОТИВ  COVID-19
                    </fo:block>
                    <fo:block color="grey" font-size="11px" text-align="center">
                        POTVRDA O IZVRŠENOJ VAKCINACIJI PROTIV COVID-19
                    </fo:block>
                    <fo:block color="grey" font-size="11px" text-align="center">
                        CONFIRMATION OF THE  COVID-19 <VACCINATION></VACCINATION>
                    </fo:block>

                    <fo:block margin-top="15px">
                        <fo:inline font-weight="bold">Име и презиме: </fo:inline><xsl:value-of select="b:licni_podaci/b:ime_prezime"/>
                    </fo:block>
                    <fo:block color="grey" font-size="10px">
                        Ime i prezime / First and Last Name
                    </fo:block>

                    <fo:block margin-top="15px">
                        <fo:inline font-weight="bold">Датум рођења: </fo:inline><xsl:value-of select="b:licni_podaci/b:datum_rodjenja"/>
                    </fo:block>
                    <fo:block color="grey" font-size="10px">
                        Datum rođenja / Date Of Birth
                    </fo:block>

                    <fo:block margin-top="15px">
                        <fo:inline font-weight="bold">Пол: </fo:inline><xsl:value-of select="b:licni_podaci/b:pol"/>
                    </fo:block>
                    <fo:block color="grey" font-size="10px">
                        Pol: <xsl:value-of select="b:licni_podaci/b:pol"/>/ Gender: <xsl:value-of select="b:licni_podaci/b:pol"/>
                    </fo:block>

                    <fo:block margin-top="15px">
                        <fo:inline font-weight="bold">ЈМБГ: </fo:inline><xsl:value-of select="b:licni_podaci/b:jmbg"/>
                    </fo:block>
                    <fo:block color="grey" font-size="10px">
                        JMBG / Personal No
                    </fo:block>

                    <fo:block margin-top="15px">
                        <fo:inline font-weight="bold">Датум давања и број серије прве дозе вакцинације: </fo:inline>
                        <xsl:value-of select="b:podaci_o_vakcinaciji/b:doze/b:doza[@redni_broj=1]/b:datum_davanja"/>
                        <fo:inline font-weight="bold">, серија:</fo:inline>
                        <xsl:value-of select="b:podaci_o_vakcinaciji/b:doze/b:doza[@redni_broj=1]/b:serija"/>
                    </fo:block>
                    <fo:block color="grey" font-size="10px">
                        Datum vakcinacije / Vaccination Date
                    </fo:block>

                    <fo:block margin-top="15px">
                        <fo:inline font-weight="bold">Датум давања и број серије друге дозе вакцине: </fo:inline>
                        <xsl:if test="b:podaci_o_vakcinaciji/b:doze/b:doza[@redni_broj=2]">
                            <xsl:value-of select="b:podaci_o_vakcinaciji/b:doze/b:doza[@redni_broj=2]/b:datum_davanja"/>
                            <fo:inline font-weight="bold">, серија:</fo:inline>
                            <xsl:value-of select="b:podaci_o_vakcinaciji/b:doze/b:doza[@redni_broj=2]/b:serija"/>
                        </xsl:if>
                    </fo:block>
                    <fo:block color="grey" font-size="10px">
                        Datum druge vakcinacije / Second Vaccination Date
                    </fo:block>

                    <fo:block margin-top="15px">
                        <fo:inline font-weight="bold">Здравствена установа која вакцинише: </fo:inline><xsl:value-of select="b:podaci_o_vakcinaciji/b:zdravstvena_ustanova"/>
                    </fo:block>
                    <fo:block color="grey" font-size="10px">
                        Zdravstvena ustanova koja vakciniše / Health care institution of vaccination
                    </fo:block>

                    <fo:block margin-top="15px">
                        <fo:inline font-weight="bold">Назив вакцине: </fo:inline><xsl:value-of select="b:podaci_o_vakcinaciji/b:naziv_vakcine"/>
                    </fo:block>
                    <fo:block color="grey" font-size="10px">
                        Naziv vakcine / Name of vaccine
                    </fo:block>

                    <fo:block margin-top="15px">
                        <fo:inline font-weight="bold">Датум издавања потврде: </fo:inline><xsl:value-of select="b:datum_izdavanja_potvrde"/>
                    </fo:block>
                    <fo:block color="grey" font-size="10px">
                        Datum izdavanja potvrde / Confirmation Release Date
                    </fo:block>

                    <fo:block margin-top="15px" text-align="right">
                        <fo:inline font-weight="bold">Здравствена установа: </fo:inline><xsl:value-of select="b:zdravstvena_ustanova"/>
                    </fo:block>
                    <fo:block color="grey" font-size="10px" text-align="right">
                        Zdravstvena ustanova / Medical institution
                    </fo:block>

                    <fo:table>
                        <fo:table-column column-width="82%"/>
                        <fo:table-column column-width="18%"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block></fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block>
                                        <fo:external-graphic content-height="90px" content-width="90px">
                                            <xsl:variable name="id"
                                                          select="b:sifra_potvrde_vakcinacije"/>
                                            <xsl:variable name="src"
                                                          select="concat('https://api.qrserver.com/v1/create-qr-code/?data=','',$id)"/>
                                            <xsl:attribute name="src">
                                                <xsl:value-of select="$src"/>
                                            </xsl:attribute>
                                        </fo:external-graphic>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block>
                        Ова потрвда важи без потписа и печата
                    </fo:block>
                    <fo:block color="grey" font-size="10px">
                        Ova potvrda važi bez potpisa i pečata / This certificate is valid without signatures and seals
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>

    </xsl:template>
</xsl:stylesheet>