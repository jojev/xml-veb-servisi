xquery version "3.1";
for $document in collection('/db/termini')
    let $datum := $document//*:datum_vreme/text()
    let $ispostovan := $document//*:ispostovan/text()
    where $datum >= "%1$s" and $datum < "%2$s" and $ispostovan = true()
    group by $doza := $document//*:doza/text()
    return concat($doza, "-", count($datum))