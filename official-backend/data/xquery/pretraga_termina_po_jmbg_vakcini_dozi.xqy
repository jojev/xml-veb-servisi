xquery version "3.1";
for $document in collection('/db/termini')
    let $jmbg := $document//*:jmbg_pacijenta/text()
    let $doza := $document//*:doza/text()
    let $tip := $document//*:tip_vakcine/text()
    where $doza = "%1$d" and $jmbg = "%2$s" and $tip = "%3$s"
    return $document