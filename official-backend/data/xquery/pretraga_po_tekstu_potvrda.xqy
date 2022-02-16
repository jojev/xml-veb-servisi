xquery version "3.1";
for $document in collection('/db/potvrda_o_vakcinaciji')
    where fn:contains($document//*:ime_prezime/text(),"%1$s") or  fn:contains($document//*:datum_rodjenja/text(),"%1$s")
    or fn:contains($document//*:sifra_potvrde_vakcinacije/text(),"%1$s")
     or fn:contains($document//*:pol/text(),"%1$s")
     or fn:contains($document//*:zdravstvena_ustanova/text(),"%1$s")
     or fn:contains($document//*:jmbg/text(),"%1$s")
     or fn:contains($document//*:naziv_vakcine/text(),"%1$s")
     or fn:contains($document//*:datum_izdavanja_potvrde/text(),"%1$s")
    return $document