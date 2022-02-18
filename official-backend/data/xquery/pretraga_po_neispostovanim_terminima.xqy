xquery version "3.1";
for $document in collection('/db/termini')
    let $datum := $document//*:datum_vreme/text()
    let $ispostovan := $document//*:ispostovan/text()
    let $obradjen := $document//*:obradjen/text()
    where $datum < "%1$s" and $ispostovan = false() and $obradjen = false()
    return $document