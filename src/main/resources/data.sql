INSERT INTO song(id, album, album_cover, artist, label, name, times_played)
VALUES (1, "All Eyez On Me", "2PAC Peace", "2Pac", "Death Row", "Ambition as a ridah", 10),
	   (2, "corona virus", "bat", "Cardi B", "Vaccine RECORDS", "Corona Virus", 1),
	   (3, "ADHD", "joyner", "Joyner Lucas", "Atlantic Records", "ADHD", 20),
	   (4, "No Pressure", "joyner", "Logic", "Atlantic Records", "No Pressure", 12),
	   (5, "N.W.A", "N.W.A", "Eazy E", "Ruthless Records", "Straight outta Comption", 20);
INSERT INTO user(id, password, role, username)
VALUES (1, "{noop}user", "client", "user"),
       (2, "{noop}user1", "client", "user1"),
       (3, "{noop}admin", "admin", "admin");