package com.example.user.master.dbUtils;

/**
 * Created by Bgdn on 5/25/2015.
 */
public class QueriesStrings {

    public static String INSERT_DATA_INTO_STATII = "INSERT INTO 'statii' ('_id', 'nume', 'cod', 'descriere') VALUES\n"+
            "(1, 'Piata Presei', 0, 'PTA Presei Libere'),\n"+
            "(2, 'Agronomie', 0, 'BD Marasti'),\n"+
            "(3, 'Casin', 0, 'BD Marasti'),\n"+
            "(4, 'BD Ion Mihalache', 0, 'BD Maresal Averescu'),\n"+
            "(5, 'Pasaj Grant', 0, 'Strada Turda'),\n"+
            "(6, 'Soseaua Crangasi', 0, 'Soseaua Crangasi'),\n"+
            "(7, 'Pod Ciurel', 0, 'BD Virtutii'),\n"+
            "(8, 'Orsova', 0, 'BD Virtutii'),\n"+
            "(9, 'Piata Crangasi', 0, 'BD Virtutii'),\n"+
            "(10, 'BD Uverturii', 0, 'BD Virtutii'),\n"+
            "(11, 'BD Timisoara', 0, 'Strada Brasov'),\n"+
            "(12, 'Drumul Taberei', 0, 'Strada Brasov'),\n"+
            "(13, 'Tricodava', 0, 'Strada Brasov'),\n"+
            "(14, 'Brasov', 0, 'Strada Brasov'),\n"+
            "(15, 'Ghencea', 0, 'BD Ghencea'),\n"+
            "(16, 'Piata Romana', 0, 'BD Magheru'),\n"+
            "(17, 'Calea Victoriei', 0, 'BD Dacia'),\n"+
            "(18, 'Calea Grivitei', 0, 'Strada General Budisteanu'),\n"+
            "(19, 'Popa Tatu', 0, 'Strada Mircea Vulcanescu'),\n"+
            "(20, 'Piata Virgiliu', 0, 'BD Dinicu Golescu'),\n"+
            "(21, 'Berzei', 0, 'Strada Stirbei Voda'),\n"+
            "(22, 'Spitalul Universitar', 0, 'BD Eroilor'),\n"+
            "(23, 'Carol Davila', 0, 'BD Eroilor'),\n"+
            "(24, 'Soseaua Pandurilor', 0, 'Strada Dr Bagdasar'),\n"+
            "(25, 'Drumul Sarii', 0, 'Drumul Taberei'),\n"+
            "(26, 'Lt Nicolae Gaina', 0, 'Drumul Taberei'),\n"+
            "(27, 'Orizont', 0, 'Drumul Taberei'),\n"+
            "(28, 'Favorit', 0, 'Drumul Taberei'),\n"+
            "(29, 'Drumul Taberei 34', 0, 'Drumul Taberei'),\n"+
            "(30, 'Piata Drumul Taberei', 0, 'Strada Brasov'),\n"+
            "(32, 'Romancierilor', 0, 'Drumul Taberei'),\n"+
            "(33, 'Piata Valea Ialomitei', 0, 'Drumul Taberei'),\n"+
            "(34, 'Posta', 0, 'Strada Valea Argesului'),\n"+
            "(35, 'Valea Argesului', 0, 'Strada Argesului'),\n"+
            "(36, 'Valea Doftanei', 0, 'Drumul Valea Doftanei'),\n"+
            "(37, 'Parcul Constantin Brancusi', 0, 'Drumul Valea Doftanei'),\n"+
            "(38, 'Cartier Constantin Brancusi', 0, 'Drumul Valea Doftanei'),\n"+
            "(39, 'Nicolae Golescu', 0, 'Strada George Enescu'),\n"+
            "(40, 'Luterana', 0, 'Strada Stirbei Voda'),\n"+
            "(41, 'Liceul Eugen Lovinescu', 0, 'Drumul Taberei'),\n"+
            "(42, 'Raul Doamnei', 0, 'Drumul Taberei'),\n"+
            "(43, 'Valea Oltului', 0, 'Strada Valea Oltului'),\n"+
            "(44, 'BD 1 Mai ', 0, 'Strada 1 Mai'),\n"+
            "(45, 'Drumetul', 0, 'Strada 1 Mai'),\n"+
            "(46, 'BD Ghencea', 0, 'Strada Brasov'),\n"+
            "(47, 'Gradina Cismigiu', 0, 'BD Regina Elisabeta'),\n"+
            "(48, 'Piata 21 Decembrie 1918', 0, 'BD Nicolae Balcescu'),\n"+
            "(49, 'Piata Mihail Kogalniceanu', 0, 'Piata Mihail Kogalniceanu'),\n"+
            "(50, 'Facultatea de Drept', 0, 'BD Mihail Kogalniceanu'),\n"+
            "(51, 'Univ. Nationala de Aparare', 0, 'Soseaua Panduri'),\n"+
            "(52, 'Profesor Dr Rainer', 0, 'Soseaua Panduri'),\n"+
            "(54, 'Soseaua Progresului', 0, 'Calea 13 septembrie'),\n"+
            "(55, 'Sebastian', 0, 'Calea 13 septembrie'),\n"+
            "(56, 'Petre Ispirescu', 0, 'BD Ghencea'),\n"+
            "(57, 'Cimitirul Ghencea', 0, 'BD Ghencea'),\n"+
            "(58, 'Garleni', 0, 'BD Ghencea'),\n"+
            "(59, 'Cartier Tudor Vladimirescu', 0, 'BD Ghencea'),\n"+
            "(60, 'Prelungirea Ghencea', 0, 'Prelungirea Ghencea'),\n"+
            "(61, 'Cartierul Latin', 0, 'Strada Cartierul Latin'),\n"+
            "(62, 'Drumul Taberei 98', 0, 'Drumul Taberei'),\n"+
            "(63, 'Aleea Vlasiei', 0, 'Drumul Taberei'),\n"+
            "(64, 'General Vasile Milea', 0, 'BD Vasile Milea'),\n"+
            "(65, 'BD Timisoara AFI', 0, 'BD Vasile Milea'),\n"+
            "(66, 'BD Iuliu Maniu', 0, 'BD Vasile Milea'),\n"+
            "(67, 'Piata Leu', 0, 'BD Iuliu Maniu'),\n"+
            "(68, 'Facultatea de Medicina', 0, 'BD Eroii Sanitari'),\n"+
            "(69, 'Piata Operei', 0, 'BD Eroii Sanitari'),\n"+
            "(70, 'Universitate', 0, 'BD Regina Elisabeta'),\n"+
            "(71, 'Piata Rosetti', 0, 'BD Carol I'),\n"+
            "(72, 'Calea Mosilor', 0, 'BD Carol I'),\n"+
            "(73, 'Foisorul de Foc', 0, 'BD Ferdinand'),\n"+
            "(74, 'Soseaua Mihai Bravu', 0, 'BD Ferdinand'),\n"+
            "(75, 'Baicului', 0, 'BD Garii Obor'),\n"+
            "(78, 'Stad. Nat. Lia Manoliu', 0, 'BD. P DE Coubertin'),\n"+
            "(79, 'Aura Buzescu', 0, 'Vatra Luminoasa'),\n"+
            "(80, 'LT. Victor Manu', 0, 'Vatra Luminoasa'),\n"+
            "(81, 'Agricultorilor', 0, 'Matei Voievod'),\n"+
            "(82, 'Scoala Iancului', 0, 'Matei Voievod'),\n"+
            "(83, 'CFR Progresul', 0, 'Sos Giurgiului'),\n"+
            "(84, 'Cap R Cristea', 0, 'Sos Giurgiului'),\n"+
            "(85, 'Anghel Nutu', 0, 'Sos Giurgiului'),\n"+
            "(86, 'Luica', 0, 'Sos Giurgiului'),\n"+
            "(87, 'Cimitirul Progresul', 0, 'Str Anghel Alexandru'),\n"+
            "(88, 'Baciului', 0, 'Str Zetarilor'),\n"+
            "(89, 'Zetarilor', 0, 'Zetarilor');";

