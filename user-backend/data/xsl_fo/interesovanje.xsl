<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.ftn.uns.ac.rs/interesovanje"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

    <xsl:template match="b:interesovanje_za_vakcinisanje">
        <fo:root font-family="Times New Roman">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="report-page">
                    <fo:region-body margin-top="0.4in" margin-left="1.3in" margin-bottom="1.3in" margin-right="1.3in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="report-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="14px" margin-bottom="3px" text-align="center" font-weight="bold">
                        Исказивање интересовања за вакцинисање против COVID-19
                    </fo:block>
                    <fo:block font-size="11px">
                        <fo:block font-size="12px" margin-top="10px">
                            Одаберите опцију:
                        </fo:block>
                        <xsl:if test="b:licni_podaci/b:drzavljanstvo = 'Drzavljanin Republike Srbije'">
                            <fo:block margin-left="45px">
                                <fo:inline border="0.8px solid black">Држављанин Републике Србије</fo:inline></fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:drzavljanstvo != 'Drzavljanin Republike Srbije'">
                            <fo:block margin-left="45px">Држављанин Републике Србије</fo:block>
                        </xsl:if>

                        <xsl:if test="b:licni_podaci/b:drzavljanstvo = 'Strani drzavljanin sa boravkom u RS'">
                            <fo:block margin-left="45px">
                                <fo:inline border="0.8px solid black">Страни држављанин са боравком у РС</fo:inline></fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:drzavljanstvo != 'Strani drzavljanin sa boravkom u RS'">
                            <fo:block margin-left="45px">Страни држављанин са боравком у РС</fo:block>
                        </xsl:if>

                        <xsl:if test="b:licni_podaci/b:drzavljanstvo = 'Strani drzavljanin bez boravkom u RS'">
                            <fo:block margin-left="45px">
                                <fo:inline border="0.8px solid black">Страни држављанин без боравком у РС</fo:inline></fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:drzavljanstvo != 'Strani drzavljanin bez boravkom u RS'">
                            <fo:block margin-left="45px">Страни држављанин без боравком у РС</fo:block>
                        </xsl:if>

                        <fo:block margin-top="10px">ЈМБГ:</fo:block>
                        <fo:block border-bottom="1px dotted black"><xsl:value-of select="b:licni_podaci/b:jmbg"/></fo:block>

                        <fo:block margin-top="10px">Име:</fo:block>
                        <fo:block border-bottom="1px dotted black"><xsl:value-of select="b:licni_podaci/b:ime"/></fo:block>

                        <fo:block margin-top="10px">Презиме:</fo:block>
                        <fo:block border-bottom="1px dotted black"><xsl:value-of select="b:licni_podaci/b:prezime"/></fo:block>

                        <fo:block margin-top="10px">Адреса електронске поште:</fo:block>
                        <fo:block border-bottom="1px dotted black"><xsl:value-of select="b:licni_podaci/b:adresa_elektronske_poste"/></fo:block>

                        <fo:block margin-top="10px">Број мобилног телефона (навести број у формату 06X..... без размака и цртица):</fo:block>
                        <fo:block border-bottom="1px dotted black"><xsl:value-of select="b:licni_podaci/b:broj_mobilnog_telefona"/></fo:block>

                        <fo:block margin-top="10px">Број фиксног телефона (навести број у формату нпр. 011..... без размака и цртица):</fo:block>
                        <fo:block border-bottom="1px dotted black"><xsl:value-of select="b:licni_podaci/b:broj_fiksnog_telefona"/></fo:block>

                        <fo:block margin-top="10px">Одаберите локацију где желите да примите вакцину (унесите општину):</fo:block>
                        <fo:block border-bottom="1px dotted black"><xsl:value-of select="b:podaci_o_vakcinisanju/b:opstina_vakcinisanja"/></fo:block>

                        <fo:block margin-top="12px">Исказујем интересовање да примим искључиво вакцину следећих произвођача за
                            који Агенција за лекове и медицинска средства потврди безбедност, ефикасност и
                            квалитет и изда дозволу за употребу лека:</fo:block>

                        <xsl:if test="b:podaci_o_vakcinisanju/b:tip_vakcine = 'Pfizer-BioNTech'">
                            <fo:block margin-left="45px">
                                <fo:inline border="0.8px solid black">Pfizer-BioNTech</fo:inline></fo:block>
                        </xsl:if>
                        <xsl:if test="b:podaci_o_vakcinisanju/b:tip_vakcine != 'Pfizer-BioNTech'">
                            <fo:block margin-left="45px">Pfizer-BioNTech</fo:block>
                        </xsl:if>

                        <xsl:if test="b:podaci_o_vakcinisanju/b:tip_vakcine = 'Sputnik V (Gamaleya истраживачки центар)'">
                            <fo:block margin-left="45px">
                                <fo:inline border="0.8px solid black">Sputnik V (Gamaleya истраживачки центар)</fo:inline></fo:block>
                        </xsl:if>
                        <xsl:if test="b:podaci_o_vakcinisanju/b:tip_vakcine != 'Sputnik V (Gamaleya истраживачки центар)'">
                            <fo:block margin-left="45px">Sputnik V (Gamaleya истраживачки центар)</fo:block>
                        </xsl:if>

                        <xsl:if test="b:podaci_o_vakcinisanju/b:tip_vakcine = 'Sinopharm'">
                            <fo:block margin-left="45px">
                                <fo:inline border="0.8px solid black">Sinopharm</fo:inline></fo:block>
                        </xsl:if>
                        <xsl:if test="b:podaci_o_vakcinisanju/b:tip_vakcine != 'Sinopharm'">
                            <fo:block margin-left="45px">Sinopharm</fo:block>
                        </xsl:if>

                        <xsl:if test="b:podaci_o_vakcinisanju/b:tip_vakcine = 'AstraZeneca'">
                            <fo:block margin-left="45px">
                                <fo:inline border="0.8px solid black">AstraZeneca</fo:inline></fo:block>
                        </xsl:if>
                        <xsl:if test="b:podaci_o_vakcinisanju/b:tip_vakcine != 'AstraZeneca'">
                            <fo:block margin-left="45px">AstraZeneca</fo:block>
                        </xsl:if>

                        <xsl:if test="b:podaci_o_vakcinisanju/b:tip_vakcine = 'Moderna'">
                            <fo:block margin-left="45px">
                                <fo:inline border="0.8px solid black">Moderna</fo:inline></fo:block>
                        </xsl:if>
                        <xsl:if test="b:podaci_o_vakcinisanju/b:tip_vakcine != 'Moderna'">
                            <fo:block margin-left="45px">Moderna</fo:block>
                        </xsl:if>

                        <xsl:if test="b:podaci_o_vakcinisanju/b:tip_vakcine = 'Било која'">
                            <fo:block margin-left="45px">
                                <fo:inline border="0.8px solid black">Било која</fo:inline></fo:block>
                        </xsl:if>
                        <xsl:if test="b:podaci_o_vakcinisanju/b:tip_vakcine != 'Било која'">
                            <fo:block margin-left="45px">Било која</fo:block>
                        </xsl:if>

                        <fo:block margin-top="10px">Да ли сте добровољни давалац крви?</fo:block>

                        <xsl:if test="b:podaci_o_vakcinisanju/b:dobrovoljni_davalac_krvi='Da'">
                            <fo:block margin-left="45px">
                                <fo:inline border="0.8px solid black">Да</fo:inline></fo:block>
                        </xsl:if>
                        <xsl:if test="b:podaci_o_vakcinisanju/b:dobrovoljni_davalac_krvi!='Da'">
                            <fo:block margin-left="45px">
                                <fo:inline>Да</fo:inline></fo:block>
                        </xsl:if>


                        <xsl:if test="b:podaci_o_vakcinisanju/b:dobrovoljni_davalac_krvi='Ne'">
                            <fo:block margin-left="45px">
                                <fo:inline border="0.8px solid black">Не</fo:inline></fo:block>
                        </xsl:if>
                        <xsl:if test="b:podaci_o_vakcinisanju/b:dobrovoljni_davalac_krvi!='Ne'">
                            <fo:block margin-left="45px">
                                <fo:inline>Не</fo:inline></fo:block>
                        </xsl:if>

                        <fo:table margin-top="80px">
                            <fo:table-column column-width="50%"/>
                            <fo:table-column column-width="50%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block>дана <fo:inline border-bottom="0.9px solid black"><xsl:value-of select="b:datum_podnosenja"/> </fo:inline></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center">
                                        <fo:block margin-top="10px">
                                            <fo:block border-top="0.9px solid black">Потпис</fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>

    </xsl:template>
</xsl:stylesheet>