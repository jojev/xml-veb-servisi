export interface InteresovanjeZaVakcinisanje {
  licni_podaci: [Osoba];
  podaci_o_vakcinisanju: [Vakcinacija];
  datum_podnosenja : [DatumPodnosenja];
}

export interface Jmbg {
  _: string;
}

export interface TipVakcineRestrikcija {
  value: string;
}

export interface TipVakcine {
  value: TipVakcineRestrikcija;
}

export interface Osoba {
  drzavljanstvo: string;
  jmbg: Jmbg;
  ime: string;
  prezime: string;
  adresa_elektronske_poste: string;
  broj_mobilnog_telefona: string;
  broj_fiksnog_telefona: string;
}

export interface Vakcinacija{
  opstina_vakcinisanja: string;
  tip_vakcine: TipVakcine;
  dobrovoljni_davalac_krvi: string;
}

export interface DatumPodnosenja {
  _: string;
}