    public static String INSERT_DATA_INTO_LINII = "INSERT INTO 'linii' ('_id', 'numar', 'interval_succedare', 'descriere') VALUES\n" +
            "(1, 1, 6, 'tramvai'),\n" +
            "(2, 4, 15, 'tramvai'),\n" +
            "(3, 5, 22, 'tramvai'),\n" +
            "(4, 7, 20, 'tramvai'),\n" +
            "(5, 8, 15, 'tramvai'),\n" +
            "(6, 11, 11, 'tramvai'),\n" +
            "(7, 14, 8, 'tramvai'),\n" +
            "(8, 16, 11, 'tramvai'),\n" +
            "(9, 20, 15, 'tramvai'),\n" +
            "(10, 21, 6, 'tramvai'),\n" +
            "(11, 23, 17, 'tramvai'),\n" +
            "(12, 25, 8, 'tramvai'),\n" +
            "(13, 27, 15, 'tramvai'),\n" +
            "(14, 32, 3, 'tramvai'),\n" +
            "(15, 35, 19, 'tramvai'),\n" +
            "(16, 36, 20, 'tramvai'),\n" +
            "(17, 40, 15, 'tramvai'),\n" +
            "(18, 41, 3, 'tramvai'),\n" +
            "(19, 42, 66, 'tramvai'),\n" +
            "(20, 45, 11, 'tramvai'),\n" +
            "(21, 46, 22, 'tramvai'),\n" +
            "(22, 47, 28, 'tramvai'),\n" +
            "(23, 56, 18, 'tramvai'),\n" +
            "(24, 61, 15, 'troleibuz'),\n" +
            "(25, 62, 10, 'troleibuz'),\n" +
            "(26, 65, 15, 'troleibuz'),\n" +
            "(27, 66, 6, 'troleibuz'),\n" +
            "(28, 69, 8, 'troleibuz'),\n" +
            "(29, 70, 17, 'troleibuz'),\n" +
            "(30, 73, 6, 'troleibuz'),\n" +
            "(31, 74, 7, 'troleibuz'),\n" +
            "(32, 77, 3, 'troleibuz'),\n" +
            "(33, 79, 20, 'troleibuz'),\n" +
            "(34, 85, 15, 'troleibuz'),\n" +
            "(35, 86, 15, 'troleibuz'),\n" +
            "(36, 90, 20, 'troleibuz'),\n" +
            "(37, 91, 20, 'troleibuz'),\n" +
            "(38, 92, 17, 'troleibuz'),\n" +
            "(39, 93, 10, 'troleibuz'),\n" +
            "(40, 96, 8, 'troleibuz'),\n" +
            "(41, 97, 7, 'troleibuz'),\n" +
            "(42, 101, 12, 'autobuz'),\n" +
            "(43, 102, 5, 'autobuz'),\n" +
            "(44, 103, 7, 'autobuz'),\n" +
            "(45, 104, 8, 'autobuz'),\n" +
            "(46, 105, 13, 'autobuz'),\n" +
            "(47, 106, 45, 'autobuz'),\n" +
            "(48, 112, 15, 'autobuz'),\n" +
            "(49, 116, 11, 'autobuz'),\n" +
            "(50, 117, 7, 'autobuz'),\n" +
            "(51, 122, 13, 'autobuz'),\n" +
            "(52, 123, 11, 'autobuz'),\n" +
            "(53, 124, 40, 'autobuz'),\n" +
            "(54, 125, 29, 'autobuz'),\n" +
            "(55, 126, 40, 'autobuz'),\n" +
            "(56, 131, 15, 'autobuz'),\n" +
            "(57, 133, 10, 'autobuz'),\n" +
            "(58, 135, 11, 'autobuz'),\n" +
            "(59, 136, 20, 'autobuz'),\n" +
            "(60, 137, 12, 'autobuz'),\n" +
            "(61, 138, 20, 'autobuz'),\n" +
            "(62, 139, 5, 'autobuz'),\n" +
            "(63, 141, 13, 'autobuz'),\n" +
            "(64, 143, 15, 'autobuz'),\n" +
            "(65, 148, 90, 'autobuz'),\n" +
            "(66, 149, 80, 'autobuz'),\n" +
            "(67, 162, 22, 'autobuz'),\n" +
            "(68, 163, 20, 'autobuz'),\n" +
            "(69, 168, 19, 'autobuz'),\n" +
            "(70, 173, 16, 'autobuz'),\n" +
            "(71, 178, 8, 'autobuz'),\n" +
            "(72, 181, 33, 'autobuz'),\n" +
            "(73, 182, 15, 'autobuz'),\n" +
            "(74, 185, 35, 'autobuz'),\n" +
            "(75, 202, 5, 'autobuz'),\n" +
            "(76, 205, 22, 'autobuz'),\n" +
            "(77, 216, 19, 'autobuz'),\n" +
            "(78, 220, 11, 'autobuz'),\n" +
            "(79, 221, 60, 'autobuz'),\n" +
            "(80, 223, 30, 'autobuz'),\n" +
            "(81, 226, 10, 'autobuz'),\n" +
            "(82, 227, 35, 'autobuz'),\n" +
            "(83, 232, 11, 'autobuz'),\n" +
            "(84, 236, 45, 'autobuz'),\n" +
            "(85, 246, 20, 'autobuz'),\n" +
            "(86, 253, 10, 'autobuz'),\n" +
            "(87, 254, 60, 'autobuz'),\n" +
            "(88, 261, 45, 'autobuz'),\n" +
            "(89, 268, 28, 'autobuz'),\n" +
            "(90, 282, 12, 'autobuz'),\n" +
            "(91, 300, 6, 'autobuz'),\n" +
            "(92, 301, 9, 'autobuz'),\n" +
            "(93, 302, 40, 'autobuz'),\n" +
            "(94, 303, 40, 'autobuz'),\n" +
            "(95, 304, 25, 'autobuz'),\n" +
            "(96, 311, 8, 'autobuz'),\n" +
            "(97, 312, 25, 'autobuz'),\n" +
            "(98, 313, 6, 'autobuz'),\n" +
            "(99, 323, 9, 'autobuz'),\n" +
            "(100, 330, 13, 'autobuz'),\n" +
            "(101, 331, 10, 'autobuz'),\n" +
            "(102, 335, 5, 'autobuz'),\n" +
            "(103, 336, 6, 'autobuz'),\n" +
            "(104, 368, 7, 'autobuz'),\n" +
            "(105, 381, 7, 'autobuz'),\n" +
            "(106, 385, 7, 'autobuz'),\n" +
            "(107, 601, 12, 'autobuz'),\n" +
            "(108, 624, 35, 'autobuz'),\n" +
            "(109, 634, 7, 'autobuz'),\n" +
            "(110, 655, 15, 'autobuz'),\n" +
            "(111, 668, 11, 'autobuz'),\n" +
            "(112, 682, 25, 'autobuz'),\n" +
            "(113, 780, 40, 'autobuz'),\n" +
            "(114, 783, 30, 'autobuz'),\n" +
            "(115, 402, 23, 'autobuz'),\n" +
            "(116, 409, 45, 'autobuz'),\n" +
            "(117, 414, 30, 'autobuz'),\n" +
            "(118, 422, 25, 'autobuz'),\n" +
            "(119, 438, 60, 'autobuz'),\n" +
            "(120, 449, 35, 'autobuz'),\n" +
            "(121, 460, 33, 'autobuz'),\n" +
            "(122, 471, 40, 'autobuz'),\n" +
            "(123, 20000, 12, '');";

