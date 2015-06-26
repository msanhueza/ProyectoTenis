<%-- 
    Author     : msanhuezal
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>   

    <head>
        
    <style>
    body {
        background-image: url("http://dunlacetennis.com/wp-content/uploads/2014/12/Best-top-desktop-tennis-wallpapers-hd-tennis-wallpaper-sport-pictures-11.jpg");
        //http://protennis360.com/wp-content/uploads/2014/08/tennis-ball-fine-art-hd-wallpaper-265-wallpaper-wallpaim.com-14069881268gnk4.jpg
    }
    </style>        
        
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Predictor Tenis</title>

    <!-- CSS y JS para JQuery -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

    <!-- CSS para boostrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>        

    <!-- CSS para progress bar de porcentaje -->
    <link rel="stylesheet" href="css/prelude.css">
    <link rel="stylesheet" href="css/rainbow.css">
    <link rel="stylesheet" href="css/progress.css">

    <!-- JS para progress bar de porcentaje -->
    <script type="text/javascript" src="js/rainbow.min.js"></script>
    <script type="text/javascript" src="js/jquery-asPieProgress.js"></script>        

    <script>
        
        jQuery(document).ready(function($){
                $('.pie_progress').asPieProgress({
                    namespace: 'pie_progress'
                });

                $('#button_start').on('click', function(){
                    $('.pie_progress').asPieProgress('start');
                });
        });
                
        
          var availableTags = [
            "Novak Djokovic",
            "Roger Federer",
            "Andy Murray",
            "Rafael Nadal",
            "David Ferrer",
            "Tomas Berdych",
            "Juan Martin Del Potro",
            "Jo-Wilfried Tsonga",
            "Janko Tipsarevic",
            "Richard Gasquet",
            "Nicolas Almagro",
            "Milos Raonic",
            "John Isner",
            "Marin Cilic",
            "Gilles Simon",
            "Stan Wawrinka",
            "Alexandr Dolgopolov",
            "Kei Nishikori",
            "Philipp Kohlschreiber",
            "Tommy Haas",
            "Sam Querrey",
            "Andreas Seppi",
            "Fernando Verdasco",
            "Mikhail Youzhny",
            "Jerzy Janowicz",
            "Mardy Fish",
            "Florian Mayer",
            "Jurgen Melzer",
            "Martin Klizan",
            "Radek Stepanek",
            "Jeremy Chardy",
            "Thomaz Bellucci",
            "Marcel Granollers",
            "Julien Benneteau",
            "Marcos Baghdatis",
            "Kevin Anderson",
            "Viktor Troicki",
            "Andy Roddick",
            "Feliciano Lopez",
            "Jarkko Nieminen",
            "Pablo Andujar",
            "Denis Istomin",
            "Nikolay Davydenko",
            "Fabio Fognini",
            "David Goffin",
            "Benoit Paire",
            "Grigor Dimitrov",
            "Marinko Matosevic",
            "Albert Ramos-Vinolas",
            "Lukas Lacko",
            "Bernard Tomic",
            "Michael Llodra",
            "Alejandro Falla",
            "Grega Zemlja",
            "Robin Haase",
            "Santiago Giraldo",
            "Paul-Henri Mathieu",
            "Yen-Hsun Lu",
            "Go Soeda",
            "Brian Baker",
            "Victor Hanescu",
            "Xavier Malisse",
            "Paolo Lorenzi",
            "Benjamin Becker",
            "Carlos Berlocq",
            "Gilles Muller",
            "Igor Sijsling",
            "Ryan Harrison",
            "Daniel Gimeno-Traver",
            "Leonardo Mayer",
            "Ivan Dodig",
            "Lukas Rosol",
            "Lukasz Kubot",
            "Bjorn Phau",
            "Guillermo Garcia-Lopez",
            "Gael Monfils",
            "Andrey Kuznetsov",
            "Tatsuma Ito",
            "Roberto Bautista Agut",
            "Evgeny Donskoy",
            "David Nalbandian",
            "Lleyton Hewitt",
            "Simone Bolelli",
            "Horacio Zeballos",
            "Aljaz Bedene",
            "Michael Russell",
            "Filippo Volandri",
            "Jurgen Zopp",
            "Olivier Rochus",
            "Ruben Ramirez Hidalgo",
            "Guillaume Rufin",
            "Steve Darcis",
            "Blaz Kavcic",
            "Flavio Cipolla",
            "Albert Montanes",
            "Guido Pella",
            "Tobias Kamke",
            "Adrian Ungur",
            "Ivo Karlovic",
            "Joao Sousa",
            "Edouard Roger-Vasselin",
            "Sergiy Stakhovsky",
            "Jesse Levine",
            "Matthew Ebden",
            "Jan Hajek",
            "Mikhail Kukushkin",
            "Nicolas Mahut",
            "Dudi Sela",
            "Igor Andreev",
            "Ruben Bemelmans",
            "Andreas Haider-Maurer",
            "Ricardas Berankis",
            "Tommy Robredo",
            "Philipp Petzschner",
            "Malek Jaziri",
            "Yuichi Sugita",
            "Florent Serra",
            "Kenny De Schepper",
            "Josselin Ouanna",
            "Martin Alund",
            "Dmitry Tursunov",
            "Matthias Bachinger",
            "Thiemo de Bakker",
            "Vasek Pospisil",
            "Rogerio Dutra Silva",
            "James Blake",
            "Tim Smyczek",
            "Alex Bogomolov Jr.",
            "Thiago Alves",
            "Antonio Veic",
            "Rajeev Ram",
            "Federico Delbonis",
            "Frederico Gil",
            "Matteo Viola",
            "Ernests Gulbis",
            "Denis Kudla",
            "Michael Berrer",
            "Gastao Elias",
            "Illya Marchenko",
            "Karol Beck",
            "Ryan Sweeting",
            "Wayne Odesnik",
            "Joao Souza",
            "Uladzimir Ignatik",
            "Marco Chiudinelli",
            "Inigo Cervantes",
            "Bobby Reynolds",
            "Danai Udomchoke",
            "Jack Sock",
            "Simon Greul",
            "Marc Gicquel",
            "Daniel Brands",
            "Jonathan Dasnieres de Veigy",
            "Ivan Sergeyev",
            "Jimmy Wang",
            "Maxime Authom",
            "Ze Zhang",
            "Mischa Zverev",
            "Marius Copil",
            "Andrey Golubev",
            "Igor Kunitsyn",
            "Dusan Lajovic",
            "Potito Starace",
            "Frank Dancevic",
            "Paul Capdeville",
            "Dustin Brown",
            "Jan-Lennard Struff",
            "Boris Pashanski",
            "Hiroki Moriya",
            "Daniel Munoz-De La Nava",
            "Agustin Velotti",
            "Guido Andreozzi",
            "Dominik Meffert",
            "Steve Johnson",
            "Juan Ignacio Chela",
            "Cedrik-Marcel Stebe",
            "Sergio Gutierrez-Ferrol",
            "Diego Schwartzman",
            "Peter Polansky",
            "Peter Gojowczyk",
            "Teymuraz Gabashvili",
            "Di Wu",
            "Javier Marti",
            "Jan Mertl",
            "Jorge Aguilar",
            "Sergei Bubka",
            "Adrian Menendez-Maceiras",
            "Ivo Minar",
            "Donald Young",
            "Rhyne Williams",
            "Adrian Mannarino",
            "Jan Hernych",
            "Facundo Arguello",
            "Amir Weintraub",
            "Yannick Mertens",
            "Arnau Brugues-Davi",
            "Gianluca Naso",
            "John Millman",
            "Marco Trungelliti",
            "Bastian Knittel",
            "Leonardo Kirche",
            "Andrej Martin",
            "Nicolas Devilder",
            "Aleksandr Nedovyesov",
            "Gregoire Burquier",
            "Robert Farah",
            "Michal Przysiezny",
            "James Duckworth",
            "Vincent Millot",
            "Evgeny Korolev",
            "Ti Chen",
            "Juan Carlos Ferrero",
            "Izak Van der Merwe",
            "Marsel Ilhan",
            "Eduardo Schwank",
            "Yuki Bhambri",
            "Sam Groth",
            "Brydan Klein",
            "Michael Yani",
            "David Guez",
            "Jesse Huta Galung",
            "Albano Olivetti",
            "Alex Kuznetsov",
            "Radu Albot",
            "Laurent Rochette",
            "Thomas Schoorel",
            "Dusan Lojda",
            "Farrukh Dustov",
            "Pavol Cervenak",
            "Ivan Navarro",
            "Maxime Teixeira",
            "Tennys Sandgren",
            "Steven Diez",
            "Guilherme Clezar",
            "Niels Desein",
            "Facundo Bagnis",
            "Alejandro Gonzalez",
            "Simone Vagnozzi",
            "Erik Chvojka",
            "Fabiano De Paula",
            "Damir Dzumhur",
            "Ricardo Hocevar",
            "John-Patrick Smith",
            "Jamie Baker",
            "Aldin Setkic",
            "Christopher Rungkat",
            "Bradley Klahn",
            "Daniel Kosakowski",
            "James Ward",
            "Matwe Middelkoop",
            "Julio Cesar Campozano",
            "Nikola Mektic",
            "Josh Goodall",
            "Victor Estrella Burgos",
            "Tsung-Hua Yang",
            "Pierre-Hugues Herbert",
            "Rik De Voest",
            "Mirza Basic",
            "Alessandro Giannessi",
            "Marcelo Demoliner",
            "Thomas Fabbiano",
            "Jiri Vesely",
            "Nikoloz Basilashvili",
            "Pedro Sousa",
            "Konstantin Kravchuk",
            "Denys Molchanov",
            "Boy Westerhof",
            "Hans Podlipnik-Castillo",
            "Carlos Salamanca",
            "Attila Balazs",
            "Riccardo Ghedin",
            "Stephane Robert",
            "Nicolas Renavand",
            "Alexander Kudryavtsev",
            "Victor Crivoi",
            "Augustin Gensse",
            "Peter Torebko",
            "Diego Junqueira",
            "Renzo Olivo",
            "Gerald Melzer",
            "Taro Daniel",
            "Maximiliano Estevez",
            "Leandro Migani",
            "Florian Reynet",
            "Ricardo Mello",
            "Robby Ginepri",
            "Eric Prodon",
            "Dzmitry Zhyrmont",
            "Jose Checa-Calvo",
            "Henri Laaksonen",
            "Kamil Capkovic",
            "Sanam Singh",
            "Andrey Kumantsov",
            "Arnaud Clement",
            "Julian Reister",
            "Daniel Evans",
            "Jonathan Eysseric",
            "Martin Fischer",
            "Lamine Ouahab",
            "Fernando Romboli",
            "Arthur De Greef",
            "Denis Zivkovic",
            "Luca Vanni",
            "Nils Langer",
            "Andrea Collarini",
            "Rui Machado",
            "Alberto Brizzi",
            "Dominic Thiem",
            "Blaz Rola",
            "Matt Reid",
            "Gerard Granollers",
            "Evgeny Kirillov",
            "Michael Linzer",
            "Marc Giner",
            "Toni Androic",
            "Fabrice Martin",
            "Stefan Seifert",
            "Riccardo Bellotti",
            "Andre Ghem",
            "Adam Feeney",
            "Marcin Gawron",
            "Roberto Carballes Baena",
            "Claudio Grassi",
            "N.Sriram Balaji",
            "Jules Marie",
            "Benjamin Mitchell",
            "Alex Bogdanovic",
            "Adam El Mihdawy",
            "Nicolas Barrientos",
            "Marek Michalicka",
            "Jason Kubler",
            "Jozef Kovalik",
            "David Souto",
            "Alexander Lobkov",
            "Axel Michon",
            "Jordi Samper-Montana",
            "Suk-Young Jeong",
            "Austin Krajicek",
            "Fritz Wolmarans",
            "Guillermo Rivera-Aranguiz",
            "Liang-Chi Huang",
            "Clement Reix",
            "Guillermo Olaso",
            "Vishnu Vardhan",
            "James McGee",
            "Maximo Gonzalez",
            "Andis Juska",
            "Luke Saville",
            "Ivan Bjelica",
            "Andres Molteni",
            "Norbert Gombos",
            "Jose Pereira",
            "Sandro Ehrat",
            "Pablo Galdon",
            "Juan-Pablo Amado",
            "Daniel Smethurst",
            "Yannik Reuter",
            "Roman Borvanov",
            "Michael Lammer",
            "Moritz Baumann",
            "Jose (Rubin) Statham",
            "Andrea Arnaboldi",
            "Michael McClune",
            "Steven Moneke",
            "Enrique Lopez-Perez",
            "Chase Buchanan",
            "Nikola Ciric",
            "Mate Pavic",
            "Bruno Sant'anna",
            "Yu Chang",
            "Marin Bradaric",
            "Romain Jouan",
            "Stephane Bohli",
            "Greg Jones",
            "Patrik Rosenholm",
            "Kevin Krawietz",
            "Daniel King-Turner",
            "Julio Silva",
            "Philipp Oswald",
            "Alex Bolt",
            "Mao-Xin Gong",
            "Patricio Heras",
            "Dino Marcan",
            "Zhe Li",
            "Saketh Myneni",
            "Robin Kern",
            "Ricardo Rodriguez - Pace",
            "Marcelo Arevalo",
            "Walter Trusendi",
            "Greg Ouellette",
            "Frederik Nielsen",
            "Tomislav Brkic",
            "Duilio Beretta",
            "Joshua Milton",
            "Timo Nieminen",
            "Juan Carlos Saez",
            "Cristobal Saavedra-Corvalan",
            "Michal Konecny",
            "Yasutaka Uchiyama",
            "Daniel Garza",
            "Jaroslav Pospisil",
            "Victor Baluda",
            "Laurent Recouderc",
            "Adrien Bossel",
            "Jerome Inzerillo",
            "Amer Delic",
            "Devin Britton",
            "Marco Cecchinato",
            "Yannick Jankovits",
            "Alessio Di Mauro",
            "Richard Bloomfield",
            "Ivo Klec",
            "Julien Obry",
            "Juan Sebastian Cabal",
            "Petru-Alexandru Luncanu",
            "Laurynas Grigelis",
            "Takanyi Garanganga",
            "Germain Gigounon",
            "Filip Krajinovic",
            "Jeevan Nedunchezhiyan",
            "Michael Quintero",
            "Viktor Galovic",
            "Jan Minar",
            "Tim Puetz",
            "Miloslav Mecir",
            "Dane Propoggia",
            "Miljan Zekic",
            "Matthew Barton",
            "Kento Takeuchi",
            "Lucas Pouille",
            "Olivier Patience",
            "Nicolas Pastor",
            "Alexander Ward",
            "Gleb Sakharov",
            "Adrian Sikora",
            "Yong-Kyu Lim",
            "Nicolas Reissig",
            "Thiago Monteiro",
            "Marton Fucsovics",
            "Hiroki Kondo",
            "Mathieu Rodrigues",
            "Thales Turini",
            "Daniel Dutra da Silva",
            "Roberto Marcora",
            "Andreas Beck",
            "Pere Riba",
            "Roman Jebavy",
            "Robert Kendrick",
            "Blake Strode",
            "Goran Tosic",
            "Carlos Gomez-Herrera",
            "Guillermo Duran",
            "Michael Look",
            "Vladimir Ivanov",
            "Mohamed Safwat",
            "Nikola Cacic",
            "Alexandre Folie",
            "Sherif Sabry",
            "Marek Semjan",
            "Oliver Golding",
            "Andre Miele",
            "Neil Pauffley",
            "Michal Schmid",
            "Marc Rath",
            "Antoine Benneteau",
            "Guillermo Hormazabal",
            "Cesar Ramirez",
            "Maximilian Neuchrist",
            "Tiago Lopes",
            "Alexey Vatutin",
            "Antoine Escoffier",
            "Peter Heller",
            "Yuri Schukin",
            "Mikhail Ledovskikh",
            "Shuichi Sekiguchi",
            "Adam Pavlasek",
            "Sebastian Decoud",
            "Juan Ignacio Londero",
            "Danilo Petrovic",
            "Alessandro Bega",
            "Pedro Zerbini",
            "Sergey Betov",
            "Ji Sung Nam",
            "Carsten Ball",
            "Nick Van Der Meer",
            "Artem Smirnov",
            "Miguel Gallardo-Valles",
            "Ilija Bozoljac",
            "Darian King",
            "Ruan Roelofse",
            "Daniele Giorgini",
            "Theodoros Angelinos",
            "Enrico Burzi",
            "Jeff Dadamo",
            "Andre Begemann",
            "Marcel Felder",
            "Michael Ryderstedt",
            "Nicolas Santos",
            "Antal Van Der Duim",
            "Sebastian Rieschick",
            "Christian Harrison",
            "Nikolaus Moser",
            "Matteo Marrai",
            "Toshihide Matsui",
            "Alexandar Lazov",
            "Joao Pedro Sorgi",
            "Fabricio Neis",
            "Fernando Gonzalez",
            "Andrew Fitzpatrick",
            "Abdullah Maqdes",
            "Charles-Antoine Brezac",
            "Marco Crugnola",
            "Elie Rousset",
            "Michael Venus",
            "Marc Sieber",
            "Egor Gerasimov",
            "Ranjeet Virali-Murugesan",
            "Christopher Diaz-Figueroa",
            "Miliaan Niesten",
            "Mauricio Echazu",
            "Grzegorz Panfil",
            "Maxim Dubarenco",
            "Juan-Martin Aranguren",
            "Alexander Slabinsky",
            "Lorenzo Giustino",
            "Ivan Nedelko",
            "Vladimir Uzhylovsky",
            "Edward Corrie",
            "Filip Peliwo",
            "Reid Carleton",
            "Stanislav Poplavskyy",
            "Christian Lindell",
            "Mikhail Biryukov",
            "Simon Cauvard",
            "Mate Delic",
            "Alexandros Jakupovic",
            "Denis Gremelmayr",
            "Daniel Cox",
            "Federico Coria",
            "Vijayant Malik",
            "Alexandre Penaud",
            "Pierre-Ludovic Duclos",
            "Federico Gaio",
            "Stanislav Vovk",
            "Ante Pavic",
            "Dimitar Kuzmanov",
            "Alexandre Sidorenko",
            "Jason Jung",
            "Facundo Mena",
            "Prakash Amritraj",
            "Tom Burn",
            "Mikhail Fufygin",
            "David Perez Sanz",
            "Dennis Novikov",
            "Nicolas Meister",
            "James Lemke",
            "Sarvar Ikramov",
            "Edoardo Eremin",
            "Artem Sitak",
            "Gianluigi Quinzi",
            "Adam Chadaj",
            "Joris De Loore",
            "Phillip Simmonds",
            "Jose Hernandez-Fernandez",
            "Philip Bester",
            "Harri Heliovaara",
            "Alexander Sadecky",
            "Joaquin-Jesus Monteferrario",
            "Chris Guccione",
            "Kyle Edmund",
            "Jan Satral",
            "N Vijay Sundar Prashanth",
            "Wesley Koolhof",
            "Eduardo Dischinger",
            "Julien Dubail",
            "Sekou Bangoura",
            "Denis Yevseyev",
            "Alexander Rumyantsev",
            "Denis Matsukevich",
            "Milan Pokrajac",
            "Chris Letcher",
            "Erik Crepaldi",
            "Michael Shabaz",
            "Morgan Phillips",
            "Augusto Laranja",
            "Gabriel Alejandro Hidalgo",
            "Andres Artunedo Martinavarro",
            "Andrei Ciumac",
            "Richard Muzaev",
            "Hiroyasu Ehara",
            "Hugo Nys",
            "David Estruch",
            "Nicolas Gustavo Kauer",
            "Jesse Witten",
            "Gabriel Trujillo-Soler",
            "Matheson Klein",
            "Marin Draganja",
            "Junn Mitsuhashi",
            "Kevin Kim",
            "Dieter Kindlmann",
            "Salvatore Caruso",
            "Sam Barry",
            "Sergio Galdos",
            "Emilio Gomez",
            "Alexey Kedryuk",
            "Stefano Travaglia",
            "David Rice",
            "Hyun-Woo Nam",
            "Jung-Woong Na",
            "Jaan-Frederik Brunken",
            "Duje Kekez",
            "Filip Horansky",
            "Bjorn Fratangelo",
            "Arsenije Zlatanovic",
            "James Marsalek",
            "Patrik Brydolf",
            "Nicolas Massu",
            "Yan Bai",
            "Jeremy Jahn",
            "Alexander Bury",
            "Eric Quigley",
            "Takuto Niki",
            "Laurent Malouli",
            "Mauricio Perez Mota",
            "Nikita Kryvonos",
            "Carlos Eduardo Severino",
            "Arata Onozawa",
            "Miguel Angel Lopez Jaen",
            "Denes Lukacs",
            "Luka Gregorc",
            "Dennis Lajola",
            "Chu-Huan Yi",
            "Catalin-Ionut Gard",
            "Robin Olin",
            "Benjamin Balleret",
            "Tiago Fernandes",
            "Luis David Martinez",
            "Francois-Arthur Vibert",
            "Riccardo Sinicropi",
            "Stefano Galvani",
            "Karim-Mohamed Maamoun",
            "Olivier Sajous",
            "Federico Zeballos",
            "Nikolai Fidirko",
            "Kittipong Wachiramanowong",
            "Valery Rudnev",
            "John Peers",
            "Nicolas Kicker",
            "Juan Vazquez-Valenzuela",
            "Franck Pepe",
            "Miguel Angel Reyes-Varela",
            "Sho Katayama",
            "Pablo Carreno Busta",
            "Dennis Nevolo",
            "Josko Topic",
            "Vladimir Obradovic",
            "Andriej Kapas",
            "Kristijan Mesaros",
            "Marko Djokovic",
            "Tristan-Samuel Weissborn",
            "Sergei Krotiouk",
            "Mikhail Vasiliev",
            "Somdev Devvarman",
            "Pedro Clar-Rossello",
            "Marcel Zimmermann",
            "Deniss Pavlovs",
            "Alessandro Petrone",
            "George Von Massow",
            "Andrei Vasilevski",
            "Emilien Firmin",
            "Markus Eriksson",
            "Sebastien Boltz",
            "Bastian Trinker",
            "Roberto Ortega-Olmedo",
            "Sami Reinwein",
            "Juan Sebastian Gomez",
            "Hugo Dellien",
            "Piotr Gadomski",
            "Ivo Mijic",
            "Kevin Botti",
            "Ryusei Makiguchi",
            "Jose Maria Paez",
            "Teodor-Dacian Craciun",
            "Daniel Nguyen",
            "Ismar Gorcic",
            "Cristian Rodriguez",
            "Jan Blecha",
            "Lucas Renard",
            "Claudio Fortuna",
            "Joss Espasandin",
            "Ivan Arenas-Gualda",
            "Marcus Daniell",
            "Luis Diaz-Barriga",
            "Isak Arvidsson",
            "Nick Lindahl",
            "Giulio Torroni",
            "Chuhan Wang",
            "Wilson Leite",
            "Sebastian Fanselow",
            "Anton Zaitcev",
            "Kevin Griekspoor",
            "Hernan Casanova",
            "Davy Sum",
            "Enrico Fioravante",
            "Shota Tagawa",
            "Marc Fornell-Mestres",
            "Dennis Novak",
            "Mauricio Astorga",
            "Giacomo Oradini",
            "Ricardo Siggia",
            "Lewis Burton",
            "Matias Sborowitz",
            "Dimitar Kutrovsky",
            "Mohammad Ghareeb",
            "Chris Wettengel",
            "Joshua Zavala",
            "Matthias Wunner",
            "Yaraslav Shyla",
            "Andoni Vivanco-Guzman",
            "Aslan Karatsev",
            "Alexandre Schnitman",
            "Filippo Leonardi",
            "Albert Alcaraz Ivorra",
            "Sean Berman",
            "Mauricio Doria-Medina",
            "Romain Arneodo",
            "Vadim Alekseenko",
            "Tomislav Ternar",
            "Jack Carpenter",
            "Roberto Quiroz",
            "Nicolaas Scholtz",
            "Torsten Wietoska",
            "Juan Lizariturry",
            "Karan Rastogi",
            "Mark Verryth",
            "Giammarco Micolani",
            "Caio Silva",
            "Lukas Jastraunig",
            "Richard Gabb",
            "Andreas Vinciguerra",
            "Patrick Ofner",
            "Ryan Rowe",
            "Ashley Hewitt",
            "Julien Demois",
            "Maximilian Dinslaken",
            "Mathias Bourgue",
            "Andrei Daescu",
            "Otakar Lucak",
            "Sasa Stojisavljevic",
            "Matthew Short",
            "Gonzalo Lama",
            "Diego Matos",
            "Kimmer Coppejans",
            "Tihomir Grozdanov",
            "Thanasi Kokkinakis",
            "Denis Bejtulahi",
            "Daniel Geib",
            "Gustavo Guerses",
            "Adam Kellner",
            "Nicholas Monroe",
            "Pascal Brunner",
            "Mehdi Ziadi",
            "Milos Sekulic",
            "Sebastian Serrano",
            "Francesco Picco",
            "Juan Pablo Ortiz",
            "Juan-Sebastian Vivanco",
            "Yuri Bezeruk",
            "Hyeon Chung",
            "Mateo Nicolas Martinez",
            "Mitchell Krueger",
            "Keith-Patrick Crowley",
            "Dominik Schulz",
            "Felipe Rios",
            "Nicolas Rosenzweig",
            "Christian Magg",
            "Mitchell Frank",
            "Yoshihito Nishioka",
            "Romain Sichez",
            "Adam Sanjurjo Hermida",
            "Miki Jankovic",
            "Jean-Marc Werner",
            "Arkadiusz Kocyla",
            "Andrew Whittington",
            "Ashwin Vijayragavan",
            "Ramkumar Ramanathan",
            "Oriol Roca Batalla",
            "Sebastian Lavie",
            "Jorge Montero",
            "Jan Stancik",
            "Juan Pablo Brzezicki",
            "Marko Lenz",
            "Gengo Kikuchi",
            "Jordi Munoz-Abreu",
            "Luke Bambridge",
            "Ralph Regus",
            "Riccardo Maiga",
            "Jaime Pulgar-Garcia",
            "Yannick Vandenbulcke",
            "Julien Cagnina",
            "Franko Skugor",
            "Eduardo Struvay",
            "Oscar Rodriguez-Sanchez",
            "Antonio Comporto",
            "Andrei Mlendea",
            "Viktor Filipenko",
            "Maverick Banes",
            "Gianni Mina",
            "Martin Rios-Benitez",
            "Dorian Descloix",
            "Denys Mylokostov",
            "Rudy Coco",
            "Peter Goldsteiner",
            "Colin Van Beem",
            "Richard Waite",
            "Alex Blumenberg",
            "Laslo Urrutia Fuentes",
            "Mark Vervoort",
            "Constantin Belot",
            "Hsin-Han Lee",
            "Tim Nekic",
            "Ruben Gonzales",
            "Richard Becker",
            "Ricardo Villacorta-Alonso",
            "Nico Matic",
            "Jeroen Benard",
            "Alexandre Tsuchiya",
            "Tomas Lipovsek Puches",
            "Filip Veger",
            "Jakub Lustyk",
            "Tomas Buchhass",
            "Ryan Agar",
            "Bowen Ouyang",
            "Jonathan Gonzalia",
            "Cheong-Eui Kim",
            "Alexander Zverev",
            "Young-Jun Kim",
            "Nick Kyrgios",
            "Jeroen Vanneste",
            "Isaac Frost",
            "Mislav Hizak",
            "Simon Ede",
            "Valentin Florez",
            "Juan-Samuel Arauzo-Martinez",
            "Yuichi Ito",
            "Bumpei Sato",
            "Andrei Plotniy",
            "Ilia Shatskiy",
            "Mikhail Vaks",
            "George Morgan",
            "Jiri Skoloudik",
            "Marvin Netuschil",
            "Michal Mertinak",
            "Rainer Schuettler",
            "Louk Sorensen",
            "Chris Eaton",
            "Alban Meuffels",
            "Tristan Lamasine",
            "Razvan Sabau",
            "Martin Vaisse",
            "David Thurner",
            "Philipp Davydenko",
            "Alexander Pavlioutchenkov",
            "Michael Bois",
            "Jose Maria Paniagua",
            "Carlos Poch-Gradin",
            "Peng Gao",
            "Gibril Diarra",
            "Daniiar Duldaev",
            "Vinayak Sharma Kaza",
            "Marko Danis",
            "Karunuday Singh",
            "Mario Haider-Maurer",
            "Mateusz Kowalczyk",
            "Murad Inoyatov",
            "Dragos Dima",
            "Yu Cheng Li",
            "Stefano Napolitano",
            "Elbert Sie",
            "Diego Hidalgo",
            "Ilija Vucic",
            "Juraj Masar",
            "Jan Kuncik",
            "Liam Broady",
            "Matteo Fago",
            "Damiano Di Ienno",
            "Marco Bortolotti",
            "Tuna Altuna",
            "Fabricio Burdisso",
            "Filipe Brandao",
            "Gorka Fraile",
            "Dominic Inglot",
            "Julian Knowle",
            "Colin Ebelthite",
            "Alexander Flock",
            "Bruno Semenzato",
            "Haythem Abid",
            "Prajnesh Gunneswaran",
            "Dong-Whee Choi",
            "Andrew Carter",
            "Gero Kretschmer",
            "Lubomir Majsajdr",
            "Marco Viola",
            "Ivan Endara",
            "Yann Marti",
            "Enrico Iannuzzi",
            "Nik Razborsek",
            "Christopher Racz",
            "Woong-Sun Jun",
            "Michal Pazicky",
            "Gavin Van Peperzeel",
            "Daniel Glancy",
            "Andre Gaspar Murta",
            "Purav Raja",
            "Daniel Lustig",
            "Simon Stadler",
            "Laslo Djere",
            "Gabriel Moraru",
            "Yannick Thivant",
            "Wishaya Trongcharoenchaikul",
            "Ervin Eleskovic",
            "Christian Garin",
            "Yuya Kibi",
            "Issam Haitham Taweel",
            "Florian Fallert",
            "Younes Rachidi",
            "Jack Schipanski",
            "Sidharth Rawat",
            "Soong-Jae Cho",
            "Matthieu Roy",
            "Guillermo Gomez-Diaz",
            "Davide Della Tommasina",
            "Juan Pablo Paz",
            "Teri Groll",
            "Bjorn Propst",
            "Stepan Khotulev",
            "Charles Costa",
            "Tyler Hochwalt",
            "Bruno Rodriguez",
            "Toby Martin",
            "Francesco Vilardo",
            "Filip Prpic",
            "Victor Romero",
            "Alexander Domijan",
            "Guillermo Carry",
            "Pablo Figueroa",
            "Mauro Bosio",
            "Micke Kontinen",
            "Takao Suzuki",
            "Tobias Blomgren",
            "Alexander Sarkissian",
            "Michael Redlicki",
            "Lennert Van Der Linden",
            "Aleksandre Metreveli",
            "Hicham Khaddari",
            "Sebastian Exequiel Pini",
            "Valentin Dimov",
            "Andrea Patracchini",
            "Tal Eros",
            "Felipe Escobar",
            "Marcus Willis",
            "Gerard Gallego-Bertran",
            "Marc Abdelnour",
            "Sean Thornley",
            "Alessandro Colella",
            "Austin Karosi",
            "Luca Margaroli",
            "Mohit Mayur Jayaprakash",
            "Joshua Jones",
            "Wan Gao",
            "Ricardo Ojeda Lara",
            "Anton Manegin",
            "Emanuele Molina",
            "Dekel Bar",
            "Shahin Khaledan",
            "Jonas Luetjen",
            "Maxime Tabatruong",
            "Tomasz Bednarek",
            "Hong Chung",
            "Caio Zampieri",
            "Matteo Trevisan",
            "Ervand Gasparyan",
            "Sergio Magro Moreno",
            "Luca Pancaldi",
            "Alexander Satschko",
            "Ryan Thacher",
            "Patrick Ciorcila",
            "Maxime Forcin",
            "Vahid Mirzadeh",
            "Anil Yuksel",
            "Evan Song",
            "Lorenzo Papasidero",
            "Luis Patino",
            "Andrei Levine",
            "Mehdi Bouras",
            "Yang Lu",
            "Adrian Partl",
            "Gustavo Sterin",
            "Francesco Garzelli"
          ];      
      
        $(function() {
          $( "#tags1" ).autocomplete({
            source: availableTags
          });
        });
      
        $(function() {
          $( "#tags2" ).autocomplete({
              source: availableTags
          });
        });      
      </script>         
    </head>
    <body>
        
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!-- You'll want to use a responsive image option so this logo looks good on devices - I recommend using something like retina.js (do a quick Google search for it and you'll find it) -->
                    <a class="navbar-brand" href="http://localhost:8080/WebServiceCliente/">Predictor Tenis ATP</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="http://www.utalca.cl/link.cgi/#/link.cgi/">Utalca</a></li>
                        <li><a href="http://www.atpworldtour.com/">Atp World Tour</a></li>
                        <li><a href="http://www.fetech.cl/">Federación Tenis Chile</a></li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
    </nav>
        
    <!-- Full Page Image Header Area -->
    <div id="top" class="header">
        <div class="vert-text"><br/><br/>
            <h4 class="well"><center>PREDICTOR DE PARTIDOS DE TENIS ATP</center></h4>
        </div>
    </div>
    <!-- /Full Page Image Header Area -->
        
    <div class="row">

       <div class="col-md-1"></div>
       <div class="col-md-4">
           <div class="well">
               
               Esta aplicación utiliza minería de datos para realizar la predicción de victorias entre jugadores
               de la Asociación de Tenistas Profesionales (ATP).
               
           </div>
           <div class="well">
               
               Cabe destacar que la predicción se realiza en base al historial de resultados y desempeño de cada jugador durante
               la temporada 2012.
               
           </div>        
           <div class="well">
               
               Para utilizar el sistema, basta con rellenar los campos de texto con los nombres de los jugadores que
               se desean evaluar, indicar la superficie donde jugarán y presionar el botón el "Calcular" para predecir el
               resultado final del encuentro.
               
           </div>           
      
       </div>

       <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                  <h3 class="panel-title"><center>¿Quién ganará?</center></h3>
                </div>
                <div class="panel-body">
                    <div id="error"></div>
                    
                    <div class="row">
                        <form action="index.jsp" method="POST">
                            <div class="col-md-6">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                         <h3 class="panel-title"><center>JUGADOR 1</center></h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="input-group input-group-lg">
                                            <input id="tags1" type="text" name="player1" class="form-control" placeholder="Nombre Jugador 1" aria-describedby="sizing-addon2">
                                        </div>
                                    </div>
                                </div>
                            </div> 

                             <div class="col-md-6">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <h3 class="panel-title"><center>JUGADOR 2</center></h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="input-group input-group-lg">
                                            <input id="tags2" type="text" name="player2" class="form-control" placeholder="Nombre Jugador 2" aria-describedby="sizing-addon2">
                                        </div>
                                    </div>
                                </div>
                             </div> 
                            <div  class="col-md-12">
                                <h5>TIPO DE SUPERFICIE</h5>
                                <div class="radio">
                                  <label><input type="radio" name="tipoSuperficie" value="Arcilla" checked="checked" /> Arcilla </label>
                                  <label><input type="radio" name="tipoSuperficie" value="Cemento" /> Cemento </label>
                                  <label><input type="radio" name="tipoSuperficie" value="Pasto" /> Pasto </label>
                                </div>                              
                            </div>
                            <center><input class="btn btn-primary" type="submit" value="Calcular" /></center>
                         </form> 
                    </div>
                    
                    
                   
            <%-- start web service invocation --%><hr/>
    <%
    try {
	ws.prediccion.PredecirPartido_Service service = new ws.prediccion.PredecirPartido_Service();
	ws.prediccion.PredecirPartido port = service.getPredecirPartidoPort();
	 // TODO initialize WS operation arguments here
	java.lang.String jugador1 = request.getParameter("player1");
	java.lang.String jugador2 = request.getParameter("player2");
        String auxSuperficie = request.getParameter("tipoSuperficie");
        int tipoSuperficie;
        if(auxSuperficie.equals("Arcilla")){
            tipoSuperficie = 1;
        }
        else if(auxSuperficie.equals("Cemento")){
            tipoSuperficie = 2;
        }
        else{ //Pasto
            tipoSuperficie = 3;
        }

	// TODO process result here
	java.util.List<java.lang.String> result = port.predecir(jugador1, jugador2, tipoSuperficie);
        if(!jugador1.isEmpty() && !jugador2.isEmpty()){
            if(!jugador1.equals(jugador2)){
                //out.println(jugador1 + " " + result.get(0) + "% </br>");
                //out.println(jugador2 + " " + result.get(1) + "%");  
                String nombreJugador1 = jugador1;
                String nombreJugador2 = jugador2;
                String resultadoJugador1 = "";
                String resultadoJugador2 = "";
                if(result != null){
                    if(result.size() == 1){
                        resultadoJugador1 = "GANADOR";
                        resultadoJugador2 = "PERDEDOR";
                    }
                    else{
                        resultadoJugador1 = "PERDEDOR";
                        resultadoJugador2 = "GANADOR";                    
                    }


                    String resultado = "" +
                            "<span class=\"label label-success\">RESULTADOS:</span><br/><br/>" +
                            "<div class=\"row\">" +
                                "<div class=\"col-md-6\">" +
                                    "<center><h2>"+nombreJugador1+"</h2></center><br/>"+
                                    "<center><h4>("+resultadoJugador1+")</h4></center><br/>"+
                                "</div>" +   

                                "<div class=\"col-md-6\">" +
                                    "<center><h2>"+nombreJugador2+"</h2></center><br/>"+
                                    "<center><h4>("+resultadoJugador2+")</h4></center><br/>"+
                                "</div>" + 
                            "</div>"; 
                    out.println(resultado);                    
                }
                else{
                    String cod = "<div class=\"alert alert-danger alert-dismissible\" role=\"alert\">" +
                                 "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>" +
                                 "<strong>Error!</strong> No existe información para uno de los jugadores ingresados." + "</div>";
                    out.println(cod);                    
                }
                
            }
            else{
                  String cod = "<div class=\"alert alert-danger alert-dismissible\" role=\"alert\">" +
                               "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>" +
                               "<strong>Error!</strong> Debe ingresar jugadores diferentes." + "</div>";
                  out.println(cod);
            }

        }
        else{
            String cod = "<div class=\"alert alert-danger alert-dismissible\" role=\"alert\">" +
                         "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>" +
                         "<strong>Error!</strong> Debe ingresar el nombre de los jugadores que desea evaluar." + "</div>";

            out.println(cod);
            
            

           
        }
	
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>                    
                </div>
            </div>

       </div>   
       <div class="col-md-1"></div>

    </div>



    </body>
</html>
