#3 
SELECT T.TøID, T.Dato, T.Tid, T.PersonligForm, T.Prestasjon, T.Varighet, T.Informasjon
FROM Treningsøkt AS T 
ORDER BY T.Dato AND T.Tid;

#4
SELECT Ø.ØvelseID, Ø.Navn
FROM (Øvelse AS Ø JOIN ØvelseIGruppe AS ØIG ON (Ø.ØvelseID=ØIG.ØvelseID))
		JOIN Øvelsegruppe AS ØG ON ØG.ØGID=ØIG.ØGID 
ORDER BY ØG.ØGID;

#5
SELECT Ø.ØvelseID, Ø.Navn, COUNT(Ø.ØvelseID) AS AntallAvØvelser
FROM (Øvelse AS Ø JOIN Øvelsesøkt AS ØØ ON (Ø.ØvelseID = ØØ.ØvelseID))
	 JOIN Treningsøkt AS T ON (T.TøID = ØØ.TøID)
ORDER BY AntallAvØvelser DESC;

