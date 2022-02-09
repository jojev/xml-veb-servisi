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
                padding-right: 20%;
                padding-bottom: 50px;
                padding-left: 20%;
                }
                .intend {
                padding-left: 10%;
                }

                .dotted{
                border-bottom: 1px dotted #000;
                text-decoration: none;
                }

                .margin-row-padding {
                border: 1px solid #ec4420;
                padding-top: 15px;
                padding-bottom: 15px;
                font-size: 20px;
                }

            </style>
        </head>
        <html>
            <body>
                <br/>
                <div class="text-padding">

                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>