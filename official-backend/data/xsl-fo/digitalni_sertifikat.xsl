<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.ftn.uns.ac.rs/digitalni_sertifikat"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    <xsl:template match="/">
        <fo:root font-family="Times New Roman">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="digitalni-sertifikat-page">
                    <fo:region-body margin="0.4in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="digitalni-sertifikat-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block-container>
                        <fo:block>
                            <fo:table>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="70%"/>
                                <fo:table-column column-width="20%"/>
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block>
                                                <fo:external-graphic
                                                        src="url(https://www.srbija.gov.rs/img/logo_60x120-2.png)"
                                                        content-width="80px"
                                                        content-height="80px" scaling="uniform"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-size="13px" font-weight="bold" padding="5px"
                                                      text-align="center">
                                                ДИГИТАЛНИ ЗЕЛЕНИ СЕРТИФИКАТ
                                                <fo:block-container text-align="center">
                                                    <fo:block font-size="12px" padding-left="100px"
                                                              font-weight="normal">Потврда о извршеној вакцинацији
                                                        против
                                                        COVID-19 и резултатима тестирања
                                                        <fo:block font-weight="bold" font-size="13px">
                                                            DIGITAL GREEN CERTIFICATE
                                                            <fo:block-container width="300px" margin-left="15px"
                                                                                text-align="center">
                                                                <fo:block font-size="12px" font-weight="normal">
                                                                    Certificate of vaccination against COVID-19 and test
                                                                    results
                                                                </fo:block>
                                                            </fo:block-container>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:block-container>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>
                                                <xsl:variable name="id"
                                                              select="b:digitalni_zeleni_sertifikat/b:podaci_o_sertifikatu/b:broj_sertifikata"/>
                                                <xsl:variable name="src"
                                                              select="concat('https://api.qrserver.com/v1/create-qr-code/?data=','',$id)"/>
                                                <fo:external-graphic content-height="scale-to-fit" height="1.00in"
                                                                     content-width="1.00in" scaling="non-uniform">
                                                    <xsl:attribute name="src">
                                                        <xsl:value-of select="$src"/>
                                                    </xsl:attribute>
                                                </fo:external-graphic>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>
                    </fo:block-container>
                    <fo:block-container>
                        <fo:block>
                            <fo:table>
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="20%"/>
                                <fo:table-column column-width="50%"/>
                                <fo:table-body>
                                    <fo:table-row border-bottom="1px solid black">
                                        <fo:table-cell>
                                            <fo:block font-size="10px" font-weight="bold" padding-top="10px">
                                                Број сертификата / Certificate ID:
                                            </fo:block>
                                            <fo:block font-size="10px" font-weight="bold" padding-top="5px">
                                                Име и презиме / Name and surname:
                                            </fo:block>
                                            <fo:block font-size="10px" font-weight="bold" padding-top="5px">
                                                Пол / Gender:
                                            </fo:block>
                                            <fo:block font-size="10px" font-weight="bold" padding-top="5px">
                                                Датум рођења / Date of birth:
                                            </fo:block>
                                            <fo:block font-size="10px" font-weight="bold" padding-top="5px">
                                                ЈМБГ / Personal No. / EBS:
                                            </fo:block>
                                            <fo:block font-size="10px" font-weight="bold" padding-top="5px">
                                                Број пасоша / Passport No.:
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-size="10px" font-weight="normal" padding-top="10px"
                                                      text-align="left" margin-left="10px">
                                                <xsl:value-of
                                                        select="b:digitalni_zeleni_sertifikat/b:podaci_o_sertifikatu/b:broj_sertifikata"/>
                                            </fo:block>
                                            <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                      text-align="left" margin-left="10px">
                                                <xsl:value-of
                                                        select="b:digitalni_zeleni_sertifikat/b:licni_podaci/b:ime_prezime"/>

                                            </fo:block>
                                            <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                      text-align="left" margin-left="10px">
                                                <xsl:if test="contains(b:digitalni_zeleni_sertifikat/b:licni_podaci/b:pol, 'Zensko')">
                                                    Zensko / Female
                                                </xsl:if>
                                                <xsl:if test="contains(b:digitalni_zeleni_sertifikat/b:licni_podaci/b:pol, 'Musko')">
                                                    Musko / Male
                                                </xsl:if>
                                            </fo:block>
                                            <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                      text-align="left" margin-left="10px">
                                                <xsl:value-of
                                                        select="b:digitalni_zeleni_sertifikat/b:licni_podaci/b:datum_rodjenja"/>
                                            </fo:block>
                                            <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                      text-align="left" margin-left="10px">
                                                <xsl:value-of
                                                        select="b:digitalni_zeleni_sertifikat/b:licni_podaci/b:jmbg"/>
                                            </fo:block>
                                            <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                      text-align="left" margin-left="10px">
                                                <xsl:value-of
                                                        select="b:digitalni_zeleni_sertifikat/b:licni_podaci/b:broj_pasosa"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-size="10px" font-weight="bold" padding-top="5px">
                                                Датум и време издавања сертификата/ Certificate issuing date and time:
                                                <fo:inline font-weight="normal">
                                                    <xsl:variable name="datetime"
                                                                  select="b:digitalni_zeleni_sertifikat/b:podaci_o_sertifikatu/b:datum_vreme_izdavanja"/>
                                                    <xsl:variable name="date"
                                                                  select="substring-before($datetime, 'T')"/>
                                                    <xsl:variable name="alltime" select="substring-after($datetime, 'T')"/>
                                                    <xsl:variable name="time" select ="substring-before($alltime, '.')"/>
                                                    <xsl:value-of select="concat($date,' ',$time)"/>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>
                        <fo:block text-align="center" font-size="12px" font-weight="bold" padding-top="2px">
                            Вакцинација / Vaccination
                        </fo:block>
                        <fo:block>
                            <fo:table>
                                <fo:table-column column-width="50%" border-right="1px solid black"/>
                                <fo:table-column column-width="50%"/>
                                <fo:table-body>

                                    <fo:table-row border-bottom="1px solid black">
                                        <xsl:for-each
                                                select="b:digitalni_zeleni_sertifikat/b:podaci_o_vakcinaciji/b:doze/b:doza">
                                            <fo:table-cell>
                                                <fo:block font-size="10px" font-weight="bold" padding-top="10px"
                                                          text-indent="5px">
                                                    Доза / Dose:
                                                    <fo:inline font-weight="normal">
                                                        <xsl:value-of select="@redni_broj"/>/2
                                                    </fo:inline>
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="bold" padding-top="5px"
                                                          text-indent="5px">
                                                    Тип / Type:

                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                          text-indent="5px">
                                                    <xsl:value-of select="b:tip"/>
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="bold" padding-top="5px"
                                                          text-indent="5px">
                                                    Произвођач и серија / Manufacturer and batch number:
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                          text-indent="5px">
                                                    <xsl:value-of select="b:proizvodjac_serija"/>
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="bold" padding-top="5px"
                                                          text-indent="5px">
                                                    Датум / Date:
                                                    <fo:inline font-weight="normal">
                                                        <xsl:value-of select="b:datum"/>
                                                    </fo:inline>
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="bold" padding-top="5px"
                                                          text-indent="5px">
                                                    Здравствена установа / Health care institution:
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                          text-indent="5px" padding-bottom="2px">
                                                    <xsl:value-of select="b:zdravstvena_ustanova"/>
                                                </fo:block>
                                            </fo:table-cell>

                                        </xsl:for-each>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block>
                            <fo:table>
                                <fo:table-column column-width="33%" border-right="1px solid black"/>
                                <fo:table-column column-width="33%" border-right="1px solid black"/>
                                <fo:table-column column-width="33%"/>
                                <fo:table-body>

                                    <fo:table-row border-bottom="1px solid black">
                                        <xsl:for-each select="b:digitalni_zeleni_sertifikat/b:testovi/b:test">
                                            <fo:table-cell>
                                                <fo:block-container font-size="11px" font-weight="bold"
                                                                    padding-top="5px" text-align="center" height="25px"
                                                                    border-bottom="1px solid black">
                                                    <fo:block>
                                                        <xsl:value-of select="@tip"/>
                                                    </fo:block>
                                                </fo:block-container>
                                                <fo:block font-size="10px" font-weight="bold" padding-top="5px"
                                                          text-indent="5px">
                                                    Врста узорка / Sample type:
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                          text-indent="5px">
                                                    <xsl:value-of select="b:vrsta_uzorka"/>
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="bold" padding-top="5px"
                                                          text-indent="5px">
                                                    Произвођач теста / Test manufacturer:
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                          text-indent="5px">
                                                    <xsl:value-of select="b:proizvodjac_testa"/>
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="bold" padding-top="5px"
                                                          margin-left="5px">
                                                    Датум и време узорковања / Date and time of sampling:

                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                          text-indent="5px">
                                                    <xsl:value-of select="b:datum_vreme_uzorkovanja"/>
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="bold" padding-top="5px"
                                                          margin-left="5px">
                                                    Датум и време издавања резултата / Date and time of result:

                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                          text-indent="5px">
                                                    <xsl:value-of select="b:datum_vreme_izdavanja_rezultata"/>
                                                </fo:block>

                                            </fo:table-cell>
                                        </xsl:for-each>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>
                        </fo:block>
                        <fo:block>
                            <fo:table>
                                <fo:table-column column-width="33%"/>
                                <fo:table-column column-width="33%"/>
                                <fo:table-column column-width="33%"/>
                                <fo:table-body>
                                    <fo:table-row background-color="#DCDCDC">
                                        <xsl:for-each select="b:digitalni_zeleni_sertifikat/b:testovi/b:test">
                                            <fo:table-cell>
                                                <fo:block font-size="10px" font-weight="bold" padding-top="5px"
                                                          text-indent="5px">
                                                    Резултат / Result:
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                          text-indent="5px">
                                                    <xsl:value-of select="b:rezultat"/>
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="bold" padding-top="5px"
                                                          text-indent="5px">
                                                    Лабораторија / Laboratory:
                                                </fo:block>
                                                <fo:block font-size="10px" font-weight="normal" padding-top="5px"
                                                          text-indent="5px">
                                                    <xsl:value-of select="b:laboratorija"/>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>
                        <fo:block>
                            <fo:table>
                                <fo:table-column column-width="12%"/>
                                <fo:table-column column-width="22%"/>
                                <fo:table-column column-width="70%"/>
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block>
                                                <fo:external-graphic
                                                        src="url(https://euprava.gov.rs/media/logos/Batut.gif)"
                                                        content-width="70px"
                                                        content-height="70px" scaling="uniform"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-size="9px" font-weight="normal" padding-top="5px">
                                                <fo:inline font-weight="bold">Сертификат издаје:</fo:inline>
                                                Институт за јавно здравље Србије "Др Милан Јовановић Батут"
                                                <fo:block font-weight="bold">
                                                    Certificate issued by:
                                                </fo:block>
                                                Institute of Public Health of Serbia "Dr Milan Jovanović Batut"
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell margin-left="100px">
                                            <fo:block margin-left="90px" font-weight="bold" font-size="9px"
                                                      padding-top="5px">
                                                Дигитални потпис / Digitally signed by:
                                            </fo:block>
                                            <fo:block>
                                                <fo:table>
                                                    <fo:table-column column-width="15%"/>
                                                    <fo:table-column column-width="75%"/>
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell>
                                                                <fo:block>
                                                                    <fo:external-graphic
                                                                            src="url(https://www.srbija.gov.rs/img/logo_60x120-2.png)"
                                                                            content-width="70px"
                                                                            content-height="70px" scaling="uniform"/>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell text-indent="0px">
                                                                <fo:block font-weight="normal" font-size="9px"
                                                                          padding-top="2px">
                                                                    РЕПУБЛИКА СРБИЈА Влада Републике Србије Канцеларија
                                                                    за информационе технологије и електронску управу
                                                                    Немањина 11, БЕОГРАД
                                                                    Датум:
                                                                    <xsl:value-of
                                                                            select="b:digitalni_zeleni_sertifikat/b:podaci_o_sertifikatu/b:datum"/>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>
                    </fo:block-container>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>