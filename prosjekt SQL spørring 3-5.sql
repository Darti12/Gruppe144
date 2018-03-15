


INSERT INTO Notat VALUES (1, "BLA BLA VLA", 1), (2, "BLALALAL", 2);

INSERT INTO Øvelse VALUES (1, "Pushups"), (2, "Situps"), (3, "Roing"), (4, "Vekter");

INSERT INTO ApparatØvelse VALUES (3), (4);

INSERT INTO ØvelseMedApparat VALUES (3, 1, 30, 4), (4, 2, 20, 3);

INSERT INTO Friøvelse VALUES (1, "Vanlige pushups"), (2, "Vanlige situps");

INSERT INTO Øvelsesøkt VALUES (1, 1), (1, 3), (2, 4), (4, 2);

INSERT INTO Øvelsesgruppe VALUES (1, "Styrke", "Styrkende øvelser"), (2, "Armstyrke", "Øvelser for armstyrke"),
								 (3, "Core", "Øvelser for kjernemuskulatur");
                                 
INSERT INTO ØvelseIGruppe VALUES (1, 2), (1, 1), (2, 3), (2, 1), (3, 1), (3, 2), (3, 3), (4, 1);#3 
SELECT T.TøID, T.Dato, T.Tid, T.PersonligForm, T.Prestasjon, T.Varighet, T.Informasjon
FROM Treningsøkt AS T 
ORDER BY T.Dato AND T.Tid;

#4
SELECT Ø.ØvelseID, Ø.Navn
FROM (Øvelse AS Ø JOIN ØvelseIGruppe AS ØIG ON (Ø.ØvelseID=ØIG.ØvelseID))
		JOIN Øvelsesgruppe AS ØG ON ØG.ØGID=ØIG.ØGID 
ORDER BY ØG.ØGID;

#5
SELECT Ø.ØvelseID, Ø.Navn, COUNT(Ø.ØvelseID) AS AntallAvØvelser
FROM (Øvelse AS Ø JOIN Øvelsesøkt AS ØØ ON (Ø.ØvelseID = ØØ.ØvelseID))
	 JOIN Treningsøkt AS T ON (T.TøID = ØØ.TøID)
ORDER BY AntallAvØvelser DESC;

