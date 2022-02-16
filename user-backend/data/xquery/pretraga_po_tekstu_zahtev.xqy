xquery version "3.1";
for $document in collection('/db/zahtev_za_sertifikat')
    where fn:contains($document//*:razlog_za_podnosenje/text(),"%1$s") or  fn:contains($document//*:mesto_podnosenja_zahteva/text(),"%1$s")
    or fn:contains($document//*:datum_podnosenja_zahteva/text(),"%1$s") or fn:contains($document//*:prezime/text(),"%1$s")
     or fn:contains($document//*:ime_prezime/text(),"%1$s")
     or fn:contains($document//*:datum_rodjenja/text(),"%1$s")
     or fn:contains($document//*:pol/text(),"%1$s")
     or fn:contains($document//*:jmbg/text(),"%1$s")
     or fn:contains($document//*:broj_pasosa/text(),"%1$s")
    return $document