    public static String INSERT_DATA_INTO_LEGATURI = "INSERT INTO 'legaturi' ('_id', 'id_linie', 'id_statie') VALUES\n" +
            "(1, 18, 1),\n" +
            "(2, 18, 2),\n" +
            "(3, 18, 3),\n" +
            "(4, 18, 4),\n" +
            "(5, 18, 5),\n" +
            "(6, 18, 6),\n" +
            "(7, 18, 9),\n" +
            "(8, 18, 7),\n" +
            "(9, 18, 8),\n" +
            "(10, 18, 10),\n" +
            "(11, 18, 11),\n" +
            "(12, 18, 12),\n" +
            "(13, 18, 13),\n" +
            "(14, 18, 14),\n" +
            "(15, 18, 15),\n" +
            "(16, 69, 16),\n" +
            "(17, 69, 17),\n" +
            "(18, 69, 18),\n" +
            "(19, 69, 19),\n" +
            "(20, 69, 20),\n" +
            "(21, 69, 21),\n" +
            "(22, 69, 22),\n" +
            "(23, 69, 23),\n" +
            "(24, 69, 24),\n" +
            "(25, 69, 25),\n" +
            "(26, 69, 26),\n" +
            "(27, 69, 27),\n" +
            "(28, 69, 28),\n" +
            "(29, 69, 29),\n" +
            "(30, 69, 30),\n" +
            "(32, 69, 13),\n" +
            "(33, 69, 32),\n" +
            "(34, 69, 33),\n" +
            "(35, 69, 34),\n" +
            "(36, 69, 35),\n" +
            "(37, 69, 36),\n" +
            "(38, 69, 37),\n" +
            "(39, 69, 38),\n" +
            "(40, 104, 16),\n" +
            "(41, 104, 39),\n" +
            "(42, 104, 40),\n" +
            "(43, 104, 18),\n" +
            "(44, 104, 19),\n" +
            "(45, 104, 20),\n" +
            "(46, 104, 21),\n" +
            "(47, 104, 22),\n" +
            "(48, 104, 23),\n" +
            "(49, 104, 24),\n" +
            "(50, 104, 25),\n" +
            "(51, 104, 26),\n" +
            "(52, 104, 27),\n" +
            "(53, 104, 28),\n" +
            "(54, 104, 29),\n" +
            "(55, 104, 30),\n" +
            "(56, 104, 13),\n" +
            "(57, 104, 41),\n" +
            "(58, 104, 42),\n" +
            "(59, 104, 34),\n" +
            "(60, 104, 35),\n" +
            "(61, 104, 43),\n" +
            "(62, 55, 16),\n" +
            "(63, 55, 39),\n" +
            "(64, 55, 40),\n" +
            "(65, 55, 18),\n" +
            "(66, 55, 19),\n" +
            "(67, 55, 20),\n" +
            "(68, 55, 21),\n" +
            "(69, 55, 22),\n" +
            "(70, 55, 23),\n" +
            "(71, 55, 51),\n" +
            "(72, 55, 25),\n" +
            "(73, 55, 26),\n" +
            "(74, 55, 27),\n" +
            "(75, 55, 28),\n" +
            "(76, 55, 44),\n" +
            "(77, 55, 45),\n" +
            "(78, 55, 46),\n" +
            "(79, 55, 15),\n" +
            "(80, 51, 16),\n" +
            "(81, 51, 48),\n" +
            "(82, 51, 47),\n" +
            "(83, 51, 49),\n" +
            "(84, 51, 50),\n" +
            "(85, 51, 22),\n" +
            "(86, 51, 23),\n" +
            "(87, 51, 51),\n" +
            "(88, 51, 52),\n" +
            "(90, 51, 54),\n" +
            "(91, 51, 55),\n" +
            "(92, 51, 56),\n" +
            "(93, 51, 57),\n" +
            "(94, 51, 58),\n" +
            "(95, 51, 15),\n" +
            "(96, 51, 59),\n" +
            "(97, 51, 60),\n" +
            "(98, 51, 61),\n" +
            "(99, 28, 35),\n" +
            "(100, 28, 62),\n" +
            "(101, 28, 63),\n" +
            "(102, 28, 41),\n" +
            "(103, 28, 13),\n" +
            "(104, 28, 45),\n" +
            "(105, 28, 44),\n" +
            "(106, 28, 28),\n" +
            "(107, 28, 27),\n" +
            "(108, 28, 64),\n" +
            "(109, 28, 65),\n" +
            "(110, 28, 66),\n" +
            "(111, 28, 67),\n" +
            "(112, 28, 68),\n" +
            "(113, 28, 69),\n" +
            "(114, 28, 50),\n" +
            "(115, 28, 49),\n" +
            "(116, 28, 47),\n" +
            "(117, 28, 70),\n" +
            "(118, 28, 71),\n" +
            "(119, 28, 72),\n" +
            "(120, 28, 73),\n" +
            "(121, 28, 74),\n" +
            "(122, 28, 75),\n" +
            "(141, 36, 78),\n" +
            "(142, 36, 79),\n" +
            "(143, 36, 80),\n" +
            "(144, 36, 74),\n" +
            "(145, 36, 81),\n" +
            "(146, 36, 82),\n" +
            "(147, 36, 72),\n" +
            "(148, 36, 70),\n" +
            "(149, 36, 47),\n" +
            "(150, 36, 49),\n" +
            "(151, 36, 50),\n" +
            "(152, 36, 69),\n" +
            "(153, 36, 68),\n" +
            "(154, 36, 67),\n" +
            "(155, 36, 66),\n" +
            "(156, 36, 65),\n" +
            "(157, 36, 27),\n" +
            "(158, 36, 28),\n" +
            "(159, 36, 44),\n" +
            "(160, 36, 45),\n" +
            "(161, 36, 13),\n" +
            "(162, 36, 41),\n" +
            "(163, 36, 42),\n" +
            "(164, 36, 34),\n" +
            "(165, 36, 35),\n" +
            "(166, 2, 83),\n" +
            "(167, 2, 84),\n" +
            "(168, 2, 85),\n" +
            "(169, 2, 86),\n" +
            "(170, 2, 87),\n" +
            "(171, 2, 87),\n" +
            "(172, 2, 89),\n" +
            "(173, 123, 45),\n" +
            "(174, 123, 45);";
}
