# xml-veb-servisi
<strong>Tim</strong>
1. Jovana Jevtić sw-20-2018
2. Andrija Vojnović sw-7-2018
3. Nikolina Tošić sw-36-2018
4. Dragana Filipović sw-47-2018

<strong>Pokretanje front</strong><br/> npm start
<br/>  User front je na portu 4200
 <br/> Official front je na portu 4201
<br/><strong>Pokretanje bek</strong><br/> u eclipsu ili intelij pokrenuti
<br/><strong>Pokretanje baze </strong><br/> Potrebno je pokrenuti preko dokera dve Exist baze pomocu komane docker run -d -p 8087:8080 -p 8443:8443 --name exist existdb/existdb
                   (User baza je na portu 8088, dok je official baza na 8087)
                  <br/> Potrebno je pokrenuti preko dokera dve Jena Fuseki baze pomocu komande docker run -d -p  3030:3030 -e ADMIN_PASSWORD=pw123 stain/jena-fuseki
                   (User baza je na portu 3030, dok je official baza na 3031), nakon toga kreirati Dataset pod nazivom VaccinacionDataset
