<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://www.ftn.uns.ac.rs/potvrda_o_vakcinaciji" version="2.0">

    <xsl:template match="b:potvrda_o_vakcinaciji">
        <head>
            <style type="text/css">
                .row {
                display: flex;
                }

                .column1 {
                flex: 50%;
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

                .centring {
                text-align: center;
                }
                .row-padding {
                font-size: 20px;
                padding-top: 2px;
                padding-bottom: 2px;
                }

                .row-padding-5 {
                padding-top: 15px;
                padding-bottom: 15px;
                font-size: 20px;
                }

                .text-padding {
                padding-right: 15%;
                padding-bottom: 50px;
                padding-left: 15%;
                }
                .intend {
                padding-left: 10%;
                }

                .dotted{
                border-bottom: 1px dotted #000;
                text-decoration: none;
                }

                .div-start {
                display: flex;
                justify-content: start;
                }
                img { max-width: 400px; height: 200px;}
                .div-between {
                display: flex;
                justify-content: space-between;
                }

                .margin-row-padding {
                border: 1px solid #ec4420;
                padding-top: 15px;
                padding-bottom: 15px;
                font-size: 20px;
                }

                .nowrap {
                white-space: nowrap ;
                }

            </style>
        </head>
        <html>
            <body>
                <br/>
                <div class="text-padding">

                    <div class="div-between">
                        <div style="float:left; width:270px; text-align: center;margin-top:20px;">
                            <img style="float:left;" src="../../images/izjzs.gif" alt=""/>

                        </div>
                        <div>
                            <div >
                                <p style="text-align: center;margin-top:40px;">
                                    <div class="nowrap" style="text-align: center"><b style="font-size:17px;">Институт за јавно здравље Србије</b></div>
                                    <div style="text-align: center">"Др Милан Јовановић Батут"</div>
                                    <div class="nowrap" style="font-size:17px;text-align: center">INSTITUT ZA JAVNO ZDRAVLJE SRBIJE</div>
                                    <div style="text-align: center">"Dr Milan Jovanović Batut"</div>
                                    <div class="nowrap" style="font-size:17px;text-align: center">INSTITUTE OF PUBLIC HEALTH OF SERBIA</div>
                                    <div style="text-align: center">"Dr Milan Jovanovic Batut"</div>
                                </p>
                            </div>
                        </div>
                    </div>
                    <p>Шифра потврде вакцинације: <xsl:value-of select="b:sifra_potvrde_vakcinacije"/></p>
                    <p>Šifra potvrde / Confirmation code</p>
                    <br></br>
                    <h3 style="text-align: center"> ПОТВРДА О ИЗВРШЕНОЈ ВАКЦИНАЦИЈИ ПРОТИВ  COVID-19</h3>
                    <p style="text-align: center;font-size:13px">POTVRDA O IZVRŠENOJ VAKCINACIJI PROTIV COVID-19</p>
                    <p style="text-align: center;font-size:13px">CONFIRMATION OF THE  COVID-19 VACCINATION</p>
                    <br/>
                    <br/>
                    <p><b>Име и презиме:</b> <xsl:value-of select="b:licni_podaci/b:ime_prezime"/></p>
                    <p style="font-size:14px;color:#808080">Ime i prezime / First and Last Name</p>
                    <p><b>Датум рођења:</b> <xsl:value-of select="b:licni_podaci/b:datum_rodjenja"/></p>
                    <p style="font-size:14px;color:#808080">Datum rođenja / Date Of Birth</p>
                    <p><b>Пол: <xsl:value-of select="b:licni_podaci/b:pol"/></b></p>
                    <p style="font-size:14px;color:#808080">Pol: <xsl:value-of select="b:licni_podaci/b:pol"/> / Gender: <xsl:value-of select="b:licni_podaci/b:pol"/></p>
                    <p><b>ЈМБГ:</b> <xsl:value-of select="b:licni_podaci/b:jmbg"/></p>
                    <p style="font-size:14px;color:#808080">JMBG / Personal No.</p>
                    <p><b>Датум давања и број серије прве дозе вакцинације:</b>
                        <xsl:value-of select="b:podaci_o_vakcinaciji/b:doze/b:doza[@redni_broj=1]/b:datum_davanja"/>
                    <b>, серија:</b> <xsl:value-of select="b:podaci_o_vakcinaciji/b:doze/b:doza[@redni_broj=1]/b:serija"/>
                    </p>
                    <p style="font-size:14px;color:#808080">Datum vakcinacije / Vaccination Date</p>
                    <p><b>Датум давања и број серије друге дозе вакцине:</b>
                        <xsl:if test="b:podaci_o_vakcinaciji/b:doze/b:doza[@redni_broj=2]">
                            <xsl:value-of select="b:podaci_o_vakcinaciji/b:doze/b:doza[@redni_broj=2]/b:datum_davanja"/>
                            <b>, серија:</b>
                            <xsl:value-of select="b:podaci_o_vakcinaciji/b:doze/b:doza[@redni_broj=2]/b:serija"/>
                        </xsl:if>
                    </p>
                    <p style="font-size:14px;color:#808080">Datum druge vakcinacije / Second Vaccination Date</p>
                    <p><b>Здравствена установа која вакцинише:</b><xsl:value-of select="b:zdravstvena_ustanova"/></p>
                    <p style="font-size:14px;color:#808080">Zdravstvena ustanova koja vakciniše / Health care institution of vaccination</p>
                    <p><b>Назив вакцине:</b> <xsl:value-of select="b:naziv_vakcine"/></p>
                    <p style="font-size:14px;color:#808080">Naziv vakcine / Name of vaccine</p>
                    <p><b>Датум издавања потврде:</b>  <xsl:value-of select="b:datum_izdavanja_potvrde"/></p>
                    <p style="font-size:14px;color:#808080">Datum izdavanja potvrde / Confirmation Release Date</p>
                    <br/>
                    <div class="row">
                        <div class="column1">
                        </div>
                        <div class="column1" style="text-align: right">
                            <p><b>Здравствена установа:  <xsl:value-of select="b:zdravstvena_ustanova"/></b></p>
                            <p style="font-size:14px;color:#808080">Zdravstvena ustanova / Medical institution</p>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="column1">
                            <p><b>Ова потрвда важи без потписа и печата </b></p>
                            <p style="font-size:14px;color:#808080">Ova potvrda važi bez potpisa i pečata / This certificate is valid without signatures and seals</p>
                        </div>
                        <div class="column1">
                            <div style="display:flex; justify-content: end;">
                            <img style="width:150px;height:150px;" alt="" title="">
                                <xsl:variable name="id"
                                              select="b:sifra_potvrde_vakcinacije"/>
                                <xsl:variable name="src"
                                              select="concat('https://api.qrserver.com/v1/create-qr-code/?data=','',$id)"/>
                                <xsl:attribute name="src">
                                    <xsl:value-of select="$src"/>
                                </xsl:attribute>
                            </img>
                            </div>
                        </div>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>