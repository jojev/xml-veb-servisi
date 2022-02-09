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
                    <p>Шифра потврде вакцинације:</p>
                    <p>Šifra potvrde / Confirmation code</p>
                    <br></br>
                    <h3 style="text-align: center"> ПОТВРДА О ИЗВРШЕНОЈ ВАКЦИНАЦИЈИ ПРОТИВ  COVID-19</h3>
                    <p style="text-align: center;font-size:13px">POTVRDA O IZVRŠENOJ VAKCINACIJI PROTIV COVID-19</p>
                    <p style="text-align: center;font-size:13px">CONFIRMATION OF THE  COVID-19 VACCINATION</p>
                    <br/>
                    <br/>
                    <br/>

                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>