xquery version "3.1";
for $document in collection('/db/saglasnost')
    where fn:contains($document//*:drzavljanstvo/text(),"%1$s") or  fn:contains($document//*:jmbg/text(),"%1$s")
    or fn:contains($document//*:prezime/text(),"%1$s") or fn:contains($document//*:ime/text(),"%1$s")
     or fn:contains($document//*:ime_roditelja/text(),"%1$s")
     or fn:contains($document//*:datum_rodjenja/text(),"%1$s")
     or fn:contains($document//*:mesto_rodjenja/text(),"%1$s")
     or fn:contains($document//*:ulica_broj/text(),"%1$s")
     or fn:contains($document//*:naselje/text(),"%1$s")
     or fn:contains($document//*:grad/text(),"%1$s")
     or fn:contains($document//*:broj_fiksnog_telefona/text(),"%1$s")
     or fn:contains($document//*:broj_mobilnog_telefona/text(),"%1$s")
     or fn:contains($document//*:imejl/text(),"%1$s")
     or fn:contains($document//*:radni_status/text(),"%1$s")
     or fn:contains($document//*:korisnik_ustanove_socijalne_zastite/text(),"%1$s")
     or fn:contains($document//*:naziv_sedista_ustanove/text(),"%1$s")
     or fn:contains($document//*:saglasnost/text(),"%1$s")
     or fn:contains($document//*:zeljena_vakcina/text(),"%1$s")
     or fn:contains($document//*:zdravstvena_ustanova/text(),"%1$s")
     or fn:contains($document//*:vakcinacijski_punkt/text(),"%1$s")
     or fn:contains($document//*:podaci_o_lekaru/text(),"%1$s")
     or fn:contains($document//*:nezeljena_reakcija/text(),"%1$s")
    return $document