<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:b="http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije" version="2.0">

    <xsl:template match="b:obrazac_za_sprovodjenje_imunizacije">
        <fo:root font-family="Times New Roman">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="obrazac_za_sprovodjenje_imunizacije_page">
                    <fo:region-body margin="0.45in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="obrazac_za_sprovodjenje_imunizacije_page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:table>
                            <fo:table-column column-width="67%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell font-weight="bold">
                                        <fo:block font-size="19px">САГЛАСНОСТ ЗА СПРОВОЂЕЊЕ ПРЕПОРУЧЕНЕ ИМУНИЗАЦИЈЕ</fo:block>
                                        <fo:block font-size="13px">(попуњава пацијент)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-weight="bold" margin-right="0px">
                                        <fo:block>
                                            <fo:external-graphic src="url(https://euprava.gov.rs/media/logos/Batut.gif)" content-width="70px"
                                                                 content-height="55px" scaling="uniform" margin-right="0px"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin-left="0px">
                                        <fo:block font-size="9px" margin-top="7px" margin-left="0px" >ИНСТИТУТ ЗА
                                            ЈАВНО ЗДРАВЉЕ СРБИЈЕ
                                            „Др Милан Јовановић Батут“</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>

                        <xsl:apply-templates select="b:podaci_koje_je_popunio_pacijent"/>
                        <xsl:apply-templates select="b:podaci_koje_je_popunio_zdravstveni_radnik"/>

                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

    <xsl:template match="b:podaci_koje_je_popunio_pacijent">
        <fo:table margin-top="21px">
            <fo:table-column column-width="39%"/>
            <fo:table-column column-width="61%"/>
            <fo:table-body>
                <fo:table-row font-size="10px">
                    <fo:table-cell>
                        <fo:block>
                            <fo:inline font-weight="bold" margin-right="10px">Држављанство        </fo:inline>
                            <fo:inline margin-left="10px">1) Република Србија | ЈМБГ</fo:inline>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black"><xsl:value-of select="b:licni_podaci/b:jmbg"/></fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" margin-left="39px" font-size="10px">
            <fo:table-column column-width="2%"/>
            <fo:table-column column-width="41%"/>
            <fo:table-column column-width="2%"/>
            <fo:table-column column-width="55%"/>
            <fo:table-body>
                <fo:table-row font-size="11px">
                    <fo:table-cell>
                        <fo:block>2)</fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:licni_podaci/b:strano_drzavljanstvo"/>.
                        </fo:block>
                        <fo:block font-size="9px" text-align="center">
                            <fo:inline>(назив страног држављанства)</fo:inline>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>|</fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:licni_podaci/b:broj_pasosa"/>.
                        </fo:block>
                        <fo:block font-size="9px" text-align="center">
                            <fo:inline>(бр. пасоша или ЕБС за стране држављане)</fo:inline>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="9.0%"/>
            <fo:table-column column-width="24%"/>
            <fo:table-column column-width="6%"/>
            <fo:table-column column-width="27%"/>
            <fo:table-column column-width="15.5%"/>
            <fo:table-column column-width="18%"/>
            <fo:table-body>
                <fo:table-row font-size="11px">
                    <fo:table-cell>
                        <fo:block font-weight="bold">Презиме  </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:licni_podaci/b:prezime"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                             | Име
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:licni_podaci/b:ime"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                             | Име родитеља
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:licni_podaci/b:ime_roditelja"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="5%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="15%"/>
            <fo:table-column column-width="26%"/>
            <fo:table-column column-width="15%"/>
            <fo:table-column column-width="27%"/>
            <fo:table-body>
                <fo:table-row font-size="11px">
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            Пол
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:pol = 'M'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:pol != 'M'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                               M,
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:pol = 'Z'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:pol != 'Z'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                               Ж,
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            | Датум рођења
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:licni_podaci/b:datum_rodjenja"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            | Место рођења
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:licni_podaci/b:mesto_rodjenja"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="22%"/>
            <fo:table-column column-width="38%"/>
            <fo:table-column column-width="15%"/>
            <fo:table-column column-width="25%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            Адреса (улица и број)
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:licni_podaci/b:adresa/b:ulica_broj"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            | Место/Насеље
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:licni_podaci/b:adresa/b:naselje"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="15%"/>
            <fo:table-column column-width="45%"/>
            <fo:table-column column-width="14%"/>
            <fo:table-column column-width="26%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            Општина/Град
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:licni_podaci/b:adresa/b:grad"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            | Тел. фиксни
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:licni_podaci/b:broj_fiksnog_telefona"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="14%"/>
            <fo:table-column column-width="38%"/>
            <fo:table-column column-width="9%"/>
            <fo:table-column column-width="39%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            Тел. мобилни
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:licni_podaci/b:broj_mobilnog_telefona"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            | Имејл
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:licni_podaci/b:imejl"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>


        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="15%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="10%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="12%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="12%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="8%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="9%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="6%"/>
            <fo:table-body>
                <fo:table-row font-size="11px">
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            Радни статус:
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:radni_status = 'zaposlen'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:radni_status != 'zaposlen'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                            запослен,
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:radni_status = 'nezaposlen'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:radni_status != 'nezaposlen'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                            незапослен,
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:radni_status = 'penzioner'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:radni_status != 'penzioner'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                            пензионер,
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:radni_status = 'ucenik'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:radni_status != 'ucenik'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                            ученик,
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:radni_status = 'student'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:radni_status != 'student'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                            студент,
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:radni_status = 'dete'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:radni_status != 'dete'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                            дете
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="23%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="15%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="12%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="10%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="7%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="8%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="7%"/>
            <fo:table-body>
                <fo:table-row font-size="11px">
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            Занимање запосленог:
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:radni_status = 'zdravstvena zastita'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:radni_status != 'zdravstvena zastita'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                            здравствена заштита,
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:radni_status = 'socijalna zastita'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:radni_status != 'socijalna zastita'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                            социјална заштита,
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:radni_status = 'prosveta'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:radni_status != 'prosveta'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                            просвета,
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:radni_status = 'MUP'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:radni_status != 'MUP'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                            МУП,
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:radni_status = 'Vojska RS'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:radni_status != 'Vojska RS'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                            Војска РС,
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:licni_podaci/b:radni_status = 'drugo'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:licni_podaci/b:radni_status != 'drugo'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block>
                            друго
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
        <fo:table-column column-width="30%"/>
        <fo:table-column column-width="3%"/>
        <fo:table-column column-width="5%"/>
        <fo:table-column column-width="3%"/>
        <fo:table-column column-width="5%"/>
        <fo:table-column column-width="27%"/>
        <fo:table-column column-width="27%"/>
        <fo:table-body>
            <fo:table-row font-size="11px">
                <fo:table-cell>
                    <fo:block font-weight="bold">
                        Корисник установе соц. зашт.
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <xsl:if test="b:licni_podaci/b:korisnik_ustanove_socijalne_zastite = 'DA'">
                        <fo:block border="0.7px solid black" text-align="center">
                            X
                        </fo:block>
                    </xsl:if>
                    <xsl:if test="b:licni_podaci/b:korisnik_ustanove_socijalne_zastite != 'DA'">
                        <fo:block border="0.7px solid black" color="white">
                            ne
                        </fo:block>
                    </xsl:if>
                </fo:table-cell>
                <fo:table-cell margin-left="2px">
                    <fo:block>
                        ДА,
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <xsl:if test="b:licni_podaci/b:korisnik_ustanove_socijalne_zastite = 'NE'">
                        <fo:block border="0.7px solid black" text-align="center">
                            X
                        </fo:block>
                    </xsl:if>
                    <xsl:if test="b:licni_podaci/b:korisnik_ustanove_socijalne_zastite != 'NE'">
                        <fo:block border="0.7px solid black" color="white">
                            ne
                        </fo:block>
                    </xsl:if>
                </fo:table-cell>
                <fo:table-cell margin-left="2px">
                    <fo:block>
                        НЕ
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block font-weight="bold">
                        | Назив и општина седишта
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block border-bottom="0.7px solid black">
                        <xsl:value-of select="b:licni_podaci/b:naziv_sedista_ustanove"/>
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>
        </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="15%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="18%"/>
            <fo:table-column column-width="3%"/>
            <fo:table-column column-width="21%"/>
            <fo:table-column column-width="40%"/>
            <fo:table-body>
                <fo:table-row font-size="11px">
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            Изјављујем да:
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:saglasnost = 'SAGLASAN SAM'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:saglasnost != 'SAGLASAN SAM'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block font-weight="bold">
                            САГЛАСАН САМ
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <xsl:if test="b:saglasnost = 'NISAM SAGLASAN'">
                            <fo:block border="0.7px solid black" text-align="center">
                                X
                            </fo:block>
                        </xsl:if>
                        <xsl:if test="b:saglasnost != 'NISAM SAGLASAN'">
                            <fo:block border="0.7px solid black" color="white">
                                ne
                            </fo:block>
                        </xsl:if>
                    </fo:table-cell>
                    <fo:table-cell margin-left="2px">
                        <fo:block font-weight="bold">
                            НИСАМ САГЛАСАН
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>
                            (означити) c спровођењем активне/пасивне
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="42%"/>
            <fo:table-column column-width="58%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            имунизације (уписати назив имунолошког лека):
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:zeljena_vakcina"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="100%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            Лекар ми је објаснио предности и ризике од спровођења активне/пасивне имунизације наведеним имунолошким леком.
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="75%"/>
            <fo:table-column column-width="25%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            Потпис пацијента или законског заступника пацијентa
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>
                            Датум:
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="75%"/>
            <fo:table-column column-width="25%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            _________________________________________________________
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:value-of select="b:datum"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="100%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template match="b:podaci_koje_je_popunio_zdravstveni_radnik">
        <fo:table margin-top="4px">
            <fo:table-column column-width="100%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block font-size="15px" text-align="center">
                            ЕВИДЕНЦИЈА О ВАКЦИНАЦИЈИ ПРОТИВ COVID-19
                        </fo:block>
                        <fo:block font-weight="9px" text-align="center">
                            (попуњава здравствени радник)
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="19%"/>
            <fo:table-column column-width="31%"/>
            <fo:table-column column-width="19%"/>
            <fo:table-column column-width="31%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            Здравствена установа
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:zdravstvena_ustanova"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>
                            Вакцинацијски пункт
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:vakcinacijski_punkt"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="40%"/>
            <fo:table-column column-width="60%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            Име, презиме, факсимил и бр. телефона лекара:
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block border-bottom="0.7px solid black">
                            <xsl:value-of select="b:podaci_o_lekaru"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px">
            <fo:table-column column-width="100%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            Пре давања вакцине прегледати особу и упознати је са користима и о могућим нежељеним реакцијама после
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            вакцинације. Обавезно уписати сваку дату вакцину и све тражене податке у овај образац и податке унети у лични
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block>
                            картон о извршеним имунизацијама и здравствени картон.
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>

        <fo:table margin-top="11px" font-size="10px" border="1px solid black" text-align="center">
            <fo:table-column column-width="15%"/>
            <fo:table-column column-width="12.5%"/>
            <fo:table-column column-width="9%"/>
            <fo:table-column column-width="13.5%"/>
            <fo:table-column column-width="10%"/>
            <fo:table-column column-width="13%"/>
            <fo:table-column column-width="13%"/>
            <fo:table-column column-width="14%"/>
            <fo:table-body>
                <fo:table-row border="1px solid black">
                    <fo:table-cell border="1px solid black">
                        <fo:block text-align="center">
                            Назив вакцине
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell border="1px solid black">
                        <fo:block text-align="center">
                            Датум давања вакцине (V1 i V2)
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell border="1px solid black">
                        <fo:block text-align="center">
                            Hачин давања вакцине
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell border="1px solid black">
                        <fo:block text-align="center">
                            Екстремитет
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell border="1px solid black">
                        <fo:block text-align="center">
                            Серија вакцине (лот)
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell border="1px solid black">
                        <fo:block text-align="center">
                            Произвођач
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell border="1px solid black">
                        <fo:block text-align="center">
                            Нежељена реакција
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell border="1px solid black">
                        <fo:block text-align="center">
                            Потпис лекара
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <xsl:for-each select="b:doze/b:doza">
                    <fo:table-row border="1px solid black" height="15px">
                        <fo:table-cell border="1px solid black">
                            <fo:block text-align="center">
                                <xsl:value-of select="b:vakcina" />
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="1px solid black">
                            <fo:block text-align="center">
                                <xsl:value-of select="b:datum_davanja_vakcine" />
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="1px solid black">
                            <fo:block text-align="center">
                                <xsl:value-of select="b:nacin_davanja_vakcine" />
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="1px solid black">
                            <fo:block text-align="center">
                                <xsl:if test="b:ekstremitet = 'DR'">
                                    <fo:inline border="1px solid black">1)</fo:inline>
                                </xsl:if>
                                <xsl:if test="b:ekstremitet != 'DR'">
                                    <fo:inline>1)</fo:inline>
                                </xsl:if>
                                <fo:inline>ДР,</fo:inline>
                                <xsl:if test="b:ekstremitet = 'LR'">
                                    <fo:inline border="1px solid black">2)</fo:inline>
                                </xsl:if>
                                <xsl:if test="b:ekstremitet != 'LR'">
                                    <fo:inline>1)</fo:inline>
                                </xsl:if>
                                <fo:inline>ЛР</fo:inline>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="1px solid black">
                            <fo:block text-align="center">
                                <xsl:value-of select="b:serija_vakcine" />
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="1px solid black">
                            <fo:block text-align="center">
                                <xsl:value-of select="b:proizvodjac" />
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="1px solid black">
                            <fo:block text-align="center">
                                <xsl:value-of select="b:nezeljena_reakcija" />
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block></fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:for-each>
            </fo:table-body>
        </fo:table>

        <fo:table font-size="10px" border="1px solid black">
            <fo:table-column column-width="100%"/>
            <fo:table-body>
                <fo:table-row border="1px solid black">
                    <fo:table-cell border="1px solid black">
                        <fo:block>
                            Привремене контраиндикације (датум утврђивања и дијагноза):
                        </fo:block>
                        <fo:block>
                            <xsl:value-of select="b:privremene_kontraindikacije" />
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row border="1px solid black">
                    <fo:table-cell border="1px solid black">
                        <fo:block>
                            Одлука комисије за трајне контраиндикације (ако постоји, уписати Да)
                        </fo:block>
                        <fo:block>
                            <xsl:value-of select="b:odluka_komisije" />
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>


        <fo:table font-size="10px">
            <fo:table-column column-width="10%"/>
            <fo:table-column column-width="90%"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            Напомена:
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>
                            Образац се чува као део медицинске документације пацијента.
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>
</xsl:stylesheet>
