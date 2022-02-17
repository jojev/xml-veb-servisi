xquery version "3.1";
for $document in collection('/db/zahtev_za_sertifikat')
    where fn:contains($document//*:odgovor/text(),"%1$s")
    return $document