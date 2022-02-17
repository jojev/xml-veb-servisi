<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.ftn.uns.ac.rs/digitalni_sertifikat" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <style type="text/css">
                    body { font-family: "Times New Roman";
                    margin-left:100px; margin-right:300px;}
                    img { max-width: 200px; height: 100px;}
                    .test:not(:last-child) {
                    content: "";
                    border-right: 1px solid black;
                    }
                    .horizontal-line {
                    border-bottom: 1px solid black;
                    }
                    .test-div {
                    display: flex;
                    justify-content: start;
                    margin-bottom:0px;
                    padding-bottom:0px;
                    margin-left:5px;
                    }

                    .test-info {
                    display:block;
                    font-size: medium;
                    margin-left:5px;
                    }

                    .div-between {
                    display: flex;
                    justify-content: space-between;
                    }

                    .div-start {
                    display: flex;
                    justify-content: start;
                    }

                    .p-info {
                    padding-top:20px;
                    float: left; width:300px;
                    font-size:medium;
                    }

                </style>
                <title>Дигитални зелени сертификат</title>
            </head>
            <body>
                <div class="div-between">
                    <div class="div-start" style="width:200px; text-align: center;">
                        <div style="display:block;">
                            <img src="../../images/rs.png" alt=""/>
                            <p>РЕПУБЛИКА СРБИЈА
                                REPUBLIC OF SERBIA
                            </p>
                        </div>
                    </div>


                    <div style="display: flex;justify-content: center;">
                        <div style="display:block;">
                            <p style="font-size:large;text-align:center">
                                <b>ДИГИТАЛНИ ЗЕЛЕНИ СЕРТИФИКАТ</b>
                            </p>
                            <div style="text-align:center;">
                                Потврда о извршеној вакцинацији против
                                COVID-19 и резултатима тестирања
                                <br/>
                                <b>DIGITAL GREEN CERTIFICATE</b>
                                <br/>Certificate of vaccination against COVID-19 and test results
                            </div>
                        </div>

                    </div>
                    <div style="display:flex; justify-content: end;">
                        <img style="width:150px;height:150px;" alt="" title="">
                            <xsl:variable name="id"
                                          select="b:digitalni_zeleni_sertifikat/b:podaci_o_sertifikatu/b:broj_sertifikata"/>
                            <xsl:variable name="src"
                                          select="concat('https://api.qrserver.com/v1/create-qr-code/?data=','',$id)"/>
                            <xsl:attribute name="src">
                                <xsl:value-of select="$src"/>
                            </xsl:attribute>
                        </img>
                    </div>
                </div>
                <div class="div-between" style="margin-top:20px;height:50px">
                    <div class="div-between">
                        <p class="p-info">
                            <b>Број сертификата / Certificate ID:</b>
                        </p>
                        <p style="padding-top:20px;font-size:medium;">
                            <xsl:value-of
                                    select="b:digitalni_zeleni_sertifikat/b:podaci_o_sertifikatu/b:broj_sertifikata"/>
                        </p>
                    </div>
                    <div class="div-between">
                        <p style="padding-top:20px; float: right; width:400px; font-size:medium;">
                            <b>Датум и време издавања сертификата / Certificate issuing date and time:</b>
                        </p>
                        <p style="padding-top:35px;font-size:medium;">
                            <xsl:variable name="datetime"
                                          select="b:digitalni_zeleni_sertifikat/b:podaci_o_sertifikatu/b:datum_vreme_izdavanja"/>
                            <xsl:variable name="date" select="substring-before($datetime, 'T')"/>
                            <xsl:variable name="time" select="substring-after($datetime, 'T')"/>
                            <xsl:value-of select="concat($date,' ',$time)"/>
                        </p>
                    </div>
                </div>
                <div class="div-between" style="height:50px">
                    <div class="div-between">
                        <p class="p-info">
                            <b>Име и презиме / Name and surname:</b>
                        </p>
                        <p style="padding-top:20px;font-size:medium;">
                            <xsl:value-of select="b:digitalni_zeleni_sertifikat/b:licni_podaci/b:ime_prezime"/>
                        </p>
                    </div>
                </div>
                <div class="div-between" style="height:50px">
                    <div class="div-between">
                        <p class="p-info">
                            <b>Пол / Gender:</b>
                        </p>
                        <p style="padding-top:20px;font-size:medium;">
                            <xsl:if test="contains(b:digitalni_zeleni_sertifikat/b:licni_podaci/b:pol, 'Zensko')">
                                Zensko / Female
                            </xsl:if>
                            <xsl:if test="contains(b:digitalni_zeleni_sertifikat/b:licni_podaci/b:pol, 'Musko')">
                                Musko / Male
                            </xsl:if>
                        </p>
                    </div>
                </div>
                <div class="div-between" style="height:50px">
                    <div class="div-between">
                        <p class="p-info">
                            <b>Датум рођења / Date of birth:</b>
                        </p>
                        <p style="padding-top:20px;font-size:medium;">
                            <xsl:value-of select="b:digitalni_zeleni_sertifikat/b:licni_podaci/b:datum_rodjenja"/>
                        </p>
                    </div>
                </div>
                <div class="div-between" style="height:50px">
                    <div class="div-between">
                        <p class="p-info">
                            <b>ЈМБГ / Presonal No. / EBS:</b>
                        </p>
                        <p style="padding-top:20px;font-size:medium;">
                            <xsl:value-of select="b:digitalni_zeleni_sertifikat/b:licni_podaci/b:jmbg"/>
                        </p>
                    </div>
                </div>
                <div class="div-between" style="height:50px;padding-bottom:10px;">
                    <div class="div-between">
                        <p class="p-info">
                            <b>Број пасоша / Passport No.</b>
                        </p>
                        <p style="padding-top:20px;font-size:medium;">
                            <xsl:value-of select="b:digitalni_zeleni_sertifikat/b:licni_podaci/b:broj_pasosa"/>
                        </p>
                    </div>
                </div>
                <div class="horizontal-line"/>
                <div style="display: flex; justify-content: center;height:50px">
                    <p style="padding-top:5px; float: center; width:300px; font-size:large;">
                        <b>Вакцинација / Vaccination</b>
                    </p>
                </div>
                <div class="div-between" style="width:400px;">

                    <xsl:for-each select="b:digitalni_zeleni_sertifikat/b:podaci_o_vakcinaciji/b:doze/b:doza">

                        <div style="display:block;" class="test">

                            <div class="div-between">
                                <p style="float: left; width:150px; font-size:medium;margin-left:5px;">
                                    <b>Доза / Dose:</b>
                                </p>
                                <p class="div-info" style="width:400px;margin-left:2px;">
                                    <xsl:value-of select="@redni_broj"/>/2
                                </p>
                            </div>
                            <div class="div-between">
                                <p style="float: left; width:150px; font-size:medium;margin-left:5px;">
                                    <b>Тип / Type:</b>
                                </p>
                                <p class="div-info" style="width:400px;margin-left:2px;">
                                    <xsl:value-of select="b:tip"/>
                                </p>
                            </div>
                            <div class="div-start" style="margin-bottom:0px;padding-bottom:0px; margin-left:2px;">
                                <p class="div-info" style="margin-left:5px;">
                                    <b>Произвођач и серија / Manufacturer and batch number:</b>
                                </p>

                            </div>
                            <div class="div-start" style="margin-left:5px;">
                                <p class="div-info" style="margin-left:5px;">
                                    <xsl:value-of select="b:proizvodjac_serija"/>
                                </p>
                            </div>
                            <div class="div-between">
                                <p class="div-info" style="margin-left:5px;">
                                    <b>Датум / Date:</b>
                                </p>
                                <p class="div-info" style="width:400px;margin-left:2px;">
                                    <xsl:value-of select="b:datum"/>
                                </p>
                            </div>
                            <div class="div-start" style="margin-bottom:0px;padding-bottom:0px; margin-left:2px;">
                                <p class="div-info" style="margin-left:5px;">
                                    <b>Здравствена установа / Health care institution:</b>
                                </p>

                            </div>
                            <div class="div-start" style="margin-left:5px;">
                                <p class="div-info" style="margin-left:5px;">
                                    <xsl:value-of select="b:zdravstvena_ustanova"/>
                                </p>
                            </div>
                        </div>

                    </xsl:for-each>
                </div>

                <div class="horizontal-line"/>
                <div class="div-between" style="width:480px;">
                    <xsl:for-each select="b:digitalni_zeleni_sertifikat/b:testovi/b:test">
                        <div style="display:block;" class="test">
                            <div>
                                <div class="div-between" style="margin-right:20px; margin-left:2px;width:350px;">
                                    <p style="font-size:medium;width:210px;float:center;">
                                        <b>
                                            <xsl:value-of select="@tip"/>
                                        </b>
                                    </p>
                                </div>
                                <div class="horizontal-line"/>
                                <div>
                                    <div class="test-div">
                                        <p class="test-info" style="padding-top:20px;">
                                            <b>Врста узорка / Sample type:</b>
                                        </p>

                                    </div>
                                    <div class="test-div">
                                        <p class="test-info">
                                            <xsl:value-of select="b:vrsta_uzorka"/>
                                        </p>
                                    </div>
                                </div>
                                <div class="test-div">
                                    <p class="test-info">
                                        <b>Произвођач теста / Test manufacturer:</b>
                                    </p>
                                </div>
                                <div class="test-div">
                                    <p class="test-info">
                                        <xsl:value-of select="b:proizvodjac_testa"/>
                                    </p>
                                </div>

                                <div class="test-div">
                                    <p class="test-info">
                                        <b>Датум и време узорковања / Date and time of sampling:</b>
                                    </p>

                                </div>
                                <div class="test-div">
                                    <p class="test-info">
                                        <xsl:value-of select="b:datum_vreme_uzorkovanja"/>
                                    </p>
                                </div>
                                <div class="test-div">
                                    <p class="test-info">
                                        <b>Датум и време издавања резултата / Date and time of result:</b>
                                    </p>

                                </div>
                                <div class="test-div">
                                    <p class="test-info">
                                        <xsl:value-of select="b:datum_vreme_izdavanja_rezultata"/>
                                    </p>
                                </div>
                            </div>


                        </div>

                    </xsl:for-each>

                </div>
                <div class="horizontal-line"/>
                <div class="div-between">
                    <xsl:for-each select="b:digitalni_zeleni_sertifikat/b:testovi/b:test">

                        <div style="display:block;background-color:#DCDCDC;width:410px">
                            <div class="test-div">
                                <p class="test-info">
                                    <b>Резултат / Result:</b>
                                </p>

                            </div>
                            <div class="test-div">
                                <p class="test-info">
                                    <xsl:value-of select="b:rezultat"/>
                                </p>
                            </div>
                            <div class="test-div">
                                <p class="test-info">
                                    <b>Лабораторија / Laboratory:</b>
                                </p>

                            </div>
                            <div class="test-div">
                                <p class="test-info">
                                    <xsl:value-of select="b:laboratorija"/>
                                </p>
                            </div>
                        </div>

                    </xsl:for-each>
                </div>
                <div class="div-between">
                    <div style="float:left; width:270px; text-align: center;margin-top:20px;">
                        <img style="float:left;" src="../../images/izjzs.gif" alt=""/>
                        <div class="div-start">
                            <p style=" float: left; width:250px; font-size:medium;text-align:left;margin-left:10px;margin-top:0px;">
                                <b>Сертификат издаје:</b>
                                Институт за јавно здравље Србије "Др Милан Јовановић Батут"
                                <b>Certificate issued by:</b>
                                Institute of Public Health of Serbia "Dr Milan Jovanović Batut"
                            </p>
                        </div>
                    </div>
                    <div style="float:right; width:280px; text-align: center;margin-top:20px;">
                        <b>Дигитални потпис / Digitally signed by:</b>
                        <img style="float:left;" src="../../images/rs.png" alt=""/>
                        <div class="div-start" style="margin-top:5px;">
                            <p style=" float: left; width:250px; font-size:medium;text-align:left;margin-left:10px;margin-top:0px;">
                                РЕПУБЛИКА СРБИЈА Влада Републике Србије Канцеларија за информационе технологије и
                                електронску управу
                                Немањина 11, БЕОГРАД
                                Датум:
                                <xsl:variable name="datetime"
                                              select="b:digitalni_zeleni_sertifikat/b:podaci_o_sertifikatu/b:datum"/>
                                <xsl:variable name="date"
                                              select="substring-before($datetime, '+')"/>
                                <xsl:value-of
                                        select="$date"/>
                            </p>
                        </div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>