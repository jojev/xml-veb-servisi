<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije" version="2.0">

    <xsl:template match="b:obrazac_za_sprovodjenje_imunizacije">
        <html>
            <head>
                <style type="text/css">
                    .row {
                    display: flex;
                    }

                    .column1 {
                    flex: 50%;
                    margin-left: 3em;
                    }

                    .column2 {
                    flex: 10%;
                    }

                    .column3 {
                    flex: 90%;
                    }

                    h1, h3 {
                    margin-bottom: 0em;
                    }

                    #institut {
                    margin-right: 50%;
                    margin-top: 2%;
                    }

                    .informations {
                    font-size: 20px;
                    }

                    .info {
                    margin-left: 2%;
                    margin-right: 1%
                    }

                    .info2 {
                    margin-left: 10.3%;
                    }

                    .sublabel {
                    display: block;
                    font-size: 18px;
                    color: #000;
                    margin-left: 21%;
                    margin-top: 0em;
                    }

                    .underline {
                    border-bottom: 1px solid black;
                    }

                    input[type="checkbox"] {
                        background-color: #fff;
                        margin: 0;
                        font: inherit;
                        color: currentColor;
                        width: 1.15em;
                        height: 1.15em;
                        border: 0.10em solid currentColor;
                        border-radius: 0.15em;
                        transform: translateY(-0.075em);
                        display: grid;
                        place-content: center;
                    }

                    input[type="checkbox"]::before {
                        content: "";
                        width: 0.65em;
                        height: 0.65em;
                        transform: scale(0);
                        transition: 120ms transform ease-in-out;
                        box-shadow: inset 1em 1em var(--form-control-color);
                        transform-origin: bottom left;
                        clip-path: polygon(14% 44%, 0 65%, 50% 100%, 100% 16%, 80% 0%, 43% 62%);
                    }

                    input[type="checkbox"]:checked::before {
                        transform: scale(1);
                    }

                    table, th, td {
                        border: 1px solid black;
                    }

                    th {
                        height: 5em;
                    }

                    td {
                        text-align: center;
                        height: 3em;
                    }
                </style>
            </head>
            <body>
                <div class="row">
                    <div class="column1">
                        <h1>САГЛАСНОСТ ЗА СПРОВОЂЕЊЕ ПРЕПОРУЧЕНЕ ИМУНИЗАЦИЈЕ</h1>
                        <h4>(попуњава пацијент)</h4>
                    </div>
                    <div class="column1">
                        <div class="row">
                            <div class="column2">
                                <img src="https://euprava.gov.rs/media/logos/Batut.gif" alt="Girl in a jacket" height="150em" width="130em"/>
                            </div>
                            <div class="column3" id="institut">
                                <p>ИНСТИТУТ ЗА ЈАВНО ЗДРАВЉЕ СРБИЈЕ "Др Милан Јовановић Батут"</p>
                            </div>
                        </div>
                    </div>
                </div>
                <xsl:apply-templates select="b:podaci_koje_je_popunio_pacijent"/>
                <xsl:apply-templates select="b:podaci_koje_je_popunio_zdravstveni_radnik"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="b:podaci_koje_je_popunio_pacijent">
        <div class="informations">
            <div class="row">
                <p class="info"><b>Држављанство</b></p>
                <p>1) Република Србија | ЈМБГ </p>
                <p class="underline" style="width: 54%; margin-left: 0.5em;"><xsl:value-of select="b:licni_podaci/b:jmbg"/></p>
            </div>
            <div class="row">
                <p class="info2">2)</p>
                <p class="underline" style="width: 33%;"><xsl:value-of select="b:licni_podaci/b:strano_drzavljanstvo"/></p>
                <p>|</p>
                <p class="underline" style="width: 33%;"> <xsl:value-of select="b:licni_podaci/b:broj_pasosa"/></p>
            </div>
            <div class="row">
                <p class="sublabel">(назив страног држављанства)</p>
                <p class="sublabel">(бр. пасоша или ЕБС за стране држављане)</p>
            </div>
            <div class="row">
                <p class="info"><b>Презиме</b></p>
                <p class="underline" style="width: 19%"><xsl:value-of select="b:licni_podaci/b:prezime"/></p>
                <p style="margin-left: 0.5em;">| <b>Име</b></p>
                <p class="underline" style="width: 19%; margin-left:1em"><xsl:value-of select="b:licni_podaci/b:ime"/></p>
                <p style="margin-left: 0.5em;">| <b>Име родитеља</b></p>
                <p class="underline" style="width: 19%; margin-left:1em;"><xsl:value-of select="b:licni_podaci/b:ime_roditelja"/></p>
            </div>
            <div class="row">
                <p class="info"><b>Пол</b></p>
                <xsl:if test="b:licni_podaci/b:pol = 'M'">
                    <input type="checkbox" disabled="disabled" name="male" checked="true" style="margin-top:1em;"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:pol != 'M'">
                    <input type="checkbox" name="male" disabled="disabled" style="margin-top:1em;"/>
                </xsl:if>
                <label for="male" style="margin-top:1em; margin-left: 0.3em;">М, </label>
                <xsl:if test="b:licni_podaci/b:pol = 'Z'">
                    <input type="checkbox" disabled="disabled" name="female" checked="true" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:pol != 'Z'">
                    <input type="checkbox" name="female" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label for="female" style="margin-top:1em; margin-left: 0.3em;">Ж</label>
                <p style="margin-left: 0.5em;">| <b>Датум рођења</b></p>
                <p class="underline" style="width: 25%; margin-left:1em;"><xsl:value-of select="b:licni_podaci/b:datum_rodjenja"/></p>
                <p style="margin-left: 0.5em;">| <b>Место рођења</b></p>
                <p class="underline" style="width: 25%; margin-left:1em;"><xsl:value-of select="b:licni_podaci/b:mesto_rodjenja"/></p>
            </div>
            <div class="row">
                <p class="info"><b>Адреса (улица и број)</b></p>
                <p class="underline" style="width: 33%"><xsl:value-of select="b:licni_podaci/b:adresa/b:ulica_broj"/></p>
                <p style="margin-left: 0.5em;">| <b>Место/Насеље</b></p>
                <p class="underline" style="width: 22%; margin-left:1em;"><xsl:value-of select="b:licni_podaci/b:adresa/b:naselje"/></p>
            </div>
            <div class="row">
                <p class="info"><b>Општина/Град</b></p>
                <p class="underline" style="width: 36%"><xsl:value-of select="b:licni_podaci/b:adresa/b:grad"/></p>
                <p style="margin-left: 0.5em;">| <b>Тел. фиксни</b></p>
                <p class="underline" style="width: 23%; margin-left:1em;"><xsl:value-of select="b:licni_podaci/b:broj_fiksnog_telefona"/></p>
            </div>
            <div class="row">
                <p class="info"><b>Тел. мобилни</b></p>
                <p class="underline" style="width: 31.5%"><xsl:value-of select="b:licni_podaci/b:broj_mobilnog_telefona"/></p>
                <p style="margin-left: 0.5em;">| <b>Имејл</b></p>
                <p class="underline" style="width: 31.5%; margin-left:1em;"><xsl:value-of select="b:licni_podaci/b:imejl"/></p>
            </div>
            <div class="row">
                <p class="info"><b>Радни статус:</b></p>
                <xsl:if test="b:licni_podaci/b:radni_status = 'zaposlen'">
                    <input type="checkbox" disabled="disabled" checked="true" style="margin-top:1em;"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:radni_status != 'zaposlen'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">запослен, </label>

                <xsl:if test="b:licni_podaci/b:radni_status = 'nezaposlen'">
                    <input type="checkbox" disabled="disabled" checked="true" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:radni_status != 'nezaposlen'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">незапослен, </label>

                <xsl:if test="b:licni_podaci/b:radni_status = 'penzioner'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:radni_status != 'penzioner'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">пензионер, </label>

                <xsl:if test="b:licni_podaci/b:radni_status = 'ucenik'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:radni_status != 'ucenik'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">ученик, </label>

                <xsl:if test="b:licni_podaci/b:radni_status = 'student'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:radni_status != 'student'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">студент, </label>

                <xsl:if test="b:licni_podaci/b:radni_status = 'dete'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:radni_status != 'dete'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">дете </label>
            </div>

            <div class="row">
                <p class="info"><b>Занимање запосленог:</b></p>
                <xsl:if test="b:licni_podaci/b:radni_status = 'zdravstvena zastita'">
                    <input type="checkbox" disabled="disabled" checked="true" style="margin-top:1em;"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:radni_status != 'zdravstvena zastita'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">здравствена заштита, </label>

                <xsl:if test="b:licni_podaci/b:radni_status = 'socijalna zastita'">
                    <input type="checkbox" disabled="disabled" checked="true" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:radni_status != 'socijalna zastita'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">социјална заштита, </label>

                <xsl:if test="b:licni_podaci/b:radni_status = 'prosveta'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:radni_status != 'prosveta'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">просвета, </label>

                <xsl:if test="b:licni_podaci/b:radni_status = 'MUP'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:radni_status != 'MUP'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">МУП, </label>

                <xsl:if test="b:licni_podaci/b:radni_status = 'Vojska RS'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:radni_status != 'Vojska RS'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">Војска РС, </label>

                <xsl:if test="b:licni_podaci/b:radni_status = 'drugo'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:radni_status != 'drugo'">
                    <input type="checkbox" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">друго </label>
            </div>
            <div class="row">
                <p class="info"><b>Корисник установе соц. зашт.</b></p>
                <xsl:if test="b:licni_podaci/b:korisnik_ustanove_socijalne_zastite = 'DA'">
                    <input type="checkbox" name="yes" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:korisnik_ustanove_socijalne_zastite != 'DA'">
                    <input type="checkbox" name="yes" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">ДА, </label>

                <xsl:if test="b:licni_podaci/b:korisnik_ustanove_socijalne_zastite = 'NE'">
                    <input type="checkbox" name="no" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:licni_podaci/b:korisnik_ustanove_socijalne_zastite != 'NE'">
                    <input type="checkbox" name="no" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">НЕ </label>

                <p style="margin-left: 0.5em;">| <b>Назив и општина седишта</b></p>
                <p class="underline" style="width: 39%; margin-left:1em;"><xsl:value-of select="b:licni_podaci/b:naziv_sedista_ustanove"/></p>
            </div>

            <div class="row">
                <p class="info"><b>Изјављујем да: </b></p>
                <xsl:if test="b:saglasnost = 'SAGLASAN SAM'">
                    <input type="checkbox" name="yes" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:saglasnost != 'SAGLASAN SAM'">
                    <input type="checkbox" name="yes" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">САГЛАСАН САМ, </label>

                <xsl:if test="b:saglasnost = 'NISAM SAGLASAN'">
                    <input type="checkbox" name="yes" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;" checked="true"/>
                </xsl:if>
                <xsl:if test="b:saglasnost != 'NISAM SAGLASAN'">
                    <input type="checkbox" name="yes" disabled="disabled" style="margin-top:1em; margin-left: 0.3em;"/>
                </xsl:if>
                <label style="margin-top:1em; margin-left: 0.3em;">НИСАМ САГЛАСАН </label>
                <p style="margin-left: 0.3em;">(означити) са спровођењем активне/пасивне</p>
            </div>
            <div class="row">
                <p class="info">имунизације (уписати назив имунолошког лека):</p>
                <p class="underline" style="width: 52%; margin-left:1em;"><xsl:value-of select="b:zeljena_vakcina"/></p>
            </div>
            <div class="row">
                <p class="info"><b>Лекар ми је објаснио предности и ризике од спровођења активне/пасивне имунизације наведеним имунолошким
                    леком.</b></p>
            </div>
            <div class="row">
                <p class="info"><b>Потпис пацијента или законског заступника пацијентa</b></p>
                <p style="width: 40%; text-align: right;"><b>Датум:</b></p>
            </div>
            <div class="row">
                <p class="info"><b>___________________________________________________</b></p>
                <p style="width: 40%; text-align: right"><b><xsl:value-of select="b:datum"/></b></p>
            </div>
        </div>
    </xsl:template>

    <xsl:template match="b:podaci_koje_je_popunio_zdravstveni_radnik">
        <div class="informations">
            <div class="row">
                <p class="info">''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''</p>
            </div>
            <div class="row">
                <h2 style="text-align: center; width: 75%">ЕВИДЕНЦИЈА О ВАКЦИНАЦИЈИ ПРОТИВ COVID-19</h2>
            </div>
            <div class="row">
                <h4 style="text-align: center; width: 75%; margin-top: 0em;">(попуњава здравствени радник)</h4>
            </div>
            <div class="row">
                <p class="info">Здравствена установа</p>
                <p class="underline" style="width: 30%; margin-left:1em;"><xsl:value-of select="b:zdravstvena_ustanova"/></p>
                <p class="info">Вакцинацијски пункт</p>
                <p class="underline" style="width: 20%; margin-left:1em;"><xsl:value-of select="b:vakcinacijski_punkt"/></p>
            </div>
            <div class="row">
                <p class="info">Име, презиме, факсимил и бр. телефона лекара:</p>
                <p class="underline" style="width: 52%; margin-left:1em;"><xsl:value-of select="b:podaci_o_lekaru"/></p>
            </div>
            <div class="row">
                <p class="info" style="width: 74%;">Пре давања вакцине прегледати особу и упознати је са користима и о могућим нежељеним реакцијама после
                    вакцинације. Обавезно уписати сваку дату вакцину и све тражене податке у овај образац и податке унети у лични
                    картон о извршеним имунизацијама и здравствени картон. </p>
            </div>
            <div class="row">
                <table style="border-collapse: collapse; width: 74%;" class="info">
                    <tr>
                        <th>Назив
                            вакцине</th>
                        <th>Датум давања
                            вакцине
                            (V1 i V2)</th>
                        <th>Hачин
                            давања
                            вакцине</th>
                        <th>Екстремитет</th>
                        <th>Серија
                            вакцине
                            (лот)</th>
                        <th>Произвођач</th>
                        <th>Нежељена
                            реакција</th>
                        <th>Потпис лекара</th>
                    </tr>
                    <xsl:for-each select="b:doze/b:doza">
                        <tr>
                            <td><xsl:value-of select="b:vakcina" /></td>
                            <td><xsl:value-of select="b:datum_davanja_vakcine" /></td>
                            <td><xsl:value-of select="b:nacin_davanja_vakcine" /></td>
                            <td>
                                <xsl:if test="b:ekstremitet = 'DR'">
                                    <span style="border: 2px solid black; width: 1em;">1)</span>
                                </xsl:if>
                                <xsl:if test="b:ekstremitet != 'DR'">
                                    <span>1)</span>
                                </xsl:if>
                                ДР,
                                <xsl:if test="b:ekstremitet = 'LR'">
                                    <span style="border: 1px solid black; width: 1em;">2)</span>
                                </xsl:if>
                                <xsl:if test="b:ekstremitet != 'LR'">
                                    <span>2)</span>
                                </xsl:if>
                                ЛР</td>
                            <td><xsl:value-of select="b:serija_vakcine" /></td>
                            <td><xsl:value-of select="b:proizvodjac" /></td>
                            <td><xsl:value-of select="b:nezeljena_reakcija" /></td>
                            <td />
                        </tr>
                    </xsl:for-each>
                    <tr>
                        <td colspan="8" style="text-align: left;">Привремене контраиндикације
                            (датум утврђивања и дијагноза): <xsl:value-of select="b:privremene_kontraindikacije" /></td>
                    </tr>
                    <tr>
                        <td colspan="8" style="text-align: left;">Одлука комисије за трајне контраиндикације (ако постоји, уписати Да)
                            <xsl:value-of select="b:odluka_komisije" /></td>
                    </tr>
                </table>
            </div>

            <div class="row">
                <p class="info"><b>Напомена:</b> Образац се чува као део медицинске документације пацијента.</p>
            </div>
        </div>
    </xsl:template>
</xsl:stylesheet>
