<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.ftn.uns.ac.rs/obrazac_za_sprovodjenje_imunizacije" version="2.0">

    <xsl:template match="/">
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
                    margin-buttom: 0em;
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

                .sublabel2:after {
                    content: "(бр. пасоша или ЕБС за стране држављане)";
                    display: block;
                    font-size: 18px;
                    color: #000;
                    margin-top: 10px;
                    margin-left: 30%;
                }

                .sublabel1:after {
                    content: "(назив страног држављанства)";
                    display: block;
                    font-size: 18px;
                    color: #000;
                    margin-top: 10px;
                    margin-left: 30%;
                }

                .underline {
                    border-bottom: 1px solid black;
                    width: 55%;
                }
            </style>
        </head>
        <html>
            <body>
                <div class="row">
                    <div class="column1">
                        <h1>САГЛАСНОСТ ЗА СПРОВОЂЕЊЕ ПРЕПОРУЧЕНЕ ИМУНИЗАЦИЈЕ</h1>
                        <h4>(попуњава пацијент)</h4>
                    </div>
                    <div class="column1">
                        <div class="row">
                            <div class="column2">
                                <img src="../../images/izjzs.gif" alt="Girl in a jacket" height="150em" width="130em"/>
                            </div>
                            <div class="column3" id="institut">
                                <p>ИНСТИТУТ ЗА ЈАВНО ЗДРАВЉЕ СРБИЈЕ "Др Милан Јовановић Батут"</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="informations">
                    <div class="row">
                        <p class="info"><b>Држављанство</b></p>
                        <p>1) Република Србија | ЈМБГ </p>
                        <p class="underline"><xsl:value-of select="b:obrazac_za_sprovodjenje_imunizacije/b:podaci_koje_je_popunio_pacijent/b:licni_podaci/b:jmbg"/></p>
                    </div>
                    <div class="row">
                        <p class="info2 sublabel1 underline">2)<xsl:value-of select="b:obrazac_za_sprovodjenje_imunizacije/b:podaci_koje_je_popunio_pacijent/b:licni_podaci/b:strano_drzavljanstvo"/>| </p>
                        <p class="sublabel2 underline"> <xsl:value-of select="b:obrazac_za_sprovodjenje_imunizacije/b:podaci_koje_je_popunio_pacijent/b:licni_podaci/b:broj_pasosa"/></p>
                    </div>
                    <div class="row">

                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
