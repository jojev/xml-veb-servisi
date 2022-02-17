xquery version "3.1";
for $document in collection('/db/interesovanje')
    where fn:contains($document//*:drzavljanstvo/text(),"%1$s") or  fn:contains($document//*:jmbg/text(),"%1$s")
    or fn:contains($document//*:ime/text(),"%1$s") or fn:contains($document//*:prezime/text(),"%1$s")
     or fn:contains($document//*:adresa_elektronske_poste/text(),"%1$s")
     or fn:contains($document//*:broj_mobilnog_telefona/text(),"%1$s")
     or fn:contains($document//*:broj_fiksnog_telefona/text(),"%1$s")
     or fn:contains($document//*:tip_vakcine/text(),"%1$s")
     or fn:contains($document//*:opstina_vakcinisanja/text(),"%1$s")
     or fn:contains($document//*:dobrovoljni_davalac_krvi/text(),"%1$s")
    return $document